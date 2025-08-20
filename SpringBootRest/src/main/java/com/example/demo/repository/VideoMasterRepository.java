package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.VideoMaster;

@Repository
public interface VideoMasterRepository extends JpaRepository<VideoMaster, Integer> {
    
    // Basic find operations
    List<VideoMaster> findByVideoDescription(String videoDescription);
    List<VideoMaster> findByVideoUrl(String videoUrl);
    List<VideoMaster> findByVideoIsActive(Boolean videoIsActive);
    List<VideoMaster> findByVideoDescriptionContainingIgnoreCase(String videoDescription);
    List<VideoMaster> findByBatchIdBetween(Integer minBatchId, Integer maxBatchId);
    List<VideoMaster> findByVideoUrlAndBatchId(String videoUrl, Integer batchId);
    
    // Count operations
    Long countByVideoIsActive(Boolean videoIsActive);
    
    // Delete operations
    void deleteByVideoUrl(String videoUrl);
    void deleteByVideoIsActive(Boolean videoIsActive);
    
    // Search operations
    @Query("SELECT v FROM VideoMaster v WHERE LOWER(v.videoDescription) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(v.videoUrl) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<VideoMaster> findByVideoDescriptionContainingIgnoreCaseOrVideoUrlContainingIgnoreCase(@Param("searchTerm") String searchTerm, @Param("searchTerm") String searchTerm2);
} 