package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "followup")
public class Followup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followup_id")
    private Integer followupId;
    
    @Column(name = "enquiry_id")
    private Integer enquiryId;
    
    @Column(name = "staff_id")
    private Integer staffId;
    
    @Column(name = "followup_date")
    private LocalDate followupDate;
    
    @Column(name = "followup_msg")
    private String followupMsg;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public Followup() {
        this.followupDate = LocalDate.now();
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Parameterized constructor
    public Followup(Integer enquiryId, Integer staffId, LocalDate followupDate,
                   String followupMsg, Boolean isActive) {
        this.enquiryId = enquiryId;
        this.staffId = staffId;
        this.followupDate = followupDate;
        this.followupMsg = followupMsg;
        this.isActive = isActive;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getFollowupId() {
        return followupId;
    }
    
    public void setFollowupId(Integer followupId) {
        this.followupId = followupId;
    }
    
    public Integer getEnquiryId() {
        return enquiryId;
    }
    
    public void setEnquiryId(Integer enquiryId) {
        this.enquiryId = enquiryId;
    }
    
    public Integer getStaffId() {
        return staffId;
    }
    
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    
    public LocalDate getFollowupDate() {
        return followupDate;
    }
    
    public void setFollowupDate(LocalDate followupDate) {
        this.followupDate = followupDate;
    }
    
    public String getFollowupMsg() {
        return followupMsg;
    }
    
    public void setFollowupMsg(String followupMsg) {
        this.followupMsg = followupMsg;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
        return "Followup{" +
                "followupId=" + followupId +
                ", enquiryId=" + enquiryId +
                ", staffId=" + staffId +
                ", followupDate=" + followupDate +
                ", followupMsg='" + followupMsg + '\'' +
                ", isActive=" + isActive +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 