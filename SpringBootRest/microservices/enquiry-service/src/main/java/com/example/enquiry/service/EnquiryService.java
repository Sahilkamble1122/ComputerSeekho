package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Enquiry;

public interface EnquiryService {
    
    // Create operations
    Enquiry saveEnquiry(Enquiry enquiry);
    List<Enquiry> saveAllEnquiries(List<Enquiry> enquiries);
    
    // Read operations
    List<Enquiry> getAllEnquiries();
    Optional<Enquiry> getEnquiryById(Integer enquiryId);
    List<Enquiry> getEnquiriesByStudentName(String studentName);
    List<Enquiry> getEnquiriesByMobile(Long mobile);
    List<Enquiry> getEnquiriesByEmail(String email);
    List<Enquiry> getEnquiriesByStatus(String status);
    List<Enquiry> getEnquiriesByStudentNameContaining(String studentName);
    List<Enquiry> getEnquiriesByEmailContaining(String email);
    List<Enquiry> getEnquiriesByDateRange(String startDate, String endDate);
    Long countEnquiriesByStatus(String status);
    
    // Update operations
    Enquiry updateEnquiry(Integer enquiryId, Enquiry enquiryDetails);
    Enquiry updateEnquiryStatus(Integer enquiryId, String status);
    Enquiry updateEnquiryStudentName(Integer enquiryId, String studentName);
    Enquiry updateEnquiryMobile(Integer enquiryId, Long mobile);
    Enquiry updateEnquiryEmail(Integer enquiryId, String email);
    Enquiry updateEnquiryCounter(Integer enquiryId, Integer enquiryCounter);
    Enquiry updateEnquiryClosure(Integer enquiryId, Integer closureReasonId, String closureReason);
    
    // Delete operations
    void deleteEnquiry(Integer enquiryId);
    void deleteEnquiriesByStatus(String status);
    void deleteAllEnquiries();
    
    // Business logic operations
    List<Enquiry> searchEnquiries(String searchTerm);
    List<Enquiry> getEnquiriesByDateRangeAndCourse(String startDate, String endDate, Integer courseId);
    List<Enquiry> getEnquiriesWithPagination(int page, int size);
    List<Enquiry> getEnquiriesSortedByDate();
    List<Enquiry> getEnquiriesSortedByStudentName();
    List<Enquiry> getEnquiriesSortedByStatus();
    List<Enquiry> getPendingEnquiries();
    List<Enquiry> getCompletedEnquiries();
    List<Enquiry> getFollowUpRequiredEnquiries();
} 