package com.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagementApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TaskManagementApplication.class, args);
        System.out.println("\n" +
                "========================================\n" +
                "  Task Management API is Running! 🚀\n" +
                "========================================\n" +
                "  URL: http://localhost:8080\n" +
                "  H2 Console: http://localhost:8080/h2-console\n" +
                "  \n" +
                "  Test the API:\n" +
                "  POST /api/auth/register - Register\n" +
                "  POST /api/auth/login - Login\n" +
                "  GET  /api/tasks - Get Tasks\n" +
                "========================================\n");
    }
}