package com.example.demo.controller;

import java.util.List;
import java.util.Map;
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

import com.example.demo.dto.EnquiryClosurePatchDTO;
import com.example.demo.model.Enquiry;
import com.example.demo.service.EnquiryService;

@RestController
@RequestMapping("/api/enquiries")
@CrossOrigin(origins = "*")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<Object> createEnquiry(@RequestBody Enquiry enquiry) {
        try {
            // Ensure POST always creates a new row (avoid stale update when ID is sent)
            enquiry.setEnquiryId(null);
            // Debug: Log the received enquiry data
            System.out.println("Received enquiry data:");
            System.out.println("enquirerName: " + enquiry.getEnquirerName());
            System.out.println("enquirerMobile: " + enquiry.getEnquirerMobile());
            System.out.println("enquirerEmailId: " + enquiry.getEnquirerEmailId());
            System.out.println("enquiryDate: " + enquiry.getEnquiryDate());
            System.out.println("enquiryCounter: " + enquiry.getEnquiryCounter());
            System.out.println("enquiryProcessedFlag: " + enquiry.getEnquiryProcessedFlag());
            
            Enquiry savedEnquiry = enquiryService.saveEnquiry(enquiry);
            return new ResponseEntity<>(savedEnquiry, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Return specific validation error message
            System.out.println("Validation error: " + e.getMessage());
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Return generic error for other exceptions
            System.out.println("General error: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("error", "Failed to create enquiry: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Enquiry>> createMultipleEnquiries(@RequestBody List<Enquiry> enquiries) {
        try {
            // Force INSERT for each record
            if (enquiries != null) {
                enquiries.forEach(e -> e.setEnquiryId(null));
            }
            List<Enquiry> savedEnquiries = enquiryService.saveAllEnquiries(enquiries);
            return new ResponseEntity<>(savedEnquiries, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Removed debug endpoint used for testing enquiry creation

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<Enquiry>> getAllEnquiries() {
        try {
            List<Enquiry> enquiries = enquiryService.getAllEnquiries();
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{enquiryId}")
    public ResponseEntity<Enquiry> getEnquiryById(@PathVariable Integer enquiryId) {
        try {
            Optional<Enquiry> enquiry = enquiryService.getEnquiryById(enquiryId);
            return enquiry.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name/{studentName}")
    public ResponseEntity<List<Enquiry>> getEnquiriesByStudentName(@PathVariable String studentName) {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesByStudentName(studentName);
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/mobile/{mobile}")
    public ResponseEntity<List<Enquiry>> getEnquiriesByMobile(@PathVariable Long mobile) {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesByMobile(mobile);
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<List<Enquiry>> getEnquiriesByEmail(@PathVariable String email) {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesByEmail(email);
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<Enquiry>> getEnquiriesByStatus(@PathVariable String status) {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesByStatus(status);
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name-contains/{studentName}")
    public ResponseEntity<List<Enquiry>> getEnquiriesByStudentNameContaining(@PathVariable String studentName) {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesByStudentNameContaining(studentName);
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/email-contains/{email}")
    public ResponseEntity<List<Enquiry>> getEnquiriesByEmailContaining(@PathVariable String email) {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesByEmailContaining(email);
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/date-range")
    public ResponseEntity<List<Enquiry>> getEnquiriesByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesByDateRange(startDate, endDate);
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countEnquiriesByStatus(@PathVariable String status) {
        try {
            Long count = enquiryService.countEnquiriesByStatus(status);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{enquiryId}")
    public ResponseEntity<Enquiry> updateEnquiry(@PathVariable Integer enquiryId, @RequestBody Enquiry enquiryDetails) {
        try {
            Enquiry updatedEnquiry = enquiryService.updateEnquiry(enquiryId, enquiryDetails);
            return new ResponseEntity<>(updatedEnquiry, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enquiryId}/status")
    public ResponseEntity<Enquiry> updateEnquiryStatus(@PathVariable Integer enquiryId, @RequestParam String status) {
        try {
            Enquiry updatedEnquiry = enquiryService.updateEnquiryStatus(enquiryId, status);
            return new ResponseEntity<>(updatedEnquiry, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enquiryId}/name")
    public ResponseEntity<Enquiry> updateEnquiryStudentName(@PathVariable Integer enquiryId, @RequestParam String studentName) {
        try {
            Enquiry updatedEnquiry = enquiryService.updateEnquiryStudentName(enquiryId, studentName);
            return new ResponseEntity<>(updatedEnquiry, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enquiryId}/mobile")
    public ResponseEntity<Enquiry> updateEnquiryMobile(@PathVariable Integer enquiryId, @RequestParam Long mobile) {
        try {
            Enquiry updatedEnquiry = enquiryService.updateEnquiryMobile(enquiryId, mobile);
            return new ResponseEntity<>(updatedEnquiry, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enquiryId}/email")
    public ResponseEntity<Enquiry> updateEnquiryEmail(@PathVariable Integer enquiryId, @RequestParam String email) {
        try {
            Enquiry updatedEnquiry = enquiryService.updateEnquiryEmail(enquiryId, email);
            return new ResponseEntity<>(updatedEnquiry, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enquiryId}/counter")
    public ResponseEntity<Enquiry> updateEnquiryCounter(@PathVariable Integer enquiryId, @RequestParam Integer enquiryCounter) {
        try {
            Enquiry updatedEnquiry = enquiryService.updateEnquiryCounter(enquiryId, enquiryCounter);
            return new ResponseEntity<>(updatedEnquiry, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enquiryId}/closure")
    public ResponseEntity<Enquiry> updateEnquiryClosure(
            @PathVariable Integer enquiryId,
            @RequestBody EnquiryClosurePatchDTO closurePatch) {
        try {
            Enquiry updated = enquiryService.updateEnquiryClosure(enquiryId, closurePatch.getClosureReasonId(), closurePatch.getClosureReason());
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enquiryId}/state")
    public ResponseEntity<Enquiry> updateEnquiryState(
            @PathVariable Integer enquiryId,
            @RequestParam Boolean enquiryState) {
        try {
            Enquiry updated = enquiryService.updateEnquiryState(enquiryId, enquiryState);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{enquiryId}")
    public ResponseEntity<Void> deleteEnquiry(@PathVariable Integer enquiryId) {
        try {
            enquiryService.deleteEnquiry(enquiryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deleteEnquiriesByStatus(@PathVariable String status) {
        try {
            enquiryService.deleteEnquiriesByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllEnquiries() {
        try {
            enquiryService.deleteAllEnquiries();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<Enquiry>> searchEnquiries(@PathVariable String searchTerm) {
        try {
            List<Enquiry> enquiries = enquiryService.searchEnquiries(searchTerm);
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/date-range-course")
    public ResponseEntity<List<Enquiry>> getEnquiriesByDateRangeAndCourse(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Integer courseId) {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesByDateRangeAndCourse(startDate, endDate, courseId);
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Enquiry>> getEnquiriesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesWithPagination(page, size);
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/date")
    public ResponseEntity<List<Enquiry>> getEnquiriesSortedByDate() {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesSortedByDate();
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/name")
    public ResponseEntity<List<Enquiry>> getEnquiriesSortedByStudentName() {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesSortedByStudentName();
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/status")
    public ResponseEntity<List<Enquiry>> getEnquiriesSortedByStatus() {
        try {
            List<Enquiry> enquiries = enquiryService.getEnquiriesSortedByStatus();
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Enquiry>> getPendingEnquiries() {
        try {
            List<Enquiry> enquiries = enquiryService.getPendingEnquiries();
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Enquiry>> getCompletedEnquiries() {
        try {
            List<Enquiry> enquiries = enquiryService.getCompletedEnquiries();
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/follow-up-required")
    public ResponseEntity<List<Enquiry>> getFollowUpRequiredEnquiries() {
        try {
            List<Enquiry> enquiries = enquiryService.getFollowUpRequiredEnquiries();
            return new ResponseEntity<>(enquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 