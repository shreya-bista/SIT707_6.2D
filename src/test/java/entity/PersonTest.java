package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.entity.Person;
import com.entity.Task;
import com.enums.PersonType;
import com.enums.Trimester;

public class PersonTest {
    private Person person;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        person = new Person(PersonType.STUDENT, "John Doe", Trimester.FIRST);
        task1 = new Task("1", new Date(),"Task 1 Description", Trimester.FIRST);
        task2 = new Task("2", new Date(),"Task 2 Description", Trimester.FIRST);
    }

    @Test
    public void testPersonConstructor() {
        assertEquals(PersonType.STUDENT, person.getPersonType());
        assertEquals("John Doe", person.getName());
        assertNotNull(person.getPid());
        assertEquals(Trimester.FIRST, person.getTrimester());
        assertNotNull(person.getTaskList());
    }

    @Test
    public void testSettersAndGetters() {
        person.setPersonType(PersonType.TUTOR);
        assertEquals(PersonType.TUTOR, person.getPersonType());

        person.setName("Jane Doe");
        assertEquals("Jane Doe", person.getName());

        person.setTrimester(Trimester.SECOND);
        assertEquals(Trimester.SECOND, person.getTrimester());

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        person.setTaskList(taskList);
        assertEquals(taskList, person.getTaskList());
    }

    @Test
    public void testAddTask() {
        person.addTask(task1);
        assertTrue(person.getTaskList().contains(task1));

        person.addTask(task2);
        assertTrue(person.getTaskList().contains(task2));
    }

    @Test
    public void testToString() {
        person.addTask(task1);
        String expectedString = "Person [personType=" + PersonType.STUDENT + ", name=" + "John Doe" + ", pId=" + person.getpId() + ", taskList=" + person.getTaskList() + ", trimester=" + Trimester.FIRST + "]";
        assertEquals(expectedString, person.toString());
    }
}
