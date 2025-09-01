package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/admin")
    public String adminPage() {
        return "forward:/index.html";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "forward:/user.html";
    }
}