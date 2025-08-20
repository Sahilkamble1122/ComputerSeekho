package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Followup;
import com.example.demo.service.FollowupService;

@RestController
@RequestMapping("/api/followups")
@CrossOrigin(origins = "*")
public class FollowupController {

    @Autowired
    private FollowupService followupService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<Followup> createFollowup(@RequestBody Followup followup) {
        try {
            Followup savedFollowup = followupService.saveFollowup(followup);
            return new ResponseEntity<>(savedFollowup, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Followup>> createMultipleFollowups(@RequestBody List<Followup> followups) {
        try {
            List<Followup> savedFollowups = followupService.saveAllFollowups(followups);
            return new ResponseEntity<>(savedFollowups, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<Followup>> getAllFollowups() {
        try {
            List<Followup> followups = followupService.getAllFollowups();
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{followupId}")
    public ResponseEntity<Followup> getFollowupById(@PathVariable Integer followupId) {
        try {
            Optional<Followup> followup = followupService.getFollowupById(followupId);
            return followup.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/enquiry/{enquiryId}")
    public ResponseEntity<List<Followup>> getFollowupsByEnquiryId(@PathVariable Integer enquiryId) {
        try {
            List<Followup> followups = followupService.getFollowupsByEnquiryId(enquiryId);
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/student/{studentId}")
    public ResponseEntity<List<Followup>> getFollowupsByStudentId(@PathVariable Integer studentId) {
        try {
            List<Followup> followups = followupService.getFollowupsByStudentId(studentId);
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<Followup>> getFollowupsByStatus(@PathVariable String status) {
        try {
            List<Followup> followups = followupService.getFollowupsByStatus(status);
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/type/{followupType}")
    public ResponseEntity<List<Followup>> getFollowupsByType(@PathVariable String followupType) {
        try {
            List<Followup> followups = followupService.getFollowupsByType(followupType);
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/date-range")
    public ResponseEntity<List<Followup>> getFollowupsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            List<Followup> followups = followupService.getFollowupsByDateRange(startDate, endDate);
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/priority/{priority}")
    public ResponseEntity<List<Followup>> getFollowupsByPriority(@PathVariable String priority) {
        try {
            List<Followup> followups = followupService.getFollowupsByPriority(priority);
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/enquiry/{enquiryId}")
    public ResponseEntity<Long> countFollowupsByEnquiryId(@PathVariable Integer enquiryId) {
        try {
            Long count = followupService.countFollowupsByEnquiryId(enquiryId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countFollowupsByStatus(@PathVariable String status) {
        try {
            Long count = followupService.countFollowupsByStatus(status);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{followupId}")
    public ResponseEntity<Followup> updateFollowup(@PathVariable Integer followupId, @RequestBody Followup followupDetails) {
        try {
            Followup updatedFollowup = followupService.updateFollowup(followupId, followupDetails);
            return new ResponseEntity<>(updatedFollowup, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{followupId}/status")
    public ResponseEntity<Followup> updateFollowupStatus(@PathVariable Integer followupId, @RequestParam String status) {
        try {
            Followup updatedFollowup = followupService.updateFollowupStatus(followupId, status);
            return new ResponseEntity<>(updatedFollowup, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{followupId}/type")
    public ResponseEntity<Followup> updateFollowupType(@PathVariable Integer followupId, @RequestParam String followupType) {
        try {
            Followup updatedFollowup = followupService.updateFollowupType(followupId, followupType);
            return new ResponseEntity<>(updatedFollowup, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{followupId}/priority")
    public ResponseEntity<Followup> updateFollowupPriority(@PathVariable Integer followupId, @RequestParam String priority) {
        try {
            Followup updatedFollowup = followupService.updateFollowupPriority(followupId, priority);
            return new ResponseEntity<>(updatedFollowup, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{followupId}/notes")
    public ResponseEntity<Followup> updateFollowupNotes(@PathVariable Integer followupId, @RequestParam String notes) {
        try {
            Followup updatedFollowup = followupService.updateFollowupNotes(followupId, notes);
            return new ResponseEntity<>(updatedFollowup, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{followupId}")
    public ResponseEntity<Void> deleteFollowup(@PathVariable Integer followupId) {
        try {
            followupService.deleteFollowup(followupId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/enquiry/{enquiryId}")
    public ResponseEntity<Void> deleteFollowupsByEnquiryId(@PathVariable Integer enquiryId) {
        try {
            followupService.deleteFollowupsByEnquiryId(enquiryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deleteFollowupsByStatus(@PathVariable String status) {
        try {
            followupService.deleteFollowupsByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllFollowups() {
        try {
            followupService.deleteAllFollowups();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<Followup>> searchFollowups(@PathVariable String searchTerm) {
        try {
            List<Followup> followups = followupService.searchFollowups(searchTerm);
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/enquiry-status")
    public ResponseEntity<List<Followup>> getFollowupsByEnquiryAndStatus(
            @RequestParam Integer enquiryId,
            @RequestParam String status) {
        try {
            List<Followup> followups = followupService.getFollowupsByEnquiryAndStatus(enquiryId, status);
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Followup>> getFollowupsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Followup> followups = followupService.getFollowupsWithPagination(page, size);
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/date")
    public ResponseEntity<List<Followup>> getFollowupsSortedByDate() {
        try {
            List<Followup> followups = followupService.getFollowupsSortedByDate();
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/priority")
    public ResponseEntity<List<Followup>> getFollowupsSortedByPriority() {
        try {
            List<Followup> followups = followupService.getFollowupsSortedByPriority();
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/type")
    public ResponseEntity<List<Followup>> getFollowupsSortedByType() {
        try {
            List<Followup> followups = followupService.getFollowupsSortedByType();
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Followup>> getPendingFollowups() {
        try {
            List<Followup> followups = followupService.getPendingFollowups();
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Followup>> getCompletedFollowups() {
        try {
            List<Followup> followups = followupService.getCompletedFollowups();
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Followup>> getOverdueFollowups() {
        try {
            List<Followup> followups = followupService.getOverdueFollowups();
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/high-priority")
    public ResponseEntity<List<Followup>> getHighPriorityFollowups() {
        try {
            List<Followup> followups = followupService.getHighPriorityFollowups();
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/today")
    public ResponseEntity<List<Followup>> getTodayFollowups() {
        try {
            List<Followup> followups = followupService.getTodayFollowups();
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Followup>> getUpcomingFollowups() {
        try {
            List<Followup> followups = followupService.getUpcomingFollowups();
            return new ResponseEntity<>(followups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 