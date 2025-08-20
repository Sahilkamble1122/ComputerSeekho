package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.ImageMaster;

public interface ImageMasterService {
    
    // CREATE OPERATIONS
    ImageMaster saveImage(ImageMaster image);
    List<ImageMaster> saveAllImages(List<ImageMaster> images);
    
    // READ OPERATIONS
    List<ImageMaster> getAllImages();
    Optional<ImageMaster> getImageById(Integer imageId);
    List<ImageMaster> getImagesByName(String imageName);
    List<ImageMaster> getImagesByType(String imageType);
    List<ImageMaster> getImagesByStatus(String status);
    List<ImageMaster> getImagesByNameContaining(String imageName);
    List<ImageMaster> getActiveImages();
    Long countActiveImages();
    
    // UPDATE OPERATIONS
    ImageMaster updateImage(Integer imageId, ImageMaster imageDetails);
    ImageMaster updateImageName(Integer imageId, String imageName);
    ImageMaster updateImageType(Integer imageId, String imageType);
    ImageMaster updateImageStatus(Integer imageId, String status);
    
    // DELETE OPERATIONS
    void deleteImage(Integer imageId);
    void deleteImagesByType(String imageType);
    void deleteImagesByStatus(String status);
    void deleteInactiveImages();
    void deleteAllImages();
    
    // BUSINESS LOGIC OPERATIONS
    List<ImageMaster> searchImages(String searchTerm);
    List<ImageMaster> getImagesWithPagination(int page, int size);
    List<ImageMaster> getImagesSortedByName();
    List<ImageMaster> getImagesSortedByType();
    List<ImageMaster> getImagesSortedByStatus();
    List<ImageMaster> getFeaturedImages();
    List<ImageMaster> getRecentImages();
} 