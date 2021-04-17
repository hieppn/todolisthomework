package com.pycogroup.todolist.repository;

import com.pycogroup.todolist.models.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TaskRepository extends MongoRepository<Task, String>, QueryByExampleExecutor<Task>{
    Task findByContent(String content);
}
