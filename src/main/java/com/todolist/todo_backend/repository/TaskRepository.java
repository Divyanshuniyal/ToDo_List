package com.todolist.todo_backend.repository;

import com.todolist.todo_backend.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
