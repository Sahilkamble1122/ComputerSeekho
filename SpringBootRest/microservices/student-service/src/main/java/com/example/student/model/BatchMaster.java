package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "batch_master")
public class BatchMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_id")
    private Integer batchId;
    
    @Column(name = "batch_name")
    private String batchName;
    
    @Column(name = "batch_start_time")
    private LocalTime batchStartTime;
    
    @Column(name = "batch_end_time")
    private LocalTime batchEndTime;
    
    @Column(name = "course_id")
    private Integer courseId;
    
    @Column(name = "presentation_date")
    private LocalDateTime presentationDate;
    
    @Column(name = "course_fees")
    private Integer courseFees;
    
    @Column(name = "course_fees_from")
    private LocalDate courseFeesFrom;
    
    @Column(name = "course_fees_to")
    private LocalDate courseFeesTo;
    
    @Column(name = "batch_is_active")
    private Boolean batchIsActive;
    
    @Column(name = "staff_id")
    private Integer staffId;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public BatchMaster() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Parameterized constructor
    public BatchMaster(String batchName, LocalTime batchStartTime, LocalTime batchEndTime,
                      Integer courseId, LocalDateTime presentationDate, Integer courseFees,
                      LocalDate courseFeesFrom, LocalDate courseFeesTo, Boolean batchIsActive,
                      Integer staffId) {
        this.batchName = batchName;
        this.batchStartTime = batchStartTime;
        this.batchEndTime = batchEndTime;
        this.courseId = courseId;
        this.presentationDate = presentationDate;
        this.courseFees = courseFees;
        this.courseFeesFrom = courseFeesFrom;
        this.courseFeesTo = courseFeesTo;
        this.batchIsActive = batchIsActive;
        this.staffId = staffId;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getBatchId() {
        return batchId;
    }
    
    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }
    
    public String getBatchName() {
        return batchName;
    }
    
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    
    public LocalTime getBatchStartTime() {
        return batchStartTime;
    }
    
    public void setBatchStartTime(LocalTime batchStartTime) {
        this.batchStartTime = batchStartTime;
    }
    
    public LocalTime getBatchEndTime() {
        return batchEndTime;
    }
    
    public void setBatchEndTime(LocalTime batchEndTime) {
        this.batchEndTime = batchEndTime;
    }
    
    public Integer getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    public LocalDateTime getPresentationDate() {
        return presentationDate;
    }
    
    public void setPresentationDate(LocalDateTime presentationDate) {
        this.presentationDate = presentationDate;
    }
    
    public Integer getCourseFees() {
        return courseFees;
    }
    
    public void setCourseFees(Integer courseFees) {
        this.courseFees = courseFees;
    }
    
    public LocalDate getCourseFeesFrom() {
        return courseFeesFrom;
    }
    
    public void setCourseFeesFrom(LocalDate courseFeesFrom) {
        this.courseFeesFrom = courseFeesFrom;
    }
    
    public LocalDate getCourseFeesTo() {
        return courseFeesTo;
    }
    
    public void setCourseFeesTo(LocalDate courseFeesTo) {
        this.courseFeesTo = courseFeesTo;
    }
    
    public Boolean getBatchIsActive() {
        return batchIsActive;
    }
    
    public void setBatchIsActive(Boolean batchIsActive) {
        this.batchIsActive = batchIsActive;
    }
    
    public Integer getStaffId() {
        return staffId;
    }
    
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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
        return "BatchMaster{" +
                "batchId=" + batchId +
                ", batchName='" + batchName + '\'' +
                ", batchStartTime=" + batchStartTime +
                ", batchEndTime=" + batchEndTime +
                ", courseId=" + courseId +
                ", presentationDate=" + presentationDate +
                ", courseFees=" + courseFees +
                ", courseFeesFrom=" + courseFeesFrom +
                ", courseFeesTo=" + courseFeesTo +
                ", batchIsActive=" + batchIsActive +
                ", staffId=" + staffId +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 