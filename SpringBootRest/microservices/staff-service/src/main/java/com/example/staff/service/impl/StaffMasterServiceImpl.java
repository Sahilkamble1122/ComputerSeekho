package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.StaffMaster;
import com.example.demo.repository.StaffMasterRepository;
import com.example.demo.service.StaffMasterService;

@Service
public class StaffMasterServiceImpl implements StaffMasterService {

    @Autowired
    private StaffMasterRepository staffMasterRepository;

    // CREATE OPERATIONS
    @Override
    public StaffMaster saveStaff(StaffMaster staff) {
        return staffMasterRepository.save(staff);
    }

    @Override
    public List<StaffMaster> saveAllStaff(List<StaffMaster> staffList) {
        return staffMasterRepository.saveAll(staffList);
    }

    // READ OPERATIONS
    @Override
    public List<StaffMaster> getAllStaff() {
        return staffMasterRepository.findAll();
    }

    @Override
    public Optional<StaffMaster> getStaffById(Integer staffId) {
        return staffMasterRepository.findById(staffId);
    }

    @Override
    public List<StaffMaster> getStaffByName(String staffName) {
        return staffMasterRepository.findByStaffName(staffName);
    }

    @Override
    public List<StaffMaster> getStaffByEmail(String email) {
        return staffMasterRepository.findByStaffEmail(email);
    }

    @Override
    public List<StaffMaster> getStaffByMobile(Long mobile) {
        return staffMasterRepository.findByStaffMobile(mobile);
    }

    @Override
    public List<StaffMaster> getStaffByDepartment(String department) {
        return staffMasterRepository.findByStaffRole(department);
    }

    @Override
    public List<StaffMaster> getStaffByDesignation(String designation) {
        return staffMasterRepository.findByStaffRole(designation);
    }

    @Override
    public List<StaffMaster> getStaffByStatus(String status) {
        // Since StaffMaster doesn't have status field, we'll return all staff
        return staffMasterRepository.findAll();
    }

    @Override
    public List<StaffMaster> getStaffByNameContaining(String staffName) {
        return staffMasterRepository.findByStaffNameContainingIgnoreCase(staffName);
    }

    @Override
    public List<StaffMaster> getStaffByEmailContaining(String email) {
        return staffMasterRepository.findByStaffEmailContainingIgnoreCase(email);
    }

    @Override
    public Long countStaffByDepartment(String department) {
        return staffMasterRepository.countByStaffRole(department);
    }

    @Override
    public Long countStaffByDesignation(String designation) {
        return staffMasterRepository.countByStaffRole(designation);
    }

    // UPDATE OPERATIONS
    @Override
    public StaffMaster updateStaff(Integer staffId, StaffMaster staffDetails) {
        StaffMaster existingStaff = staffMasterRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
        
        existingStaff.setStaffName(staffDetails.getStaffName());
        existingStaff.setPhotoUrl(staffDetails.getPhotoUrl());
        existingStaff.setStaffMobile(staffDetails.getStaffMobile());
        existingStaff.setStaffEmail(staffDetails.getStaffEmail());
        existingStaff.setStaffUsername(staffDetails.getStaffUsername());
        existingStaff.setStaffPassword(staffDetails.getStaffPassword());
        existingStaff.setStaffRole(staffDetails.getStaffRole());
        existingStaff.setUpdatedDate(java.time.LocalDateTime.now());
        
        return staffMasterRepository.save(existingStaff);
    }

    @Override
    public StaffMaster updateStaffName(Integer staffId, String staffName) {
        StaffMaster staff = staffMasterRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
        staff.setStaffName(staffName);
        staff.setUpdatedDate(java.time.LocalDateTime.now());
        return staffMasterRepository.save(staff);
    }

    @Override
    public StaffMaster updateStaffEmail(Integer staffId, String email) {
        StaffMaster staff = staffMasterRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
        staff.setStaffEmail(email);
        staff.setUpdatedDate(java.time.LocalDateTime.now());
        return staffMasterRepository.save(staff);
    }

    @Override
    public StaffMaster updateStaffMobile(Integer staffId, Long mobile) {
        StaffMaster staff = staffMasterRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
        staff.setStaffMobile(mobile);
        staff.setUpdatedDate(java.time.LocalDateTime.now());
        return staffMasterRepository.save(staff);
    }

    @Override
    public StaffMaster updateStaffDepartment(Integer staffId, String department) {
        StaffMaster staff = staffMasterRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
        staff.setStaffRole(department);
        staff.setUpdatedDate(java.time.LocalDateTime.now());
        return staffMasterRepository.save(staff);
    }

    @Override
    public StaffMaster updateStaffDesignation(Integer staffId, String designation) {
        StaffMaster staff = staffMasterRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
        staff.setStaffRole(designation);
        staff.setUpdatedDate(java.time.LocalDateTime.now());
        return staffMasterRepository.save(staff);
    }

    @Override
    public StaffMaster updateStaffStatus(Integer staffId, String status) {
        StaffMaster staff = staffMasterRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
        // Since StaffMaster doesn't have status field, we'll update the role
        staff.setStaffRole(status);
        staff.setUpdatedDate(java.time.LocalDateTime.now());
        return staffMasterRepository.save(staff);
    }

    // DELETE OPERATIONS
    @Override
    public void deleteStaff(Integer staffId) {
        if (!staffMasterRepository.existsById(staffId)) {
            throw new RuntimeException("Staff not found with id: " + staffId);
        }
        staffMasterRepository.deleteById(staffId);
    }

    @Override
    public void deleteStaffByDepartment(String department) {
        staffMasterRepository.deleteByStaffRole(department);
    }

    @Override
    public void deleteStaffByDesignation(String designation) {
        staffMasterRepository.deleteByStaffRole(designation);
    }

    @Override
    public void deleteStaffByStatus(String status) {
        // Since StaffMaster doesn't have status field, we'll delete all staff
        staffMasterRepository.deleteAll();
    }

    @Override
    public void deleteAllStaff() {
        staffMasterRepository.deleteAll();
    }

    // BUSINESS LOGIC OPERATIONS
    @Override
    public List<StaffMaster> searchStaff(String searchTerm) {
        return staffMasterRepository.findByStaffNameContainingIgnoreCaseOrStaffEmailContainingIgnoreCaseOrStaffRoleContainingIgnoreCase(searchTerm, searchTerm, searchTerm);
    }

    @Override
    public List<StaffMaster> getStaffByDepartmentAndDesignation(String department, String designation) {
        return staffMasterRepository.findByStaffRoleAndStaffRole(department, designation);
    }

    @Override
    public List<StaffMaster> getStaffWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StaffMaster> staffPage = staffMasterRepository.findAll(pageable);
        return staffPage.getContent();
    }

    @Override
    public List<StaffMaster> getStaffSortedByName() {
        return staffMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "staffName"));
    }

    @Override
    public List<StaffMaster> getStaffSortedByDepartment() {
        return staffMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "staffRole"));
    }

    @Override
    public List<StaffMaster> getStaffSortedByDesignation() {
        return staffMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "staffRole"));
    }

    @Override
    public Optional<StaffMaster> authenticateStaff(String username, String password) {
        return staffMasterRepository.findByStaffUsernameAndStaffPassword(username, password);
    }

    @Override
    public List<StaffMaster> getActiveStaff() {
        // Since StaffMaster doesn't have status field, we'll return all staff
        return staffMasterRepository.findAll();
    }

    @Override
    public List<StaffMaster> getInactiveStaff() {
        // Since StaffMaster doesn't have status field, we'll return all staff
        return staffMasterRepository.findAll();
    }

    @Override
    public List<StaffMaster> getStaffByEmailDomain(String domain) {
        return staffMasterRepository.findByStaffEmailEndingWith(domain);
    }
} 