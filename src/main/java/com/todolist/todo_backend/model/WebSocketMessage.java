package com.todolist.todo_backend.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketMessage {
    private String type; // e.g., "ADD", "DELETE"
    private Todo todo;
}
