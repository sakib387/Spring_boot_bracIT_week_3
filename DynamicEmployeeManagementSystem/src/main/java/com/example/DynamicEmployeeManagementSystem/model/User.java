package com.example.DynamicEmployeeManagementSystem.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String isAdmin;
    private String username;
    private String password;

 
    public User() {
    }


    public User(Long userId, String amin, String username, String password) {
        this.userId = userId;
        this.isAdmin = amin;
        this.username = username;
        this.password = password;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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




    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}