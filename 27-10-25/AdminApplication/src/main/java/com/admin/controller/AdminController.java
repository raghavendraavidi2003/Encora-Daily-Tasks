package com.admin.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "adminLogin";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("adminLoggedIn", true);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "❌ Invalid credentials. Try admin / admin123");
            return "adminLogin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @GetMapping("/")
    public String redirectRoot() {
        return "redirect:/admin/login";
    }
}
