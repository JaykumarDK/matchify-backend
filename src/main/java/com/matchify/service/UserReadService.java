package com.matchify.service;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.matchify.entity.Education;
import com.matchify.entity.ExtraUserInfo;
import com.matchify.entity.FamilyDetails;
import com.matchify.entity.Login;
import com.matchify.entity.PersonalInfo;
import com.matchify.repository.EducationRepository;
import com.matchify.repository.ExtraUserInfoRepository;
import com.matchify.repository.FamilyDetailsRepository;
import com.matchify.repository.LoginRepository;
import com.matchify.repository.PersonalInfoRepository;

@Service
public class UserReadService {

    private final LoginRepository loginRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final ExtraUserInfoRepository extraUserInfoRepository;
    private final EducationRepository educationRepository;
    private final FamilyDetailsRepository familyDetailsRepository;

    public UserReadService(
            LoginRepository loginRepository,
            PersonalInfoRepository personalInfoRepository,
            ExtraUserInfoRepository extraUserInfoRepository,
            EducationRepository educationRepository,
            FamilyDetailsRepository familyDetailsRepository) {

        this.loginRepository = loginRepository;
        this.personalInfoRepository = personalInfoRepository;
        this.extraUserInfoRepository = extraUserInfoRepository;
        this.educationRepository = educationRepository;
        this.familyDetailsRepository = familyDetailsRepository;
    }

    // -------- READ METHODS --------

    public Optional<Login> getLogin(int userId) {
        return loginRepository.findById(userId);
    }

    public Optional<PersonalInfo> getPersonalInfo(int userId) {
        return personalInfoRepository.findById(userId);
    }

    public Optional<ExtraUserInfo> getExtraUserInfo(int userId) {
        return extraUserInfoRepository.findById(userId);
    }

    public Optional<Education> getEducation(int userId) {
        return educationRepository.findById(userId);
    }

    public Optional<FamilyDetails> getFamilyDetails(int userId) {
        return familyDetailsRepository.findById(userId);
    }

	public @Nullable Object getFullProfile(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
