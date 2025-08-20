package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "album_master")
public class AlbumMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Integer albumId;
    
    @Column(name = "album_name")
    private String albumName;
    
    @Column(name = "album_description")
    private String albumDescription;
    
    @Column(name = "start_date")
    private LocalDateTime startDate;
    
    @Column(name = "end_date")
    private LocalDateTime endDate;
    
    @Column(name = "album_is_active")
    private Boolean albumIsActive;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public AlbumMaster() {
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now();
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Parameterized constructor
    public AlbumMaster(String albumName, String albumDescription, LocalDateTime startDate,
                      LocalDateTime endDate, Boolean albumIsActive) {
        this.albumName = albumName;
        this.albumDescription = albumDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.albumIsActive = albumIsActive;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getAlbumId() {
        return albumId;
    }
    
    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }
    
    public String getAlbumName() {
        return albumName;
    }
    
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    
    public String getAlbumDescription() {
        return albumDescription;
    }
    
    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
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
    
    public Boolean getAlbumIsActive() {
        return albumIsActive;
    }
    
    public void setAlbumIsActive(Boolean albumIsActive) {
        this.albumIsActive = albumIsActive;
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
        return "AlbumMaster{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", albumDescription='" + albumDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", albumIsActive=" + albumIsActive +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 