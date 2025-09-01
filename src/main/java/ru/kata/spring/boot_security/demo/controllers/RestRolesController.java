package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestRolesController {

    private final RoleService roleService;

    @Autowired
    public RestRolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAll();
    }
}
