package com.example.DynamicEmployeeManagementSystem.controller;

import com.example.DynamicEmployeeManagementSystem.jwt.JwtUtil;
import com.example.DynamicEmployeeManagementSystem.model.Employee;
import com.example.DynamicEmployeeManagementSystem.model.User;
import com.example.DynamicEmployeeManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtUtil jwtUtil;

    public UserController(UserService userService) {
        this.userService = userService;
    }

  /*  // Registration endpoint (POST)
    @PostMapping("/login")
    public User loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        // System.out.println(userName);

        return userService.login(userName);

    }
    */

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody User user) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String role = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("ROLE_USER");
            // System.out.println(role);
            return jwtUtil.generateToken(user.getUsername(), role);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user1) {
        System.out.println(user1.getIsAdmin());

        return userService.registerUser(user1);
    }

}
