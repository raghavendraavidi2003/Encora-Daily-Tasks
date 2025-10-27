package com.bankapp.controller;

import com.bankapp.model.User;
import com.bankapp.util.EmailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.sql.*;

@Controller
public class ChequeBookController {

    private final String DB_URL = "jdbc:mysql://localhost:3306/banking_app";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "Nani@2003";

    @PostMapping("/chequebook-request")
    public String processChequeBookRequest(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            // Insert request into database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO checkbook_requests (user_id) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserId());
            ps.executeUpdate();

            // Send confirmation email
            String subject = "Cheque Book Request Submitted";
            String body = "Dear " + user.getName() + ",\n\nYour cheque book request has been received and is being processed.\n\nRegards,\nBanking App";
            EmailUtil.sendEmail(user.getEmail(), subject, body);

            model.addAttribute("message", "Cheque book request submitted successfully!");
            model.addAttribute("user", user);
            return "dashboard";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while submitting your request.");
            model.addAttribute("user", user);
            return "dashboard";
        }
    }
}
