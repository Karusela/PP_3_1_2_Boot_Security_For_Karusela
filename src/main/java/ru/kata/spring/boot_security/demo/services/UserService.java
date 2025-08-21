package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(Long id);

    public void deleteUser(Long id);

    void update(User user, Long id);

    User findByUsername(String username);

    User findByEmail(String email);
}