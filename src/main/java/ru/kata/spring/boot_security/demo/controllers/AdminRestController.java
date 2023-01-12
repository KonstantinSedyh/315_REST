package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return  ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
            userService.addUser(user);
            return  ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.findUserById(id);
        return  ResponseEntity.ok(user);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername (Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return  ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user) {
        userService.updateUser(user);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

}
