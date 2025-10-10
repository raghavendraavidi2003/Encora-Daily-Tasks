package com.taskmanager.service;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.model.User;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    
    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        User currentUser = getCurrentUser();
        
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TaskStatus.valueOf(request.getStatus().toUpperCase()))
                .priority(Priority.valueOf(request.getPriority().toUpperCase()))
                .createdBy(currentUser)
                .assignedTo(currentUser) // Initially assign to creator
                .build();
        
        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }
    
    public List<TaskResponse> getAllTasks() {
        User currentUser = getCurrentUser();
        List<Task> tasks;
        
        // ADMIN and MANAGER can see all tasks, USER can only see their own
        if (currentUser.getRole().name().equals("ADMIN") || 
            currentUser.getRole().name().equals("MANAGER")) {
            tasks = taskRepository.findAll();
        } else {
            tasks = taskRepository.findByAssignedTo(currentUser);
        }
        
        return tasks.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        
        User currentUser = getCurrentUser();
        
        // Check if user has permission to view this task
        if (!currentUser.getRole().name().equals("ADMIN") && 
            !currentUser.getRole().name().equals("MANAGER") &&
            !task.getAssignedTo().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You don't have permission to view this task");
        }
        
        return mapToResponse(task);
    }
    
    @Transactional
    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.valueOf(request.getStatus().toUpperCase()));
        task.setPriority(Priority.valueOf(request.getPriority().toUpperCase()));
        
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }
    
    @Transactional
    public TaskResponse updateTaskStatus(Long id, String status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        
        User currentUser = getCurrentUser();
        
        // USER can only update their own tasks
        if (!currentUser.getRole().name().equals("ADMIN") && 
            !currentUser.getRole().name().equals("MANAGER") &&
            !task.getAssignedTo().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You don't have permission to update this task");
        }
        
        task.setStatus(TaskStatus.valueOf(status.toUpperCase()));
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }
    
    @Transactional
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskRepository.delete(task);
    }
    
    public List<TaskResponse> getTasksByStatus(String status) {
        User currentUser = getCurrentUser();
        TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
        List<Task> tasks;
        
        if (currentUser.getRole().name().equals("ADMIN") || 
            currentUser.getRole().name().equals("MANAGER")) {
            tasks = taskRepository.findByStatus(taskStatus);
        } else {
            tasks = taskRepository.findByAssignedToAndStatus(currentUser, taskStatus);
        }
        
        return tasks.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public TaskResponse assignTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        task.setAssignedTo(user);
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }
    
    // Helper methods
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
    
    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().name())
                .priority(task.getPriority().name())
                .assignedTo(task.getAssignedTo().getUsername())
                .createdBy(task.getCreatedBy().getUsername())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}