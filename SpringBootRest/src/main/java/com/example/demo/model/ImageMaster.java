package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "image_master")
public class ImageMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;
    
    @Column(name = "image_path")
    private String imagePath;
    
    @Column(name = "album_id")
    private Integer albumId;
    
    @Column(name = "is_album_cover")
    private Boolean isAlbumCover;
    
    @Column(name = "image_is_active")
    private Boolean imageIsActive;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public ImageMaster() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Parameterized constructor
    public ImageMaster(String imagePath, Integer albumId, Boolean isAlbumCover, Boolean imageIsActive) {
        this.imagePath = imagePath;
        this.albumId = albumId;
        this.isAlbumCover = isAlbumCover;
        this.imageIsActive = imageIsActive;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getImageId() {
        return imageId;
    }
    
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public Integer getAlbumId() {
        return albumId;
    }
    
    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }
    
    public Boolean getIsAlbumCover() {
        return isAlbumCover;
    }
    
    public void setIsAlbumCover(Boolean isAlbumCover) {
        this.isAlbumCover = isAlbumCover;
    }
    
    public Boolean getImageIsActive() {
        return imageIsActive;
    }
    
    public void setImageIsActive(Boolean imageIsActive) {
        this.imageIsActive = imageIsActive;
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
        return "ImageMaster{" +
                "imageId=" + imageId +
                ", imagePath='" + imagePath + '\'' +
                ", albumId=" + albumId +
                ", isAlbumCover=" + isAlbumCover +
                ", imageIsActive=" + imageIsActive +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 