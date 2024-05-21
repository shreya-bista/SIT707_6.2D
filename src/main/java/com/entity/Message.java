package com.entity;

import java.util.Date;

public class Message {
    private Person sender;
    private Person receiver;
    private String content;
    private Date timestamp;
    private Task task;


    public Message(Person sender, Person receiver, String content, Task task) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.task = task;
        this.timestamp = new Date();
    }
    public Person getSender() {
        return sender;
    }
    public void setSender(Person sender) {
        this.sender = sender;
    }
    public Person getReceiver() {
        return receiver;
    }
    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public Task getTask() {
        return task;
    }
    public void setTask(Task task) {
        this.task = task;
    }
    @Override
    public String toString() {
        return "Message [sender=" + sender + ", receiver=" + receiver + ", content=" + content + ", timestamp="
                + timestamp + ", task=" + task + "]";
    }

    
    
}