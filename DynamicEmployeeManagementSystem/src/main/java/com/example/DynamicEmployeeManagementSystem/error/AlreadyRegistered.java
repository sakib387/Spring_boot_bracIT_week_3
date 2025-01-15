package com.example.DynamicEmployeeManagementSystem.error;

public class AlreadyRegistered extends RuntimeException {

    public AlreadyRegistered(String message) {
        super(message);
    }
}
