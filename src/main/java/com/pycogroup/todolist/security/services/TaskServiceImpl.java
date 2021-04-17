package com.pycogroup.todolist.security.services;

import com.mongodb.BasicDBObject;
import com.pycogroup.todolist.models.Task;
import com.pycogroup.todolist.models.User;
import com.pycogroup.todolist.repository.TaskRepository;
import com.pycogroup.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Task createTask(String userId, Task task) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throwExceptions(HttpStatus.UNAUTHORIZED, "Not sign in yet");
        }
        User content = mongoOperations.findOne(new Query(Criteria.where("taskList.content").is(task.getContent())), User.class);
        System.out.println(content);
        if (content != null) {
            throwExceptions(HttpStatus.BAD_REQUEST, "Task is existed");
        }
        String taskId = UUID.randomUUID().toString();
        task.setId(taskId);
        if (user.getTaskList() == null) {
            user.setTaskList(Arrays.asList(task));
        } else {
            user.getTaskList().add(task);
        }
        userRepository.save(user);
        return task;
    }

    @Override
    public void deleteTask(String userId, String taskId) {
        User user = getCurrentUser(userId);
        if (user == null) {
            throwExceptions(HttpStatus.UNAUTHORIZED, "User does not have permission.");
        }
        Query task = Query.query(Criteria.where("taskList._id").is(taskId));
        Update update = new Update().pull("taskList", new BasicDBObject("_id", taskId));
        mongoOperations.updateFirst(task, update, User.class);
    }

    @Override
    public void updateTask(String userId, String taskId, String content, String status) {
        User user = getCurrentUser(userId);
        if (user == null) {
            throwExceptions(HttpStatus.UNAUTHORIZED, "User does not have permission.");
        }
        User owner = mongoOperations.findOne(new Query(Criteria.where("taskList._id").is(taskId)), User.class);
        if(owner==null){
            throwExceptions(HttpStatus.NOT_FOUND, "Task not found");
        }
        User findTask = mongoOperations.findOne(new Query(Criteria.where("taskList._id").is(taskId)), User.class);
        if (content!=null && status!=null){
            mongoTemplate.updateMulti(
                    new Query(Criteria.where("taskList._id").is(taskId)),
                    new Update().set("taskList.$.content", content).set("taskList.$.status", status),
                    User.class
            );
        }else{
            if(content==null&& status!=null){
                mongoTemplate.updateMulti(
                        new Query(Criteria.where("taskList._id").is(taskId)),
                        new Update().set("taskList.$.status", status),
                        User.class
                );
            }else{
                if(content!=null&& status==null){
                    mongoTemplate.updateMulti(
                            new Query(Criteria.where("taskList._id").is(taskId)),
                            new Update().set("taskList.$.content", content),
                            User.class
                    );
                }
                else{
                    throwExceptions(HttpStatus.OK,"Everything is date");
                }
            }
        }
    }

    private User getCurrentUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    private void throwExceptions(HttpStatus status, String s) {
        throw new ResponseStatusException(
                status, s
        );
    }
}
