package com.taskmanager.service;

import com.taskmanager.dto.SystemStats;
import com.taskmanager.dto.UserResponse;
import com.taskmanager.model.Role;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.model.User;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }
    
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToUserResponse(user);
    }
    
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
    
    @Transactional
    public UserResponse updateUserRole(Long id, String roleStr) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        try {
            Role role = Role.valueOf(roleStr.toUpperCase());
            user.setRole(role);
            User updatedUser = userRepository.save(user);
            return mapToUserResponse(updatedUser);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + roleStr);
        }
    }
    
    public SystemStats getSystemStats() {
        long totalUsers = userRepository.count();
        long totalTasks = taskRepository.count();
        long completedTasks = taskRepository.findByStatus(TaskStatus.COMPLETED).size();
        long pendingTasks = taskRepository.findByStatus(TaskStatus.TODO).size() + 
                           taskRepository.findByStatus(TaskStatus.IN_PROGRESS).size();
        
        return SystemStats.builder()
                .totalUsers(totalUsers)
                .totalTasks(totalTasks)
                .completedTasks(completedTasks)
                .pendingTasks(pendingTasks)
                .build();
    }
    
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .enabled(user.isEnabled())
                .build();
    }
}