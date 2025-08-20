package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Followup;

@Repository
public interface FollowupRepository extends JpaRepository<Followup, Integer> {
    
    // Basic find operations
    List<Followup> findByEnquiryId(Integer enquiryId);
    List<Followup> findByStaffId(Integer staffId);
    List<Followup> findByIsActive(Boolean isActive);
    List<Followup> findByFollowupMsgContainingIgnoreCase(String followupMsg);
    List<Followup> findByFollowupDate(LocalDate followupDate);
    List<Followup> findByFollowupDateBeforeAndIsActive(LocalDate followupDate, Boolean isActive);
    List<Followup> findByFollowupDateAfterAndIsActive(LocalDate followupDate, Boolean isActive);
    List<Followup> findByEnquiryIdAndIsActive(Integer enquiryId, Boolean isActive);
    
    // Count operations
    Long countByEnquiryId(Integer enquiryId);
    Long countByIsActive(Boolean isActive);
    
    // Delete operations
    void deleteByEnquiryId(Integer enquiryId);
    void deleteByIsActive(Boolean isActive);
    
    // Search operations
    @Query("SELECT f FROM Followup f WHERE LOWER(f.followupMsg) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR CAST(f.isActive AS string) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Followup> findByFollowupMsgContainingIgnoreCaseOrIsActiveContainingIgnoreCase(@Param("searchTerm") String searchTerm, @Param("searchTerm") String searchTerm2);
    
    // Date range operations
    @Query("SELECT f FROM Followup f WHERE f.followupDate BETWEEN :startDate AND :endDate")
    List<Followup> findByFollowupDateBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);
} 