package com.matchify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "education")
public class Education {

    @Id
    private int uID;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "u_ID", foreignKey = @ForeignKey(name = "fk_education_login"))
    @JsonBackReference
    private Login login;

    @Column(name = "higher_education", nullable = false, length = 100)
    private String highereducation;

    @Column(name = "annual_income")
    private Integer annualincome;

    @Column(name = "job_location", length = 100)
    private String job_location;

    @Column(name = "occupation", length = 100)
    private String occupation;

    public Education() {}

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

    public String getHigherEducation() {
        return highereducation;
    }

    public void setHigherEducation(String higherEducation) {
        this.highereducation = higherEducation;
    }

    public Integer getAnnualIncome() {
        return annualincome;
    }

    public void setAnnualIncome(Integer annualIncome) {
        this.annualincome = annualIncome;
    }
    public String getJob_location() {
        return job_location;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
