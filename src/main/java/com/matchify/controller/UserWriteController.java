package com.matchify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.matchify.dto.UserRegisterRequest;
import com.matchify.service.UserWriteService;

@RestController
@RequestMapping("/api/users")
public class UserWriteController {

    private final UserWriteService userWriteService;

    public UserWriteController(UserWriteService userWriteService) {
        this.userWriteService = userWriteService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest request) {

        int userId = userWriteService.registerUser(request);

        return ResponseEntity.ok(
                java.util.Map.of(
                        "message", "User registered successfully",
                        "userId", userId
                )
        );
    }
}
