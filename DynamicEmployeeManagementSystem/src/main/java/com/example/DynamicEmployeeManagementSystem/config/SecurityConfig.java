package com.example.DynamicEmployeeManagementSystem.config;

import com.example.DynamicEmployeeManagementSystem.repository.UserRepository;
import com.example.DynamicEmployeeManagementSystem.service.CustomUserDetailsService;
import com.example.DynamicEmployeeManagementSystem.service.UserService;
import com.sun.source.doctree.AuthorTree;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {


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
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // No password encoding
    }


}
