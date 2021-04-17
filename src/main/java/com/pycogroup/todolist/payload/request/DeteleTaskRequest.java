package com.pycogroup.todolist.payload.request;

import javax.validation.constraints.NotBlank;

public class DeteleTaskRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
