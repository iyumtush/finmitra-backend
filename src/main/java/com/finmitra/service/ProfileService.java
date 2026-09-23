package com.finmitra.service;

import com.finmitra.dto.UserProfileDto;

public interface ProfileService {
    UserProfileDto getUserProfile(String userEmail);
    UserProfileDto updateUserProfile(String userEmail, UserProfileDto profileDto);
}
