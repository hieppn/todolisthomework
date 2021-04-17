package com.pycogroup.todolist.payload.request;

import javax.validation.constraints.NotBlank;

public class UpdateTaskRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String taskId;
    private String content;
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
