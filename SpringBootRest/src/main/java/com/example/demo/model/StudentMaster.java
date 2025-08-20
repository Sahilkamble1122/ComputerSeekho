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
@Table(name = "student_master")
public class StudentMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;
    
    @Column(name = "student_name")
    private String studentName;
    
    @Column(name = "student_address")
    private String studentAddress;
    
    @Column(name = "student_gender")
    private String studentGender;
    
    @Column(name = "photo_url")
    private String photoUrl;
    
    @Column(name = "student_dob")
    private LocalDate studentDob;
    
    @Column(name = "student_qualification")
    private String studentQualification;
    
    @Column(name = "student_mobile")
    private Long studentMobile;
    
    @Column(name = "student_email")
    private String studentEmail;
    
    @Column(name = "batch_id")
    private Integer batchId;
    
    @Column(name = "course_id")
    private Integer courseId;
    
    @Column(name = "student_password")
    private String studentPassword;
    
    @Column(name = "student_username")
    private String studentUsername;
    
    @Column(name = "is_placed")
    private Boolean isPlaced;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    @Column(name = "pending_fees")
    private Double pendingFees;
    
    @Column(name = "course_fee")
    private Double courseFee;
    
    // Default constructor
    public StudentMaster() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.isPlaced = false; // Default value for new students
    }
    
    // Parameterized constructor
    public StudentMaster(String studentName, String studentAddress, String studentGender,
                        String photoUrl, LocalDate studentDob, String studentQualification,
                        Long studentMobile, String studentEmail, Integer batchId, Integer courseId,
                        String studentPassword, String studentUsername) {
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentGender = studentGender;
        this.photoUrl = photoUrl;
        this.studentDob = studentDob;
        this.studentQualification = studentQualification;
        this.studentMobile = studentMobile;
        this.studentEmail = studentEmail;
        this.batchId = batchId;
        this.courseId = courseId;
        this.studentPassword = studentPassword;
        this.studentUsername = studentUsername;
        this.isPlaced = false; // Default value for new students
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getStudentAddress() {
        return studentAddress;
    }
    
    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }
    
    public String getStudentGender() {
        return studentGender;
    }
    
    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }
    
    public String getPhotoUrl() {
        return photoUrl;
    }
    
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    
    public LocalDate getStudentDob() {
        return studentDob;
    }
    
    public void setStudentDob(LocalDate studentDob) {
        this.studentDob = studentDob;
    }
    
    public String getStudentQualification() {
        return studentQualification;
    }
    
    public void setStudentQualification(String studentQualification) {
        this.studentQualification = studentQualification;
    }
    
    public Long getStudentMobile() {
        return studentMobile;
    }
    
    public void setStudentMobile(Long studentMobile) {
        this.studentMobile = studentMobile;
    }
    
    public String getStudentEmail() {
        return studentEmail;
    }
    
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
    
    public Integer getBatchId() {
        return batchId;
    }
    
    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }
    
    public Integer getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    public String getStudentPassword() {
        return studentPassword;
    }
    
    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
    
    public String getStudentUsername() {
        return studentUsername;
    }
    
    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
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
    
    public Double getPendingFees() {
        return pendingFees;
    }
    public void setPendingFees(Double pendingFees) {
        this.pendingFees = pendingFees;
    }
    
    public Double getCourseFee() {
        return courseFee;
    }
    
    public void setCourseFee(Double courseFee) {
        this.courseFee = courseFee;
    }
    
    @Override
    public String toString() {
        return "StudentMaster{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", studentGender='" + studentGender + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", studentDob=" + studentDob +
                ", studentQualification='" + studentQualification + '\'' +
                ", studentMobile=" + studentMobile +
                ", studentEmail='" + studentEmail + '\'' +
                ", batchId=" + batchId +
                ", courseId=" + courseId +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentUsername='" + studentUsername + '\'' +
                ", isPlaced=" + isPlaced +
                ", pendingFees=" + pendingFees +
                ", courseFee=" + courseFee +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 