package com.taskmanager.dto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String assignedTo;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}