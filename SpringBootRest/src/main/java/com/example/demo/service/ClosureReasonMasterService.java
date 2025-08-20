package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.ClosureReasonMaster;

public interface ClosureReasonMasterService {
    
    // CREATE OPERATIONS
    ClosureReasonMaster saveClosureReason(ClosureReasonMaster closureReason);
    List<ClosureReasonMaster> saveAllClosureReasons(List<ClosureReasonMaster> closureReasons);
    
    // READ OPERATIONS
    List<ClosureReasonMaster> getAllClosureReasons();
    Optional<ClosureReasonMaster> getClosureReasonById(Integer closureReasonId);
    List<ClosureReasonMaster> getClosureReasonsByName(String reasonName);
    List<ClosureReasonMaster> getClosureReasonsByDescription(String description);
    List<ClosureReasonMaster> getClosureReasonsByStatus(String status);
    List<ClosureReasonMaster> getClosureReasonsByNameContaining(String reasonName);
    List<ClosureReasonMaster> getActiveClosureReasons();
    Long countActiveClosureReasons();
    
    // UPDATE OPERATIONS
    ClosureReasonMaster updateClosureReason(Integer closureReasonId, ClosureReasonMaster closureReasonDetails);
    ClosureReasonMaster updateClosureReasonName(Integer closureReasonId, String reasonName);
    ClosureReasonMaster updateClosureReasonDescription(Integer closureReasonId, String description);
    ClosureReasonMaster updateClosureReasonStatus(Integer closureReasonId, String status);
    
    // DELETE OPERATIONS
    void deleteClosureReason(Integer closureReasonId);
    void deleteClosureReasonsByStatus(String status);
    void deleteInactiveClosureReasons();
    void deleteAllClosureReasons();
    
    // BUSINESS LOGIC OPERATIONS
    List<ClosureReasonMaster> searchClosureReasons(String searchTerm);
    List<ClosureReasonMaster> getClosureReasonsWithPagination(int page, int size);
    List<ClosureReasonMaster> getClosureReasonsSortedByName();
    List<ClosureReasonMaster> getClosureReasonsSortedByStatus();
    List<ClosureReasonMaster> getFrequentlyUsedClosureReasons();
} 