package com.pycogroup.todolist.security.services;

import com.pycogroup.todolist.models.Task;

public interface TaskService {
    Task createTask(String userId,Task task);
    void deleteTask(String userId, String taskId);
    void updateTask(String userId, String taskId, String content, String status);
}
