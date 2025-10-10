package com.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    @NotBlank(message = "Status is required")
    private String status; // TODO, IN_PROGRESS, COMPLETED, CANCELLED
    
    @NotBlank(message = "Priority is required")
    private String priority; // LOW, MEDIUM, HIGH, URGENT
}

