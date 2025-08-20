package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.StaffMaster;

public interface StaffMasterService {
    
    // CREATE OPERATIONS
    StaffMaster saveStaff(StaffMaster staff);
    List<StaffMaster> saveAllStaff(List<StaffMaster> staffList);
    
    // READ OPERATIONS
    List<StaffMaster> getAllStaff();
    Optional<StaffMaster> getStaffById(Integer staffId);
    List<StaffMaster> getStaffByName(String staffName);
    List<StaffMaster> getStaffByEmail(String email);
    List<StaffMaster> getStaffByMobile(Long mobile);
    List<StaffMaster> getStaffByDepartment(String department);
    List<StaffMaster> getStaffByDesignation(String designation);
    List<StaffMaster> getStaffByStatus(String status);
    List<StaffMaster> getStaffByNameContaining(String staffName);
    List<StaffMaster> getStaffByEmailContaining(String email);
    Long countStaffByDepartment(String department);
    Long countStaffByDesignation(String designation);
    
    // UPDATE OPERATIONS
    StaffMaster updateStaff(Integer staffId, StaffMaster staffDetails);
    StaffMaster updateStaffName(Integer staffId, String staffName);
    StaffMaster updateStaffEmail(Integer staffId, String email);
    StaffMaster updateStaffMobile(Integer staffId, Long mobile);
    StaffMaster updateStaffDepartment(Integer staffId, String department);
    StaffMaster updateStaffDesignation(Integer staffId, String designation);
    StaffMaster updateStaffStatus(Integer staffId, String status);
    
    // DELETE OPERATIONS
    void deleteStaff(Integer staffId);
    void deleteStaffByDepartment(String department);
    void deleteStaffByDesignation(String designation);
    void deleteStaffByStatus(String status);
    void deleteAllStaff();
    
    // BUSINESS LOGIC OPERATIONS
    List<StaffMaster> searchStaff(String searchTerm);
    List<StaffMaster> getStaffByDepartmentAndDesignation(String department, String designation);
    List<StaffMaster> getStaffWithPagination(int page, int size);
    List<StaffMaster> getStaffSortedByName();
    List<StaffMaster> getStaffSortedByDepartment();
    List<StaffMaster> getStaffSortedByDesignation();
    Optional<StaffMaster> authenticateStaff(String username, String password);
    List<StaffMaster> getActiveStaff();
    List<StaffMaster> getInactiveStaff();
    List<StaffMaster> getStaffByEmailDomain(String domain);
} 