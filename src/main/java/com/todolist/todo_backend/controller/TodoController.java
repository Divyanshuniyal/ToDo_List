package com.todolist.todo_backend.controller;

import com.todolist.todo_backend.model.Todo;
import com.todolist.todo_backend.model.WebSocketMessage;
import com.todolist.todo_backend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Get all todos
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Add a new todo
    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoRepository.save(todo);
        messagingTemplate.convertAndSend("/topic/todos", new WebSocketMessage("ADD", savedTodo));
        return savedTodo;
    }

    // Delete a todo by ID
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoRepository.deleteById(id);
        Todo deletedTodo = new Todo();
        deletedTodo.setId(id);
        messagingTemplate.convertAndSend("/topic/todos", new WebSocketMessage("DELETE", deletedTodo));
    }

    // Update a todo
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable String id, @RequestBody Todo updatedTodo) {
        updatedTodo.setId(id);
        Todo savedTodo = todoRepository.save(updatedTodo);
        messagingTemplate.convertAndSend("/topic/todos", new WebSocketMessage("UPDATE", savedTodo));
        return savedTodo;
    }
}
