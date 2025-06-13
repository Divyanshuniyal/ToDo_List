package com.todolist.todo_backend.model;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    private String id;

    @NotBlank
    private String title;

    private String description;
    private String priority;
    private String category;
    private LocalDate dueDate;
    private boolean completed = false;
}
