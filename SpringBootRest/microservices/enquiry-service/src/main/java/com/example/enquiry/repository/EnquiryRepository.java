package com.example.demo.repository;

import com.example.demo.model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {
    
    // Find enquiries by enquirer name (case-insensitive)
    List<Enquiry> findByEnquirerNameContainingIgnoreCase(String enquirerName);
    
    // Find enquiries by email
    List<Enquiry> findByEnquirerEmailId(String emailId);
    
    // Find enquiries by mobile number
    List<Enquiry> findByEnquirerMobile(Long mobile);
    
    // Find processed enquiries
    List<Enquiry> findByEnquiryProcessedFlagTrue();
    
    // Find unprocessed enquiries
    List<Enquiry> findByEnquiryProcessedFlagFalse();
    
    // Find enquiries by closure reason
    List<Enquiry> findByClosureReason(String closureReason);
    
    // Find enquiries by closure reason ID
    List<Enquiry> findByClosureReasonId(Integer closureReasonId);
    
    // Find enquiries by date range
    List<Enquiry> findByEnquiryDateBetween(LocalDate startDate, LocalDate endDate);
    
    // Find enquiries by date
    List<Enquiry> findByEnquiryDate(LocalDate enquiryDate);
    
    // Find enquiries by query keywords
    List<Enquiry> findByEnquirerQueryContainingIgnoreCase(String query);
    
    // Find enquiry by exact name and mobile
    Optional<Enquiry> findByEnquirerNameAndEnquirerMobile(String enquirerName, Long mobile);
    
    // Custom query to find enquiries with specific query keywords
    @Query("SELECT e FROM Enquiry e WHERE e.enquirerQuery LIKE %:keyword%")
    List<Enquiry> findByQueryKeyword(@Param("keyword") String keyword);
    
    // Custom query to count unprocessed enquiries
    @Query("SELECT COUNT(e) FROM Enquiry e WHERE e.enquiryProcessedFlag = false")
    Long countUnprocessedEnquiries();
    
    // Custom query to find enquiries by date range and status
    @Query("SELECT e FROM Enquiry e WHERE e.enquiryDate BETWEEN :startDate AND :endDate AND e.enquiryProcessedFlag = :processed")
    List<Enquiry> findByDateRangeAndStatus(@Param("startDate") LocalDate startDate, 
                                         @Param("endDate") LocalDate endDate, 
                                         @Param("processed") Boolean processed);
} 