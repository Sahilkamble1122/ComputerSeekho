package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ClosureReasonMaster;

@Repository
public interface ClosureReasonMasterRepository extends JpaRepository<ClosureReasonMaster, Integer> {
    
    // Basic find operations
    List<ClosureReasonMaster> findByClosureReasonName(String closureReasonName);
    List<ClosureReasonMaster> findByClosureReasonDesc(String closureReasonDesc);
    List<ClosureReasonMaster> findByClosureReasonIsActive(Boolean closureReasonIsActive);
    List<ClosureReasonMaster> findByClosureReasonNameContainingIgnoreCase(String closureReasonName);
    
    // Count operations
    Long countByClosureReasonIsActive(Boolean closureReasonIsActive);
    
    // Delete operations
    void deleteByClosureReasonIsActive(Boolean closureReasonIsActive);
    
    // Search operations
    @Query("SELECT cr FROM ClosureReasonMaster cr WHERE LOWER(cr.closureReasonName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(cr.closureReasonDesc) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<ClosureReasonMaster> findByClosureReasonNameContainingIgnoreCaseOrClosureReasonDescContainingIgnoreCase(@Param("searchTerm") String searchTerm, @Param("searchTerm") String searchTerm2);
} 