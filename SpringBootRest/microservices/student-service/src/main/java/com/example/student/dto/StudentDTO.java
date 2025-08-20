package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class StudentDTO {
    
    // ===========================================
    // REQUEST DTOs
    // ===========================================
    
    public static class CreateStudentRequest {
        @NotBlank(message = "Student name is required")
        @Size(min = 2, max = 100, message = "Student name must be between 2 and 100 characters")
        private String studentName;
        
        @NotBlank(message = "Student address is required")
        @Size(max = 500, message = "Address must not exceed 500 characters")
        private String studentAddress;
        
        @NotBlank(message = "Student gender is required")
        @Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "Gender must be MALE, FEMALE, or OTHER")
        private String studentGender;
        
        private String photoUrl;
        
        @NotNull(message = "Date of birth is required")
        private LocalDate studentDob;
        
        @NotBlank(message = "Student qualification is required")
        @Size(max = 200, message = "Qualification must not exceed 200 characters")
        private String studentQualification;
        
        @NotNull(message = "Mobile number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
        private String studentMobile;
        
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        private String studentEmail;
        
        @NotNull(message = "Batch ID is required")
        private Integer batchId;
        
        @NotNull(message = "Course ID is required")
        private Integer courseId;
        
        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        private String studentPassword;
        
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
        private String studentUsername;
        
        // Constructors
        public CreateStudentRequest() {}
        
        public CreateStudentRequest(String studentName, String studentAddress, String studentGender,
                                 String photoUrl, LocalDate studentDob, String studentQualification,
                                 String studentMobile, String studentEmail, Integer batchId, Integer courseId,
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
        }
        
        // Getters and Setters
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        
        public String getStudentAddress() { return studentAddress; }
        public void setStudentAddress(String studentAddress) { this.studentAddress = studentAddress; }
        
        public String getStudentGender() { return studentGender; }
        public void setStudentGender(String studentGender) { this.studentGender = studentGender; }
        
        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        
        public LocalDate getStudentDob() { return studentDob; }
        public void setStudentDob(LocalDate studentDob) { this.studentDob = studentDob; }
        
        public String getStudentQualification() { return studentQualification; }
        public void setStudentQualification(String studentQualification) { this.studentQualification = studentQualification; }
        
        public String getStudentMobile() { return studentMobile; }
        public void setStudentMobile(String studentMobile) { this.studentMobile = studentMobile; }
        
        public String getStudentEmail() { return studentEmail; }
        public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
        
        public Integer getBatchId() { return batchId; }
        public void setBatchId(Integer batchId) { this.batchId = batchId; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public String getStudentPassword() { return studentPassword; }
        public void setStudentPassword(String studentPassword) { this.studentPassword = studentPassword; }
        
        public String getStudentUsername() { return studentUsername; }
        public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }
    }
    
    public static class UpdateStudentRequest {
        @Size(min = 2, max = 100, message = "Student name must be between 2 and 100 characters")
        private String studentName;
        
        @Size(max = 500, message = "Address must not exceed 500 characters")
        private String studentAddress;
        
        @Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "Gender must be MALE, FEMALE, or OTHER")
        private String studentGender;
        
        private String photoUrl;
        
        private LocalDate studentDob;
        
        @Size(max = 200, message = "Qualification must not exceed 200 characters")
        private String studentQualification;
        
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
        private String studentMobile;
        
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        private String studentEmail;
        
        private Integer batchId;
        private Integer courseId;
        
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        private String studentPassword;
        
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
        private String studentUsername;
        
        // Constructors
        public UpdateStudentRequest() {}
        
        // Getters and Setters
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        
        public String getStudentAddress() { return studentAddress; }
        public void setStudentAddress(String studentAddress) { this.studentAddress = studentAddress; }
        
        public String getStudentGender() { return studentGender; }
        public void setStudentGender(String studentGender) { this.studentGender = studentGender; }
        
        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        
        public LocalDate getStudentDob() { return studentDob; }
        public void setStudentDob(LocalDate studentDob) { this.studentDob = studentDob; }
        
        public String getStudentQualification() { return studentQualification; }
        public void setStudentQualification(String studentQualification) { this.studentQualification = studentQualification; }
        
        public String getStudentMobile() { return studentMobile; }
        public void setStudentMobile(String studentMobile) { this.studentMobile = studentMobile; }
        
        public String getStudentEmail() { return studentEmail; }
        public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
        
        public Integer getBatchId() { return batchId; }
        public void setBatchId(Integer batchId) { this.batchId = batchId; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public String getStudentPassword() { return studentPassword; }
        public void setStudentPassword(String studentPassword) { this.studentPassword = studentPassword; }
        
        public String getStudentUsername() { return studentUsername; }
        public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }
    }
    
    // ===========================================
    // RESPONSE DTOs
    // ===========================================
    
    public static class StudentResponse {
        private Integer studentId;
        private String studentName;
        private String studentAddress;
        private String studentGender;
        private String photoUrl;
        private LocalDate studentDob;
        private String studentQualification;
        private String studentMobile;
        private String studentEmail;
        private Integer batchId;
        private Integer courseId;
        private String studentUsername;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        
        // Additional fields for better response
        private String courseName;
        private String batchName;
        
        // Constructors
        public StudentResponse() {}
        
        public StudentResponse(Integer studentId, String studentName, String studentAddress, String studentGender,
                            String photoUrl, LocalDate studentDob, String studentQualification, String studentMobile,
                            String studentEmail, Integer batchId, Integer courseId, String studentUsername,
                            LocalDateTime createdDate, LocalDateTime updatedDate) {
            this.studentId = studentId;
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
            this.studentUsername = studentUsername;
            this.createdDate = createdDate;
            this.updatedDate = updatedDate;
        }
        
        // Getters and Setters
        public Integer getStudentId() { return studentId; }
        public void setStudentId(Integer studentId) { this.studentId = studentId; }
        
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        
        public String getStudentAddress() { return studentAddress; }
        public void setStudentAddress(String studentAddress) { this.studentAddress = studentAddress; }
        
        public String getStudentGender() { return studentGender; }
        public void setStudentGender(String studentGender) { this.studentGender = studentGender; }
        
        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        
        public LocalDate getStudentDob() { return studentDob; }
        public void setStudentDob(LocalDate studentDob) { this.studentDob = studentDob; }
        
        public String getStudentQualification() { return studentQualification; }
        public void setStudentQualification(String studentQualification) { this.studentQualification = studentQualification; }
        
        public String getStudentMobile() { return studentMobile; }
        public void setStudentMobile(String studentMobile) { this.studentMobile = studentMobile; }
        
        public String getStudentEmail() { return studentEmail; }
        public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
        
        public Integer getBatchId() { return batchId; }
        public void setBatchId(Integer batchId) { this.batchId = batchId; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public String getStudentUsername() { return studentUsername; }
        public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }
        
        public LocalDateTime getCreatedDate() { return createdDate; }
        public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
        
        public LocalDateTime getUpdatedDate() { return updatedDate; }
        public void setUpdatedDate(LocalDateTime updatedDate) { this.updatedDate = updatedDate; }
        
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        
        public String getBatchName() { return batchName; }
        public void setBatchName(String batchName) { this.batchName = batchName; }
    }
    
    public static class StudentSummaryResponse {
        private Integer studentId;
        private String studentName;
        private String studentEmail;
        private String studentMobile;
        private String studentGender;
        private Integer batchId;
        private Integer courseId;
        private String courseName;
        private String batchName;
        
        // Constructors
        public StudentSummaryResponse() {}
        
        public StudentSummaryResponse(Integer studentId, String studentName, String studentEmail,
                                   String studentMobile, String studentGender, Integer batchId,
                                   Integer courseId, String courseName, String batchName) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.studentEmail = studentEmail;
            this.studentMobile = studentMobile;
            this.studentGender = studentGender;
            this.batchId = batchId;
            this.courseId = courseId;
            this.courseName = courseName;
            this.batchName = batchName;
        }
        
        // Getters and Setters
        public Integer getStudentId() { return studentId; }
        public void setStudentId(Integer studentId) { this.studentId = studentId; }
        
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        
        public String getStudentEmail() { return studentEmail; }
        public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
        
        public String getStudentMobile() { return studentMobile; }
        public void setStudentMobile(String studentMobile) { this.studentMobile = studentMobile; }
        
        public String getStudentGender() { return studentGender; }
        public void setStudentGender(String studentGender) { this.studentGender = studentGender; }
        
        public Integer getBatchId() { return batchId; }
        public void setBatchId(Integer batchId) { this.batchId = batchId; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        
        public String getBatchName() { return batchName; }
        public void setBatchName(String batchName) { this.batchName = batchName; }
    }
    
    // ===========================================
    // SEARCH DTOs
    // ===========================================
    
    public static class StudentSearchRequest {
        private String studentName;
        private String studentEmail;
        private String studentMobile;
        private String studentGender;
        private String studentQualification;
        private Integer batchId;
        private Integer courseId;
        private LocalDate startDate;
        private LocalDate endDate;
        private String searchTerm;
        
        // Constructors
        public StudentSearchRequest() {}
        
        // Getters and Setters
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        
        public String getStudentEmail() { return studentEmail; }
        public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
        
        public String getStudentMobile() { return studentMobile; }
        public void setStudentMobile(String studentMobile) { this.studentMobile = studentMobile; }
        
        public String getStudentGender() { return studentGender; }
        public void setStudentGender(String studentGender) { this.studentGender = studentGender; }
        
        public String getStudentQualification() { return studentQualification; }
        public void setStudentQualification(String studentQualification) { this.studentQualification = studentQualification; }
        
        public Integer getBatchId() { return batchId; }
        public void setBatchId(Integer batchId) { this.batchId = batchId; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public LocalDate getStartDate() { return startDate; }
        public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
        
        public LocalDate getEndDate() { return endDate; }
        public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
        
        public String getSearchTerm() { return searchTerm; }
        public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
    }
} 