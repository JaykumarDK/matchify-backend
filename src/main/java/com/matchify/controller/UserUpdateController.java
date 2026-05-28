package com.matchify.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.matchify.dto.UpdateProfileRequest;
import com.matchify.entity.ExtraUserInfo;
import com.matchify.entity.Login;
import com.matchify.repository.LoginRepository;
import com.matchify.service.UserUpdateService;

@RestController
@RequestMapping("/api/users")
public class UserUpdateController {

    private final UserUpdateService userUpdateService;
    private LoginRepository loginRepository;

    

public UserUpdateController(UserUpdateService userUpdateService, LoginRepository loginRepository) {
		super();
		this.userUpdateService = userUpdateService;
		this.loginRepository = loginRepository;
	}

	//    @PutMapping("/{userId}/profile")
//    public ResponseEntity<?> updateProfile(
//            @PathVariable int userId,
//            @ModelAttribute UserRegisterRequest request) {
//
//        userUpdateService.updateUserProfile(userId, request);
//
//        return ResponseEntity.ok(
//                java.util.Map.of("message", "Profile updated successfully"));
//    }
//    @PutMapping(value="/{userId}/profile")
//public ResponseEntity<?> updateProfile(
//        @PathVariable int userId,
//        @ModelAttribute UpdateProfileRequest req) throws IOException {
//
//    userUpdateService.updateUserProfile(userId, req);
//    return ResponseEntity.ok("Profile Updated");
//}
    @PutMapping("/{userId}/profile")
    public ResponseEntity<?> updateProfile(
            @PathVariable int userId,
            @RequestBody UpdateProfileRequest req) throws IOException {

        userUpdateService.updateUserProfile(userId, req);
        return ResponseEntity.ok("Profile Updated");
    }
    
    @PutMapping(value="/{userId}/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(
            @PathVariable int userId,
            @RequestParam("image") MultipartFile image) throws IOException {

        Login login = loginRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ExtraUserInfo extra = login.getExtraUserInfo();

        if(image != null && !image.isEmpty()){
            extra.setImage(image.getBytes());
        }

        loginRepository.save(login);

        return ResponseEntity.ok("Image Uploaded Successfully");
    }
}
