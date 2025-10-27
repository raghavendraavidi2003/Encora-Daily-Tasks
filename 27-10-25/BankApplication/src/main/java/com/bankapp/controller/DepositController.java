package com.bankapp.controller;

import com.bankapp.dao.UserDao;
import com.bankapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class DepositController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/deposit")
    public String showDepositForm(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "deposit";
    }

    @PostMapping("/deposit")
    public String processDeposit(@RequestParam double amount,
                               HttpSession session,
                               Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        if (amount <= 0) {
            model.addAttribute("error", "Amount must be positive.");
            return "deposit";
        }

        try {
            double newBalance = user.getAccountBalance() + amount;
            boolean updated = userDao.updateBalance(user.getUserId(), newBalance);
            
            if (updated) {
                user.setAccountBalance(newBalance);
                session.setAttribute("user", user);
                model.addAttribute("message", "Deposit successful.");
                model.addAttribute("user", user);
                return "dashboard";
            } else {
                model.addAttribute("error", "Deposit failed.");
                return "deposit";
            }
        } catch (SQLException e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
            return "deposit";
        }
    }
}
