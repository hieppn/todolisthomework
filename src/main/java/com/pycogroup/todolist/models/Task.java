package com.pycogroup.todolist.models;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
@Document(collection = "tasks")
public class Task {
    @Id
    private String taskId;

    @NotBlank
    private String content;
    @NotBlank
    @Builder.Default
    private String status= "todo";

    public Task() {

    }

    public Task(String content) {
        this.content = content;
        this.status = "todo";
    }

    public String getId() {
        return taskId;
    }
    public void setId(String taskId) {
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
