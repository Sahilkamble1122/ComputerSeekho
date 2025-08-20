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
@Table(name = "placement")
public class Placement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placement_id")
    private Integer placementId;
    
    @Column(name = "student_id")
    private Integer studentId;
    
    @Column(name = "course_id")
    private Integer courseId;
    
    @Column(name = "batch_id")
    private Integer batchId;
    
    @Column(name = "company_name")
    private String companyName;
    
    @Column(name = "designation")
    private String designation;
    
    @Column(name = "placement_date")
    private LocalDate placementDate;
    
    @Column(name = "is_placed")
    private Boolean isPlaced;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public Placement() {
        this.placementDate = LocalDate.now();
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Parameterized constructor
    public Placement(Integer studentId, Integer courseId, Integer batchId,
                    String companyName, String designation, LocalDate placementDate,
                    Boolean isPlaced) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.batchId = batchId;
        this.companyName = companyName;
        this.designation = designation;
        this.placementDate = placementDate;
        this.isPlaced = isPlaced;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getPlacementId() {
        return placementId;
    }
    
    public void setPlacementId(Integer placementId) {
        this.placementId = placementId;
    }
    
    public Integer getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    public Integer getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    public Integer getBatchId() {
        return batchId;
    }
    
    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public String getDesignation() {
        return designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public LocalDate getPlacementDate() {
        return placementDate;
    }
    
    public void setPlacementDate(LocalDate placementDate) {
        this.placementDate = placementDate;
    }
    
    public Boolean getIsPlaced() {
        return isPlaced;
    }
    
    public void setIsPlaced(Boolean isPlaced) {
        this.isPlaced = isPlaced;
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
        return "Placement{" +
                "placementId=" + placementId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", batchId=" + batchId +
                ", companyName='" + companyName + '\'' +
                ", designation='" + designation + '\'' +
                ", placementDate=" + placementDate +
                ", isPlaced=" + isPlaced +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 