package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Followup;
import com.example.demo.repository.FollowupRepository;
import com.example.demo.service.FollowupService;

@Service
public class FollowupServiceImpl implements FollowupService {

    @Autowired
    private FollowupRepository followupRepository;

    // CREATE OPERATIONS
    @Override
    public Followup saveFollowup(Followup followup) {
        return followupRepository.save(followup);
    }

    @Override
    public List<Followup> saveAllFollowups(List<Followup> followups) {
        return followupRepository.saveAll(followups);
    }

    // READ OPERATIONS
    @Override
    public List<Followup> getAllFollowups() {
        return followupRepository.findAll();
    }

    @Override
    public Optional<Followup> getFollowupById(Integer followupId) {
        return followupRepository.findById(followupId);
    }

    @Override
    public List<Followup> getFollowupsByEnquiryId(Integer enquiryId) {
        return followupRepository.findByEnquiryId(enquiryId);
    }

    @Override
    public List<Followup> getFollowupsByStudentId(Integer studentId) {
        // Since Followup doesn't have studentId, we'll use staffId as a substitute
        return followupRepository.findByStaffId(studentId);
    }

    @Override
    public List<Followup> getFollowupsByStatus(String status) {
        // Convert status string to boolean for active/inactive
        Boolean isActive = "Active".equalsIgnoreCase(status);
        return followupRepository.findByIsActive(isActive);
    }

    @Override
    public List<Followup> getFollowupsByType(String followupType) {
        // Since Followup doesn't have followupType, we'll use followupMsg as a substitute
        return followupRepository.findByFollowupMsgContainingIgnoreCase(followupType);
    }

    @Override
    public List<Followup> getFollowupsByDateRange(String startDate, String endDate) {
        return followupRepository.findByFollowupDateBetween(startDate, endDate);
    }

    @Override
    public List<Followup> getFollowupsByPriority(String priority) {
        // Since Followup doesn't have priority, we'll use followupMsg as a substitute
        return followupRepository.findByFollowupMsgContainingIgnoreCase(priority);
    }

    @Override
    public Long countFollowupsByEnquiryId(Integer enquiryId) {
        return followupRepository.countByEnquiryId(enquiryId);
    }

    @Override
    public Long countFollowupsByStatus(String status) {
        Boolean isActive = "Active".equalsIgnoreCase(status);
        return followupRepository.countByIsActive(isActive);
    }

    // UPDATE OPERATIONS
    @Override
    public Followup updateFollowup(Integer followupId, Followup followupDetails) {
        Followup existingFollowup = followupRepository.findById(followupId)
                .orElseThrow(() -> new RuntimeException("Followup not found with id: " + followupId));
        
        existingFollowup.setEnquiryId(followupDetails.getEnquiryId());
        existingFollowup.setStaffId(followupDetails.getStaffId());
        existingFollowup.setFollowupDate(followupDetails.getFollowupDate());
        existingFollowup.setFollowupMsg(followupDetails.getFollowupMsg());
        existingFollowup.setIsActive(followupDetails.getIsActive());
        existingFollowup.setUpdatedDate(LocalDateTime.now());
        
        return followupRepository.save(existingFollowup);
    }

    @Override
    public Followup updateFollowupStatus(Integer followupId, String status) {
        Followup followup = followupRepository.findById(followupId)
                .orElseThrow(() -> new RuntimeException("Followup not found with id: " + followupId));
        Boolean isActive = "Active".equalsIgnoreCase(status);
        followup.setIsActive(isActive);
        followup.setUpdatedDate(LocalDateTime.now());
        return followupRepository.save(followup);
    }

    @Override
    public Followup updateFollowupType(Integer followupId, String followupType) {
        Followup followup = followupRepository.findById(followupId)
                .orElseThrow(() -> new RuntimeException("Followup not found with id: " + followupId));
        followup.setFollowupMsg(followupType);
        followup.setUpdatedDate(LocalDateTime.now());
        return followupRepository.save(followup);
    }

    @Override
    public Followup updateFollowupPriority(Integer followupId, String priority) {
        Followup followup = followupRepository.findById(followupId)
                .orElseThrow(() -> new RuntimeException("Followup not found with id: " + followupId));
        followup.setFollowupMsg(priority);
        followup.setUpdatedDate(LocalDateTime.now());
        return followupRepository.save(followup);
    }

    @Override
    public Followup updateFollowupNotes(Integer followupId, String notes) {
        Followup followup = followupRepository.findById(followupId)
                .orElseThrow(() -> new RuntimeException("Followup not found with id: " + followupId));
        followup.setFollowupMsg(notes);
        followup.setUpdatedDate(LocalDateTime.now());
        return followupRepository.save(followup);
    }

    // DELETE OPERATIONS
    @Override
    public void deleteFollowup(Integer followupId) {
        if (!followupRepository.existsById(followupId)) {
            throw new RuntimeException("Followup not found with id: " + followupId);
        }
        followupRepository.deleteById(followupId);
    }

    @Override
    public void deleteFollowupsByEnquiryId(Integer enquiryId) {
        followupRepository.deleteByEnquiryId(enquiryId);
    }

    @Override
    public void deleteFollowupsByStatus(String status) {
        Boolean isActive = "Active".equalsIgnoreCase(status);
        followupRepository.deleteByIsActive(isActive);
    }

    @Override
    public void deleteAllFollowups() {
        followupRepository.deleteAll();
    }

    // BUSINESS LOGIC OPERATIONS
    @Override
    public List<Followup> searchFollowups(String searchTerm) {
        return followupRepository.findByFollowupMsgContainingIgnoreCaseOrIsActiveContainingIgnoreCase(searchTerm, searchTerm);
    }

    @Override
    public List<Followup> getFollowupsByEnquiryAndStatus(Integer enquiryId, String status) {
        Boolean isActive = "Active".equalsIgnoreCase(status);
        return followupRepository.findByEnquiryIdAndIsActive(enquiryId, isActive);
    }

    @Override
    public List<Followup> getFollowupsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Followup> followupPage = followupRepository.findAll(pageable);
        return followupPage.getContent();
    }

    @Override
    public List<Followup> getFollowupsSortedByDate() {
        return followupRepository.findAll(Sort.by(Sort.Direction.ASC, "followupDate"));
    }

    @Override
    public List<Followup> getFollowupsSortedByPriority() {
        return followupRepository.findAll(Sort.by(Sort.Direction.ASC, "followupMsg"));
    }

    @Override
    public List<Followup> getFollowupsSortedByType() {
        return followupRepository.findAll(Sort.by(Sort.Direction.ASC, "followupMsg"));
    }

    @Override
    public List<Followup> getPendingFollowups() {
        return followupRepository.findByIsActive(false);
    }

    @Override
    public List<Followup> getCompletedFollowups() {
        return followupRepository.findByIsActive(true);
    }

    @Override
    public List<Followup> getOverdueFollowups() {
        LocalDate today = LocalDate.now();
        return followupRepository.findByFollowupDateBeforeAndIsActive(today, false);
    }

    @Override
    public List<Followup> getHighPriorityFollowups() {
        return followupRepository.findByFollowupMsgContainingIgnoreCase("High");
    }

    @Override
    public List<Followup> getTodayFollowups() {
        LocalDate today = LocalDate.now();
        return followupRepository.findByFollowupDate(today);
    }

    @Override
    public List<Followup> getUpcomingFollowups() {
        LocalDate today = LocalDate.now();
        return followupRepository.findByFollowupDateAfterAndIsActive(today, false);
    }
} 