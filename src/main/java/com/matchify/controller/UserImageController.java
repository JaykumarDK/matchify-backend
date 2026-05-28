package com.matchify.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.matchify.service.UserImageService;

@RestController
@RequestMapping("/api/users")
public class UserImageController {
	private UserImageService imageService;

	public UserImageController(UserImageService imageService) {
		super();
		this.imageService = imageService;
	}
	@PostMapping("/{userId}/image")
	public ResponseEntity<?>uploadImage(
			@PathVariable int userId,@RequestParam("image")MultipartFile image){
		try {
			imageService.uploadProfileImage(userId, image);
			return ResponseEntity.ok(java.util.Map.of("message","Image Upload Successfully"));
			
		}catch(Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(java.util.Map.of("error",exception.getMessage()));
		}
		
	}
	
	 @GetMapping("/{userId}/image")
	    public ResponseEntity<byte[]> getImage(@PathVariable int userId) {

	        byte[] image = imageService.getProfileImage(userId);

	        if (image == null) {
	            return ResponseEntity.notFound().build();
	        }

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG); // change if PNG

	        return new ResponseEntity<>(image, headers, HttpStatus.OK);
	    }
	

    }
