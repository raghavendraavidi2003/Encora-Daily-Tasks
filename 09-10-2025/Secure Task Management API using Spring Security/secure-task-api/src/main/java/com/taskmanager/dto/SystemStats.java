package com.taskmanager.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemStats {
    private long totalUsers;
    private long totalTasks;
    private long completedTasks;
    private long pendingTasks;
}