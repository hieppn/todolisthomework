package com.pycogroup.todolist.controllers;

import com.pycogroup.todolist.models.Task;
import com.pycogroup.todolist.payload.request.CreateTaskRequest;
import com.pycogroup.todolist.payload.request.DeteleTaskRequest;
import com.pycogroup.todolist.payload.request.UpdateTaskRequest;
import com.pycogroup.todolist.payload.response.MessageResponse;
import com.pycogroup.todolist.security.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("api/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody CreateTaskRequest createTaskRequest) {
        Task task = modelMapper.map(createTaskRequest, Task.class);
        taskService.createTask(createTaskRequest.getUserId(),task);
        return ResponseEntity.ok(new MessageResponse("Create new task successfully"));
    }
    @DeleteMapping("")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody DeteleTaskRequest deteleTaskRequest) {
        taskService.deleteTask(deteleTaskRequest.getUserId(),deteleTaskRequest.getTaskId());
        return ResponseEntity.ok(new MessageResponse("Delete task successfully"));
    }
    @PutMapping("")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UpdateTaskRequest updateTaskRequest) {
        taskService.updateTask(updateTaskRequest.getUserId(),updateTaskRequest.getTaskId(),updateTaskRequest.getContent(),updateTaskRequest.getStatus());
        return ResponseEntity.ok(new MessageResponse("Update task successfully"));
    }
}
