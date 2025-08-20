package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.BatchMaster;

public interface BatchMasterService {
    
    // CREATE OPERATIONS
    BatchMaster saveBatch(BatchMaster batch);
    List<BatchMaster> saveAllBatches(List<BatchMaster> batches);
    
    // READ OPERATIONS
    List<BatchMaster> getAllBatches();
    Optional<BatchMaster> getBatchById(Integer batchId);
    List<BatchMaster> getBatchesByName(String batchName);
    List<BatchMaster> getBatchesByCourseId(Integer courseId);
    List<BatchMaster> getBatchesByStaffId(Integer staffId);
    List<BatchMaster> getBatchesByStatus(String status);
    List<BatchMaster> getBatchesByNameContaining(String batchName);
    List<BatchMaster> getBatchesByCapacityRange(Integer minCapacity, Integer maxCapacity);
    Long countBatchesByCourseId(Integer courseId);
    Long countBatchesByStaffId(Integer staffId);
    
    // UPDATE OPERATIONS
    BatchMaster updateBatch(Integer batchId, BatchMaster batchDetails);
    BatchMaster updateBatchName(Integer batchId, String batchName);
    BatchMaster updateBatchStatus(Integer batchId, String status);
    BatchMaster updateBatchCapacity(Integer batchId, Integer capacity);
    BatchMaster updateBatchStaff(Integer batchId, Integer staffId);
    
    // DELETE OPERATIONS
    void deleteBatch(Integer batchId);
    void deleteBatchesByCourseId(Integer courseId);
    void deleteBatchesByStaffId(Integer staffId);
    void deleteBatchesByStatus(String status);
    void deleteAllBatches();
    
    // BUSINESS LOGIC OPERATIONS
    List<BatchMaster> searchBatches(String searchTerm);
    List<BatchMaster> getBatchesByCourseAndStaff(Integer courseId, Integer staffId);
    List<BatchMaster> getBatchesWithPagination(int page, int size);
    List<BatchMaster> getBatchesSortedByName();
    List<BatchMaster> getBatchesSortedByCapacity();
    List<BatchMaster> getBatchesSortedByCourseId();
    List<BatchMaster> getActiveBatches();
    List<BatchMaster> getAvailableBatches();
    List<BatchMaster> getFullBatches();
} 