package com.finmitra.controller;

import com.finmitra.dto.UserProfileDto;
import com.finmitra.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<UserProfileDto> getUserProfile(Authentication authentication) {
        String userEmail = authentication.getName();
        UserProfileDto profile = profileService.getUserProfile(userEmail);
        return ResponseEntity.ok(profile);
    }

    @PutMapping
    public ResponseEntity<UserProfileDto> updateUserProfile(
            @RequestBody UserProfileDto profileDto,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        UserProfileDto updatedProfile = profileService.updateUserProfile(userEmail, profileDto);
        return ResponseEntity.ok(updatedProfile);
    }
}
