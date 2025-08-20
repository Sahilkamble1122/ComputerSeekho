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

import com.example.demo.model.Placement;
import com.example.demo.service.PlacementService;

@RestController
@RequestMapping("/api/placements")
@CrossOrigin(origins = "*")
public class PlacementController {

    @Autowired
    private PlacementService placementService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<Placement> createPlacement(@RequestBody Placement placement) {
        try {
            Placement savedPlacement = placementService.savePlacement(placement);
            return new ResponseEntity<>(savedPlacement, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Placement>> createMultiplePlacements(@RequestBody List<Placement> placements) {
        try {
            List<Placement> savedPlacements = placementService.saveAllPlacements(placements);
            return new ResponseEntity<>(savedPlacements, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<Placement>> getAllPlacements() {
        try {
            List<Placement> placements = placementService.getAllPlacements();
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{placementId}")
    public ResponseEntity<Placement> getPlacementById(@PathVariable Integer placementId) {
        try {
            Optional<Placement> placement = placementService.getPlacementById(placementId);
            return placement.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/student/{studentId}")
    public ResponseEntity<List<Placement>> getPlacementsByStudentId(@PathVariable Integer studentId) {
        try {
            List<Placement> placements = placementService.getPlacementsByStudentId(studentId);
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/company/{companyName}")
    public ResponseEntity<List<Placement>> getPlacementsByCompanyName(@PathVariable String companyName) {
        try {
            List<Placement> placements = placementService.getPlacementsByCompanyName(companyName);
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/position/{position}")
    public ResponseEntity<List<Placement>> getPlacementsByPosition(@PathVariable String position) {
        try {
            List<Placement> placements = placementService.getPlacementsByPosition(position);
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<Placement>> getPlacementsByStatus(@PathVariable String status) {
        try {
            List<Placement> placements = placementService.getPlacementsByStatus(status);
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/salary-range")
    public ResponseEntity<List<Placement>> getPlacementsBySalaryRange(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary) {
        try {
            List<Placement> placements = placementService.getPlacementsBySalaryRange(minSalary, maxSalary);
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/date-range")
    public ResponseEntity<List<Placement>> getPlacementsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            List<Placement> placements = placementService.getPlacementsByDateRange(startDate, endDate);
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/student/{studentId}")
    public ResponseEntity<Long> countPlacementsByStudentId(@PathVariable Integer studentId) {
        try {
            Long count = placementService.countPlacementsByStudentId(studentId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countPlacementsByStatus(@PathVariable String status) {
        try {
            Long count = placementService.countPlacementsByStatus(status);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{placementId}")
    public ResponseEntity<Placement> updatePlacement(@PathVariable Integer placementId, @RequestBody Placement placementDetails) {
        try {
            Placement updatedPlacement = placementService.updatePlacement(placementId, placementDetails);
            return new ResponseEntity<>(updatedPlacement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{placementId}/status")
    public ResponseEntity<Placement> updatePlacementStatus(@PathVariable Integer placementId, @RequestParam String status) {
        try {
            Placement updatedPlacement = placementService.updatePlacementStatus(placementId, status);
            return new ResponseEntity<>(updatedPlacement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{placementId}/salary")
    public ResponseEntity<Placement> updatePlacementSalary(@PathVariable Integer placementId, @RequestParam Double salary) {
        try {
            Placement updatedPlacement = placementService.updatePlacementSalary(placementId, salary);
            return new ResponseEntity<>(updatedPlacement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{placementId}/company")
    public ResponseEntity<Placement> updatePlacementCompany(@PathVariable Integer placementId, @RequestParam String companyName) {
        try {
            Placement updatedPlacement = placementService.updatePlacementCompany(placementId, companyName);
            return new ResponseEntity<>(updatedPlacement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{placementId}/position")
    public ResponseEntity<Placement> updatePlacementPosition(@PathVariable Integer placementId, @RequestParam String position) {
        try {
            Placement updatedPlacement = placementService.updatePlacementPosition(placementId, position);
            return new ResponseEntity<>(updatedPlacement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{placementId}/isplaced")
    public ResponseEntity<Placement> updatePlacementIsPlaced(@PathVariable Integer placementId, @RequestParam Boolean isPlaced) {
        try {
            Placement updatedPlacement = placementService.updatePlacementIsPlaced(placementId, isPlaced);
            return new ResponseEntity<>(updatedPlacement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{placementId}")
    public ResponseEntity<Void> deletePlacement(@PathVariable Integer placementId) {
        try {
            placementService.deletePlacement(placementId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deletePlacementsByStudentId(@PathVariable Integer studentId) {
        try {
            placementService.deletePlacementsByStudentId(studentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deletePlacementsByStatus(@PathVariable String status) {
        try {
            placementService.deletePlacementsByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllPlacements() {
        try {
            placementService.deleteAllPlacements();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<Placement>> searchPlacements(@PathVariable String searchTerm) {
        try {
            List<Placement> placements = placementService.searchPlacements(searchTerm);
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/company-position")
    public ResponseEntity<List<Placement>> getPlacementsByCompanyAndPosition(
            @RequestParam String companyName,
            @RequestParam String position) {
        try {
            List<Placement> placements = placementService.getPlacementsByCompanyAndPosition(companyName, position);
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Placement>> getPlacementsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Placement> placements = placementService.getPlacementsWithPagination(page, size);
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/salary")
    public ResponseEntity<List<Placement>> getPlacementsSortedBySalary() {
        try {
            List<Placement> placements = placementService.getPlacementsSortedBySalary();
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/date")
    public ResponseEntity<List<Placement>> getPlacementsSortedByDate() {
        try {
            List<Placement> placements = placementService.getPlacementsSortedByDate();
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/company")
    public ResponseEntity<List<Placement>> getPlacementsSortedByCompany() {
        try {
            List<Placement> placements = placementService.getPlacementsSortedByCompany();
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/successful")
    public ResponseEntity<List<Placement>> getSuccessfulPlacements() {
        try {
            List<Placement> placements = placementService.getSuccessfulPlacements();
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Placement>> getPendingPlacements() {
        try {
            List<Placement> placements = placementService.getPendingPlacements();
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/failed")
    public ResponseEntity<List<Placement>> getFailedPlacements() {
        try {
            List<Placement> placements = placementService.getFailedPlacements();
            return new ResponseEntity<>(placements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/average-salary/company/{companyName}")
    public ResponseEntity<Double> getAverageSalaryByCompany(@PathVariable String companyName) {
        try {
            Double averageSalary = placementService.getAverageSalaryByCompany(companyName);
            return new ResponseEntity<>(averageSalary, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/total-salary/student/{studentId}")
    public ResponseEntity<Double> getTotalSalaryByStudentId(@PathVariable Integer studentId) {
        try {
            Double totalSalary = placementService.getTotalSalaryByStudentId(studentId);
            return new ResponseEntity<>(totalSalary, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 