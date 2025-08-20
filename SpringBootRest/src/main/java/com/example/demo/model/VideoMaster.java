package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "video_master")
public class VideoMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Integer videoId;
    
    @Column(name = "video_description")
    private String videoDescription;
    
    @Column(name = "video_url")
    private String videoUrl;
    
    @Column(name = "batch_id")
    private Integer batchId;
    
    @Column(name = "start_date")
    private LocalDateTime startDate;
    
    @Column(name = "end_date")
    private LocalDateTime endDate;
    
    @Column(name = "video_is_active")
    private Boolean videoIsActive;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public VideoMaster() {
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now();
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Parameterized constructor
    public VideoMaster(String videoDescription, String videoUrl, Integer batchId,
                      LocalDateTime startDate, LocalDateTime endDate, Boolean videoIsActive) {
        this.videoDescription = videoDescription;
        this.videoUrl = videoUrl;
        this.batchId = batchId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.videoIsActive = videoIsActive;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getVideoId() {
        return videoId;
    }
    
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
    
    public String getVideoDescription() {
        return videoDescription;
    }
    
    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }
    
    public String getVideoUrl() {
        return videoUrl;
    }
    
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    
    public Integer getBatchId() {
        return batchId;
    }
    
    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    
    public Boolean getVideoIsActive() {
        return videoIsActive;
    }
    
    public void setVideoIsActive(Boolean videoIsActive) {
        this.videoIsActive = videoIsActive;
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
        return "VideoMaster{" +
                "videoId=" + videoId +
                ", videoDescription='" + videoDescription + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", batchId=" + batchId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", videoIsActive=" + videoIsActive +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 