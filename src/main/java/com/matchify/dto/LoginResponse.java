package com.matchify.dto;

public class LoginResponse {
    public int userId;
    public String email;

    public LoginResponse(int userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
