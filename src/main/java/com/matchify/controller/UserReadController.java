package com.matchify.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.matchify.entity.Education;
import com.matchify.entity.ExtraUserInfo;
import com.matchify.entity.FamilyDetails;
import com.matchify.entity.Login;
import com.matchify.entity.PersonalInfo;
import com.matchify.service.UserReadService;

@RestController
@RequestMapping("/api/users")
public class UserReadController {

    private final UserReadService userReadService;

    public UserReadController(UserReadService userReadService) {
        this.userReadService = userReadService;
    }

    // ---------- LOGIN ----------
    @GetMapping("/{userId}/login")
    public ResponseEntity<Login> getLogin(@PathVariable int userId) {
        Optional<Login> login = userReadService.getLogin(userId);
        return login.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // ---------- PERSONAL INFO ----------
    @GetMapping("/{userId}/personal-info")
    public ResponseEntity<PersonalInfo> getPersonalInfo(@PathVariable int userId) {
        return userReadService.getPersonalInfo(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- EXTRA USER INFO ----------
    @GetMapping("/{userId}/extra-info")
    public ResponseEntity<ExtraUserInfo> getExtraUserInfo(@PathVariable int userId) {
        return userReadService.getExtraUserInfo(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- EDUCATION ----------
    @GetMapping("/{userId}/education")
    public ResponseEntity<Education> getEducation(@PathVariable int userId) {
        return userReadService.getEducation(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- FAMILY DETAILS ----------
    @GetMapping("/{userId}/family-details")
    public ResponseEntity<FamilyDetails> getFamilyDetails(@PathVariable int userId) {
        return userReadService.getFamilyDetails(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getFullProfile(@PathVariable int userId) {
        return ResponseEntity.ok(userReadService.getFullProfile(userId));
    }
}
