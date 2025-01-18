package com.example.DynamicEmployeeManagementSystem.config;

import com.example.DynamicEmployeeManagementSystem.jwt.JwtFilter;
import com.example.DynamicEmployeeManagementSystem.repository.UserRepository;
import com.example.DynamicEmployeeManagementSystem.service.CustomUserDetailsService;
import com.example.DynamicEmployeeManagementSystem.service.UserService;
import com.sun.source.doctree.AuthorTree;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {


    private JwtFilter jwtFilter;

    private UserDetailsService userDetailsService;

    public SecurityConfig(JwtFilter jwtFilter, UserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(cutomizet -> cutomizet.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/employees/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/employees/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/employees/*").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }


}
