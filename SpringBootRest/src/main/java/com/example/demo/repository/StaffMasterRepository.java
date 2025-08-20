package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StaffMaster;

@Repository
public interface StaffMasterRepository extends JpaRepository<StaffMaster, Integer> {
    
    // Basic find operations
    List<StaffMaster> findByStaffName(String staffName);
    List<StaffMaster> findByStaffEmail(String staffEmail);
    List<StaffMaster> findByStaffMobile(Long staffMobile);
    List<StaffMaster> findByStaffRole(String staffRole);
    List<StaffMaster> findByStaffNameContainingIgnoreCase(String staffName);
    List<StaffMaster> findByStaffEmailContainingIgnoreCase(String staffEmail);
    List<StaffMaster> findByStaffRoleAndStaffRole(String staffRole1, String staffRole2);
    
    // Count operations
    Long countByStaffRole(String staffRole);
    
    // Delete operations
    void deleteByStaffRole(String staffRole);
    
    // Search operations
    @Query("SELECT s FROM StaffMaster s WHERE LOWER(s.staffName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(s.staffEmail) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(s.staffRole) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<StaffMaster> findByStaffNameContainingIgnoreCaseOrStaffEmailContainingIgnoreCaseOrStaffRoleContainingIgnoreCase(@Param("searchTerm") String searchTerm, @Param("searchTerm") String searchTerm2, @Param("searchTerm") String searchTerm3);
    
    // Authentication
    Optional<StaffMaster> findByStaffUsernameAndStaffPassword(String username, String password);
    Optional<StaffMaster> findByStaffUsername(String username);
    
    // Email domain operations
    @Query("SELECT s FROM StaffMaster s WHERE s.staffEmail LIKE CONCAT('%', :domain)")
    List<StaffMaster> findByStaffEmailEndingWith(@Param("domain") String domain);
} 