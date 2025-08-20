package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class StaffDTO {
    
    // ===========================================
    // REQUEST DTOs
    // ===========================================
    
    public static class CreateStaffRequest {
        @NotBlank(message = "Staff name is required")
        @Size(min = 2, max = 100, message = "Staff name must be between 2 and 100 characters")
        private String staffName;
        
        @NotBlank(message = "Staff address is required")
        @Size(max = 500, message = "Address must not exceed 500 characters")
        private String staffAddress;
        
        @NotBlank(message = "Staff gender is required")
        @Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "Gender must be MALE, FEMALE, or OTHER")
        private String staffGender;
        
        @NotBlank(message = "Staff designation is required")
        @Size(max = 100, message = "Designation must not exceed 100 characters")
        private String staffDesignation;
        
        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
        private String staffMobile;
        
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        private String staffEmail;
        
        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        private String staffPassword;
        
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
        private String staffUsername;
        
        @NotNull(message = "Date of joining is required")
        private LocalDate dateOfJoining;
        
        @Size(max = 200, message = "Qualification must not exceed 200 characters")
        private String qualification;
        
        private String photoUrl;
        
        @NotNull(message = "Staff active status is required")
        private Boolean staffIsActive;
        
        // Constructors
        public CreateStaffRequest() {}
        
        public CreateStaffRequest(String staffName, String staffAddress, String staffGender,
                               String staffDesignation, String staffMobile, String staffEmail,
                               String staffPassword, String staffUsername, LocalDate dateOfJoining,
                               String qualification, String photoUrl, Boolean staffIsActive) {
            this.staffName = staffName;
            this.staffAddress = staffAddress;
            this.staffGender = staffGender;
            this.staffDesignation = staffDesignation;
            this.staffMobile = staffMobile;
            this.staffEmail = staffEmail;
            this.staffPassword = staffPassword;
            this.staffUsername = staffUsername;
            this.dateOfJoining = dateOfJoining;
            this.qualification = qualification;
            this.photoUrl = photoUrl;
            this.staffIsActive = staffIsActive;
        }
        
        // Getters and Setters
        public String getStaffName() { return staffName; }
        public void setStaffName(String staffName) { this.staffName = staffName; }
        
        public String getStaffAddress() { return staffAddress; }
        public void setStaffAddress(String staffAddress) { this.staffAddress = staffAddress; }
        
        public String getStaffGender() { return staffGender; }
        public void setStaffGender(String staffGender) { this.staffGender = staffGender; }
        
        public String getStaffDesignation() { return staffDesignation; }
        public void setStaffDesignation(String staffDesignation) { this.staffDesignation = staffDesignation; }
        
        public String getStaffMobile() { return staffMobile; }
        public void setStaffMobile(String staffMobile) { this.staffMobile = staffMobile; }
        
        public String getStaffEmail() { return staffEmail; }
        public void setStaffEmail(String staffEmail) { this.staffEmail = staffEmail; }
        
        public String getStaffPassword() { return staffPassword; }
        public void setStaffPassword(String staffPassword) { this.staffPassword = staffPassword; }
        
        public String getStaffUsername() { return staffUsername; }
        public void setStaffUsername(String staffUsername) { this.staffUsername = staffUsername; }
        
        public LocalDate getDateOfJoining() { return dateOfJoining; }
        public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }
        
        public String getQualification() { return qualification; }
        public void setQualification(String qualification) { this.qualification = qualification; }
        
        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        
        public Boolean getStaffIsActive() { return staffIsActive; }
        public void setStaffIsActive(Boolean staffIsActive) { this.staffIsActive = staffIsActive; }
    }
    
    public static class UpdateStaffRequest {
        @Size(min = 2, max = 100, message = "Staff name must be between 2 and 100 characters")
        private String staffName;
        
        @Size(max = 500, message = "Address must not exceed 500 characters")
        private String staffAddress;
        
        @Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "Gender must be MALE, FEMALE, or OTHER")
        private String staffGender;
        
        @Size(max = 100, message = "Designation must not exceed 100 characters")
        private String staffDesignation;
        
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
        private String staffMobile;
        
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        private String staffEmail;
        
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        private String staffPassword;
        
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
        private String staffUsername;
        
        private LocalDate dateOfJoining;
        
        @Size(max = 200, message = "Qualification must not exceed 200 characters")
        private String qualification;
        
        private String photoUrl;
        private Boolean staffIsActive;
        
        // Constructors
        public UpdateStaffRequest() {}
        
        // Getters and Setters
        public String getStaffName() { return staffName; }
        public void setStaffName(String staffName) { this.staffName = staffName; }
        
        public String getStaffAddress() { return staffAddress; }
        public void setStaffAddress(String staffAddress) { this.staffAddress = staffAddress; }
        
        public String getStaffGender() { return staffGender; }
        public void setStaffGender(String staffGender) { this.staffGender = staffGender; }
        
        public String getStaffDesignation() { return staffDesignation; }
        public void setStaffDesignation(String staffDesignation) { this.staffDesignation = staffDesignation; }
        
        public String getStaffMobile() { return staffMobile; }
        public void setStaffMobile(String staffMobile) { this.staffMobile = staffMobile; }
        
        public String getStaffEmail() { return staffEmail; }
        public void setStaffEmail(String staffEmail) { this.staffEmail = staffEmail; }
        
        public String getStaffPassword() { return staffPassword; }
        public void setStaffPassword(String staffPassword) { this.staffPassword = staffPassword; }
        
        public String getStaffUsername() { return staffUsername; }
        public void setStaffUsername(String staffUsername) { this.staffUsername = staffUsername; }
        
        public LocalDate getDateOfJoining() { return dateOfJoining; }
        public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }
        
        public String getQualification() { return qualification; }
        public void setQualification(String qualification) { this.qualification = qualification; }
        
        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        
        public Boolean getStaffIsActive() { return staffIsActive; }
        public void setStaffIsActive(Boolean staffIsActive) { this.staffIsActive = staffIsActive; }
    }
    
    // ===========================================
    // RESPONSE DTOs
    // ===========================================
    
    public static class StaffResponse {
        private Integer staffId;
        private String staffName;
        private String staffAddress;
        private String staffGender;
        private String staffDesignation;
        private String staffMobile;
        private String staffEmail;
        private String staffUsername;
        private LocalDate dateOfJoining;
        private String qualification;
        private String photoUrl;
        private Boolean staffIsActive;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        
        // Additional fields for better response
        private Integer studentCount;
        private Integer courseCount;
        
        // Constructors
        public StaffResponse() {}
        
        public StaffResponse(Integer staffId, String staffName, String staffAddress, String staffGender,
                          String staffDesignation, String staffMobile, String staffEmail, String staffUsername,
                          LocalDate dateOfJoining, String qualification, String photoUrl, Boolean staffIsActive,
                          LocalDateTime createdDate, LocalDateTime updatedDate) {
            this.staffId = staffId;
            this.staffName = staffName;
            this.staffAddress = staffAddress;
            this.staffGender = staffGender;
            this.staffDesignation = staffDesignation;
            this.staffMobile = staffMobile;
            this.staffEmail = staffEmail;
            this.staffUsername = staffUsername;
            this.dateOfJoining = dateOfJoining;
            this.qualification = qualification;
            this.photoUrl = photoUrl;
            this.staffIsActive = staffIsActive;
            this.createdDate = createdDate;
            this.updatedDate = updatedDate;
        }
        
        // Getters and Setters
        public Integer getStaffId() { return staffId; }
        public void setStaffId(Integer staffId) { this.staffId = staffId; }
        
        public String getStaffName() { return staffName; }
        public void setStaffName(String staffName) { this.staffName = staffName; }
        
        public String getStaffAddress() { return staffAddress; }
        public void setStaffAddress(String staffAddress) { this.staffAddress = staffAddress; }
        
        public String getStaffGender() { return staffGender; }
        public void setStaffGender(String staffGender) { this.staffGender = staffGender; }
        
        public String getStaffDesignation() { return staffDesignation; }
        public void setStaffDesignation(String staffDesignation) { this.staffDesignation = staffDesignation; }
        
        public String getStaffMobile() { return staffMobile; }
        public void setStaffMobile(String staffMobile) { this.staffMobile = staffMobile; }
        
        public String getStaffEmail() { return staffEmail; }
        public void setStaffEmail(String staffEmail) { this.staffEmail = staffEmail; }
        
        public String getStaffUsername() { return staffUsername; }
        public void setStaffUsername(String staffUsername) { this.staffUsername = staffUsername; }
        
        public LocalDate getDateOfJoining() { return dateOfJoining; }
        public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }
        
        public String getQualification() { return qualification; }
        public void setQualification(String qualification) { this.qualification = qualification; }
        
        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        
        public Boolean getStaffIsActive() { return staffIsActive; }
        public void setStaffIsActive(Boolean staffIsActive) { this.staffIsActive = staffIsActive; }
        
        public LocalDateTime getCreatedDate() { return createdDate; }
        public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
        
        public LocalDateTime getUpdatedDate() { return updatedDate; }
        public void setUpdatedDate(LocalDateTime updatedDate) { this.updatedDate = updatedDate; }
        
        public Integer getStudentCount() { return studentCount; }
        public void setStudentCount(Integer studentCount) { this.studentCount = studentCount; }
        
        public Integer getCourseCount() { return courseCount; }
        public void setCourseCount(Integer courseCount) { this.courseCount = courseCount; }
    }
    
    public static class StaffSummaryResponse {
        private Integer staffId;
        private String staffName;
        private String staffDesignation;
        private String staffEmail;
        private String staffMobile;
        private String staffGender;
        private Boolean staffIsActive;
        private LocalDate dateOfJoining;
        private Integer studentCount;
        private Integer courseCount;
        
        // Constructors
        public StaffSummaryResponse() {}
        
        public StaffSummaryResponse(Integer staffId, String staffName, String staffDesignation,
                                 String staffEmail, String staffMobile, String staffGender,
                                 Boolean staffIsActive, LocalDate dateOfJoining, Integer studentCount, Integer courseCount) {
            this.staffId = staffId;
            this.staffName = staffName;
            this.staffDesignation = staffDesignation;
            this.staffEmail = staffEmail;
            this.staffMobile = staffMobile;
            this.staffGender = staffGender;
            this.staffIsActive = staffIsActive;
            this.dateOfJoining = dateOfJoining;
            this.studentCount = studentCount;
            this.courseCount = courseCount;
        }
        
        // Getters and Setters
        public Integer getStaffId() { return staffId; }
        public void setStaffId(Integer staffId) { this.staffId = staffId; }
        
        public String getStaffName() { return staffName; }
        public void setStaffName(String staffName) { this.staffName = staffName; }
        
        public String getStaffDesignation() { return staffDesignation; }
        public void setStaffDesignation(String staffDesignation) { this.staffDesignation = staffDesignation; }
        
        public String getStaffEmail() { return staffEmail; }
        public void setStaffEmail(String staffEmail) { this.staffEmail = staffEmail; }
        
        public String getStaffMobile() { return staffMobile; }
        public void setStaffMobile(String staffMobile) { this.staffMobile = staffMobile; }
        
        public String getStaffGender() { return staffGender; }
        public void setStaffGender(String staffGender) { this.staffGender = staffGender; }
        
        public Boolean getStaffIsActive() { return staffIsActive; }
        public void setStaffIsActive(Boolean staffIsActive) { this.staffIsActive = staffIsActive; }
        
        public LocalDate getDateOfJoining() { return dateOfJoining; }
        public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }
        
        public Integer getStudentCount() { return studentCount; }
        public void setStudentCount(Integer studentCount) { this.studentCount = studentCount; }
        
        public Integer getCourseCount() { return courseCount; }
        public void setCourseCount(Integer courseCount) { this.courseCount = courseCount; }
    }
    
    // ===========================================
    // SEARCH DTOs
    // ===========================================
    
    public static class StaffSearchRequest {
        private String staffName;
        private String staffEmail;
        private String staffMobile;
        private String staffGender;
        private String staffDesignation;
        private Boolean staffIsActive;
        private LocalDate startDate;
        private LocalDate endDate;
        private String searchTerm;
        
        // Constructors
        public StaffSearchRequest() {}
        
        // Getters and Setters
        public String getStaffName() { return staffName; }
        public void setStaffName(String staffName) { this.staffName = staffName; }
        
        public String getStaffEmail() { return staffEmail; }
        public void setStaffEmail(String staffEmail) { this.staffEmail = staffEmail; }
        
        public String getStaffMobile() { return staffMobile; }
        public void setStaffMobile(String staffMobile) { this.staffMobile = staffMobile; }
        
        public String getStaffGender() { return staffGender; }
        public void setStaffGender(String staffGender) { this.staffGender = staffGender; }
        
        public String getStaffDesignation() { return staffDesignation; }
        public void setStaffDesignation(String staffDesignation) { this.staffDesignation = staffDesignation; }
        
        public Boolean getStaffIsActive() { return staffIsActive; }
        public void setStaffIsActive(Boolean staffIsActive) { this.staffIsActive = staffIsActive; }
        
        public LocalDate getStartDate() { return startDate; }
        public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
        
        public LocalDate getEndDate() { return endDate; }
        public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
        
        public String getSearchTerm() { return searchTerm; }
        public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
    }
} 