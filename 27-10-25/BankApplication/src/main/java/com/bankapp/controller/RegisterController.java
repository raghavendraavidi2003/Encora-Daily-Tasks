package com.bankapp.controller;

import com.bankapp.dao.UserDao;
import com.bankapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class RegisterController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String password,
                             Model model) {
        try {
            String userId = userDao.generateUserId();
            User user = new User(userId, name, email, password, 0.0);
            
            boolean success = userDao.registerUser(user);
            if (success) {
                model.addAttribute("message", "Registration successful. Please login.");
                return "login";
            } else {
                model.addAttribute("error", "Registration failed. Try again.");
                return "register";
            }
        } catch (SQLException e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
            return "register";
        }
    }
}
