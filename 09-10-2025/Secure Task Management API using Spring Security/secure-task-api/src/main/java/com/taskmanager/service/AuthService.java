package com.taskmanager.service;

import com.taskmanager.dto.AuthRequest;
import com.taskmanager.dto.AuthResponse;
import com.taskmanager.dto.RegisterRequest;
import com.taskmanager.model.Role;
import com.taskmanager.model.User;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse register(RegisterRequest request) {
        
        // Check if user already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Determine role (default to USER if not specified)
        Role role = Role.USER;
        if (request.getRole() != null && !request.getRole().isEmpty()) {
            try {
                role = Role.valueOf(request.getRole().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid role: " + request.getRole());
            }
        }
        
        // Create new user
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .enabled(true)
                .build();
        
        userRepository.save(user);
        
        // Generate JWT token
        var jwtToken = jwtService.generateToken(user);
        
        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(user.getRole().name())
                .message("Registration successful")
                .build();
    }
    
    public AuthResponse authenticate(AuthRequest request) {
        
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        
        // Load user details
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Generate JWT token
        var jwtToken = jwtService.generateToken(user);
        
        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(user.getRole().name())
                .message("Login successful")
                .build();
    }
}