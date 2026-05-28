package com.matchify.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "personal_info")
public class PersonalInfo {

    @Id
    private int uID;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "u_ID", foreignKey = @ForeignKey(name = "fk_personalInfo_login"))
    @JsonBackReference
    private Login login;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(nullable = false, length = 50)
    private String religion;

    @Column(nullable = false)
    private String caste;

    @Column(name = "marital_status", length = 20)
    private String maritalStatus;

    @Column(name = "mobileNo", nullable = false, unique = true)
    private double mobileNo;

    public PersonalInfo() {}

    public int getU_ID() {
        return uID;
    }

    public void setU_ID(int u_ID) {
        this.uID = u_ID;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public double getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }
}
