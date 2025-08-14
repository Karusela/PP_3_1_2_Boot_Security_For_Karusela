package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    public AdminController(UserService userService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping(value = "/")
//    public String homePage() {
//        return "user";
//    }

    @GetMapping
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/saveUser")
    public String addNewUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRoles", roleService.getAll());
        return "new";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

//    @GetMapping(value = "/admin/updateInfo")
//    public String updateUser(@RequestParam("userid") int id, Model model) {
//        User user = userService.getUser(id);
//        model.addAttribute("user", user);
//        return "user-info";
//    }
//
//    @GetMapping(value = "/admin/deleteUser")
//    public String deleteUser(@RequestParam("userid") int id, Model model) {
//        userService.deleteUser(id);
//        return "redirect:/";
//    }
}