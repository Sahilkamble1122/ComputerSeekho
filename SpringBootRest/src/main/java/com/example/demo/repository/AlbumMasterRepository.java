package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AlbumMaster;

@Repository
public interface AlbumMasterRepository extends JpaRepository<AlbumMaster, Integer> {
    
    // Basic find operations
    List<AlbumMaster> findByAlbumName(String albumName);
    List<AlbumMaster> findByAlbumDescription(String albumDescription);
    List<AlbumMaster> findByAlbumIsActive(Boolean albumIsActive);
    List<AlbumMaster> findByAlbumNameContainingIgnoreCase(String albumName);
    List<AlbumMaster> findByAlbumIsActiveTrue();
    
    // Count operations
    Long countByAlbumIsActive(Boolean albumIsActive);
    
    // Delete operations
    void deleteByAlbumIsActive(Boolean albumIsActive);
    void deleteByAlbumIsActiveFalse();
    
    // Search operations
    @Query("SELECT a FROM AlbumMaster a WHERE LOWER(a.albumName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.albumDescription) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<AlbumMaster> findByAlbumNameContainingIgnoreCaseOrAlbumDescriptionContainingIgnoreCase(@Param("searchTerm") String searchTerm);
} 