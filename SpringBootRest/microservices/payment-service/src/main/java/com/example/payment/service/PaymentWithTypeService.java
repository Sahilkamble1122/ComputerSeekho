package com.example.demo.service;

import com.example.demo.model.PaymentWithTypeDTO;
import java.util.List;
import java.util.Optional;

public interface PaymentWithTypeService {
    
    // Create operations
    PaymentWithTypeDTO savePaymentWithType(PaymentWithTypeDTO paymentWithTypeDTO);
    List<PaymentWithTypeDTO> saveAllPaymentsWithType(List<PaymentWithTypeDTO> payments);
    
    // Read operations
    List<PaymentWithTypeDTO> getAllPaymentsWithType();
    Optional<PaymentWithTypeDTO> getPaymentWithTypeById(Integer paymentId);
    List<PaymentWithTypeDTO> getPaymentsByStudentId(Integer studentId);
    List<PaymentWithTypeDTO> getPaymentsByCourseId(Integer courseId);
    List<PaymentWithTypeDTO> getPaymentsByBatchId(Integer batchId);
    List<PaymentWithTypeDTO> getPaymentsByTypeId(Integer paymentTypeId);
    List<PaymentWithTypeDTO> getPaymentsByStatus(String status);
    List<PaymentWithTypeDTO> getPaymentsByDateRange(String startDate, String endDate);
    List<PaymentWithTypeDTO> getPaymentsByAmountRange(Double minAmount, Double maxAmount);
    
    // Update operations
    PaymentWithTypeDTO updatePaymentWithType(Integer paymentId, PaymentWithTypeDTO paymentWithTypeDTO);
    PaymentWithTypeDTO updatePaymentStatus(Integer paymentId, String status);
    PaymentWithTypeDTO updatePaymentAmount(Integer paymentId, Double amount);
    PaymentWithTypeDTO updatePaymentType(Integer paymentId, Integer paymentTypeId);
    
    // Delete operations
    void deletePaymentWithType(Integer paymentId);
    void deletePaymentsByStudentId(Integer studentId);
    void deletePaymentsByStatus(String status);
    
    // Business logic operations
    Double getTotalAmountByStudentId(Integer studentId);
    Double getTotalAmountByCourseId(Integer courseId);
    Double getTotalAmountByBatchId(Integer batchId);
    Long countPaymentsByStudentId(Integer studentId);
    Long countPaymentsByStatus(String status);
    List<PaymentWithTypeDTO> getPendingPayments();
    List<PaymentWithTypeDTO> getCompletedPayments();
    List<PaymentWithTypeDTO> getOverduePayments();
    Object getPaymentSummaryByStudentId(Integer studentId);
    Object getPaymentSummaryByCourseId(Integer courseId);
}
