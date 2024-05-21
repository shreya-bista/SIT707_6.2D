package com.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.entity.Message;
import com.entity.Person;
import com.entity.Task;

public class ChatManager {
    private ArrayList<Message> messages;

    public ChatManager() {
        this.messages = new ArrayList<>();
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public ArrayList<Message> getAllMessages() {
        return this.messages;
    }

    public ArrayList<Message> getAllMessagesByPersonAndTask(Person person, Task task ) {
        ArrayList<Message> tempMessages = new ArrayList<>();

        for (Message message : messages) {
            if(message.getTask().equals(task) && (message.getReceiver().equals(person) || message.getSender().equals(person))){
                tempMessages.add(message);
            }
        }
        // Sort messages by timestamp in descending order (latest on top)
        Collections.sort(tempMessages, Comparator.comparing(Message::getTimestamp).reversed());

        return tempMessages;
    }
}
