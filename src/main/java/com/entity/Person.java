package com.entity;

import java.util.ArrayList;

import com.enums.PersonType;
import com.enums.Trimester;
import com.github.javafaker.Faker;

public class Person {
    
    private PersonType personType;
    private String name;
    private String pId;
    private ArrayList<Task> taskList;
    private Trimester trimester;
    
    public Trimester getTrimester() {
        return trimester;
    }
    public void setTrimester(Trimester trimester) {
        this.trimester = trimester;
    }
    public PersonType getPersonType() {
        return personType;
    }
    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPid(){
        return this.pId;
    }

    public Person(PersonType personType, String name, Trimester trimester) {
        this.personType = personType;
        this.name = name;
        this.pId = new Faker().number().digits(5);
        this.taskList = new ArrayList<>();
        this.trimester = trimester;
    }
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task){
        this.taskList.add(task);
    }
    @Override
    public String toString() {
        return "Person [personType=" + personType + ", name=" + name + ", pId=" + pId + ", taskList=" + taskList
                + ", trimester=" + trimester + "]";
    }
    public String getpId() {
        return pId;
    }

    

}