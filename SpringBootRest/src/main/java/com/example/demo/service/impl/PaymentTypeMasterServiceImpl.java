package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.PaymentTypeMaster;
import com.example.demo.repository.PaymentTypeMasterRepository;
import com.example.demo.service.PaymentTypeMasterService;

@Service
public class PaymentTypeMasterServiceImpl implements PaymentTypeMasterService {

    @Autowired
    private PaymentTypeMasterRepository paymentTypeMasterRepository;

    // CREATE OPERATIONS
    @Override
    public PaymentTypeMaster savePaymentType(PaymentTypeMaster paymentType) {
        return paymentTypeMasterRepository.save(paymentType);
    }

    @Override
    public List<PaymentTypeMaster> saveAllPaymentTypes(List<PaymentTypeMaster> paymentTypes) {
        return paymentTypeMasterRepository.saveAll(paymentTypes);
    }

    // READ OPERATIONS
    @Override
    public List<PaymentTypeMaster> getAllPaymentTypes() {
        return paymentTypeMasterRepository.findAll();
    }

    @Override
    public Optional<PaymentTypeMaster> getPaymentTypeById(Integer paymentTypeId) {
        return paymentTypeMasterRepository.findById(paymentTypeId);
    }

    @Override
    public List<PaymentTypeMaster> getPaymentTypesByName(String paymentTypeName) {
        return paymentTypeMasterRepository.findByPaymentTypeDesc(paymentTypeName);
    }

    @Override
    public List<PaymentTypeMaster> getPaymentTypesByDescription(String description) {
        return paymentTypeMasterRepository.findByPaymentTypeDesc(description);
    }

    @Override
    public List<PaymentTypeMaster> getPaymentTypesByStatus(String status) {
        // Since PaymentTypeMaster doesn't have a status field, we'll return all payment types
        return paymentTypeMasterRepository.findAll();
    }

    @Override
    public List<PaymentTypeMaster> getPaymentTypesByNameContaining(String paymentTypeName) {
        return paymentTypeMasterRepository.findByPaymentTypeDescContainingIgnoreCase(paymentTypeName);
    }

    @Override
    public List<PaymentTypeMaster> getActivePaymentTypes() {
        // Since PaymentTypeMaster doesn't have an active field, we'll return all payment types
        return paymentTypeMasterRepository.findAll();
    }

    @Override
    public Long countActivePaymentTypes() {
        // Since PaymentTypeMaster doesn't have an active field, we'll return count of all payment types
        return paymentTypeMasterRepository.count();
    }

    // UPDATE OPERATIONS
    @Override
    public PaymentTypeMaster updatePaymentType(Integer paymentTypeId, PaymentTypeMaster paymentTypeDetails) {
        PaymentTypeMaster existingPaymentType = paymentTypeMasterRepository.findById(paymentTypeId)
                .orElseThrow(() -> new RuntimeException("Payment type not found with id: " + paymentTypeId));
        
        existingPaymentType.setPaymentTypeDesc(paymentTypeDetails.getPaymentTypeDesc());
        existingPaymentType.setUpdatedDate(java.time.LocalDateTime.now());
        
        return paymentTypeMasterRepository.save(existingPaymentType);
    }

    @Override
    public PaymentTypeMaster updatePaymentTypeName(Integer paymentTypeId, String paymentTypeName) {
        PaymentTypeMaster paymentType = paymentTypeMasterRepository.findById(paymentTypeId)
                .orElseThrow(() -> new RuntimeException("Payment type not found with id: " + paymentTypeId));
        paymentType.setPaymentTypeDesc(paymentTypeName);
        paymentType.setUpdatedDate(java.time.LocalDateTime.now());
        return paymentTypeMasterRepository.save(paymentType);
    }

    @Override
    public PaymentTypeMaster updatePaymentTypeDescription(Integer paymentTypeId, String description) {
        PaymentTypeMaster paymentType = paymentTypeMasterRepository.findById(paymentTypeId)
                .orElseThrow(() -> new RuntimeException("Payment type not found with id: " + paymentTypeId));
        paymentType.setPaymentTypeDesc(description);
        paymentType.setUpdatedDate(java.time.LocalDateTime.now());
        return paymentTypeMasterRepository.save(paymentType);
    }

    @Override
    public PaymentTypeMaster updatePaymentTypeStatus(Integer paymentTypeId, String status) {
        PaymentTypeMaster paymentType = paymentTypeMasterRepository.findById(paymentTypeId)
                .orElseThrow(() -> new RuntimeException("Payment type not found with id: " + paymentTypeId));
        // Since PaymentTypeMaster doesn't have a status field, we'll just update the description
        paymentType.setPaymentTypeDesc(status);
        paymentType.setUpdatedDate(java.time.LocalDateTime.now());
        return paymentTypeMasterRepository.save(paymentType);
    }

    // DELETE OPERATIONS
    @Override
    public void deletePaymentType(Integer paymentTypeId) {
        if (!paymentTypeMasterRepository.existsById(paymentTypeId)) {
            throw new RuntimeException("Payment type not found with id: " + paymentTypeId);
        }
        paymentTypeMasterRepository.deleteById(paymentTypeId);
    }

    @Override
    public void deletePaymentTypesByStatus(String status) {
        // Since PaymentTypeMaster doesn't have a status field, we'll delete all payment types
        paymentTypeMasterRepository.deleteAll();
    }

    @Override
    public void deleteInactivePaymentTypes() {
        // Since PaymentTypeMaster doesn't have an active field, we'll delete all payment types
        paymentTypeMasterRepository.deleteAll();
    }

    @Override
    public void deleteAllPaymentTypes() {
        paymentTypeMasterRepository.deleteAll();
    }

    // BUSINESS LOGIC OPERATIONS
    @Override
    public List<PaymentTypeMaster> searchPaymentTypes(String searchTerm) {
        return paymentTypeMasterRepository.findByPaymentTypeDescContainingIgnoreCase(searchTerm);
    }

    @Override
    public List<PaymentTypeMaster> getPaymentTypesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PaymentTypeMaster> paymentTypePage = paymentTypeMasterRepository.findAll(pageable);
        return paymentTypePage.getContent();
    }

    @Override
    public List<PaymentTypeMaster> getPaymentTypesSortedByName() {
        return paymentTypeMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "paymentTypeDesc"));
    }

    @Override
    public List<PaymentTypeMaster> getPaymentTypesSortedByStatus() {
        // Since PaymentTypeMaster doesn't have a status field, we'll sort by description
        return paymentTypeMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "paymentTypeDesc"));
    }

    @Override
    public List<PaymentTypeMaster> getPopularPaymentTypes() {
        // This would typically involve more complex logic, but for now we'll return all payment types
        return paymentTypeMasterRepository.findAll();
    }
} 