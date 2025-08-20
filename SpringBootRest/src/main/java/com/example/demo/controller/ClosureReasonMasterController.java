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

import com.example.demo.model.ClosureReasonMaster;
import com.example.demo.service.ClosureReasonMasterService;

@RestController
@RequestMapping("/api/closure-reasons")
@CrossOrigin(origins = "*")
public class ClosureReasonMasterController {

    @Autowired
    private ClosureReasonMasterService closureReasonMasterService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<ClosureReasonMaster> createClosureReason(@RequestBody ClosureReasonMaster closureReason) {
        try {
            ClosureReasonMaster savedClosureReason = closureReasonMasterService.saveClosureReason(closureReason);
            return new ResponseEntity<>(savedClosureReason, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ClosureReasonMaster>> createMultipleClosureReasons(@RequestBody List<ClosureReasonMaster> closureReasons) {
        try {
            List<ClosureReasonMaster> savedClosureReasons = closureReasonMasterService.saveAllClosureReasons(closureReasons);
            return new ResponseEntity<>(savedClosureReasons, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<ClosureReasonMaster>> getAllClosureReasons() {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.getAllClosureReasons();
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{closureReasonId}")
    public ResponseEntity<ClosureReasonMaster> getClosureReasonById(@PathVariable Integer closureReasonId) {
        try {
            Optional<ClosureReasonMaster> closureReason = closureReasonMasterService.getClosureReasonById(closureReasonId);
            return closureReason.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name/{reasonName}")
    public ResponseEntity<List<ClosureReasonMaster>> getClosureReasonsByName(@PathVariable String reasonName) {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.getClosureReasonsByName(reasonName);
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/description/{description}")
    public ResponseEntity<List<ClosureReasonMaster>> getClosureReasonsByDescription(@PathVariable String description) {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.getClosureReasonsByDescription(description);
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<ClosureReasonMaster>> getClosureReasonsByStatus(@PathVariable String status) {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.getClosureReasonsByStatus(status);
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name-contains/{reasonName}")
    public ResponseEntity<List<ClosureReasonMaster>> getClosureReasonsByNameContaining(@PathVariable String reasonName) {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.getClosureReasonsByNameContaining(reasonName);
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<ClosureReasonMaster>> getActiveClosureReasons() {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.getActiveClosureReasons();
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/active")
    public ResponseEntity<Long> countActiveClosureReasons() {
        try {
            Long count = closureReasonMasterService.countActiveClosureReasons();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{closureReasonId}")
    public ResponseEntity<ClosureReasonMaster> updateClosureReason(@PathVariable Integer closureReasonId, @RequestBody ClosureReasonMaster closureReasonDetails) {
        try {
            ClosureReasonMaster updatedClosureReason = closureReasonMasterService.updateClosureReason(closureReasonId, closureReasonDetails);
            return new ResponseEntity<>(updatedClosureReason, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{closureReasonId}/name")
    public ResponseEntity<ClosureReasonMaster> updateClosureReasonName(@PathVariable Integer closureReasonId, @RequestParam String reasonName) {
        try {
            ClosureReasonMaster updatedClosureReason = closureReasonMasterService.updateClosureReasonName(closureReasonId, reasonName);
            return new ResponseEntity<>(updatedClosureReason, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{closureReasonId}/description")
    public ResponseEntity<ClosureReasonMaster> updateClosureReasonDescription(@PathVariable Integer closureReasonId, @RequestParam String description) {
        try {
            ClosureReasonMaster updatedClosureReason = closureReasonMasterService.updateClosureReasonDescription(closureReasonId, description);
            return new ResponseEntity<>(updatedClosureReason, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{closureReasonId}/status")
    public ResponseEntity<ClosureReasonMaster> updateClosureReasonStatus(@PathVariable Integer closureReasonId, @RequestParam String status) {
        try {
            ClosureReasonMaster updatedClosureReason = closureReasonMasterService.updateClosureReasonStatus(closureReasonId, status);
            return new ResponseEntity<>(updatedClosureReason, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{closureReasonId}")
    public ResponseEntity<Void> deleteClosureReason(@PathVariable Integer closureReasonId) {
        try {
            closureReasonMasterService.deleteClosureReason(closureReasonId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deleteClosureReasonsByStatus(@PathVariable String status) {
        try {
            closureReasonMasterService.deleteClosureReasonsByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/inactive")
    public ResponseEntity<Void> deleteInactiveClosureReasons() {
        try {
            closureReasonMasterService.deleteInactiveClosureReasons();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllClosureReasons() {
        try {
            closureReasonMasterService.deleteAllClosureReasons();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<ClosureReasonMaster>> searchClosureReasons(@PathVariable String searchTerm) {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.searchClosureReasons(searchTerm);
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<ClosureReasonMaster>> getClosureReasonsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.getClosureReasonsWithPagination(page, size);
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/name")
    public ResponseEntity<List<ClosureReasonMaster>> getClosureReasonsSortedByName() {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.getClosureReasonsSortedByName();
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/status")
    public ResponseEntity<List<ClosureReasonMaster>> getClosureReasonsSortedByStatus() {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.getClosureReasonsSortedByStatus();
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/frequently-used")
    public ResponseEntity<List<ClosureReasonMaster>> getFrequentlyUsedClosureReasons() {
        try {
            List<ClosureReasonMaster> closureReasons = closureReasonMasterService.getFrequentlyUsedClosureReasons();
            return new ResponseEntity<>(closureReasons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 