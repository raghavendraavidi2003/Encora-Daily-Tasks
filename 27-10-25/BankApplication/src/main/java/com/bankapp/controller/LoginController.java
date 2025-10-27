package com.bankapp.controller;

import com.bankapp.dao.UserDao;
import com.bankapp.model.User;
import com.bankapp.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;

    // In-memory OTP store
    private static ConcurrentHashMap<String, String> otpStore = new ConcurrentHashMap<>();
    
    

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, Model model) {
        try {
            User user = userDao.getUserByEmail(email);
            if (user == null) {
                model.addAttribute("error", "No user registered with this email.");
                return "login";
            }

            // Generate OTP
            String otp = generateOTP();
            otpStore.put(email, otp);

            // Send OTP via email
            String subject = "Your OTP for BankApp Login";
            String body = "Dear " + user.getName() + ",\n\nYour OTP for login is: " + otp + "\n\nThank you.";
            EmailUtil.sendEmail(email, subject, body);

            model.addAttribute("email", email);
            return "otpVerification";

        } catch (SQLException e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "Error sending email: " + e.getMessage());
            return "login";
        }
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static boolean verifyOTP(String email, String otp) {
        String storedOtp = otpStore.get(email);
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpStore.remove(email);
            return true;
        }
        return false;
    }

}
