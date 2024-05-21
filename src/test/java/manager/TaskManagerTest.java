package manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.entity.Task;
import com.enums.Trimester;
import com.github.javafaker.Faker;
import com.manager.TaskManager;

public class TaskManagerTest {
    private TaskManager taskManager;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager();
        faker = new Faker(new Locale("en-AU"));

    }

    @Test
    public void testAddTask() {
        Task task = getTask(0);
        taskManager.addTask(task);

        ArrayList<Task> tasks = taskManager.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = getTask(0);
        Task task2 = getTask(1);
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        ArrayList<Task> tasks = taskManager.getAllTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    public void testGetTaskById() {
        Task task1 = getTask(0);
        Task task2 = getTask(1);
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        Task foundTask = taskManager.getTaskById(task1.getTaskId());
        assertEquals(task1, foundTask);

        Task notFoundTask = taskManager.getTaskById("3");
        assertNull(notFoundTask);
    }

    @Test
    public void testGetTaskByTrimester() {
        Task task1 = getTask(0);
        Task task2 = getTask(1);
        Task task3 = getTask(0);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        ArrayList<Task> firstTrimesterTasks = taskManager.getTaskByTrimester(Trimester.FIRST);
        assertEquals(2, firstTrimesterTasks.size());
        assertTrue(firstTrimesterTasks.contains(task1));
        assertTrue(firstTrimesterTasks.contains(task3));

        ArrayList<Task> secondTrimesterTasks = taskManager.getTaskByTrimester(Trimester.SECOND);
        assertEquals(1, secondTrimesterTasks.size());
        assertTrue(secondTrimesterTasks.contains(task2));
    }

    public Task getTask(int n){
        return new Task("Task "+faker.number().digits(4), faker.date().future(90, TimeUnit.DAYS), faker.lorem().sentence(5), Trimester.values()[n]);
    }
}
