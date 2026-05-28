package com.matchify.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matchify.dto.UserRegisterRequest;
import com.matchify.entity.*;
import com.matchify.repository.*;

@Service
public class UserWriteService {

    private final LoginRepository loginRepository;

    public UserWriteService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Transactional
    public int registerUser(UserRegisterRequest req) {

        // ---------- LOGIN ----------
        Login login = new Login();
        login.setEmail(req.email);
        login.setPassword(req.password);

        // ---------- PERSONAL INFO ----------
        PersonalInfo personal = new PersonalInfo();
        personal.setName(req.name);
        personal.setDob(req.dob);
        personal.setGender(req.gender);
        personal.setReligion(req.religion);
        personal.setCaste(req.caste);
        personal.setMaritalStatus(req.maritalStatus);
        personal.setMobileNo(req.mobileNo);
        personal.setLogin(login);

        // ---------- EXTRA USER INFO ----------
        ExtraUserInfo extra = new ExtraUserInfo();
        extra.setAge(req.age);
        extra.setAddress(req.address);
        extra.setHeight(req.height);
        extra.setWeight(req.weight);
        extra.setMotherTongue(req.motherTongue);
        extra.setHobbies(req.hobbies);
        extra.setImage(req.image);
        extra.setLogin(login);

        // ---------- EDUCATION ----------
        Education education = new Education();
        education.setHigherEducation(req.higherEducation);
        education.setOccupation(req.occupation);
        education.setAnnualIncome(req.annualIncome);
        education.setJob_location(req.jobLocation);
        education.setLogin(login);

        // ---------- FAMILY DETAILS ----------
        FamilyDetails family = new FamilyDetails();
        family.setFatherOccupation(req.fatherOccupation);
        family.setMotherOccupation(req.motherOccupation);
        family.setSiblingCount(req.siblingCount);
        family.setFamilyType(req.familyType);
        family.setFamilyStatus(req.familyStatus);
        family.setLogin(login);

        // ---------- LINK ENTITIES ----------
        login.setPersonalInfo(personal);
        login.setExtraUserInfo(extra);
        login.setEducation(education);
        login.setFamilyDetails(family);

        // ---------- SAVE ----------
        Login savedUser = loginRepository.save(login);

        return savedUser.getU_ID();
    }
}
