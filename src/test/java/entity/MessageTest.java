package entity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.entity.Message;
import com.entity.Person;
import com.entity.Task;
import com.enums.PersonType;
import com.enums.Trimester;

public class MessageTest {
    private Person sender;
    private Person receiver;
    private Task task;

    @BeforeEach
    public void setUp() {
        sender = new Person(PersonType.STUDENT, "Sender Name", Trimester.FIRST);
        receiver = new Person(PersonType.TUTOR, "Receiver Name", Trimester.FIRST);
        task = new Task("1", new Date(),"Task Description 001", Trimester.FIRST);
    }

    @Test
    public void testMessageConstructor() {
        String content = "Message content";
        Message message = new Message(sender, receiver, content, task);

        assertEquals(sender, message.getSender());
        assertEquals(receiver, message.getReceiver());
        assertEquals(content, message.getContent());
        assertEquals(task, message.getTask());
        assertNotNull(message.getTimestamp());
    }

    @Test
    public void testSettersAndGetters() {
        String content = "Message content";
        Message message = new Message(sender, receiver, content, task);

        Person newSender = new Person(PersonType.STUDENT, "New Sender", Trimester.SECOND);
        message.setSender(newSender);
        assertEquals(newSender, message.getSender());

        Person newReceiver = new Person(PersonType.TUTOR, "New Receiver", Trimester.SECOND);
        message.setReceiver(newReceiver);
        assertEquals(newReceiver, message.getReceiver());

        String newContent = "New message content";
        message.setContent(newContent);
        assertEquals(newContent, message.getContent());

        Task newTask = new Task("2", new Date(),"Task Description 002", Trimester.FIRST);
        message.setTask(newTask);
        assertEquals(newTask, message.getTask());

        Date newTimestamp = new Date();
        message.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, message.getTimestamp());
    }

    @Test
    public void testToString() {
        String content = "Message content";
        Message message = new Message(sender, receiver, content, task);

        String expectedString = "Message [sender=" + sender + ", receiver=" + receiver + ", content=" + content
                + ", timestamp=" + message.getTimestamp() + ", task=" + task + "]";
        assertEquals(expectedString, message.toString());
    }
}
