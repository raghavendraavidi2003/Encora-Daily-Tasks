package com.admin.controller;

import com.admin.dao.UserDao;
import com.admin.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        List<User> users = userDao.getAllUsers();
        model.addAttribute("userCount", users.size());
        model.addAttribute("totalBalance", users.stream().mapToDouble(User::getBalance).sum());
        return "adminDashboard";
    }

    @GetMapping("/users")
    public String listUsers(HttpSession session, Model model) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        List<User> users = userDao.getAllUsers();
        model.addAttribute("userList", users);
        return "userList";
    }

    @GetMapping("/add-user")
    public String addUserForm(HttpSession session) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }
        return "addUser";
    }

    @PostMapping("/add-user")
    public String addUser(@RequestParam String name, @RequestParam String email,
                          @RequestParam String pwd, @RequestParam double balance,
                          Model model, HttpSession session) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        String id = "U" + System.currentTimeMillis();
        User user = new User(id, name, email, pwd, balance);
        int result = userDao.registerUser(user);

        if (result > 0) {
            model.addAttribute("message", "✅ User added successfully with ID: " + id);
            return "success";
        } else {
            model.addAttribute("message", "❌ Failed to add user.");
            return "error";
        }
    }

    @GetMapping("/edit-user")
    public String editUser(@RequestParam String id, Model model, HttpSession session) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        User user = userDao.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute User user, HttpSession session) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        userDao.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete-user")
    public String deleteUser(@RequestParam String id, HttpSession session) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        userDao.deleteUser(id);
        return "redirect:/admin/users";
    }
}
