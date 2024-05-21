package com.main;

import java.util.ArrayList;
import java.util.Date;

import com.entity.Message;
import com.entity.Person;
import com.entity.Task;
import com.enums.PersonType;
import com.enums.Status;
import com.enums.Trimester;
import com.manager.ChatManager;
import com.manager.PersonManager;
import com.manager.TaskManager;

public class OnTrack {
    private PersonManager personManager;
    private TaskManager taskManager;
    private ChatManager chatManager;

    public OnTrack() {
        this.taskManager = new TaskManager();
        this.chatManager = new ChatManager();
        this.personManager = new PersonManager(taskManager, chatManager);
    }

    public Person createStudent(String name) {
        return personManager.createStudent(name);
    }

    public Person createTutor(String name) {
        return personManager.createTutor(name);
    }

    public Task createTask(String name, Date dueDate, String comment, Trimester trimester) {
        Task task = new Task(name, dueDate, comment, trimester);
        taskManager.addTask(task);
        return task;
    }

    public void submitTask(Person student, Task task, Status status) {
        if (student.getPersonType() == PersonType.STUDENT) {
            task.submitTask(status);
            System.out.println("Student " + student.getName() + " has submitted task " + task.getName() + " with status " + status);
        }
    }

    public void addCommentToTask(Person tutor, Task task, String comment) {
        if (tutor.getPersonType() == PersonType.TUTOR) {
            task.setComment(comment);
            System.out.println("Tutor " + tutor.getName() + " added a comment to task " + task.getName() + ": " + comment);
        }
    }

    public void sendMessage(Person sender, Person receiver, String content, Task task) {
        Message message = new Message(sender, receiver, content, task);
        chatManager.sendMessage(message);
        System.out.println("Message sent from " + sender.getName() + " to " + receiver.getName() + ": " + content);
    }

    public ArrayList<Message> getMessages(Person person, Task task) {
        return chatManager.getAllMessagesByPersonAndTask(person, task);
    }

    public void rejectTask(Person tutor, Task task) {
        if (tutor.getPersonType() == PersonType.TUTOR) {
            task.setStatus(Status.REJECTED);
            System.out.println("Tutor " + tutor.getName() + " has rejected task " + task.getName());
        }
    }

    public void approveTask(Person tutor, Task task) {
        if (tutor.getPersonType() == PersonType.TUTOR) {
            task.setStatus(Status.APPROVED);
            System.out.println("Tutor " + tutor.getName() + " has approved task " + task.getName());
        }
    }
}
