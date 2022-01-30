package com.example.spring_boot_rest.controller;

import com.example.spring_boot_rest.model.User;
import com.example.spring_boot_rest.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    MyService myService;

    // открытие страницы приветствия авторизованного пользователя
    @GetMapping("/hello")
    public String printWelcome(Model model) {
        User user = myService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "user/user";
    }
}
