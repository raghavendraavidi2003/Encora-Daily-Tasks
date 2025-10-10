package com.taskmanager.controller;


import com.taskmanager.dto.TaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService taskService;
    
    // USER, MANAGER, ADMIN can create tasks
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(request));
    }
    
    // Get all tasks (USER sees own tasks, MANAGER/ADMIN see all)
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    
    // Update task (only MANAGER and ADMIN)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request
    ) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }
    
    // Update task status (USER can update own tasks)
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, status));
    }
    
    // Delete task (only ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }
    
    // Get tasks by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> getTasksByStatus(@PathVariable String status) {
        return ResponseEntity.ok(taskService.getTasksByStatus(status));
    }
    
    // Assign task to user (only MANAGER and ADMIN)
    @PatchMapping("/{id}/assign/{userId}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<TaskResponse> assignTask(
            @PathVariable Long id,
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(taskService.assignTask(id, userId));
    }
}