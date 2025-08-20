package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "closure_reason_master")
public class ClosureReasonMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closure_reason_id")
    private Integer closureReasonId;
    
    @Column(name = "closure_reason_name")
    private String closureReasonName;
    
    @Column(name = "closure_reason_desc")
    private String closureReasonDesc;
    
    @Column(name = "closure_reason_is_active")
    private Boolean closureReasonIsActive;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public ClosureReasonMaster() {
        this.closureReasonIsActive = true;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Parameterized constructor
    public ClosureReasonMaster(String closureReasonName, String closureReasonDesc) {
        this.closureReasonName = closureReasonName;
        this.closureReasonDesc = closureReasonDesc;
        this.closureReasonIsActive = true;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Full parameterized constructor
    public ClosureReasonMaster(String closureReasonName, String closureReasonDesc, Boolean closureReasonIsActive) {
        this.closureReasonName = closureReasonName;
        this.closureReasonDesc = closureReasonDesc;
        this.closureReasonIsActive = closureReasonIsActive;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getClosureReasonId() {
        return closureReasonId;
    }
    
    public void setClosureReasonId(Integer closureReasonId) {
        this.closureReasonId = closureReasonId;
    }
    
    public String getClosureReasonName() {
        return closureReasonName;
    }
    
    public void setClosureReasonName(String closureReasonName) {
        this.closureReasonName = closureReasonName;
    }
    
    public String getClosureReasonDesc() {
        return closureReasonDesc;
    }
    
    public void setClosureReasonDesc(String closureReasonDesc) {
        this.closureReasonDesc = closureReasonDesc;
    }
    
    public Boolean getClosureReasonIsActive() {
        return closureReasonIsActive;
    }
    
    public void setClosureReasonIsActive(Boolean closureReasonIsActive) {
        this.closureReasonIsActive = closureReasonIsActive;
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
        return "ClosureReasonMaster{" +
                "closureReasonId=" + closureReasonId +
                ", closureReasonName='" + closureReasonName + '\'' +
                ", closureReasonDesc='" + closureReasonDesc + '\'' +
                ", closureReasonIsActive=" + closureReasonIsActive +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 