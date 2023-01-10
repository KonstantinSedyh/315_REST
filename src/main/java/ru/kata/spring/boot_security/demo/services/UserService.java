package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    void addUser(User user);

    void updateUser(User user);

    User findUserById(Integer id);

    void deleteUserById(Integer id);

    User findUserByUserName(String name);

    User findByEmail (String email);
}
