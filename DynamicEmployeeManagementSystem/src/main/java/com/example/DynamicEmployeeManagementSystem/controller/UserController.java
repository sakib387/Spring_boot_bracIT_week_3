package com.example.DynamicEmployeeManagementSystem.controller;

import com.example.DynamicEmployeeManagementSystem.model.User;
import com.example.DynamicEmployeeManagementSystem.service.UserService;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registration endpoint (POST)
    @PostMapping("/login")
    public User loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        System.out.println(userName);
        try {
            return userService.login(userName);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user1) {
        System.out.println(user1.getIsAdmin());

        return userService.registerUser(user1) ;
    }
}
