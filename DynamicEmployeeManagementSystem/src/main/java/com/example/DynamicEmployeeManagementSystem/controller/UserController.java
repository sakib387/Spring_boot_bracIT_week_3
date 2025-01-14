package com.example.DynamicEmployeeManagementSystem.controller;

import com.example.DynamicEmployeeManagementSystem.model.User;
import com.example.DynamicEmployeeManagementSystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registration endpoint (POST)
    @PostMapping( )
    public User registerUser(@RequestBody User user) {
        try {
             return userService.registerUser(user);
        } catch (RuntimeException e) {
           throw new NullPointerException();
        }
    }
    @GetMapping
    public String kiso(){
      return "kkdk";
    }
}
