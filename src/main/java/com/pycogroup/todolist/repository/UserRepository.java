package com.pycogroup.todolist.repository;

import java.util.Optional;

import com.pycogroup.todolist.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  User findByUserId(String id);
}
