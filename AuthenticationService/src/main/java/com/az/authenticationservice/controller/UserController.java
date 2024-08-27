package com.az.authenticationservice.controller;

import com.az.authenticationservice.domain.User;
import com.az.authenticationservice.domain.UserRole;
import com.az.authenticationservice.service.UserRoleService;
import com.az.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Autowired
    public UserController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    //user
    @PostMapping(value = "/user")
    @PreAuthorize("hasAuthority('HR')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/user")
    @PreAuthorize("hasAuthority('HR')")
    public ResponseEntity<User> deleteUser(@RequestParam int userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasAuthority('HR')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('HR')")
    public ResponseEntity<User> getUserById(@RequestParam int userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/roles")
    @PreAuthorize("hasAuthority('HR')")
    public ResponseEntity<List<UserRole>> getAllUserRoles() {
        List<UserRole> userRoles = userRoleService.getAllUserRoles();
        return ResponseEntity.ok(userRoles);
    }






}
