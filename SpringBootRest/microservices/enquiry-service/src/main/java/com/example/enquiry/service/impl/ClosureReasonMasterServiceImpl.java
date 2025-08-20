package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.ClosureReasonMaster;
import com.example.demo.repository.ClosureReasonMasterRepository;
import com.example.demo.service.ClosureReasonMasterService;

@Service
public class ClosureReasonMasterServiceImpl implements ClosureReasonMasterService {

    @Autowired
    private ClosureReasonMasterRepository closureReasonMasterRepository;

    // CREATE OPERATIONS
    @Override
    public ClosureReasonMaster saveClosureReason(ClosureReasonMaster closureReason) {
        return closureReasonMasterRepository.save(closureReason);
    }

    @Override
    public List<ClosureReasonMaster> saveAllClosureReasons(List<ClosureReasonMaster> closureReasons) {
        return closureReasonMasterRepository.saveAll(closureReasons);
    }

    // READ OPERATIONS
    @Override
    public List<ClosureReasonMaster> getAllClosureReasons() {
        return closureReasonMasterRepository.findAll();
    }

    @Override
    public Optional<ClosureReasonMaster> getClosureReasonById(Integer closureReasonId) {
        return closureReasonMasterRepository.findById(closureReasonId);
    }

    @Override
    public List<ClosureReasonMaster> getClosureReasonsByName(String reasonName) {
        return closureReasonMasterRepository.findByClosureReasonName(reasonName);
    }

    @Override
    public List<ClosureReasonMaster> getClosureReasonsByDescription(String description) {
        return closureReasonMasterRepository.findByClosureReasonDesc(description);
    }

    @Override
    public List<ClosureReasonMaster> getClosureReasonsByStatus(String status) {
        Boolean isActive = "Active".equalsIgnoreCase(status);
        return closureReasonMasterRepository.findByClosureReasonIsActive(isActive);
    }

    @Override
    public List<ClosureReasonMaster> getClosureReasonsByNameContaining(String reasonName) {
        return closureReasonMasterRepository.findByClosureReasonNameContainingIgnoreCase(reasonName);
    }

    @Override
    public List<ClosureReasonMaster> getActiveClosureReasons() {
        return closureReasonMasterRepository.findByClosureReasonIsActive(true);
    }

    @Override
    public Long countActiveClosureReasons() {
        return closureReasonMasterRepository.countByClosureReasonIsActive(true);
    }

    // UPDATE OPERATIONS
    @Override
    public ClosureReasonMaster updateClosureReason(Integer closureReasonId, ClosureReasonMaster closureReasonDetails) {
        ClosureReasonMaster existingClosureReason = closureReasonMasterRepository.findById(closureReasonId)
                .orElseThrow(() -> new RuntimeException("Closure reason not found with id: " + closureReasonId));
        
        existingClosureReason.setClosureReasonName(closureReasonDetails.getClosureReasonName());
        existingClosureReason.setClosureReasonDesc(closureReasonDetails.getClosureReasonDesc());
        existingClosureReason.setClosureReasonIsActive(closureReasonDetails.getClosureReasonIsActive());
        existingClosureReason.setUpdatedDate(java.time.LocalDateTime.now());
        
        return closureReasonMasterRepository.save(existingClosureReason);
    }

    @Override
    public ClosureReasonMaster updateClosureReasonName(Integer closureReasonId, String reasonName) {
        ClosureReasonMaster closureReason = closureReasonMasterRepository.findById(closureReasonId)
                .orElseThrow(() -> new RuntimeException("Closure reason not found with id: " + closureReasonId));
        closureReason.setClosureReasonName(reasonName);
        closureReason.setUpdatedDate(java.time.LocalDateTime.now());
        return closureReasonMasterRepository.save(closureReason);
    }

    @Override
    public ClosureReasonMaster updateClosureReasonDescription(Integer closureReasonId, String description) {
        ClosureReasonMaster closureReason = closureReasonMasterRepository.findById(closureReasonId)
                .orElseThrow(() -> new RuntimeException("Closure reason not found with id: " + closureReasonId));
        closureReason.setClosureReasonDesc(description);
        closureReason.setUpdatedDate(java.time.LocalDateTime.now());
        return closureReasonMasterRepository.save(closureReason);
    }

    @Override
    public ClosureReasonMaster updateClosureReasonStatus(Integer closureReasonId, String status) {
        ClosureReasonMaster closureReason = closureReasonMasterRepository.findById(closureReasonId)
                .orElseThrow(() -> new RuntimeException("Closure reason not found with id: " + closureReasonId));
        Boolean isActive = "Active".equalsIgnoreCase(status);
        closureReason.setClosureReasonIsActive(isActive);
        closureReason.setUpdatedDate(java.time.LocalDateTime.now());
        return closureReasonMasterRepository.save(closureReason);
    }

    // DELETE OPERATIONS
    @Override
    public void deleteClosureReason(Integer closureReasonId) {
        if (!closureReasonMasterRepository.existsById(closureReasonId)) {
            throw new RuntimeException("Closure reason not found with id: " + closureReasonId);
        }
        closureReasonMasterRepository.deleteById(closureReasonId);
    }

    @Override
    public void deleteClosureReasonsByStatus(String status) {
        Boolean isActive = "Active".equalsIgnoreCase(status);
        closureReasonMasterRepository.deleteByClosureReasonIsActive(isActive);
    }

    @Override
    public void deleteInactiveClosureReasons() {
        closureReasonMasterRepository.deleteByClosureReasonIsActive(false);
    }

    @Override
    public void deleteAllClosureReasons() {
        closureReasonMasterRepository.deleteAll();
    }

    // BUSINESS LOGIC OPERATIONS
    @Override
    public List<ClosureReasonMaster> searchClosureReasons(String searchTerm) {
        return closureReasonMasterRepository.findByClosureReasonNameContainingIgnoreCaseOrClosureReasonDescContainingIgnoreCase(searchTerm, searchTerm);
    }

    @Override
    public List<ClosureReasonMaster> getClosureReasonsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClosureReasonMaster> closureReasonPage = closureReasonMasterRepository.findAll(pageable);
        return closureReasonPage.getContent();
    }

    @Override
    public List<ClosureReasonMaster> getClosureReasonsSortedByName() {
        return closureReasonMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "reasonName"));
    }

    @Override
    public List<ClosureReasonMaster> getClosureReasonsSortedByStatus() {
        return closureReasonMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "status"));
    }

    @Override
    public List<ClosureReasonMaster> getFrequentlyUsedClosureReasons() {
        // This would typically involve more complex logic, but for now we'll return active reasons
        return closureReasonMasterRepository.findByClosureReasonIsActive(true);
    }
} 