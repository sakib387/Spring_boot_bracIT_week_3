package com.example.DynamicEmployeeManagementSystem.service;

import com.example.DynamicEmployeeManagementSystem.model.User;
import com.example.DynamicEmployeeManagementSystem.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        System.out.println(username);

        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found: " + username);
        }
        String role = user.getIsAdmin().equalsIgnoreCase("yes") ? "ADMIN" : "USER";
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + role)))
                .build();

    }

}
