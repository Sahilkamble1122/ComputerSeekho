package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EnquiryDTO {
    
    // ===========================================
    // REQUEST DTOs
    // ===========================================
    
    public static class CreateEnquiryRequest {
        @NotBlank(message = "Student name is required")
        @Size(min = 2, max = 100, message = "Student name must be between 2 and 100 characters")
        private String studentName;
        
        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
        private String mobile;
        
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        private String email;
        
        @NotBlank(message = "Address is required")
        @Size(max = 500, message = "Address must not exceed 500 characters")
        private String address;
        
        @NotBlank(message = "Qualification is required")
        @Size(max = 200, message = "Qualification must not exceed 200 characters")
        private String qualification;
        
        @NotNull(message = "Course ID is required")
        private Integer courseId;
        
        @NotBlank(message = "Enquiry source is required")
        @Pattern(regexp = "^(WEBSITE|REFERRAL|SOCIAL_MEDIA|ADVERTISEMENT|WALK_IN|OTHER)$", 
                message = "Enquiry source must be WEBSITE, REFERRAL, SOCIAL_MEDIA, ADVERTISEMENT, WALK_IN, or OTHER")
        private String enquirySource;
        
        @NotBlank(message = "Enquiry status is required")
        @Pattern(regexp = "^(NEW|CONTACTED|FOLLOW_UP|CONVERTED|LOST|CLOSED)$", 
                message = "Enquiry status must be NEW, CONTACTED, FOLLOW_UP, CONVERTED, LOST, or CLOSED")
        private String enquiryStatus;
        
        @Size(max = 1000, message = "Remarks must not exceed 1000 characters")
        private String remarks;
        
        @Size(max = 200, message = "Next follow-up date must be in YYYY-MM-DD format")
        private String nextFollowUpDate;
        
        private Boolean enquiryState;
        
        // Constructors
        public CreateEnquiryRequest() {}
        
        public CreateEnquiryRequest(String studentName, String mobile, String email, String address,
                                 String qualification, Integer courseId, String enquirySource,
                                 String enquiryStatus, String remarks, String nextFollowUpDate, Boolean enquiryState) {
            this.studentName = studentName;
            this.mobile = mobile;
            this.email = email;
            this.address = address;
            this.qualification = qualification;
            this.courseId = courseId;
            this.enquirySource = enquirySource;
            this.enquiryStatus = enquiryStatus;
            this.remarks = remarks;
            this.nextFollowUpDate = nextFollowUpDate;
            this.enquiryState = enquiryState;
        }
        
        // Getters and Setters
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        
        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        
        public String getQualification() { return qualification; }
        public void setQualification(String qualification) { this.qualification = qualification; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public String getEnquirySource() { return enquirySource; }
        public void setEnquirySource(String enquirySource) { this.enquirySource = enquirySource; }
        
        public String getEnquiryStatus() { return enquiryStatus; }
        public void setEnquiryStatus(String enquiryStatus) { this.enquiryStatus = enquiryStatus; }
        
        public String getRemarks() { return remarks; }
        public void setRemarks(String remarks) { this.remarks = remarks; }
        
        public String getNextFollowUpDate() { return nextFollowUpDate; }
        public void setNextFollowUpDate(String nextFollowUpDate) { this.nextFollowUpDate = nextFollowUpDate; }
        
        public Boolean getEnquiryState() { return enquiryState; }
        public void setEnquiryState(Boolean enquiryState) { this.enquiryState = enquiryState; }
    }
    
    public static class UpdateEnquiryRequest {
        @Size(min = 2, max = 100, message = "Student name must be between 2 and 100 characters")
        private String studentName;
        
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
        private String mobile;
        
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        private String email;
        
        @Size(max = 500, message = "Address must not exceed 500 characters")
        private String address;
        
        @Size(max = 200, message = "Qualification must not exceed 200 characters")
        private String qualification;
        
        private Integer courseId;
        
        @Pattern(regexp = "^(WEBSITE|REFERRAL|SOCIAL_MEDIA|ADVERTISEMENT|WALK_IN|OTHER)$", 
                message = "Enquiry source must be WEBSITE, REFERRAL, SOCIAL_MEDIA, ADVERTISEMENT, WALK_IN, or OTHER")
        private String enquirySource;
        
        @Pattern(regexp = "^(NEW|CONTACTED|FOLLOW_UP|CONVERTED|LOST|CLOSED)$", 
                message = "Enquiry status must be NEW, CONTACTED, FOLLOW_UP, CONVERTED, LOST, or CLOSED")
        private String enquiryStatus;
        
        @Size(max = 1000, message = "Remarks must not exceed 1000 characters")
        private String remarks;
        
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Next follow-up date must be in YYYY-MM-DD format")
        private String nextFollowUpDate;
        
        private Boolean enquiryState;
        
        // Constructors
        public UpdateEnquiryRequest() {}
        
        // Getters and Setters
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        
        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        
        public String getQualification() { return qualification; }
        public void setQualification(String qualification) { this.qualification = qualification; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public String getEnquirySource() { return enquirySource; }
        public void setEnquirySource(String enquirySource) { this.enquirySource = enquirySource; }
        
        public String getEnquiryStatus() { return enquiryStatus; }
        public void setEnquiryStatus(String enquiryStatus) { this.enquiryStatus = enquiryStatus; }
        
        public String getRemarks() { return remarks; }
        public void setRemarks(String remarks) { this.remarks = remarks; }
        
        public String getNextFollowUpDate() { return nextFollowUpDate; }
        public void setNextFollowUpDate(String nextFollowUpDate) { this.nextFollowUpDate = nextFollowUpDate; }
        
        public Boolean getEnquiryState() { return enquiryState; }
        public void setEnquiryState(Boolean enquiryState) { this.enquiryState = enquiryState; }
    }
    
    // ===========================================
    // RESPONSE DTOs
    // ===========================================
    
    public static class EnquiryResponse {
        private Integer enquiryId;
        private String studentName;
        private String mobile;
        private String email;
        private String address;
        private String qualification;
        private Integer courseId;
        private String enquirySource;
        private String enquiryStatus;
        private String remarks;
        private LocalDate nextFollowUpDate;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private Boolean enquiryState;
        
        // Additional fields for better response
        private String courseName;
        private Integer followUpCount;
        private String lastFollowUpDate;
        
        // Constructors
        public EnquiryResponse() {}
        
        public EnquiryResponse(Integer enquiryId, String studentName, String mobile, String email,
                             String address, String qualification, Integer courseId, String enquirySource,
                             String enquiryStatus, String remarks, LocalDate nextFollowUpDate,
                             LocalDateTime createdDate, LocalDateTime updatedDate, Boolean enquiryState) {
            this.enquiryId = enquiryId;
            this.studentName = studentName;
            this.mobile = mobile;
            this.email = email;
            this.address = address;
            this.qualification = qualification;
            this.courseId = courseId;
            this.enquirySource = enquirySource;
            this.enquiryStatus = enquiryStatus;
            this.remarks = remarks;
            this.nextFollowUpDate = nextFollowUpDate;
            this.createdDate = createdDate;
            this.updatedDate = updatedDate;
            this.enquiryState = enquiryState;
        }
        
        // Getters and Setters
        public Integer getEnquiryId() { return enquiryId; }
        public void setEnquiryId(Integer enquiryId) { this.enquiryId = enquiryId; }
        
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        
        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        
        public String getQualification() { return qualification; }
        public void setQualification(String qualification) { this.qualification = qualification; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public String getEnquirySource() { return enquirySource; }
        public void setEnquirySource(String enquirySource) { this.enquirySource = enquirySource; }
        
        public String getEnquiryStatus() { return enquiryStatus; }
        public void setEnquiryStatus(String enquiryStatus) { this.enquiryStatus = enquiryStatus; }
        
        public String getRemarks() { return remarks; }
        public void setRemarks(String remarks) { this.remarks = remarks; }
        
        public LocalDate getNextFollowUpDate() { return nextFollowUpDate; }
        public void setNextFollowUpDate(LocalDate nextFollowUpDate) { this.nextFollowUpDate = nextFollowUpDate; }
        
        public LocalDateTime getCreatedDate() { return createdDate; }
        public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
        
        public LocalDateTime getUpdatedDate() { return updatedDate; }
        public void setUpdatedDate(LocalDateTime updatedDate) { this.updatedDate = updatedDate; }
        
        public Boolean getEnquiryState() { return enquiryState; }
        public void setEnquiryState(Boolean enquiryState) { this.enquiryState = enquiryState; }
        
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        
        public Integer getFollowUpCount() { return followUpCount; }
        public void setFollowUpCount(Integer followUpCount) { this.followUpCount = followUpCount; }
        
        public String getLastFollowUpDate() { return lastFollowUpDate; }
        public void setLastFollowUpDate(String lastFollowUpDate) { this.lastFollowUpDate = lastFollowUpDate; }
    }
    
    public static class EnquirySummaryResponse {
        private Integer enquiryId;
        private String studentName;
        private String mobile;
        private String email;
        private String enquiryStatus;
        private String enquirySource;
        private Integer courseId;
        private String courseName;
        private LocalDate nextFollowUpDate;
        private LocalDateTime createdDate;
        private Boolean enquiryState;
        
        // Constructors
        public EnquirySummaryResponse() {}
        
        public EnquirySummaryResponse(Integer enquiryId, String studentName, String mobile, String email,
                                   String enquiryStatus, String enquirySource, Integer courseId,
                                   String courseName, LocalDate nextFollowUpDate, LocalDateTime createdDate, Boolean enquiryState) {
            this.enquiryId = enquiryId;
            this.studentName = studentName;
            this.mobile = mobile;
            this.email = email;
            this.enquiryStatus = enquiryStatus;
            this.enquirySource = enquirySource;
            this.courseId = courseId;
            this.courseName = courseName;
            this.nextFollowUpDate = nextFollowUpDate;
            this.createdDate = createdDate;
            this.enquiryState = enquiryState;
        }
        
        // Getters and Setters
        public Integer getEnquiryId() { return enquiryId; }
        public void setEnquiryId(Integer enquiryId) { this.enquiryId = enquiryId; }
        
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        
        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getEnquiryStatus() { return enquiryStatus; }
        public void setEnquiryStatus(String enquiryStatus) { this.enquiryStatus = enquiryStatus; }
        
        public String getEnquirySource() { return enquirySource; }
        public void setEnquirySource(String enquirySource) { this.enquirySource = enquirySource; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        
        public LocalDate getNextFollowUpDate() { return nextFollowUpDate; }
        public void setNextFollowUpDate(LocalDate nextFollowUpDate) { this.nextFollowUpDate = nextFollowUpDate; }
        
        public LocalDateTime getCreatedDate() { return createdDate; }
        public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
        
        public Boolean getEnquiryState() { return enquiryState; }
        public void setEnquiryState(Boolean enquiryState) { this.enquiryState = enquiryState; }
    }
    
    // ===========================================
    // SEARCH DTOs
    // ===========================================
    
    public static class EnquirySearchRequest {
        private String studentName;
        private String mobile;
        private String email;
        private String enquiryStatus;
        private String enquirySource;
        private Integer courseId;
        private LocalDate startDate;
        private LocalDate endDate;
        private String searchTerm;
        
        // Constructors
        public EnquirySearchRequest() {}
        
        // Getters and Setters
        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }
        
        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getEnquiryStatus() { return enquiryStatus; }
        public void setEnquiryStatus(String enquiryStatus) { this.enquiryStatus = enquiryStatus; }
        
        public String getEnquirySource() { return enquirySource; }
        public void setEnquirySource(String enquirySource) { this.enquirySource = enquirySource; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public LocalDate getStartDate() { return startDate; }
        public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
        
        public LocalDate getEndDate() { return endDate; }
        public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
        
        public String getSearchTerm() { return searchTerm; }
        public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
    }
    
    // ===========================================
    // STATISTICS DTOs
    // ===========================================
    
    public static class EnquiryStatisticsResponse {
        private Integer totalEnquiries;
        private Integer newEnquiries;
        private Integer contactedEnquiries;
        private Integer convertedEnquiries;
        private Integer lostEnquiries;
        private Integer closedEnquiries;
        private String mostPopularSource;
        private String mostPopularCourse;
        private Integer courseId;
        
        // Constructors
        public EnquiryStatisticsResponse() {}
        
        public EnquiryStatisticsResponse(Integer totalEnquiries, Integer newEnquiries, Integer contactedEnquiries,
                                      Integer convertedEnquiries, Integer lostEnquiries, Integer closedEnquiries,
                                      String mostPopularSource, String mostPopularCourse, Integer courseId) {
            this.totalEnquiries = totalEnquiries;
            this.newEnquiries = newEnquiries;
            this.contactedEnquiries = contactedEnquiries;
            this.convertedEnquiries = convertedEnquiries;
            this.lostEnquiries = lostEnquiries;
            this.closedEnquiries = closedEnquiries;
            this.mostPopularSource = mostPopularSource;
            this.mostPopularCourse = mostPopularCourse;
            this.courseId = courseId;
        }
        
        // Getters and Setters
        public Integer getTotalEnquiries() { return totalEnquiries; }
        public void setTotalEnquiries(Integer totalEnquiries) { this.totalEnquiries = totalEnquiries; }
        
        public Integer getNewEnquiries() { return newEnquiries; }
        public void setNewEnquiries(Integer newEnquiries) { this.newEnquiries = newEnquiries; }
        
        public Integer getContactedEnquiries() { return contactedEnquiries; }
        public void setContactedEnquiries(Integer contactedEnquiries) { this.contactedEnquiries = contactedEnquiries; }
        
        public Integer getConvertedEnquiries() { return convertedEnquiries; }
        public void setConvertedEnquiries(Integer convertedEnquiries) { this.convertedEnquiries = convertedEnquiries; }
        
        public Integer getLostEnquiries() { return lostEnquiries; }
        public void setLostEnquiries(Integer lostEnquiries) { this.lostEnquiries = lostEnquiries; }
        
        public Integer getClosedEnquiries() { return closedEnquiries; }
        public void setClosedEnquiries(Integer closedEnquiries) { this.closedEnquiries = closedEnquiries; }
        
        public String getMostPopularSource() { return mostPopularSource; }
        public void setMostPopularSource(String mostPopularSource) { this.mostPopularSource = mostPopularSource; }
        
        public String getMostPopularCourse() { return mostPopularCourse; }
        public void setMostPopularCourse(String mostPopularCourse) { this.mostPopularCourse = mostPopularCourse; }
        
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
    }
} 