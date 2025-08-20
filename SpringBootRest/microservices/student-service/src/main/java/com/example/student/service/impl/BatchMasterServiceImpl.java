package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.BatchMaster;
import com.example.demo.repository.BatchMasterRepository;
import com.example.demo.service.BatchMasterService;

@Service
public class BatchMasterServiceImpl implements BatchMasterService {

    @Autowired
    private BatchMasterRepository batchMasterRepository;

    // CREATE OPERATIONS
    @Override
    public BatchMaster saveBatch(BatchMaster batch) {
        return batchMasterRepository.save(batch);
    }

    @Override
    public List<BatchMaster> saveAllBatches(List<BatchMaster> batches) {
        return batchMasterRepository.saveAll(batches);
    }

    // READ OPERATIONS
    @Override
    public List<BatchMaster> getAllBatches() {
        return batchMasterRepository.findAll();
    }

    @Override
    public Optional<BatchMaster> getBatchById(Integer batchId) {
        return batchMasterRepository.findById(batchId);
    }

    @Override
    public List<BatchMaster> getBatchesByName(String batchName) {
        return batchMasterRepository.findByBatchName(batchName);
    }

    @Override
    public List<BatchMaster> getBatchesByCourseId(Integer courseId) {
        return batchMasterRepository.findByCourseId(courseId);
    }

    @Override
    public List<BatchMaster> getBatchesByStaffId(Integer staffId) {
        return batchMasterRepository.findByStaffId(staffId);
    }

    @Override
    public List<BatchMaster> getBatchesByStatus(String status) {
        // Since BatchMaster doesn't have a status field, we'll use batchIsActive instead
        // Convert status string to boolean for active/inactive batches
        Boolean isActive = "Active".equalsIgnoreCase(status);
        return batchMasterRepository.findByBatchIsActive(isActive);
    }

    @Override
    public List<BatchMaster> getBatchesByNameContaining(String batchName) {
        return batchMasterRepository.findByBatchNameContainingIgnoreCase(batchName);
    }

    @Override
    public List<BatchMaster> getBatchesByCapacityRange(Integer minCapacity, Integer maxCapacity) {
        // Since BatchMaster doesn't have capacity field, we'll use courseFees as a substitute
        return batchMasterRepository.findByCourseFeesBetween(minCapacity, maxCapacity);
    }

    @Override
    public Long countBatchesByCourseId(Integer courseId) {
        return batchMasterRepository.countByCourseId(courseId);
    }

    @Override
    public Long countBatchesByStaffId(Integer staffId) {
        return batchMasterRepository.countByStaffId(staffId);
    }

    // UPDATE OPERATIONS
    @Override
    public BatchMaster updateBatch(Integer batchId, BatchMaster batchDetails) {
        BatchMaster existingBatch = batchMasterRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found with id: " + batchId));
        
        existingBatch.setBatchName(batchDetails.getBatchName());
        existingBatch.setBatchStartTime(batchDetails.getBatchStartTime());
        existingBatch.setBatchEndTime(batchDetails.getBatchEndTime());
        existingBatch.setCourseId(batchDetails.getCourseId());
        existingBatch.setPresentationDate(batchDetails.getPresentationDate());
        existingBatch.setCourseFees(batchDetails.getCourseFees());
        existingBatch.setCourseFeesFrom(batchDetails.getCourseFeesFrom());
        existingBatch.setCourseFeesTo(batchDetails.getCourseFeesTo());
        existingBatch.setBatchIsActive(batchDetails.getBatchIsActive());
        existingBatch.setStaffId(batchDetails.getStaffId());
        existingBatch.setUpdatedDate(java.time.LocalDateTime.now());
        
        return batchMasterRepository.save(existingBatch);
    }

    @Override
    public BatchMaster updateBatchName(Integer batchId, String batchName) {
        BatchMaster batch = batchMasterRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found with id: " + batchId));
        batch.setBatchName(batchName);
        batch.setUpdatedDate(java.time.LocalDateTime.now());
        return batchMasterRepository.save(batch);
    }

    @Override
    public BatchMaster updateBatchStatus(Integer batchId, String status) {
        BatchMaster batch = batchMasterRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found with id: " + batchId));
        // Convert status string to boolean
        Boolean isActive = "Active".equalsIgnoreCase(status);
        batch.setBatchIsActive(isActive);
        batch.setUpdatedDate(java.time.LocalDateTime.now());
        return batchMasterRepository.save(batch);
    }

    @Override
    public BatchMaster updateBatchCapacity(Integer batchId, Integer capacity) {
        BatchMaster batch = batchMasterRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found with id: " + batchId));
        // Use courseFees as capacity since BatchMaster doesn't have capacity field
        batch.setCourseFees(capacity);
        batch.setUpdatedDate(java.time.LocalDateTime.now());
        return batchMasterRepository.save(batch);
    }

    @Override
    public BatchMaster updateBatchStaff(Integer batchId, Integer staffId) {
        BatchMaster batch = batchMasterRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found with id: " + batchId));
        batch.setStaffId(staffId);
        batch.setUpdatedDate(java.time.LocalDateTime.now());
        return batchMasterRepository.save(batch);
    }

    // DELETE OPERATIONS
    @Override
    public void deleteBatch(Integer batchId) {
        if (!batchMasterRepository.existsById(batchId)) {
            throw new RuntimeException("Batch not found with id: " + batchId);
        }
        batchMasterRepository.deleteById(batchId);
    }

    @Override
    public void deleteBatchesByCourseId(Integer courseId) {
        batchMasterRepository.deleteByCourseId(courseId);
    }

    @Override
    public void deleteBatchesByStaffId(Integer staffId) {
        batchMasterRepository.deleteByStaffId(staffId);
    }

    @Override
    public void deleteBatchesByStatus(String status) {
        // Convert status string to boolean for active/inactive batches
        Boolean isActive = "Active".equalsIgnoreCase(status);
        batchMasterRepository.deleteByBatchIsActive(isActive);
    }

    @Override
    public void deleteAllBatches() {
        batchMasterRepository.deleteAll();
    }

    // BUSINESS LOGIC OPERATIONS
    @Override
    public List<BatchMaster> searchBatches(String searchTerm) {
        return batchMasterRepository.findByBatchNameContainingIgnoreCaseOrStatusContainingIgnoreCase(searchTerm, searchTerm);
    }

    @Override
    public List<BatchMaster> getBatchesByCourseAndStaff(Integer courseId, Integer staffId) {
        return batchMasterRepository.findByCourseIdAndStaffId(courseId, staffId);
    }

    @Override
    public List<BatchMaster> getBatchesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BatchMaster> batchPage = batchMasterRepository.findAll(pageable);
        return batchPage.getContent();
    }

    @Override
    public List<BatchMaster> getBatchesSortedByName() {
        return batchMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "batchName"));
    }

    @Override
    public List<BatchMaster> getBatchesSortedByCapacity() {
        // Use courseFees as capacity since BatchMaster doesn't have capacity field
        return batchMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "courseFees"));
    }

    @Override
    public List<BatchMaster> getBatchesSortedByCourseId() {
        return batchMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "courseId"));
    }

    @Override
    public List<BatchMaster> getActiveBatches() {
        return batchMasterRepository.findByBatchIsActive(true);
    }

    @Override
    public List<BatchMaster> getAvailableBatches() {
        // Return active batches with course fees greater than 0
        return batchMasterRepository.findByBatchIsActiveAndCourseFeesGreaterThan(true, 0);
    }

    @Override
    public List<BatchMaster> getFullBatches() {
        // Return active batches with course fees equal to 0 (assuming this means full)
        return batchMasterRepository.findByBatchIsActiveAndCourseFees(true, 0);
    }
} 