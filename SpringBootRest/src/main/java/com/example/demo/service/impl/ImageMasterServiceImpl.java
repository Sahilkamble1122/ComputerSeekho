package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.ImageMaster;
import com.example.demo.repository.ImageMasterRepository;
import com.example.demo.service.ImageMasterService;

@Service
public class ImageMasterServiceImpl implements ImageMasterService {

    @Autowired
    private ImageMasterRepository imageMasterRepository;

    // CREATE OPERATIONS
    @Override
    public ImageMaster saveImage(ImageMaster image) {
        return imageMasterRepository.save(image);
    }

    @Override
    public List<ImageMaster> saveAllImages(List<ImageMaster> images) {
        return imageMasterRepository.saveAll(images);
    }

    // READ OPERATIONS
    @Override
    public List<ImageMaster> getAllImages() {
        return imageMasterRepository.findAll();
    }

    @Override
    public Optional<ImageMaster> getImageById(Integer imageId) {
        return imageMasterRepository.findById(imageId);
    }

    @Override
    public List<ImageMaster> getImagesByName(String imageName) {
        return imageMasterRepository.findByImagePath(imageName);
    }

    @Override
    public List<ImageMaster> getImagesByType(String imageType) {
        return imageMasterRepository.findByImagePath(imageType);
    }

    @Override
    public List<ImageMaster> getImagesByStatus(String status) {
        Boolean isActive = "Active".equalsIgnoreCase(status);
        return imageMasterRepository.findByImageIsActive(isActive);
    }

    @Override
    public List<ImageMaster> getImagesByNameContaining(String imageName) {
        return imageMasterRepository.findByImagePathContainingIgnoreCase(imageName);
    }

    @Override
    public List<ImageMaster> getActiveImages() {
        return imageMasterRepository.findByImageIsActive(true);
    }

    @Override
    public Long countActiveImages() {
        return imageMasterRepository.countByImageIsActive(true);
    }

    // UPDATE OPERATIONS
    @Override
    public ImageMaster updateImage(Integer imageId, ImageMaster imageDetails) {
        ImageMaster existingImage = imageMasterRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + imageId));
        
        existingImage.setImagePath(imageDetails.getImagePath());
        existingImage.setAlbumId(imageDetails.getAlbumId());
        existingImage.setIsAlbumCover(imageDetails.getIsAlbumCover());
        existingImage.setImageIsActive(imageDetails.getImageIsActive());
        existingImage.setUpdatedDate(java.time.LocalDateTime.now());
        
        return imageMasterRepository.save(existingImage);
    }

    @Override
    public ImageMaster updateImageName(Integer imageId, String imageName) {
        ImageMaster image = imageMasterRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + imageId));
        image.setImagePath(imageName);
        image.setUpdatedDate(java.time.LocalDateTime.now());
        return imageMasterRepository.save(image);
    }

    @Override
    public ImageMaster updateImageType(Integer imageId, String imageType) {
        ImageMaster image = imageMasterRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + imageId));
        image.setImagePath(imageType);
        image.setUpdatedDate(java.time.LocalDateTime.now());
        return imageMasterRepository.save(image);
    }

    @Override
    public ImageMaster updateImageStatus(Integer imageId, String status) {
        ImageMaster image = imageMasterRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + imageId));
        Boolean isActive = "Active".equalsIgnoreCase(status);
        image.setImageIsActive(isActive);
        image.setUpdatedDate(java.time.LocalDateTime.now());
        return imageMasterRepository.save(image);
    }

    // DELETE OPERATIONS
    @Override
    public void deleteImage(Integer imageId) {
        if (!imageMasterRepository.existsById(imageId)) {
            throw new RuntimeException("Image not found with id: " + imageId);
        }
        imageMasterRepository.deleteById(imageId);
    }

    @Override
    public void deleteImagesByType(String imageType) {
        imageMasterRepository.deleteByImagePath(imageType);
    }

    @Override
    public void deleteImagesByStatus(String status) {
        Boolean isActive = "Active".equalsIgnoreCase(status);
        imageMasterRepository.deleteByImageIsActive(isActive);
    }

    @Override
    public void deleteInactiveImages() {
        imageMasterRepository.deleteByImageIsActive(false);
    }

    @Override
    public void deleteAllImages() {
        imageMasterRepository.deleteAll();
    }

    // BUSINESS LOGIC OPERATIONS
    @Override
    public List<ImageMaster> searchImages(String searchTerm) {
        return imageMasterRepository.findByImagePathContainingIgnoreCaseOrImageIsActiveContainingIgnoreCase(searchTerm, searchTerm);
    }

    @Override
    public List<ImageMaster> getImagesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ImageMaster> imagePage = imageMasterRepository.findAll(pageable);
        return imagePage.getContent();
    }

    @Override
    public List<ImageMaster> getImagesSortedByName() {
        return imageMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "imagePath"));
    }

    @Override
    public List<ImageMaster> getImagesSortedByType() {
        return imageMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "imagePath"));
    }

    @Override
    public List<ImageMaster> getImagesSortedByStatus() {
        return imageMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "imageIsActive"));
    }

    @Override
    public List<ImageMaster> getFeaturedImages() {
        // This would typically involve more complex logic, but for now we'll return active images
        return imageMasterRepository.findByImageIsActive(true);
    }

    @Override
    public List<ImageMaster> getRecentImages() {
        // This would typically involve more complex logic, but for now we'll return active images
        return imageMasterRepository.findByImageIsActive(true);
    }
} 