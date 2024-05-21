### Critical Observation of Existing Functions:

1. **OnTrack Class:**
   - **Input:** Users (students and tutors), tasks, messages
   - **Output:** Task statuses, messages, task approval/rejection
   
2. **PersonManager Class:**
   - **Input:** Person type, name
   - **Output:** List of persons by type, persons by trimester

3. **TaskManager Class:**
   - **Input:** Task details (name, due date, comment, trimester)
   - **Output:** List of tasks, tasks by trimester

4. **ChatManager Class:**
   - **Input:** Messages (sender, receiver, content, task)
   - **Output:** List of messages, messages by person and task

### Planned Functions for OnTrack:

1. **Enhanced Task Management:**
   - Function to update task details (name, due date, comment)
   - Function to delete tasks

2. **Extended Messaging System:**
   - Functionality to delete messages
   - Functionality to mark messages as read/unread
   - Function to filter messages by date range


# OnTrack Customer Requirement Stories

## Story 1: Task Management

As a user of OnTrack, I want to be able to manage tasks effectively. This includes creating new tasks with details such as name, due date, and description. I should also have the ability to update task details, delete tasks if necessary, and view tasks sorted by trimester.

## Story 2: Messaging System

As a user of OnTrack, I want to have a messaging system that allows seamless communication between students and tutors. I should be able to send messages related to specific tasks, view all messages exchanged between a person and a task, and filter messages by date range. Additionally, I should be able to delete messages and mark messages as read/unread.



### Design and Implementation of OnTrack Functions

To design and implement the functions for the OnTrack project, we'll follow a structured approach that ensures the functionality meets the requirements and can be effectively tested for all the Right-BICEP properties.

#### 1. Task Management

- **Design:**
  - Implement methods in the `TaskManager` class to handle task creation, updating, deletion, and retrieval.
  - Ensure the methods validate input parameters and handle edge cases appropriately.

- **Implementation:**
  - Create methods `createTask`, `updateTask`, and `getTaskById` in the `TaskManager` class.
  - Use proper exception handling to deal with errors like invalid input or task not found.
  - Implement unit tests to verify the functionality for all the Right-BICEP properties:
    - **Right:** Verify the correctness of task creation, updating, and deletion.
    - **Bounded:** Test with different boundary values for task attributes like name length, due date range, etc.
    - **Cross-check:** Ensure tasks are correctly updated and deleted, and the correct task is retrieved by ID.
    - **Error conditions:** Validate error handling for cases like invalid input parameters or non-existent tasks.
    - **Performance:** Evaluate the performance of task retrieval for large datasets.

#### 2. Messaging System

- **Design:**
  - Implement methods in the `ChatManager` class to handle message creation, deletion, retrieval, and filtering.
  - Design a data structure to store messages efficiently and support fast retrieval and filtering.

- **Implementation:**
  - Create methods `sendMessage`, `deleteMessage`, `getAllMessages`, and `getMessagesByPersonAndTask` in the `ChatManager` class.
  - Implement message filtering based on sender, receiver, task, and date range.
  - Ensure proper indexing or caching mechanisms for efficient message retrieval.
  - Develop unit tests covering all Right-BICEP properties, including different message scenarios, edge cases, error handling, and performance evaluation.

