package manager;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.entity.Person;
import com.entity.Task;
import com.enums.PersonType;
import com.enums.Trimester;
import com.github.javafaker.Faker;
import com.manager.ChatManager;
import com.manager.PersonManager;
import com.manager.TaskManager;

public class PersonManagerTest {
    private TaskManager taskManager;
    private ChatManager chatManager;
    private PersonManager personManager;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        taskManager = mock(TaskManager.class);
        chatManager = mock(ChatManager.class);
        personManager = new PersonManager(taskManager, chatManager);
        faker = new Faker(new Locale("en-AU"));
    }

    @Test
    public void testCreateStudent() {
        Person student = personManager.createStudent("Student Name");
        assertEquals(PersonType.STUDENT, student.getPersonType());
        assertEquals("Student Name", student.getName());
        assertTrue(personManager.getPersonListByPersonType(PersonType.STUDENT).contains(student));
    }

    @Test
    public void testCreateTutor() {
        Person tutor = personManager.createTutor("Tutor Name");
        assertEquals(PersonType.TUTOR, tutor.getPersonType());
        assertEquals("Tutor Name", tutor.getName());
        assertTrue(personManager.getPersonListByPersonType(PersonType.TUTOR).contains(tutor));
    }

    @Test
    public void testGetPersonListByPersonType() {
        personManager.createStudent("Student Name");
        personManager.createTutor("Tutor Name");

        ArrayList<Person> students = personManager.getPersonListByPersonType(PersonType.STUDENT);
        assertEquals(1, students.size());
        assertEquals("Student Name", students.get(0).getName());

        ArrayList<Person> tutors = personManager.getPersonListByPersonType(PersonType.TUTOR);
        assertEquals(1, tutors.size());
        assertEquals("Tutor Name", tutors.get(0).getName());
    }

    @Test
    public void testGetStudentByTrimester() {
        personManager.createStudent("Student Name");
        Person student = personManager.getPersonListByPersonType(PersonType.STUDENT).get(0);

        ArrayList<Person> studentsByTrimester = personManager.getStudentByTrimester(student.getTrimester());
        assertEquals(1, studentsByTrimester.size());
        assertEquals(student, studentsByTrimester.get(0));
    }

    @Test
    public void testGetTutorByTrimester() {
        personManager.createTutor("Tutor Name");
        Person tutor = personManager.getPersonListByPersonType(PersonType.TUTOR).get(0);

        ArrayList<Person> tutorsByTrimester = personManager.getTutorByTrimester(tutor.getTrimester());
        assertEquals(1, tutorsByTrimester.size());
        assertEquals(tutor, tutorsByTrimester.get(0));
    }

    @Test
    public void testOptInTaskToStudent() {
        Person student = personManager.createStudent("Student Name");
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Task("Task 001", faker.date().future(90, TimeUnit.DAYS), faker.lorem().sentence(5), Trimester.FIRST);
        tasks.add(task);
        when(taskManager.getTaskByTrimester(student.getTrimester())).thenReturn(tasks);

        personManager.OptInTaskToStudent(student, student.getTrimester());
        assertEquals(1, student.getTaskList().size());
        assertEquals(task, student.getTaskList().get(0));
    }

    @Test
    public void testOptInTaskToTutor() {
        Person tutor = personManager.createTutor("Tutor Name");
        Task task = new Task("Task 001", faker.date().future(90, TimeUnit.DAYS), faker.lorem().sentence(5), Trimester.FIRST);

        personManager.OptInTaskToTutor(tutor, task);
        assertEquals(1, tutor.getTaskList().size());
        assertEquals(task, tutor.getTaskList().get(0));
    }

    @Test
    public void testAssignTaskToPerson() {
        Person student = personManager.createStudent("Student Name");
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Task("Task 001", faker.date().future(90, TimeUnit.DAYS), faker.lorem().sentence(5), Trimester.FIRST);
        tasks.add(task);
        when(taskManager.getTaskByTrimester(student.getTrimester())).thenReturn(tasks);

        personManager.assignTaskToPerson();
        assertEquals(1, student.getTaskList().size());
        assertEquals(task, student.getTaskList().get(0));
    }
}

