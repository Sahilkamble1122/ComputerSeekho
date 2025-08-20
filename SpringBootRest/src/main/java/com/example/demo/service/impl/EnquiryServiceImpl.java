package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Enquiry;
import com.example.demo.repository.EnquiryRepository;
import com.example.demo.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private EnquiryRepository enquiryRepository;

    // Create operations
    @Override
    public Enquiry saveEnquiry(Enquiry enquiry) {
        // Set default values if not provided
        if (enquiry.getEnquiryDate() == null) {
            enquiry.setEnquiryDate(LocalDate.now());
        }
        
        if (enquiry.getEnquiryCounter() == null) {
            enquiry.setEnquiryCounter(0);
        }
        
        if (enquiry.getEnquiryProcessedFlag() == null) {
            enquiry.setEnquiryProcessedFlag(false);
        }
        
        if (enquiry.getCreatedDate() == null) {
            enquiry.setCreatedDate(java.time.LocalDateTime.now());
        }
        
        if (enquiry.getUpdatedDate() == null) {
            enquiry.setUpdatedDate(java.time.LocalDateTime.now());
        }
        
        // Only validate if values are provided (allow null for now)
        if (enquiry.getEnquirerName() != null && enquiry.getEnquirerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Enquirer name cannot be empty if provided");
        }
        
        if (enquiry.getEnquirerEmailId() != null && !enquiry.getEnquirerEmailId().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        if (enquiry.getEnquirerMobile() != null && enquiry.getEnquirerMobile().toString().length() < 10) {
            throw new IllegalArgumentException("Mobile number must be at least 10 digits");
        }
        
        return enquiryRepository.save(enquiry);
    }

    @Override
    public List<Enquiry> saveAllEnquiries(List<Enquiry> enquiries) {
        return enquiryRepository.saveAll(enquiries);
    }

    // Read operations
    @Override
    public List<Enquiry> getAllEnquiries() {
        return enquiryRepository.findAll();
    }

    @Override
    public Optional<Enquiry> getEnquiryById(Integer enquiryId) {
        return enquiryRepository.findById(enquiryId);
    }

    @Override
    public List<Enquiry> getEnquiriesByStudentName(String studentName) {
        return enquiryRepository.findByEnquirerNameContainingIgnoreCase(studentName);
    }

    @Override
    public List<Enquiry> getEnquiriesByMobile(Long mobile) {
        return enquiryRepository.findByEnquirerMobile(mobile);
    }

    @Override
    public List<Enquiry> getEnquiriesByEmail(String email) {
        return enquiryRepository.findByEnquirerEmailId(email);
    }

    @Override
    public List<Enquiry> getEnquiriesByStatus(String status) {
        // Filter in service since method doesn't exist directly for String status
        List<Enquiry> allEnquiries = enquiryRepository.findAll();
        Boolean processedFlag = null;
        if ("true".equalsIgnoreCase(status) || "processed".equalsIgnoreCase(status)) {
            processedFlag = true;
        } else if ("false".equalsIgnoreCase(status) || "unprocessed".equalsIgnoreCase(status) || "pending".equalsIgnoreCase(status)) {
            processedFlag = false;
        }

        if (processedFlag != null) {
            Boolean finalProcessedFlag = processedFlag;
            return allEnquiries.stream()
                    .filter(enquiry -> enquiry.getEnquiryProcessedFlag() != null &&
                                    enquiry.getEnquiryProcessedFlag().equals(finalProcessedFlag))
                    .collect(Collectors.toList());
        }
        return List.of(); // Return empty list if status is not recognized
    }

    @Override
    public List<Enquiry> getEnquiriesByStudentNameContaining(String studentName) {
        return enquiryRepository.findByEnquirerNameContainingIgnoreCase(studentName);
    }

    @Override
    public List<Enquiry> getEnquiriesByEmailContaining(String email) {
        // Filter in service since method doesn't exist
        List<Enquiry> allEnquiries = enquiryRepository.findAll();
        return allEnquiries.stream()
                .filter(enquiry -> enquiry.getEnquirerEmailId() != null &&
                                enquiry.getEnquirerEmailId().toLowerCase().contains(email.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Enquiry> getEnquiriesByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return enquiryRepository.findByEnquiryDateBetween(start, end);
    }

    @Override
    public Long countEnquiriesByStatus(String status) {
        // Filter in service since method doesn't exist directly for String status
        List<Enquiry> allEnquiries = enquiryRepository.findAll();
        Boolean processedFlag = null;
        if ("true".equalsIgnoreCase(status) || "processed".equalsIgnoreCase(status)) {
            processedFlag = true;
        } else if ("false".equalsIgnoreCase(status) || "unprocessed".equalsIgnoreCase(status) || "pending".equalsIgnoreCase(status)) {
            processedFlag = false;
        }

        if (processedFlag != null) {
            Boolean finalProcessedFlag = processedFlag;
            return allEnquiries.stream()
                    .filter(enquiry -> enquiry.getEnquiryProcessedFlag() != null &&
                                    enquiry.getEnquiryProcessedFlag().equals(finalProcessedFlag))
                    .count();
        }
        return 0L; // Return 0 if status is not recognized
    }

    // Update operations
    @Override
    public Enquiry updateEnquiry(Integer enquiryId, Enquiry enquiryDetails) {
        Optional<Enquiry> existingEnquiry = enquiryRepository.findById(enquiryId);
        if (existingEnquiry.isPresent()) {
            Enquiry enquiry = existingEnquiry.get();
            enquiry.setEnquirerName(enquiryDetails.getEnquirerName());
            enquiry.setEnquirerAddress(enquiryDetails.getEnquirerAddress());
            enquiry.setEnquirerMobile(enquiryDetails.getEnquirerMobile());
            enquiry.setEnquirerAlternateMobile(enquiryDetails.getEnquirerAlternateMobile());
            enquiry.setEnquirerEmailId(enquiryDetails.getEnquirerEmailId());
            enquiry.setEnquiryDate(enquiryDetails.getEnquiryDate());
            enquiry.setEnquirerQuery(enquiryDetails.getEnquirerQuery());
            enquiry.setClosureReasonId(enquiryDetails.getClosureReasonId());
            enquiry.setClosureReason(enquiryDetails.getClosureReason());
            enquiry.setEnquiryProcessedFlag(enquiryDetails.getEnquiryProcessedFlag());
            enquiry.setCourseId(enquiryDetails.getCourseId());
            enquiry.setAssignedStaffId(enquiryDetails.getAssignedStaffId());
            enquiry.setStudentName(enquiryDetails.getStudentName());
            enquiry.setUpdatedDate(java.time.LocalDateTime.now());
            return enquiryRepository.save(enquiry);
        }
        throw new RuntimeException("Enquiry not found with id: " + enquiryId);
    }

    @Override
    public Enquiry updateEnquiryStatus(Integer enquiryId, String status) {
        Optional<Enquiry> existingEnquiry = enquiryRepository.findById(enquiryId);
        if (existingEnquiry.isPresent()) {
            Enquiry enquiry = existingEnquiry.get();
            Boolean processedFlag = null;
            if ("true".equalsIgnoreCase(status) || "processed".equalsIgnoreCase(status)) {
                processedFlag = true;
            } else if ("false".equalsIgnoreCase(status) || "unprocessed".equalsIgnoreCase(status) || "pending".equalsIgnoreCase(status)) {
                processedFlag = false;
            }
            if (processedFlag != null) {
                enquiry.setEnquiryProcessedFlag(processedFlag);
                enquiry.setUpdatedDate(java.time.LocalDateTime.now());
                return enquiryRepository.save(enquiry);
            } else {
                throw new IllegalArgumentException("Invalid status provided: " + status);
            }
        }
        throw new RuntimeException("Enquiry not found with id: " + enquiryId);
    }

    @Override
    public Enquiry updateEnquiryStudentName(Integer enquiryId, String studentName) {
        Optional<Enquiry> existingEnquiry = enquiryRepository.findById(enquiryId);
        if (existingEnquiry.isPresent()) {
            Enquiry enquiry = existingEnquiry.get();
            enquiry.setEnquirerName(studentName);
            enquiry.setUpdatedDate(java.time.LocalDateTime.now());
            return enquiryRepository.save(enquiry);
        }
        throw new RuntimeException("Enquiry not found with id: " + enquiryId);
    }

    @Override
    public Enquiry updateEnquiryMobile(Integer enquiryId, Long mobile) {
        Optional<Enquiry> existingEnquiry = enquiryRepository.findById(enquiryId);
        if (existingEnquiry.isPresent()) {
            Enquiry enquiry = existingEnquiry.get();
            enquiry.setEnquirerMobile(mobile);
            enquiry.setUpdatedDate(java.time.LocalDateTime.now());
            return enquiryRepository.save(enquiry);
        }
        throw new RuntimeException("Enquiry not found with id: " + enquiryId);
    }

    @Override
    public Enquiry updateEnquiryEmail(Integer enquiryId, String email) {
        Optional<Enquiry> existingEnquiry = enquiryRepository.findById(enquiryId);
        if (existingEnquiry.isPresent()) {
            Enquiry enquiry = existingEnquiry.get();
            enquiry.setEnquirerEmailId(email);
            enquiry.setUpdatedDate(java.time.LocalDateTime.now());
            return enquiryRepository.save(enquiry);
        }
        throw new RuntimeException("Enquiry not found with id: " + enquiryId);
    }

    @Override
    public Enquiry updateEnquiryCounter(Integer enquiryId, Integer enquiryCounter) {
        Optional<Enquiry> existingEnquiry = enquiryRepository.findById(enquiryId);
        if (existingEnquiry.isPresent()) {
            Enquiry enquiry = existingEnquiry.get();
            enquiry.setEnquiryCounter(enquiryCounter);
            enquiry.setUpdatedDate(java.time.LocalDateTime.now());
            return enquiryRepository.save(enquiry);
        }
        throw new RuntimeException("Enquiry not found with id: " + enquiryId);
    }

    @Override
    public Enquiry updateEnquiryClosure(Integer enquiryId, Integer closureReasonId, String closureReason) {
        Optional<Enquiry> existingEnquiry = enquiryRepository.findById(enquiryId);
        if (existingEnquiry.isPresent()) {
            Enquiry enquiry = existingEnquiry.get();
            enquiry.setClosureReasonId(closureReasonId);
            enquiry.setClosureReason(closureReason);
            enquiry.setUpdatedDate(java.time.LocalDateTime.now());
            return enquiryRepository.save(enquiry);
        }
        throw new RuntimeException("Enquiry not found with id: " + enquiryId);
    }

    @Override
    public Enquiry updateEnquiryState(Integer enquiryId, Boolean enquiryState) {
        Optional<Enquiry> existingEnquiry = enquiryRepository.findById(enquiryId);
        if (existingEnquiry.isPresent()) {
            Enquiry enquiry = existingEnquiry.get();
            enquiry.setEnquiryState(enquiryState);
            enquiry.setUpdatedDate(java.time.LocalDateTime.now());
            return enquiryRepository.save(enquiry);
        }
        throw new RuntimeException("Enquiry not found with id: " + enquiryId);
    }

    // Delete operations
    @Override
    public void deleteEnquiry(Integer enquiryId) {
        if (!enquiryRepository.existsById(enquiryId)) {
            throw new RuntimeException("Enquiry not found with id: " + enquiryId);
        }
        enquiryRepository.deleteById(enquiryId);
    }

    @Override
    public void deleteEnquiriesByStatus(String status) {
        List<Enquiry> enquiriesToDelete = getEnquiriesByStatus(status);
        enquiryRepository.deleteAll(enquiriesToDelete);
    }

    @Override
    public void deleteAllEnquiries() {
        enquiryRepository.deleteAll();
    }

    // Business logic operations
    @Override
    public List<Enquiry> searchEnquiries(String searchTerm) {
        return enquiryRepository.findByQueryKeyword(searchTerm);
    }

    @Override
    public List<Enquiry> getEnquiriesByDateRangeAndCourse(String startDate, String endDate, Integer courseId) {
        List<Enquiry> dateRangeEnquiries = getEnquiriesByDateRange(startDate, endDate);
        return dateRangeEnquiries.stream()
                .filter(enquiry -> enquiry.getCourseId() != null && enquiry.getCourseId().equals(courseId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Enquiry> getEnquiriesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Enquiry> enquiryPage = enquiryRepository.findAll(pageable);
        return enquiryPage.getContent();
    }

    @Override
    public List<Enquiry> getEnquiriesSortedByDate() {
        return enquiryRepository.findAll(Sort.by(Sort.Direction.DESC, "enquiryDate"));
    }

    @Override
    public List<Enquiry> getEnquiriesSortedByStudentName() {
        return enquiryRepository.findAll(Sort.by(Sort.Direction.ASC, "enquirerName"));
    }

    @Override
    public List<Enquiry> getEnquiriesSortedByStatus() {
        return enquiryRepository.findAll(Sort.by(Sort.Direction.ASC, "enquiryProcessedFlag"));
    }

    @Override
    public List<Enquiry> getPendingEnquiries() {
        return enquiryRepository.findByEnquiryProcessedFlagFalse();
    }

    @Override
    public List<Enquiry> getCompletedEnquiries() {
        return enquiryRepository.findByEnquiryProcessedFlagTrue();
    }

    @Override
    public List<Enquiry> getFollowUpRequiredEnquiries() {
        // Filter enquiries that need follow-up (custom logic)
        List<Enquiry> allEnquiries = enquiryRepository.findAll();
        return allEnquiries.stream()
                .filter(enquiry -> enquiry.getFollowUpDate() != null &&
                                enquiry.getFollowUpDate().isBefore(LocalDate.now()) &&
                                !enquiry.getEnquiryProcessedFlag())
                .collect(Collectors.toList());
    }
} 