package com.pycogroup.todolist.payload.request;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class CreateTaskRequest {
    @NotBlank
    private String content;
    @NotBlank
    private String userId;
    @NotBlank
    @Builder.Default
    private String status ="todo";
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
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
