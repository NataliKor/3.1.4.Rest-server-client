package com.example.spring_boot_rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    // открытие страницы авторизации
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // обработчик выхода Spring Security
    @PostMapping("/logout")
    public String logout() {
        return "login";
    }
}
