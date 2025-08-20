package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ImageMaster;

@Repository
public interface ImageMasterRepository extends JpaRepository<ImageMaster, Integer> {
    
    // Basic find operations
    List<ImageMaster> findByImagePath(String imagePath);
    List<ImageMaster> findByImageIsActive(Boolean imageIsActive);
    List<ImageMaster> findByImagePathContainingIgnoreCase(String imagePath);
    
    // Count operations
    Long countByImageIsActive(Boolean imageIsActive);
    
    // Delete operations
    void deleteByImagePath(String imagePath);
    void deleteByImageIsActive(Boolean imageIsActive);
    
    // Search operations
    @Query("SELECT i FROM ImageMaster i WHERE LOWER(i.imagePath) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR CAST(i.imageIsActive AS string) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<ImageMaster> findByImagePathContainingIgnoreCaseOrImageIsActiveContainingIgnoreCase(@Param("searchTerm") String searchTerm, @Param("searchTerm") String searchTerm2);
} 