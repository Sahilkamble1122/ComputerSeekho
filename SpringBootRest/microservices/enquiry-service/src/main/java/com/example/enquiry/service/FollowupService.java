package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Followup;

public interface FollowupService {
    
    // CREATE OPERATIONS
    Followup saveFollowup(Followup followup);
    List<Followup> saveAllFollowups(List<Followup> followups);
    
    // READ OPERATIONS
    List<Followup> getAllFollowups();
    Optional<Followup> getFollowupById(Integer followupId);
    List<Followup> getFollowupsByEnquiryId(Integer enquiryId);
    List<Followup> getFollowupsByStudentId(Integer studentId);
    List<Followup> getFollowupsByStatus(String status);
    List<Followup> getFollowupsByType(String followupType);
    List<Followup> getFollowupsByDateRange(String startDate, String endDate);
    List<Followup> getFollowupsByPriority(String priority);
    Long countFollowupsByEnquiryId(Integer enquiryId);
    Long countFollowupsByStatus(String status);
    
    // UPDATE OPERATIONS
    Followup updateFollowup(Integer followupId, Followup followupDetails);
    Followup updateFollowupStatus(Integer followupId, String status);
    Followup updateFollowupType(Integer followupId, String followupType);
    Followup updateFollowupPriority(Integer followupId, String priority);
    Followup updateFollowupNotes(Integer followupId, String notes);
    
    // DELETE OPERATIONS
    void deleteFollowup(Integer followupId);
    void deleteFollowupsByEnquiryId(Integer enquiryId);
    void deleteFollowupsByStatus(String status);
    void deleteAllFollowups();
    
    // BUSINESS LOGIC OPERATIONS
    List<Followup> searchFollowups(String searchTerm);
    List<Followup> getFollowupsByEnquiryAndStatus(Integer enquiryId, String status);
    List<Followup> getFollowupsWithPagination(int page, int size);
    List<Followup> getFollowupsSortedByDate();
    List<Followup> getFollowupsSortedByPriority();
    List<Followup> getFollowupsSortedByType();
    List<Followup> getPendingFollowups();
    List<Followup> getCompletedFollowups();
    List<Followup> getOverdueFollowups();
    List<Followup> getHighPriorityFollowups();
    List<Followup> getTodayFollowups();
    List<Followup> getUpcomingFollowups();
} 