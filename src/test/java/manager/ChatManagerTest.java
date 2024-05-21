package manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.entity.Message;
import com.entity.Person;
import com.entity.Task;
import com.enums.PersonType;
import com.enums.Trimester;
import com.github.javafaker.Faker;
import com.manager.ChatManager;

public class ChatManagerTest {
    private ChatManager chatManager;
    private Person student;
    private Person tutor;
    private Task task;
    private Faker faker;
    
    @BeforeEach
    public void setUp() {
        faker = new Faker(new Locale("en-AU"));
        chatManager = new ChatManager();
        student = new Person(PersonType.STUDENT, "Student Name", Trimester.FIRST);
        tutor = new Person(PersonType.TUTOR, "Tutor Name", Trimester.FIRST);
        task = new Task("Task 001", faker.date().future(90, TimeUnit.DAYS), faker.lorem().sentence(5), Trimester.FIRST);
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(student, tutor, faker.lorem().sentence(), task);
        chatManager.sendMessage(message);

        List<Message> messages = chatManager.getAllMessages();
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));
    }

    @Test
    public void testGetAllMessages() {
        Message message1 = new Message(student, tutor, faker.lorem().sentence(), task);
        Message message2 = new Message(student, tutor, faker.lorem().sentence(), task);
        chatManager.sendMessage(message1);
        chatManager.sendMessage(message2);

        List<Message> messages = chatManager.getAllMessages();
        assertEquals(2, messages.size());
        assertTrue(messages.contains(message1));
        assertTrue(messages.contains(message2));
    }

    @Test
    public void testGetAllMessagesByPersonAndTask() throws InterruptedException {
        Message message1 = new Message(student, tutor, faker.lorem().sentence(), task);
        Thread.sleep(10); // Ensure different timestamps
        Message message2 = new Message(student, tutor, faker.lorem().sentence(), task);
        chatManager.sendMessage(message1);
        chatManager.sendMessage(message2);

        ArrayList<Message> messages = chatManager.getAllMessagesByPersonAndTask(student, task);
        assertEquals(2, messages.size());
        assertEquals(message2, messages.get(0)); // message2 should be first as it is later
        assertEquals(message1, messages.get(1));
    }
}
