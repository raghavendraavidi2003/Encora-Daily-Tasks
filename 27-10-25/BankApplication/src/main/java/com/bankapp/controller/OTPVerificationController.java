package com.bankapp.controller;
import com.bankapp.controller.LoginController;
import com.bankapp.dao.UserDao;
import com.bankapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller

public class OTPVerificationController {

    @Autowired
    private UserDao userDao;

    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestParam String email,
                           @RequestParam String otp,
                           HttpSession session,
                           Model model) {
        try {
            if (LoginController.verifyOTP(email, otp)) {
                User user = userDao.getUserByEmail(email);
                session.setAttribute("user", user);
                return "redirect:/dashboard";
            } else {
                model.addAttribute("email", email);
                model.addAttribute("error", "Invalid OTP. Please try again.");
                return "otpVerification";
            }
        } catch (SQLException e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
            return "otpVerification";
        }
    }
}
