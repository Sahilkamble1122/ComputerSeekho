package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Placement;

public interface PlacementService {
    
    // CREATE OPERATIONS
    Placement savePlacement(Placement placement);
    List<Placement> saveAllPlacements(List<Placement> placements);
    
    // READ OPERATIONS
    List<Placement> getAllPlacements();
    Optional<Placement> getPlacementById(Integer placementId);
    List<Placement> getPlacementsByStudentId(Integer studentId);
    List<Placement> getPlacementsByCompanyName(String companyName);
    List<Placement> getPlacementsByPosition(String position);
    List<Placement> getPlacementsByStatus(String status);
    List<Placement> getPlacementsBySalaryRange(Double minSalary, Double maxSalary);
    List<Placement> getPlacementsByDateRange(String startDate, String endDate);
    Long countPlacementsByStudentId(Integer studentId);
    Long countPlacementsByStatus(String status);
    
    // UPDATE OPERATIONS
    Placement updatePlacement(Integer placementId, Placement placementDetails);
    Placement updatePlacementStatus(Integer placementId, String status);
    Placement updatePlacementSalary(Integer placementId, Double salary);
    Placement updatePlacementCompany(Integer placementId, String companyName);
    Placement updatePlacementPosition(Integer placementId, String position);
    Placement updatePlacementIsPlaced(Integer placementId, Boolean isPlaced);
    
    // DELETE OPERATIONS
    void deletePlacement(Integer placementId);
    void deletePlacementsByStudentId(Integer studentId);
    void deletePlacementsByStatus(String status);
    void deleteAllPlacements();
    
    // BUSINESS LOGIC OPERATIONS
    List<Placement> searchPlacements(String searchTerm);
    List<Placement> getPlacementsByCompanyAndPosition(String companyName, String position);
    List<Placement> getPlacementsWithPagination(int page, int size);
    List<Placement> getPlacementsSortedBySalary();
    List<Placement> getPlacementsSortedByDate();
    List<Placement> getPlacementsSortedByCompany();
    List<Placement> getSuccessfulPlacements();
    List<Placement> getPendingPlacements();
    List<Placement> getFailedPlacements();
    Double getAverageSalaryByCompany(String companyName);
    Double getTotalSalaryByStudentId(Integer studentId);
} 