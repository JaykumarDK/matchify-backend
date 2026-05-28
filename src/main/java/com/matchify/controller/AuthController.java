package com.matchify.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matchify.dto.LoginRequest;
import com.matchify.entity.Login;
import com.matchify.repository.LoginRepository;
import com.matchify.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginRepository loginRepo;
    private final JwtUtil jwtUtil;

    public AuthController(LoginRepository loginRepo, JwtUtil jwtUtil) {
        this.loginRepo = loginRepo;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        Login user = loginRepo.findByEmail(req.getEmail());

        if(user == null || !user.getPassword().equals(req.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error","Invalid credentials"));
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "userId", user.getU_ID()
        ));
    }
}