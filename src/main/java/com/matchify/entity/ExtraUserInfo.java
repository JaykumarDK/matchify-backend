package com.matchify.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "extra_user_info")
public class ExtraUserInfo {

    @Id
    @Column(name = "u_ID")
    private int extraInfoId;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "u_ID", foreignKey = @ForeignKey(name = "fk_extra_info_login"))
    @JsonBackReference
    private Login login;

    @Column(name = "age")
    private Integer age;

    private String address;
    private String motherTongue;
    private Integer height;
    private Integer weight;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @ElementCollection
    @CollectionTable(
        name = "user_hobbies",
        joinColumns = @JoinColumn(name = "ExtraInfo_ID")
    )
    @Column(name = "hobby")
    private List<String> hobbies;

    public ExtraUserInfo() {}

    public int getExtraInfoId() {
        return extraInfoId;
    }

    public void setExtraInfoId(int extraInfoId) {
        this.extraInfoId = extraInfoId;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
