package com.example.backend.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public String login(String username, String password) {
        if ("juan".equals(username) && "1234".equals(password)) {
            return "fake-jwt-token";
        }
        throw new RuntimeException("Invalid credentials");
    }
}
