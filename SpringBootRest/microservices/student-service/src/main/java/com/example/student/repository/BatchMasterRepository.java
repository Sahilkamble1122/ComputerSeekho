package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BatchMaster;

@Repository
public interface BatchMasterRepository extends JpaRepository<BatchMaster, Integer> {
    
    // Basic find operations
    List<BatchMaster> findByBatchName(String batchName);
    List<BatchMaster> findByCourseId(Integer courseId);
    List<BatchMaster> findByStaffId(Integer staffId);
    List<BatchMaster> findByBatchNameContainingIgnoreCase(String batchName);
    List<BatchMaster> findByCourseIdAndStaffId(Integer courseId, Integer staffId);
    
    // Additional find operations for BatchMaster entity
    List<BatchMaster> findByBatchIsActive(Boolean batchIsActive);
    List<BatchMaster> findByCourseFeesBetween(Integer minFees, Integer maxFees);
    List<BatchMaster> findByBatchIsActiveAndCourseFeesGreaterThan(Boolean batchIsActive, Integer courseFees);
    List<BatchMaster> findByBatchIsActiveAndCourseFees(Boolean batchIsActive, Integer courseFees);
    
    // Count operations
    Long countByCourseId(Integer courseId);
    Long countByStaffId(Integer staffId);
    
    // Delete operations
    void deleteByCourseId(Integer courseId);
    void deleteByStaffId(Integer staffId);
    void deleteByBatchIsActive(Boolean batchIsActive);
    
    // Search operations
    @Query("SELECT b FROM BatchMaster b WHERE LOWER(b.batchName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR CAST(b.batchIsActive AS string) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<BatchMaster> findByBatchNameContainingIgnoreCaseOrStatusContainingIgnoreCase(@Param("searchTerm") String searchTerm, @Param("searchTerm") String searchTerm2);
} 