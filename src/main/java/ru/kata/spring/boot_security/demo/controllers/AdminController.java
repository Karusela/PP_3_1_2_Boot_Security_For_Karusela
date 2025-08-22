package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("role", new Role());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("currentUser", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }

//    @GetMapping("/new")
//    public String addNewUser(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("roles", roleService.getAll());
//        return "new";
//    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String remove(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

//    @GetMapping("/edit")
//    public String printEditForm(@RequestParam("id") Long id, Model model) {
//        model.addAttribute("user", userService.getUser(id));
//        model.addAttribute("roles", roleService.getAll());
//        return "edit";
//    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        userService.update(user, id);
        return "redirect:/admin";
    }
}