package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Placement;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, Integer> {
    
    // Basic find operations
    List<Placement> findByStudentId(Integer studentId);
    List<Placement> findByCompanyName(String companyName);
    List<Placement> findByDesignation(String designation);
    List<Placement> findByIsPlaced(Boolean isPlaced);
    List<Placement> findByBatchIdBetween(Integer minBatchId, Integer maxBatchId);
    List<Placement> findByCompanyNameAndDesignation(String companyName, String designation);
    
    // Count operations
    Long countByStudentId(Integer studentId);
    Long countByIsPlaced(Boolean isPlaced);
    
    // Delete operations
    void deleteByStudentId(Integer studentId);
    void deleteByIsPlaced(Boolean isPlaced);
    
    // Search operations
    @Query("SELECT p FROM Placement p WHERE LOWER(p.companyName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.designation) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR CAST(p.isPlaced AS string) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Placement> findByCompanyNameContainingIgnoreCaseOrDesignationContainingIgnoreCaseOrIsPlacedContainingIgnoreCase(@Param("searchTerm") String searchTerm, @Param("searchTerm") String searchTerm2, @Param("searchTerm") String searchTerm3);
    
    // Date range operations
    @Query("SELECT p FROM Placement p WHERE p.placementDate BETWEEN :startDate AND :endDate")
    List<Placement> findByPlacementDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    // Average batchId operations (as substitute for salary)
    @Query("SELECT AVG(p.batchId) FROM Placement p WHERE p.companyName = :companyName")
    Double findAverageBatchIdByCompanyName(@Param("companyName") String companyName);
    
    // Sum operations
    @Query("SELECT SUM(p.batchId) FROM Placement p WHERE p.studentId = :studentId")
    Double sumBatchIdByStudentId(@Param("studentId") Integer studentId);
} 