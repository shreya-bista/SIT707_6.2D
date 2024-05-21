
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.entity.Message;
import com.entity.Person;
import com.entity.Task;
import com.enums.PersonType;
import com.enums.Status;
import com.enums.Trimester;
import com.github.javafaker.Faker;
import com.main.OnTrack;

public class OnTrackTest {
    private OnTrack onTrack;
    private Person student;
    private Person tutor;
    private Task task;

    @BeforeEach
    public void setUp() {
        onTrack = new OnTrack();
        student = onTrack.createStudent("Student Name");
        tutor = onTrack.createTutor("Tutor Name");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JUNE, 1); // set to a specific date
        Date dueDate = calendar.getTime();
        task = onTrack.createTask("Task Name", dueDate, "Initial Comment", Trimester.FIRST);
    }

    @Test
    public void testCreateStudent() {
        assertNotNull(student);
        assertEquals(PersonType.STUDENT, student.getPersonType());
        assertEquals("Student Name", student.getName());
    }

    @Test
    public void testCreateTutor() {
        assertNotNull(tutor);
        assertEquals(PersonType.TUTOR, tutor.getPersonType());
        assertEquals("Tutor Name", tutor.getName());
    }

    @Test
    public void testCreateTask() {
        assertNotNull(task);
        assertEquals("Task Name", task.getName());
        assertNotNull(task.getDueDate());
        assertEquals("Initial Comment", task.getComment());
        assertEquals(Trimester.FIRST, task.getTrimester());
    }

    @Test
    public void testSubmitTask() {
        onTrack.submitTask(student, task, Status.COMPLETED);
        assertEquals(Status.COMPLETED, task.getStatus());
    }

    @Test
    public void testAddCommentToTask() {
        onTrack.addCommentToTask(tutor, task, "New Comment");
        assertEquals("New Comment", task.getComment());
    }

    @Test
    public void testSendMessage() {
        onTrack.sendMessage(student, tutor, "Hello, Tutor!", task);
        ArrayList<Message> messages = onTrack.getMessages(student, task);
        assertEquals(1, messages.size());
        assertEquals("Hello, Tutor!", messages.get(0).getContent());
        assertEquals(student, messages.get(0).getSender());
        assertEquals(tutor, messages.get(0).getReceiver());
    }

    @Test
    public void testGetMessages() {
        onTrack.sendMessage(student, tutor, "Message 1", task);
        onTrack.sendMessage(tutor, student, "Message 2", task);

        ArrayList<Message> messages = onTrack.getMessages(student, task);
        assertEquals(2, messages.size());
    }

    @Test
    public void testRejectTask() {
        onTrack.rejectTask(tutor, task);
        assertEquals(Status.REJECTED, task.getStatus());
    }

    @Test
    public void testApproveTask() {
        onTrack.approveTask(tutor, task);
        assertEquals(Status.APPROVED, task.getStatus());
    }
}
