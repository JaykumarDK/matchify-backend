package com.matchify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "family_details")
public class FamilyDetails {

    @Id
    private int uID;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "u_ID", foreignKey = @ForeignKey(name = "fk_family_details_login"))
    @JsonBackReference
    private Login login;

    @Column(name = "father_occupation", length = 100, nullable = false)
    private String fatherOccupation;

    @Column(name = "mother_occupation", length = 100, nullable = false)
    private String motherOccupation;

    @Column(name = "family_type", length = 50, nullable = false)
    private String familyType;

    @Column(name = "family_status", length = 50, nullable = false)
    private String familyStatus;

    @Column(name = "sibling_count")
    private int siblingCount;

    public FamilyDetails() {}

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

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getFamilyType() {
        return familyType;
    }

    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public int getSiblingCount() {
        return siblingCount;
    }

    public void setSiblingCount(int siblingCount) {
        this.siblingCount = siblingCount;
    }
}
