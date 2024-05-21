package com.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.entity.Person;
import com.entity.Task;
import com.enums.PersonType;
import com.enums.Trimester;

public class PersonManager {
    private TaskManager taskManager;
    private ChatManager chatManager;
    private Set<Trimester> usedTrimesters = new HashSet<>();

    public PersonManager(TaskManager taskManager, ChatManager chatManager){
        this.taskManager = taskManager;
        this.chatManager = chatManager;
    }
    
ArrayList<Person> personList = new ArrayList<>();

    public Person createStudent(String name){
        return createPerson(PersonType.STUDENT, name);
    }

    public Person createTutor(String name){
        return createPerson(PersonType.TUTOR, name);
    }
    
    public Person createPerson(PersonType personType, String name){
        if (usedTrimesters.size() == Trimester.values().length) {
            usedTrimesters.clear();
        }
        Trimester trimester = getRandomUnusedTrimester();
        Person person = new Person(personType, name, trimester);
        personList.add(person);
        return person;
    }

    public ArrayList<Person> getPersonListByPersonType(PersonType personType){
        return personList.stream()
                    .filter(person -> person.getPersonType() == personType)
                    .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Person> getStudentByTrimester(Trimester trimester){
        var studentList = getPersonListByPersonType(PersonType.STUDENT);
        return studentList.stream()
                    .filter(person -> trimester == person.getTrimester())
                    .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Person> getTutorByTrimester(Trimester trimester){
        var studentList = getPersonListByPersonType(PersonType.TUTOR);
        return studentList.stream()
                    .filter(person -> trimester == person.getTrimester())
                    .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public void OptInTaskToStudent(Person person, Trimester trimester){
        if(person.getPersonType().equals(PersonType.STUDENT)){
            ArrayList<Task> taskList = taskManager.getTaskByTrimester(trimester);
            person.setTaskList(taskList);
        }
    }

    public void OptInTaskToTutor(Person person, Task task){
        if(person.getPersonType().equals(PersonType.TUTOR)){
            person.addTask(task);
        }
    }

    public void assignTaskToPerson(){
        for (Person person : personList) {
            person.setTaskList(taskManager.getTaskByTrimester(person.getTrimester()));
        }
    }
    
    private Trimester getRandomUnusedTrimester() {
        Trimester trimester;
        do {
            trimester = Trimester.getRandomTrimester();
        } while (usedTrimesters.contains(trimester));
        return trimester;
    }
}
