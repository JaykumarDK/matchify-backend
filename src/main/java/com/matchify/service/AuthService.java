package com.matchify.service;

import org.springframework.stereotype.Service;

import com.matchify.entity.Login;
import com.matchify.repository.LoginRepository;

@Service
public class AuthService {

    private final LoginRepository loginRepository;

    public AuthService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Login authenticate(String email, String password) {
        return loginRepository.findByEmailAndPassword(email, password);
    }
}
