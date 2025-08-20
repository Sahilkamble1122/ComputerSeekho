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
@Table(name = "enquiry")
public class Enquiry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enquiry_id")
    private Integer enquiryId;
    
    @Column(name = "enquirer_name")
    private String enquirerName;
    
    @Column(name = "enquirer_address")
    private String enquirerAddress;
    
    @Column(name = "enquirer_mobile")
    private Long enquirerMobile;
    
    @Column(name = "enquirer_alternate_mobile")
    private Long enquirerAlternateMobile;
    
    @Column(name = "enquirer_email_id")
    private String enquirerEmailId;
    
    @Column(name = "enquiry_date")
    private LocalDate enquiryDate;
    
    @Column(name = "enquirer_query")
    private String enquirerQuery;
    
    @Column(name = "closure_reason_id")
    private Integer closureReasonId;
    
    @Column(name = "closure_reason")
    private String closureReason;
    
    @Column(name = "enquiry_processed_flag")
    private Boolean enquiryProcessedFlag;
    
    @Column(name = "course_id")
    private Integer courseId;
    
    @Column(name = "assigned_staff_id")
    private Integer assignedStaffId;
    
    @Column(name = "student_name")
    private String studentName;
    
    @Column(name = "enquiry_counter")
    private Integer enquiryCounter;
    
    @Column(name = "follow_up_date")
    private LocalDate followUpDate;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public Enquiry() {
        this.enquiryDate = LocalDate.now();
        this.enquiryCounter = 0; // Default counter value is now 0
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.enquiryProcessedFlag = false;
    }
    
    // Parameterized constructor
    public Enquiry(String enquirerName, String enquirerAddress, Long enquirerMobile,
                   Long enquirerAlternateMobile, String enquirerEmailId, LocalDate enquiryDate,
                   String enquirerQuery, Integer closureReasonId, String closureReason,
                   Boolean enquiryProcessedFlag, Integer courseId, Integer assignedStaffId, 
                   String studentName, Integer enquiryCounter, LocalDate followUpDate) {
        this.enquirerName = enquirerName;
        this.enquirerAddress = enquirerAddress;
        this.enquirerMobile = enquirerMobile;
        this.enquirerAlternateMobile = enquirerAlternateMobile;
        this.enquirerEmailId = enquirerEmailId;
        this.enquiryDate = enquiryDate;
        this.enquirerQuery = enquirerQuery;
        this.closureReasonId = closureReasonId;
        this.closureReason = closureReason;
        this.enquiryProcessedFlag = enquiryProcessedFlag;
        this.courseId = courseId;
        this.assignedStaffId = assignedStaffId;
        this.studentName = studentName;
        this.enquiryCounter = enquiryCounter != null ? enquiryCounter : 1;
        this.followUpDate = followUpDate;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getEnquiryId() {
        return enquiryId;
    }
    
    public void setEnquiryId(Integer enquiryId) {
        this.enquiryId = enquiryId;
    }
    
    public String getEnquirerName() {
        return enquirerName;
    }
    
    public void setEnquirerName(String enquirerName) {
        this.enquirerName = enquirerName;
    }
    
    public String getEnquirerAddress() {
        return enquirerAddress;
    }
    
    public void setEnquirerAddress(String enquirerAddress) {
        this.enquirerAddress = enquirerAddress;
    }
    
    public Long getEnquirerMobile() {
        return enquirerMobile;
    }
    
    public void setEnquirerMobile(Long enquirerMobile) {
        this.enquirerMobile = enquirerMobile;
    }
    
    public Long getEnquirerAlternateMobile() {
        return enquirerAlternateMobile;
    }
    
    public void setEnquirerAlternateMobile(Long enquirerAlternateMobile) {
        this.enquirerAlternateMobile = enquirerAlternateMobile;
    }
    
    public String getEnquirerEmailId() {
        return enquirerEmailId;
    }
    
    public void setEnquirerEmailId(String enquirerEmailId) {
        this.enquirerEmailId = enquirerEmailId;
    }
    
    public LocalDate getEnquiryDate() {
        return enquiryDate;
    }
    
    public void setEnquiryDate(LocalDate enquiryDate) {
        this.enquiryDate = enquiryDate;
    }
    
    public String getEnquirerQuery() {
        return enquirerQuery;
    }
    
    public void setEnquirerQuery(String enquirerQuery) {
        this.enquirerQuery = enquirerQuery;
    }
    
    public Integer getClosureReasonId() {
        return closureReasonId;
    }
    
    public void setClosureReasonId(Integer closureReasonId) {
        this.closureReasonId = closureReasonId;
    }
    
    public String getClosureReason() {
        return closureReason;
    }
    
    public void setClosureReason(String closureReason) {
        this.closureReason = closureReason;
    }
    
    public Boolean getEnquiryProcessedFlag() {
        return enquiryProcessedFlag;
    }
    
    public void setEnquiryProcessedFlag(Boolean enquiryProcessedFlag) {
        this.enquiryProcessedFlag = enquiryProcessedFlag;
    }
    
    public Integer getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    public Integer getAssignedStaffId() {
        return assignedStaffId;
    }
    
    public void setAssignedStaffId(Integer assignedStaffId) {
        this.assignedStaffId = assignedStaffId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public Integer getEnquiryCounter() {
        return enquiryCounter;
    }
    
    public void setEnquiryCounter(Integer enquiryCounter) {
        this.enquiryCounter = enquiryCounter;
    }
    
    public LocalDate getFollowUpDate() {
        return followUpDate;
    }
    
    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate = followUpDate;
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
        return "Enquiry{" +
                "enquiryId=" + enquiryId +
                ", enquirerName='" + enquirerName + '\'' +
                ", enquirerAddress='" + enquirerAddress + '\'' +
                ", enquirerMobile=" + enquirerMobile +
                ", enquirerAlternateMobile=" + enquirerAlternateMobile +
                ", enquirerEmailId='" + enquirerEmailId + '\'' +
                ", enquiryDate=" + enquiryDate +
                ", enquirerQuery='" + enquirerQuery + '\'' +
                ", closureReasonId=" + closureReasonId +
                ", closureReason='" + closureReason + '\'' +
                ", enquiryProcessedFlag=" + enquiryProcessedFlag +
                ", courseId=" + courseId +
                ", assignedStaffId=" + assignedStaffId +
                ", studentName='" + studentName + '\'' +
                ", enquiryCounter=" + enquiryCounter +
                ", followUpDate=" + followUpDate +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 