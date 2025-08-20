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

import com.example.demo.model.BatchMaster;
import com.example.demo.service.BatchMasterService;

@RestController
@RequestMapping("/api/batches")
@CrossOrigin(origins = "*")
public class BatchMasterController {

    @Autowired
    private BatchMasterService batchMasterService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<BatchMaster> createBatch(@RequestBody BatchMaster batch) {
        try {
            BatchMaster savedBatch = batchMasterService.saveBatch(batch);
            return new ResponseEntity<>(savedBatch, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<BatchMaster>> createMultipleBatches(@RequestBody List<BatchMaster> batches) {
        try {
            List<BatchMaster> savedBatches = batchMasterService.saveAllBatches(batches);
            return new ResponseEntity<>(savedBatches, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<BatchMaster>> getAllBatches() {
        try {
            List<BatchMaster> batches = batchMasterService.getAllBatches();
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<BatchMaster> getBatchById(@PathVariable Integer batchId) {
        try {
            Optional<BatchMaster> batch = batchMasterService.getBatchById(batchId);
            return batch.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name/{batchName}")
    public ResponseEntity<List<BatchMaster>> getBatchesByName(@PathVariable String batchName) {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesByName(batchName);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/course/{courseId}")
    public ResponseEntity<List<BatchMaster>> getBatchesByCourseId(@PathVariable Integer courseId) {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesByCourseId(courseId);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/staff/{staffId}")
    public ResponseEntity<List<BatchMaster>> getBatchesByStaffId(@PathVariable Integer staffId) {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesByStaffId(staffId);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<BatchMaster>> getBatchesByStatus(@PathVariable String status) {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesByStatus(status);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name-contains/{batchName}")
    public ResponseEntity<List<BatchMaster>> getBatchesByNameContaining(@PathVariable String batchName) {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesByNameContaining(batchName);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/capacity-range")
    public ResponseEntity<List<BatchMaster>> getBatchesByCapacityRange(
            @RequestParam Integer minCapacity,
            @RequestParam Integer maxCapacity) {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesByCapacityRange(minCapacity, maxCapacity);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/course/{courseId}")
    public ResponseEntity<Long> countBatchesByCourseId(@PathVariable Integer courseId) {
        try {
            Long count = batchMasterService.countBatchesByCourseId(courseId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/staff/{staffId}")
    public ResponseEntity<Long> countBatchesByStaffId(@PathVariable Integer staffId) {
        try {
            Long count = batchMasterService.countBatchesByStaffId(staffId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{batchId}")
    public ResponseEntity<BatchMaster> updateBatch(@PathVariable Integer batchId, @RequestBody BatchMaster batchDetails) {
        try {
            BatchMaster updatedBatch = batchMasterService.updateBatch(batchId, batchDetails);
            return new ResponseEntity<>(updatedBatch, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{batchId}/name")
    public ResponseEntity<BatchMaster> updateBatchName(@PathVariable Integer batchId, @RequestParam String batchName) {
        try {
            BatchMaster updatedBatch = batchMasterService.updateBatchName(batchId, batchName);
            return new ResponseEntity<>(updatedBatch, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{batchId}/status")
    public ResponseEntity<BatchMaster> updateBatchStatus(@PathVariable Integer batchId, @RequestParam String status) {
        try {
            BatchMaster updatedBatch = batchMasterService.updateBatchStatus(batchId, status);
            return new ResponseEntity<>(updatedBatch, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{batchId}/capacity")
    public ResponseEntity<BatchMaster> updateBatchCapacity(@PathVariable Integer batchId, @RequestParam Integer capacity) {
        try {
            BatchMaster updatedBatch = batchMasterService.updateBatchCapacity(batchId, capacity);
            return new ResponseEntity<>(updatedBatch, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{batchId}/staff")
    public ResponseEntity<BatchMaster> updateBatchStaff(@PathVariable Integer batchId, @RequestParam Integer staffId) {
        try {
            BatchMaster updatedBatch = batchMasterService.updateBatchStaff(batchId, staffId);
            return new ResponseEntity<>(updatedBatch, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{batchId}")
    public ResponseEntity<Void> deleteBatch(@PathVariable Integer batchId) {
        try {
            batchMasterService.deleteBatch(batchId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Void> deleteBatchesByCourseId(@PathVariable Integer courseId) {
        try {
            batchMasterService.deleteBatchesByCourseId(courseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/staff/{staffId}")
    public ResponseEntity<Void> deleteBatchesByStaffId(@PathVariable Integer staffId) {
        try {
            batchMasterService.deleteBatchesByStaffId(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deleteBatchesByStatus(@PathVariable String status) {
        try {
            batchMasterService.deleteBatchesByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllBatches() {
        try {
            batchMasterService.deleteAllBatches();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<BatchMaster>> searchBatches(@PathVariable String searchTerm) {
        try {
            List<BatchMaster> batches = batchMasterService.searchBatches(searchTerm);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/course-staff")
    public ResponseEntity<List<BatchMaster>> getBatchesByCourseAndStaff(
            @RequestParam Integer courseId,
            @RequestParam Integer staffId) {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesByCourseAndStaff(courseId, staffId);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<BatchMaster>> getBatchesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesWithPagination(page, size);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/name")
    public ResponseEntity<List<BatchMaster>> getBatchesSortedByName() {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesSortedByName();
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/capacity")
    public ResponseEntity<List<BatchMaster>> getBatchesSortedByCapacity() {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesSortedByCapacity();
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/course")
    public ResponseEntity<List<BatchMaster>> getBatchesSortedByCourseId() {
        try {
            List<BatchMaster> batches = batchMasterService.getBatchesSortedByCourseId();
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<BatchMaster>> getActiveBatches() {
        try {
            List<BatchMaster> batches = batchMasterService.getActiveBatches();
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<BatchMaster>> getAvailableBatches() {
        try {
            List<BatchMaster> batches = batchMasterService.getAvailableBatches();
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/full")
    public ResponseEntity<List<BatchMaster>> getFullBatches() {
        try {
            List<BatchMaster> batches = batchMasterService.getFullBatches();
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 