package com.entity;

import java.util.Calendar;
import java.util.Date;

import com.enums.Status;
import com.enums.Trimester;
import com.github.javafaker.Faker;

public class Task {
    private String name;
    private Date dueDate;
    private Status status;
    private String comment;
    private String taskId;
    private Trimester trimester;


    public Task(String name, Date dueDate, String comment, Trimester trimester) {
        this.name = name;
        this.dueDate = dueDate;
        this.status = Status.PENDING;
        this.comment = comment;
        this.taskId = new Faker().number().digits(5);
        this.trimester = trimester;
    }

    

    public Trimester getTrimester() {
        return trimester;
    }



    public void setTrimester(Trimester trimester) {
        this.trimester = trimester;
    }



    public String getTaskId() {
        return taskId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void submitTask(Status status){
        setStatus(status);
    }


    public void requestForExtension() {
            Date currentDate = this.dueDate;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            Date newDate = calendar.getTime();
            this.dueDate = newDate;
       
    }
    @Override
    public String toString() {
        return "Task [name=" + name + ", dueDate=" + dueDate + ", status=" + status + ", comment=" + comment
                + ", taskId=" + taskId + ", trimester=" + trimester + "]";
    }

    

    
}
