package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_master")
public class CourseMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;
    
    @Column(name = "course_name")
    private String courseName;
    
    @Column(name = "course_description")
    private String courseDescription;
    
    @Column(name = "course_duration")
    private Integer courseDuration;
    
    @Column(name = "course_syllabus")
    private String courseSyllabus;
    
    @Column(name = "age_grp_type")
    private String ageGrpType;
    
    @Column(name = "course_is_active")
    private Boolean courseIsActive;
    
    @Column(name = "cover_photo")
    private String coverPhoto;
    
    @Column(name = "video_id")
    private Integer videoId;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public CourseMaster() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Parameterized constructor
    public CourseMaster(String courseName, String courseDescription, Integer courseDuration, 
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
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseDescription() {
        return courseDescription;
    }
    
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    
    public Integer getCourseDuration() {
        return courseDuration;
    }
    
    public void setCourseDuration(Integer courseDuration) {
        this.courseDuration = courseDuration;
    }
    
    public String getCourseSyllabus() {
        return courseSyllabus;
    }
    
    public void setCourseSyllabus(String courseSyllabus) {
        this.courseSyllabus = courseSyllabus;
    }
    
    public String getAgeGrpType() {
        return ageGrpType;
    }
    
    public void setAgeGrpType(String ageGrpType) {
        this.ageGrpType = ageGrpType;
    }
    
    public Boolean getCourseIsActive() {
        return courseIsActive;
    }
    
    public void setCourseIsActive(Boolean courseIsActive) {
        this.courseIsActive = courseIsActive;
    }
    
    public String getCoverPhoto() {
        return coverPhoto;
    }
    
    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
    
    public Integer getVideoId() {
        return videoId;
    }
    
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
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
        return "CourseMaster{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseDuration=" + courseDuration +
                ", courseSyllabus='" + courseSyllabus + '\'' +
                ", ageGrpType='" + ageGrpType + '\'' +
                ", courseIsActive=" + courseIsActive +
                ", coverPhoto='" + coverPhoto + '\'' +
                ", videoId=" + videoId +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 