package com.az.authenticationservice.controller;

import com.az.authenticationservice.domain.User;
import com.az.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity<User> deleteUser(@RequestParam int userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserById(@RequestParam int userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}
