package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.entity.Task;
import com.enums.Status;
import com.enums.Trimester;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JUNE, 1); // set to a specific date
        Date dueDate = calendar.getTime();
        task = new Task("Task Name", dueDate, "Initial Comment", Trimester.FIRST);
    }

    @Test
    public void testTaskConstructor() {
        assertEquals("Task Name", task.getName());
        assertNotNull(task.getDueDate());
        assertEquals(Status.PENDING, task.getStatus());
        assertEquals("Initial Comment", task.getComment());
        assertNotNull(task.getTaskId());
        assertEquals(Trimester.FIRST, task.getTrimester());
    }

    @Test
    public void testSettersAndGetters() {
        task.setName("New Task Name");
        assertEquals("New Task Name", task.getName());

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JULY, 1); // set to another specific date
        Date newDueDate = calendar.getTime();
        task.setDueDate(newDueDate);
        assertEquals(newDueDate, task.getDueDate());

        task.setStatus(Status.APPROVED);
        assertEquals(Status.APPROVED, task.getStatus());

        task.setComment("New Comment");
        assertEquals("New Comment", task.getComment());

        task.setTrimester(Trimester.SECOND);
        assertEquals(Trimester.SECOND, task.getTrimester());
    }

    @Test
    public void testSubmitTask() {
        task.submitTask(Status.COMPLETED);
        assertEquals(Status.COMPLETED, task.getStatus());
    }

    @Test
    public void testRequestForExtension() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JUNE, 1); // reset to the initial due date
        Date initialDueDate = calendar.getTime();
        task.setDueDate(initialDueDate);
        
        task.requestForExtension();

        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date expectedNewDueDate = calendar.getTime();
        assertEquals(expectedNewDueDate, task.getDueDate());
    }

    @Test
    public void testToString() {
        String expectedString = "Task [name=Task Name, dueDate=" + task.getDueDate() + ", status=PENDING, comment=Initial Comment, taskId=" + task.getTaskId() + ", trimester=FIRST]";
        assertEquals(expectedString, task.toString());
    }
}
