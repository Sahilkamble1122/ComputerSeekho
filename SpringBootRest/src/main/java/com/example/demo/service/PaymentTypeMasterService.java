package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.PaymentTypeMaster;

public interface PaymentTypeMasterService {
    
    // CREATE OPERATIONS
    PaymentTypeMaster savePaymentType(PaymentTypeMaster paymentType);
    List<PaymentTypeMaster> saveAllPaymentTypes(List<PaymentTypeMaster> paymentTypes);
    
    // READ OPERATIONS
    List<PaymentTypeMaster> getAllPaymentTypes();
    Optional<PaymentTypeMaster> getPaymentTypeById(Integer paymentTypeId);
    List<PaymentTypeMaster> getPaymentTypesByName(String paymentTypeName);
    List<PaymentTypeMaster> getPaymentTypesByDescription(String description);
    List<PaymentTypeMaster> getPaymentTypesByStatus(String status);
    List<PaymentTypeMaster> getPaymentTypesByNameContaining(String paymentTypeName);
    List<PaymentTypeMaster> getActivePaymentTypes();
    Long countActivePaymentTypes();
    
    // UPDATE OPERATIONS
    PaymentTypeMaster updatePaymentType(Integer paymentTypeId, PaymentTypeMaster paymentTypeDetails);
    PaymentTypeMaster updatePaymentTypeName(Integer paymentTypeId, String paymentTypeName);
    PaymentTypeMaster updatePaymentTypeDescription(Integer paymentTypeId, String description);
    PaymentTypeMaster updatePaymentTypeStatus(Integer paymentTypeId, String status);
    
    // DELETE OPERATIONS
    void deletePaymentType(Integer paymentTypeId);
    void deletePaymentTypesByStatus(String status);
    void deleteInactivePaymentTypes();
    void deleteAllPaymentTypes();
    
    // BUSINESS LOGIC OPERATIONS
    List<PaymentTypeMaster> searchPaymentTypes(String searchTerm);
    List<PaymentTypeMaster> getPaymentTypesWithPagination(int page, int size);
    List<PaymentTypeMaster> getPaymentTypesSortedByName();
    List<PaymentTypeMaster> getPaymentTypesSortedByStatus();
    List<PaymentTypeMaster> getPopularPaymentTypes();
} 