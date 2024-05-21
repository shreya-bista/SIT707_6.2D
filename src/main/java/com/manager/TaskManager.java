package com.manager;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.entity.Task;
import com.enums.Trimester;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(String taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    public ArrayList<Task> getTaskByTrimester(Trimester trimester) {
        return tasks.stream()
                .filter(task -> task.getTrimester() == trimester)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}