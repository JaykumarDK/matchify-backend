package com.matchify.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matchify.dto.UpdateProfileRequest;
import com.matchify.dto.UserRegisterRequest;
import com.matchify.entity.*;
import com.matchify.repository.*;

@Service
public class UserUpdateService {

    private final LoginRepository loginRepository;

    public UserUpdateService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

//    @Transactional
//    public void updateUserProfile(int userId, UpdateProfileRequest req) throws IOException {
//
//        Login login = loginRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // ---------- LOGIN ----------
//        login.setEmail(req.email);
//        //login.setPassword(req.password);
//
//        // ---------- PERSONAL INFO ----------
//        PersonalInfo personal = login.getPersonalInfo();
//        personal.setName(req.name);
//        personal.setDob(req.dob);
//        personal.setGender(req.gender);
//        personal.setReligion(req.religion);
//        personal.setCaste(req.caste);
//        personal.setMaritalStatus(req.maritalStatus);
//        personal.setMobileNo(req.mobileNo);
//
//        // ---------- EXTRA USER INFO ----------
//        ExtraUserInfo extra = login.getExtraUserInfo();
//        extra.setAge(req.age);
//        extra.setAddress(req.address);
//        extra.setHeight(req.height);
//        extra.setWeight(req.weight);
//        extra.setMotherTongue(req.motherTongue);
//        extra.setHobbies(req.hobbies);
//
//        // ---------- EDUCATION ----------
//        Education edu = login.getEducation();
//        edu.setHigherEducation(req.education);
//        edu.setOccupation(req.occupation);
//        edu.setAnnualIncome(req.annualIncome);
//        edu.setJob_location(req.jobLocation);
//
//        // ---------- FAMILY DETAILS ----------
//        FamilyDetails family = login.getFamilyDetails();
//        family.setFatherOccupation(req.fatherOccupation);
//        family.setMotherOccupation(req.motherOccupation);
//        family.setSiblingCount(req.siblingCount);
//        family.setFamilyType(req.familyType);
//        family.setFamilyStatus(req.familyStatus);
//         
//        if(req.getImage()!=null && !req.getImage().isEmpty()){
//            extra.setImage(req.getImage().getBytes());
//        }
//        // ---------- SAVE ----------
//        loginRepository.save(login); // cascades to all
//    }
////}
//@Service
//public class UserUpdateService {
//
//    private final LoginRepository loginRepository;
//
//    public UserUpdateService(LoginRepository loginRepository) {
//        this.loginRepository = loginRepository;
//    }

    @Transactional
    public void updateUserProfile(int userId, UpdateProfileRequest req) throws IOException {

        Login login = loginRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ---------- LOGIN ----------
        if(req.getEmail()!=null)
            login.setEmail(req.getEmail());

        // ---------- PERSONAL INFO ----------
        PersonalInfo personal = login.getPersonalInfo();

        if(req.getName()!=null)
            personal.setName(req.getName());

        if(req.getDob()!=null)
            personal.setDob(req.getDob());

        if(req.getGender()!=null)
            personal.setGender(req.getGender());

        if(req.getReligion()!=null)
            personal.setReligion(req.getReligion());

        if(req.getCaste()!=null)
            personal.setCaste(req.getCaste());

        if(req.getMaritalStatus()!=null)
            personal.setMaritalStatus(req.getMaritalStatus());

        if(req.getMobileNo()!=null)
            personal.setMobileNo(req.getMobileNo());

        // ---------- EXTRA INFO ----------
        ExtraUserInfo extra = login.getExtraUserInfo();

        if(req.getAge()!=null)
            extra.setAge(req.getAge());

        if(req.getAddress()!=null)
            extra.setAddress(req.getAddress());

        if(req.getHeight()!=null)
            extra.setHeight(req.getHeight());

        if(req.getWeight()!=null)
            extra.setWeight(req.getWeight());

        if(req.getMotherTongue()!=null)
            extra.setMotherTongue(req.getMotherTongue());

        if(req.getHobbies()!=null)
            extra.setHobbies(req.getHobbies());

        // ---------- EDUCATION ----------
        Education edu = login.getEducation();

        if(req.getEducation()!=null)
            edu.setHigherEducation(req.getEducation());

        if(req.getOccupation()!=null)
            edu.setOccupation(req.getOccupation());

        if(req.getAnnualIncome()!=null)
            edu.setAnnualIncome(req.getAnnualIncome());

        if(req.getJobLocation()!=null)
            edu.setJob_location(req.getJobLocation());

        // ---------- FAMILY ----------
        FamilyDetails family = login.getFamilyDetails();

        if(req.getFatherOccupation()!=null)
            family.setFatherOccupation(req.getFatherOccupation());

        if(req.getMotherOccupation()!=null)
            family.setMotherOccupation(req.getMotherOccupation());

        if(req.getFamilyType()!=null)
            family.setFamilyType(req.getFamilyType());

        if(req.getFamilyStatus()!=null)
            family.setFamilyStatus(req.getFamilyStatus());

//        family.setSiblingCount(req.getSiblingCount());

        loginRepository.save(login);
    }
}