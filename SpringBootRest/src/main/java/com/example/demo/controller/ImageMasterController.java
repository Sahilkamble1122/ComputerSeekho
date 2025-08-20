package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ImageMaster;
import com.example.demo.service.ImageMasterService;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class ImageMasterController {

    @Autowired
    private ImageMasterService imageMasterService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<ImageMaster> createImage(@RequestBody ImageMaster image) {
        try {
            ImageMaster savedImage = imageMasterService.saveImage(image);
            return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ImageMaster>> createMultipleImages(@RequestBody List<ImageMaster> images) {
        try {
            List<ImageMaster> savedImages = imageMasterService.saveAllImages(images);
            return new ResponseEntity<>(savedImages, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected");
        }
        try {
            // Get the project root directory (where the application is running from)
            String projectRoot = System.getProperty("user.dir");
            String uploadsDir = projectRoot + "/uploads";
            
            // Create uploads directory if it doesn't exist
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get(uploadsDir));
            
            // Generate a safe filename
            String originalFilename = file.getOriginalFilename();
            String safeFilename = System.currentTimeMillis() + "_" +
                (originalFilename != null ? originalFilename.replaceAll("[^a-zA-Z0-9.\\-_]", "_") : "image");
            
            // Save the file
            String filePath = uploadsDir + "/" + safeFilename;
            file.transferTo(new File(filePath));
            
            // Return relative path for consistency
            String relativePath = "uploads/" + safeFilename;
            return ResponseEntity.ok("File uploaded successfully: " + relativePath);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating uploads directory: " + e.getMessage());
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<ImageMaster>> getAllImages() {
        try {
            List<ImageMaster> images = imageMasterService.getAllImages();
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageMaster> getImageById(@PathVariable Integer imageId) {
        try {
            Optional<ImageMaster> image = imageMasterService.getImageById(imageId);
            return image.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name/{imageName}")
    public ResponseEntity<List<ImageMaster>> getImagesByName(@PathVariable String imageName) {
        try {
            List<ImageMaster> images = imageMasterService.getImagesByName(imageName);
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/type/{imageType}")
    public ResponseEntity<List<ImageMaster>> getImagesByType(@PathVariable String imageType) {
        try {
            List<ImageMaster> images = imageMasterService.getImagesByType(imageType);
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<ImageMaster>> getImagesByStatus(@PathVariable String status) {
        try {
            List<ImageMaster> images = imageMasterService.getImagesByStatus(status);
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name-contains/{imageName}")
    public ResponseEntity<List<ImageMaster>> getImagesByNameContaining(@PathVariable String imageName) {
        try {
            List<ImageMaster> images = imageMasterService.getImagesByNameContaining(imageName);
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<ImageMaster>> getActiveImages() {
        try {
            List<ImageMaster> images = imageMasterService.getActiveImages();
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/active")
    public ResponseEntity<Long> countActiveImages() {
        try {
            Long count = imageMasterService.countActiveImages();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{imageId}")
    public ResponseEntity<ImageMaster> updateImage(@PathVariable Integer imageId, @RequestBody ImageMaster imageDetails) {
        try {
            ImageMaster updatedImage = imageMasterService.updateImage(imageId, imageDetails);
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{imageId}/name")
    public ResponseEntity<ImageMaster> updateImageName(@PathVariable Integer imageId, @RequestParam String imageName) {
        try {
            ImageMaster updatedImage = imageMasterService.updateImageName(imageId, imageName);
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{imageId}/type")
    public ResponseEntity<ImageMaster> updateImageType(@PathVariable Integer imageId, @RequestParam String imageType) {
        try {
            ImageMaster updatedImage = imageMasterService.updateImageType(imageId, imageType);
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{imageId}/status")
    public ResponseEntity<ImageMaster> updateImageStatus(@PathVariable Integer imageId, @RequestParam String status) {
        try {
            ImageMaster updatedImage = imageMasterService.updateImageStatus(imageId, status);
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer imageId) {
        try {
            imageMasterService.deleteImage(imageId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/type/{imageType}")
    public ResponseEntity<Void> deleteImagesByType(@PathVariable String imageType) {
        try {
            imageMasterService.deleteImagesByType(imageType);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deleteImagesByStatus(@PathVariable String status) {
        try {
            imageMasterService.deleteImagesByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/inactive")
    public ResponseEntity<Void> deleteInactiveImages() {
        try {
            imageMasterService.deleteInactiveImages();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllImages() {
        try {
            imageMasterService.deleteAllImages();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<ImageMaster>> searchImages(@PathVariable String searchTerm) {
        try {
            List<ImageMaster> images = imageMasterService.searchImages(searchTerm);
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<ImageMaster>> getImagesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<ImageMaster> images = imageMasterService.getImagesWithPagination(page, size);
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/name")
    public ResponseEntity<List<ImageMaster>> getImagesSortedByName() {
        try {
            List<ImageMaster> images = imageMasterService.getImagesSortedByName();
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/type")
    public ResponseEntity<List<ImageMaster>> getImagesSortedByType() {
        try {
            List<ImageMaster> images = imageMasterService.getImagesSortedByType();
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/status")
    public ResponseEntity<List<ImageMaster>> getImagesSortedByStatus() {
        try {
            List<ImageMaster> images = imageMasterService.getImagesSortedByStatus();
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/featured")
    public ResponseEntity<List<ImageMaster>> getFeaturedImages() {
        try {
            List<ImageMaster> images = imageMasterService.getFeaturedImages();
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recent")
    public ResponseEntity<List<ImageMaster>> getRecentImages() {
        try {
            List<ImageMaster> images = imageMasterService.getRecentImages();
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 