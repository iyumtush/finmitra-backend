package com.finmitra.service.impl;

import com.finmitra.dto.UserProfileDto;
import com.finmitra.entity.User;
import com.finmitra.exception.APIException;
import com.finmitra.repository.UserRepository;
import com.finmitra.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserProfileDto getUserProfile(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "User not found with email: " + userEmail));

        return mapToDto(user);
    }

    @Override
    @Transactional
    public UserProfileDto updateUserProfile(String userEmail, UserProfileDto profileDto) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "User not found with email: " + userEmail));

        if (profileDto.getFullName() != null && !profileDto.getFullName().trim().isEmpty()) {
            user.setName(profileDto.getFullName().trim());
        }
        user.setAge(profileDto.getAge());
        user.setOccupation(profileDto.getOccupation());
        user.setCity(profileDto.getCity());
        user.setMonthlyIncome(profileDto.getMonthlyIncome());
        user.setMonthlyFixedExpenses(profileDto.getMonthlyFixedExpenses());
        user.setMonthlyEmis(profileDto.getMonthlyEmis());
        user.setDependents(profileDto.getDependents() != null ? profileDto.getDependents() : 0);
        user.setRiskTolerance(profileDto.getRiskTolerance() != null ? profileDto.getRiskTolerance() : "Moderate");
        user.setInvestmentHorizon(profileDto.getInvestmentHorizon());
        user.setPrimaryGoal(profileDto.getPrimaryGoal());
        user.setTargetGoalAmount(profileDto.getTargetGoalAmount());
        user.setTargetRetirementAge(profileDto.getTargetRetirementAge() != null ? profileDto.getTargetRetirementAge() : 60);
        user.setEmergencyFundMonths(profileDto.getEmergencyFundMonths() != null ? profileDto.getEmergencyFundMonths() : 6);
        user.setIsProfileCompleted(true);

        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    private UserProfileDto mapToDto(User user) {
        UserProfileDto dto = new UserProfileDto();
        dto.setFullName(user.getName());
        dto.setAge(user.getAge());
        dto.setOccupation(user.getOccupation());
        dto.setCity(user.getCity());
        dto.setMonthlyIncome(user.getMonthlyIncome());
        dto.setMonthlyFixedExpenses(user.getMonthlyFixedExpenses());
        dto.setMonthlyEmis(user.getMonthlyEmis());
        dto.setDependents(user.getDependents());
        dto.setRiskTolerance(user.getRiskTolerance());
        dto.setInvestmentHorizon(user.getInvestmentHorizon());
        dto.setPrimaryGoal(user.getPrimaryGoal());
        dto.setTargetGoalAmount(user.getTargetGoalAmount());
        dto.setTargetRetirementAge(user.getTargetRetirementAge());
        dto.setEmergencyFundMonths(user.getEmergencyFundMonths());
        dto.setIsProfileCompleted(user.getIsProfileCompleted() != null ? user.getIsProfileCompleted() : false);
        return dto;
    }
}
