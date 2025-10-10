package com.taskmanager.controller;

import com.taskmanager.dto.SystemStats;
import com.taskmanager.dto.UserResponse;
import com.taskmanager.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    
    private final AdminService adminService;
    
    // Get all users (ADMIN only)
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }
    
    // Get user by ID (ADMIN only)
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getUserById(id));
    }
    
    // Delete user (ADMIN only)
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    
    // Update user role (ADMIN only)
    @PatchMapping("/users/{id}/role")
    public ResponseEntity<UserResponse> updateUserRole(
            @PathVariable Long id,
            @RequestParam String role
    ) {
        return ResponseEntity.ok(adminService.updateUserRole(id, role));
    }
    
    // Get system statistics (ADMIN only)
    @GetMapping("/stats")
    public ResponseEntity<SystemStats> getSystemStats() {
        return ResponseEntity.ok(adminService.getSystemStats());
    }
}