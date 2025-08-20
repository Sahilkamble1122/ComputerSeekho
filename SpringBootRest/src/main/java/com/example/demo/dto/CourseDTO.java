package com.example.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CourseDTO {
    
    // ===========================================
    // REQUEST DTOs
    // ===========================================
    
    public static class CreateCourseRequest {
        @NotBlank(message = "Course name is required")
        @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
        private String courseName;
        
        @NotBlank(message = "Course description is required")
        @Size(max = 3000, message = "Course description must not exceed 1000 characters")
        private String courseDescription;
        
        @NotNull(message = "Course duration is required")
        @Positive(message = "Course duration must be positive")
        private Integer courseDuration;
        
        @NotBlank(message = "Course syllabus is required")
        @Size(max = 2000, message = "Course syllabus must not exceed 2000 characters")
        private String courseSyllabus;
        
        @NotBlank(message = "Age group type is required")
        private String ageGrpType;
        
        @NotNull(message = "Course active status is required")
        private Boolean courseIsActive;
        
        private String coverPhoto;
        private Integer videoId;
        
        // Constructors
        public CreateCourseRequest() {}
        
        public CreateCourseRequest(String courseName, String courseDescription, Integer courseDuration,
                                String courseSyllabus, String ageGrpType, Boolean courseIsActive,
                                String coverPhoto, Integer videoId) {
            this.courseName = courseName;
            this.courseDescription = courseDescription;
            this.courseDuration = courseDuration;
            this.courseSyllabus = courseSyllabus;
            this.ageGrpType = ageGrpType;
            this.courseIsActive = courseIsActive;
            this.coverPhoto = coverPhoto;
            this.videoId = videoId;
        }
        
        // Getters and Setters
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        
        public String getCourseDescription() { return courseDescription; }
        public void setCourseDescription(String courseDescription) { this.courseDescription = courseDescription; }
        
        public Integer getCourseDuration() { return courseDuration; }
        public void setCourseDuration(Integer courseDuration) { this.courseDuration = courseDuration; }
        
        public String getCourseSyllabus() { return courseSyllabus; }
        public void setCourseSyllabus(String courseSyllabus) { this.courseSyllabus = courseSyllabus; }
        
        public String getAgeGrpType() { return ageGrpType; }
        public void setAgeGrpType(String ageGrpType) { this.ageGrpType = ageGrpType; }
        
        public Boolean getCourseIsActive() { return courseIsActive; }
        public void setCourseIsActive(Boolean courseIsActive) { this.courseIsActive = courseIsActive; }
        
        public String getCoverPhoto() { return coverPhoto; }
        public void setCoverPhoto(String coverPhoto) { this.coverPhoto = coverPhoto; }
        
        public Integer getVideoId() { return videoId; }
        public void setVideoId(Integer videoId) { this.videoId = videoId; }
    }
    
    public static class UpdateCourseRequest {
        @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
        private String courseName;
        
        @Size(max = 1000, message = "Course description must not exceed 1000 characters")
        private String courseDescription;
        
        @Positive(message = "Course duration must be positive")
        private Integer courseDuration;
        
        @Size(max = 2000, message = "Course syllabus must not exceed 2000 characters")
        private String courseSyllabus;
        
        private String ageGrpType;
        
        private Boolean courseIsActive;
        private String coverPhoto;
        private Integer videoId;
        
        // Constructors
        public UpdateCourseRequest() {}
        
        // Getters and Setters
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        
        public String getCourseDescription() { return courseDescription; }
        public void setCourseDescription(String courseDescription) { this.courseDescription = courseDescription; }
        
        public Integer getCourseDuration() { return courseDuration; }
        public void setCourseDuration(Integer courseDuration) { this.courseDuration = courseDuration; }
        
        public String getCourseSyllabus() { return courseSyllabus; }
        public void setCourseSyllabus(String courseSyllabus) { this.courseSyllabus = courseSyllabus; }
        
        public String getAgeGrpType() { return ageGrpType; }
        public void setAgeGrpType(String ageGrpType) { this.ageGrpType = ageGrpType; }
        
        public Boolean getCourseIsActive() { return courseIsActive; }
        public void setCourseIsActive(Boolean courseIsActive) { this.courseIsActive = courseIsActive; }
        
        public String getCoverPhoto() { return coverPhoto; }
        public void setCoverPhoto(String coverPhoto) { this.coverPhoto = coverPhoto; }
        
        public Integer getVideoId() { return videoId; }
        public void setVideoId(Integer videoId) { this.videoId = videoId; }
    }
    
    // ===========================================
    // RESPONSE DTOs
    // ===========================================
    
    public static class CourseResponse {
        private Integer courseId;
        private String courseName;
        private String courseDescription;
        private Integer courseDuration;
        private String courseSyllabus;
        private String ageGrpType;
        private Boolean courseIsActive;
        private String coverPhoto;
        private Integer videoId;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        
        // Additional fields for better response
        private String videoTitle;
        private Integer studentCount;
        private Integer batchCount;
        
        // Constructors
        public CourseResponse() {}
        
        public CourseResponse(Integer courseId, String courseName, String courseDescription,
                           Integer courseDuration, String courseSyllabus, String ageGrpType,
                           Boolean courseIsActive, String coverPhoto, Integer videoId,
                           LocalDateTime createdDate, LocalDateTime updatedDate) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.courseDescription = courseDescription;
            this.courseDuration = courseDuration;
            this.courseSyllabus = courseSyllabus;
            this.ageGrpType = ageGrpType;
            this.courseIsActive = courseIsActive;
            this.coverPhoto = coverPhoto;
            this.videoId = videoId;
            this.createdDate = createdDate;
            this.updatedDate = updatedDate;
        }
        
        // Getters and Setters
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        
        public String getCourseDescription() { return courseDescription; }
        public void setCourseDescription(String courseDescription) { this.courseDescription = courseDescription; }
        
        public Integer getCourseDuration() { return courseDuration; }
        public void setCourseDuration(Integer courseDuration) { this.courseDuration = courseDuration; }
        
        public String getCourseSyllabus() { return courseSyllabus; }
        public void setCourseSyllabus(String courseSyllabus) { this.courseSyllabus = courseSyllabus; }
        
        public String getAgeGrpType() { return ageGrpType; }
        public void setAgeGrpType(String ageGrpType) { this.ageGrpType = ageGrpType; }
        
        public Boolean getCourseIsActive() { return courseIsActive; }
        public void setCourseIsActive(Boolean courseIsActive) { this.courseIsActive = courseIsActive; }
        
        public String getCoverPhoto() { return coverPhoto; }
        public void setCoverPhoto(String coverPhoto) { this.coverPhoto = coverPhoto; }
        
        public Integer getVideoId() { return videoId; }
        public void setVideoId(Integer videoId) { this.videoId = videoId; }
        
        public LocalDateTime getCreatedDate() { return createdDate; }
        public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
        
        public LocalDateTime getUpdatedDate() { return updatedDate; }
        public void setUpdatedDate(LocalDateTime updatedDate) { this.updatedDate = updatedDate; }
        
        public String getVideoTitle() { return videoTitle; }
        public void setVideoTitle(String videoTitle) { this.videoTitle = videoTitle; }
        
        public Integer getStudentCount() { return studentCount; }
        public void setStudentCount(Integer studentCount) { this.studentCount = studentCount; }
        
        public Integer getBatchCount() { return batchCount; }
        public void setBatchCount(Integer batchCount) { this.batchCount = batchCount; }
    }
    
    public static class CourseSummaryResponse {
        private Integer courseId;
        private String courseName;
        private String courseDescription;
        private Integer courseDuration;
        private String ageGrpType;
        private Boolean courseIsActive;
        private String coverPhoto;
        private Integer studentCount;
        private Integer batchCount;
        
        // Constructors
        public CourseSummaryResponse() {}
        
        public CourseSummaryResponse(Integer courseId, String courseName, String courseDescription,
                                  Integer courseDuration, String ageGrpType, Boolean courseIsActive,
                                  String coverPhoto, Integer studentCount, Integer batchCount) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.courseDescription = courseDescription;
            this.courseDuration = courseDuration;
            this.ageGrpType = ageGrpType;
            this.courseIsActive = courseIsActive;
            this.coverPhoto = coverPhoto;
            this.studentCount = studentCount;
            this.batchCount = batchCount;
        }
        
        // Getters and Setters
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        
        public String getCourseDescription() { return courseDescription; }
        public void setCourseDescription(String courseDescription) { this.courseDescription = courseDescription; }
        
        public Integer getCourseDuration() { return courseDuration; }
        public void setCourseDuration(Integer courseDuration) { this.courseDuration = courseDuration; }
        
        public String getAgeGrpType() { return ageGrpType; }
        public void setAgeGrpType(String ageGrpType) { this.ageGrpType = ageGrpType; }
        
        public Boolean getCourseIsActive() { return courseIsActive; }
        public void setCourseIsActive(Boolean courseIsActive) { this.courseIsActive = courseIsActive; }
        
        public String getCoverPhoto() { return coverPhoto; }
        public void setCoverPhoto(String coverPhoto) { this.coverPhoto = coverPhoto; }
        
        public Integer getStudentCount() { return studentCount; }
        public void setStudentCount(Integer studentCount) { this.studentCount = studentCount; }
        
        public Integer getBatchCount() { return batchCount; }
        public void setBatchCount(Integer batchCount) { this.batchCount = batchCount; }
    }
    
    // ===========================================
    // SEARCH DTOs
    // ===========================================
    
    public static class CourseSearchRequest {
        private String courseName;
        private String ageGrpType;
        private Boolean courseIsActive;
        private String searchTerm;
        private Integer minDuration;
        private Integer maxDuration;
        
        // Constructors
        public CourseSearchRequest() {}
        
        // Getters and Setters
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        
        public String getAgeGrpType() { return ageGrpType; }
        public void setAgeGrpType(String ageGrpType) { this.ageGrpType = ageGrpType; }
        
        public Boolean getCourseIsActive() { return courseIsActive; }
        public void setCourseIsActive(Boolean courseIsActive) { this.courseIsActive = courseIsActive; }
        
        public String getSearchTerm() { return searchTerm; }
        public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
        
        public Integer getMinDuration() { return minDuration; }
        public void setMinDuration(Integer minDuration) { this.minDuration = minDuration; }
        
        public Integer getMaxDuration() { return maxDuration; }
        public void setMaxDuration(Integer maxDuration) { this.maxDuration = maxDuration; }
    }
} 