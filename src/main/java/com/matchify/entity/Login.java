package com.matchify.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uID;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    @JsonManagedReference
    private PersonalInfo personalInfo;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ExtraUserInfo extraUserInfo;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Education education;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    @JsonManagedReference
    private FamilyDetails familyDetails;

    public Login() {}

    public int getU_ID() {
        return uID;
    }

    public void setU_ID(int u_ID) {
        this.uID = u_ID;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public ExtraUserInfo getExtraUserInfo() {
        return extraUserInfo;
    }

    public void setExtraUserInfo(ExtraUserInfo extraUserInfo) {
        this.extraUserInfo = extraUserInfo;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public FamilyDetails getFamilyDetails() {
        return familyDetails;
    }

    public void setFamilyDetails(FamilyDetails familyDetails) {
        this.familyDetails = familyDetails;
    }
}
