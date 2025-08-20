package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Placement;
import com.example.demo.repository.PlacementRepository;
import com.example.demo.service.PlacementService;

@Service
public class PlacementServiceImpl implements PlacementService {

    @Autowired
    private PlacementRepository placementRepository;

    // CREATE OPERATIONS
    @Override
    public Placement savePlacement(Placement placement) {
        return placementRepository.save(placement);
    }

    @Override
    public List<Placement> saveAllPlacements(List<Placement> placements) {
        return placementRepository.saveAll(placements);
    }

    // READ OPERATIONS
    @Override
    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }

    @Override
    public Optional<Placement> getPlacementById(Integer placementId) {
        return placementRepository.findById(placementId);
    }

    @Override
    public List<Placement> getPlacementsByStudentId(Integer studentId) {
        return placementRepository.findByStudentId(studentId);
    }

    @Override
    public List<Placement> getPlacementsByCompanyName(String companyName) {
        return placementRepository.findByCompanyName(companyName);
    }

    @Override
    public List<Placement> getPlacementsByPosition(String position) {
        return placementRepository.findByDesignation(position);
    }

    @Override
    public List<Placement> getPlacementsByStatus(String status) {
        Boolean isPlaced = "Placed".equalsIgnoreCase(status);
        return placementRepository.findByIsPlaced(isPlaced);
    }

    @Override
    public List<Placement> getPlacementsBySalaryRange(Double minSalary, Double maxSalary) {
        // Since Placement doesn't have salary field, we'll use batchId as a substitute
        return placementRepository.findByBatchIdBetween(minSalary.intValue(), maxSalary.intValue());
    }

    @Override
    public List<Placement> getPlacementsByDateRange(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return placementRepository.findByPlacementDateBetween(start, end);
    }

    @Override
    public Long countPlacementsByStudentId(Integer studentId) {
        return placementRepository.countByStudentId(studentId);
    }

    @Override
    public Long countPlacementsByStatus(String status) {
        Boolean isPlaced = "Placed".equalsIgnoreCase(status);
        return placementRepository.countByIsPlaced(isPlaced);
    }

    // UPDATE OPERATIONS
    @Override
    public Placement updatePlacement(Integer placementId, Placement placementDetails) {
        Placement existingPlacement = placementRepository.findById(placementId)
                .orElseThrow(() -> new RuntimeException("Placement not found with id: " + placementId));
        
        existingPlacement.setStudentId(placementDetails.getStudentId());
        existingPlacement.setCourseId(placementDetails.getCourseId());
        existingPlacement.setBatchId(placementDetails.getBatchId());
        existingPlacement.setCompanyName(placementDetails.getCompanyName());
        existingPlacement.setDesignation(placementDetails.getDesignation());
        existingPlacement.setPlacementDate(placementDetails.getPlacementDate());
        existingPlacement.setIsPlaced(placementDetails.getIsPlaced());
        existingPlacement.setUpdatedDate(java.time.LocalDateTime.now());
        
        return placementRepository.save(existingPlacement);
    }

    @Override
    public Placement updatePlacementStatus(Integer placementId, String status) {
        Placement placement = placementRepository.findById(placementId)
                .orElseThrow(() -> new RuntimeException("Placement not found with id: " + placementId));
        Boolean isPlaced = "Placed".equalsIgnoreCase(status);
        placement.setIsPlaced(isPlaced);
        placement.setUpdatedDate(java.time.LocalDateTime.now());
        return placementRepository.save(placement);
    }

    @Override
    public Placement updatePlacementSalary(Integer placementId, Double salary) {
        Placement placement = placementRepository.findById(placementId)
                .orElseThrow(() -> new RuntimeException("Placement not found with id: " + placementId));
        // Since Placement doesn't have salary field, we'll use batchId as a substitute
        placement.setBatchId(salary.intValue());
        placement.setUpdatedDate(java.time.LocalDateTime.now());
        return placementRepository.save(placement);
    }

    @Override
    public Placement updatePlacementCompany(Integer placementId, String companyName) {
        Placement placement = placementRepository.findById(placementId)
                .orElseThrow(() -> new RuntimeException("Placement not found with id: " + placementId));
        placement.setCompanyName(companyName);
        placement.setUpdatedDate(java.time.LocalDateTime.now());
        return placementRepository.save(placement);
    }

    @Override
    public Placement updatePlacementPosition(Integer placementId, String position) {
        Placement placement = placementRepository.findById(placementId)
                .orElseThrow(() -> new RuntimeException("Placement not found with id: " + placementId));
        placement.setDesignation(position);
        placement.setUpdatedDate(java.time.LocalDateTime.now());
        return placementRepository.save(placement);
    }

    @Override
    public Placement updatePlacementIsPlaced(Integer placementId, Boolean isPlaced) {
        Placement placement = placementRepository.findById(placementId)
                .orElseThrow(() -> new RuntimeException("Placement not found with id: " + placementId));
        placement.setIsPlaced(isPlaced);
        placement.setUpdatedDate(java.time.LocalDateTime.now());
        return placementRepository.save(placement);
    }

    // DELETE OPERATIONS
    @Override
    public void deletePlacement(Integer placementId) {
        if (!placementRepository.existsById(placementId)) {
            throw new RuntimeException("Placement not found with id: " + placementId);
        }
        placementRepository.deleteById(placementId);
    }

    @Override
    public void deletePlacementsByStudentId(Integer studentId) {
        placementRepository.deleteByStudentId(studentId);
    }

    @Override
    public void deletePlacementsByStatus(String status) {
        Boolean isPlaced = "Placed".equalsIgnoreCase(status);
        placementRepository.deleteByIsPlaced(isPlaced);
    }

    @Override
    public void deleteAllPlacements() {
        placementRepository.deleteAll();
    }

    // BUSINESS LOGIC OPERATIONS
    @Override
    public List<Placement> searchPlacements(String searchTerm) {
        return placementRepository.findByCompanyNameContainingIgnoreCaseOrDesignationContainingIgnoreCaseOrIsPlacedContainingIgnoreCase(searchTerm, searchTerm, searchTerm);
    }

    @Override
    public List<Placement> getPlacementsByCompanyAndPosition(String companyName, String position) {
        return placementRepository.findByCompanyNameAndDesignation(companyName, position);
    }

    @Override
    public List<Placement> getPlacementsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Placement> placementPage = placementRepository.findAll(pageable);
        return placementPage.getContent();
    }

    @Override
    public List<Placement> getPlacementsSortedBySalary() {
        // Since Placement doesn't have salary field, we'll sort by batchId
        return placementRepository.findAll(Sort.by(Sort.Direction.ASC, "batchId"));
    }

    @Override
    public List<Placement> getPlacementsSortedByDate() {
        return placementRepository.findAll(Sort.by(Sort.Direction.ASC, "placementDate"));
    }

    @Override
    public List<Placement> getPlacementsSortedByCompany() {
        return placementRepository.findAll(Sort.by(Sort.Direction.ASC, "companyName"));
    }

    @Override
    public List<Placement> getSuccessfulPlacements() {
        return placementRepository.findByIsPlaced(true);
    }

    @Override
    public List<Placement> getPendingPlacements() {
        return placementRepository.findByIsPlaced(false);
    }

    @Override
    public List<Placement> getFailedPlacements() {
        return placementRepository.findByIsPlaced(false);
    }

    @Override
    public Double getAverageSalaryByCompany(String companyName) {
        // Since Placement doesn't have salary field, we'll return average batchId
        return placementRepository.findAverageBatchIdByCompanyName(companyName);
    }

    @Override
    public Double getTotalSalaryByStudentId(Integer studentId) {
        // Since Placement doesn't have salary field, we'll return sum of batchId
        return placementRepository.sumBatchIdByStudentId(studentId);
    }
} 