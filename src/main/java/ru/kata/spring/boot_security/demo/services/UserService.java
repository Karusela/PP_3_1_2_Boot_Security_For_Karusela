package ru.kata.spring.boot_security.demo.services;



import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(int id);

    public void deleteUser(int id);
}