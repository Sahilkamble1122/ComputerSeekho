package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff_master")
public class StaffMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer staffId;
    
    @Column(name = "staff_name")
    private String staffName;
    
    @Column(name = "photo_url")
    private String photoUrl;
    
    @Column(name = "staff_mobile")
    private Long staffMobile;
    
    @Column(name = "staff_email")
    private String staffEmail;
    
    @Column(name = "staff_username")
    private String staffUsername;
    
    @Column(name = "staff_password")
    private String staffPassword;
    
    @Column(name = "staff_role")
    private String staffRole; // Teaching/Non-teaching/House keeping
    
    @Column(name = "staff_address")
    private String staffAddress;

    @Column(name = "staff_gender")
    private String staffGender;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public StaffMaster() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Parameterized constructor
    public StaffMaster(String staffName, String photoUrl, Long staffMobile,
                      String staffEmail, String staffUsername, String staffPassword,
                      String staffRole) {
        this.staffName = staffName;
        this.photoUrl = photoUrl;
        this.staffMobile = staffMobile;
        this.staffEmail = staffEmail;
        this.staffUsername = staffUsername;
        this.staffPassword = staffPassword;
        this.staffRole = staffRole;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getStaffId() {
        return staffId;
    }
    
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    
    public String getStaffName() {
        return staffName;
    }
    
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    public String getPhotoUrl() {
        return photoUrl;
    }
    
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    
    public Long getStaffMobile() {
        return staffMobile;
    }
    
    public void setStaffMobile(Long staffMobile) {
        this.staffMobile = staffMobile;
    }
    
    public String getStaffEmail() {
        return staffEmail;
    }
    
    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }
    
    public String getStaffUsername() {
        return staffUsername;
    }
    
    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }
    
    public String getStaffPassword() {
        return staffPassword;
    }
    
    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }
    
    public String getStaffRole() {
        return staffRole;
    }
    
    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }
    
    public String getStaffAddress() {
        return staffAddress;
    }
    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }
    public String getStaffGender() {
        return staffGender;
    }
    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    @Override
    public String toString() {
        return "StaffMaster{" +
                "staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", staffMobile=" + staffMobile +
                ", staffEmail='" + staffEmail + '\'' +
                ", staffUsername='" + staffUsername + '\'' +
                ", staffPassword='" + staffPassword + '\'' +
                ", staffRole='" + staffRole + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 