package com.example.DynamicEmployeeManagementSystem.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;

    private boolean isAdmin;

    public User() {
    }

    public User(Long id, String username, String password, boolean isAdmin) {
        this.userId = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        if(isAdmin)
        return "ROLE_ADMIN";
        return "ROLE_USER";
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public void setRoles(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
