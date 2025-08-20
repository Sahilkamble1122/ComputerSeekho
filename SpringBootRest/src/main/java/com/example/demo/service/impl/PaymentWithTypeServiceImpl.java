package com.example.demo.service.impl;

import com.example.demo.model.PaymentWithTypeDTO;
import com.example.demo.repository.PaymentWithTypeRepository;
import com.example.demo.service.PaymentWithTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentWithTypeServiceImpl implements PaymentWithTypeService {

    @Autowired
    private PaymentWithTypeRepository paymentWithTypeRepository;

    // Create operations
    @Override
    public PaymentWithTypeDTO savePaymentWithType(PaymentWithTypeDTO paymentWithTypeDTO) {
        if (paymentWithTypeDTO.getCreatedDate() == null) {
            paymentWithTypeDTO.setCreatedDate(java.time.LocalDateTime.now());
        }
        if (paymentWithTypeDTO.getUpdatedDate() == null) {
            paymentWithTypeDTO.setUpdatedDate(java.time.LocalDateTime.now());
        }
        return paymentWithTypeRepository.save(paymentWithTypeDTO);
    }

    @Override
    public List<PaymentWithTypeDTO> saveAllPaymentsWithType(List<PaymentWithTypeDTO> payments) {
        payments.forEach(payment -> {
            if (payment.getCreatedDate() == null) {
                payment.setCreatedDate(java.time.LocalDateTime.now());
            }
            if (payment.getUpdatedDate() == null) {
                payment.setUpdatedDate(java.time.LocalDateTime.now());
            }
        });
        return paymentWithTypeRepository.saveAll(payments);
    }

    // Read operations
    @Override
    public List<PaymentWithTypeDTO> getAllPaymentsWithType() {
        return paymentWithTypeRepository.findAll();
    }

    @Override
    public Optional<PaymentWithTypeDTO> getPaymentWithTypeById(Integer paymentId) {
        return paymentWithTypeRepository.findById(paymentId);
    }

    @Override
    public List<PaymentWithTypeDTO> getPaymentsByStudentId(Integer studentId) {
        return paymentWithTypeRepository.findByStudentId(studentId);
    }

    @Override
    public List<PaymentWithTypeDTO> getPaymentsByCourseId(Integer courseId) {
        return paymentWithTypeRepository.findByCourseId(courseId);
    }

    @Override
    public List<PaymentWithTypeDTO> getPaymentsByBatchId(Integer batchId) {
        return paymentWithTypeRepository.findByBatchId(batchId);
    }

    @Override
    public List<PaymentWithTypeDTO> getPaymentsByTypeId(Integer paymentTypeId) {
        return paymentWithTypeRepository.findByPaymentTypeId(paymentTypeId);
    }

    @Override
    public List<PaymentWithTypeDTO> getPaymentsByStatus(String status) {
        return paymentWithTypeRepository.findByStatus(status);
    }

    @Override
    public List<PaymentWithTypeDTO> getPaymentsByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return paymentWithTypeRepository.findByPaymentDateBetween(start, end);
    }

    @Override
    public List<PaymentWithTypeDTO> getPaymentsByAmountRange(Double minAmount, Double maxAmount) {
        return paymentWithTypeRepository.findByAmountBetween(minAmount, maxAmount);
    }

    // Update operations
    @Override
    public PaymentWithTypeDTO updatePaymentWithType(Integer paymentId, PaymentWithTypeDTO paymentWithTypeDTO) {
        Optional<PaymentWithTypeDTO> existingPayment = paymentWithTypeRepository.findById(paymentId);
        if (existingPayment.isPresent()) {
            PaymentWithTypeDTO payment = existingPayment.get();
            payment.setPaymentTypeId(paymentWithTypeDTO.getPaymentTypeId());
            payment.setPaymentTypeDesc(paymentWithTypeDTO.getPaymentTypeDesc());
            payment.setPaymentDate(paymentWithTypeDTO.getPaymentDate());
            payment.setStudentId(paymentWithTypeDTO.getStudentId());
            payment.setCourseId(paymentWithTypeDTO.getCourseId());
            payment.setBatchId(paymentWithTypeDTO.getBatchId());
            payment.setAmount(paymentWithTypeDTO.getAmount());
            payment.setStatus(paymentWithTypeDTO.getStatus());
            payment.setUpdatedDate(java.time.LocalDateTime.now());
            return paymentWithTypeRepository.save(payment);
        }
        throw new RuntimeException("Payment not found with id: " + paymentId);
    }

    @Override
    public PaymentWithTypeDTO updatePaymentStatus(Integer paymentId, String status) {
        Optional<PaymentWithTypeDTO> existingPayment = paymentWithTypeRepository.findById(paymentId);
        if (existingPayment.isPresent()) {
            PaymentWithTypeDTO payment = existingPayment.get();
            payment.setStatus(status);
            payment.setUpdatedDate(java.time.LocalDateTime.now());
            return paymentWithTypeRepository.save(payment);
        }
        throw new RuntimeException("Payment not found with id: " + paymentId);
    }

    @Override
    public PaymentWithTypeDTO updatePaymentAmount(Integer paymentId, Double amount) {
        Optional<PaymentWithTypeDTO> existingPayment = paymentWithTypeRepository.findById(paymentId);
        if (existingPayment.isPresent()) {
            PaymentWithTypeDTO payment = existingPayment.get();
            payment.setAmount(amount);
            payment.setUpdatedDate(java.time.LocalDateTime.now());
            return paymentWithTypeRepository.save(payment);
        }
        throw new RuntimeException("Payment not found with id: " + paymentId);
    }

    @Override
    public PaymentWithTypeDTO updatePaymentType(Integer paymentId, Integer paymentTypeId) {
        Optional<PaymentWithTypeDTO> existingPayment = paymentWithTypeRepository.findById(paymentId);
        if (existingPayment.isPresent()) {
            PaymentWithTypeDTO payment = existingPayment.get();
            payment.setPaymentTypeId(paymentTypeId);
            payment.setUpdatedDate(java.time.LocalDateTime.now());
            return paymentWithTypeRepository.save(payment);
        }
        throw new RuntimeException("Payment not found with id: " + paymentId);
    }

    // Delete operations
    @Override
    public void deletePaymentWithType(Integer paymentId) {
        if (!paymentWithTypeRepository.existsById(paymentId)) {
            throw new RuntimeException("Payment not found with id: " + paymentId);
        }
        paymentWithTypeRepository.deleteById(paymentId);
    }

    @Override
    public void deletePaymentsByStudentId(Integer studentId) {
        List<PaymentWithTypeDTO> paymentsToDelete = paymentWithTypeRepository.findByStudentId(studentId);
        paymentWithTypeRepository.deleteAll(paymentsToDelete);
    }

    @Override
    public void deletePaymentsByStatus(String status) {
        List<PaymentWithTypeDTO> paymentsToDelete = paymentWithTypeRepository.findByStatus(status);
        paymentWithTypeRepository.deleteAll(paymentsToDelete);
    }

    // Business logic operations
    @Override
    public Double getTotalAmountByStudentId(Integer studentId) {
        return paymentWithTypeRepository.sumAmountByStudentId(studentId);
    }

    @Override
    public Double getTotalAmountByCourseId(Integer courseId) {
        return paymentWithTypeRepository.sumAmountByCourseId(courseId);
    }

    @Override
    public Double getTotalAmountByBatchId(Integer batchId) {
        return paymentWithTypeRepository.sumAmountByBatchId(batchId);
    }

    @Override
    public Long countPaymentsByStudentId(Integer studentId) {
        return paymentWithTypeRepository.countByStudentId(studentId);
    }

    @Override
    public Long countPaymentsByStatus(String status) {
        return paymentWithTypeRepository.countByStatus(status);
    }

    @Override
    public List<PaymentWithTypeDTO> getPendingPayments() {
        return paymentWithTypeRepository.findPendingPayments();
    }

    @Override
    public List<PaymentWithTypeDTO> getCompletedPayments() {
        return paymentWithTypeRepository.findCompletedPayments();
    }

    @Override
    public List<PaymentWithTypeDTO> getOverduePayments() {
        return paymentWithTypeRepository.findOverduePayments();
    }

    @Override
    public Object getPaymentSummaryByStudentId(Integer studentId) {
        Map<String, Object> summary = new HashMap<>();
        summary.put("studentId", studentId);
        summary.put("totalAmount", getTotalAmountByStudentId(studentId));
        summary.put("totalPayments", countPaymentsByStudentId(studentId));
        summary.put("pendingPayments", countPaymentsByStatus("PENDING"));
        summary.put("completedPayments", countPaymentsByStatus("COMPLETED"));
        summary.put("overduePayments", countPaymentsByStatus("OVERDUE"));
        return summary;
    }

    @Override
    public Object getPaymentSummaryByCourseId(Integer courseId) {
        Map<String, Object> summary = new HashMap<>();
        summary.put("courseId", courseId);
        summary.put("totalAmount", getTotalAmountByCourseId(courseId));
        summary.put("totalPayments", paymentWithTypeRepository.findByCourseId(courseId).size());
        summary.put("pendingPayments", paymentWithTypeRepository.findByCourseId(courseId)
                .stream().filter(p -> "PENDING".equals(p.getStatus())).count());
        summary.put("completedPayments", paymentWithTypeRepository.findByCourseId(courseId)
                .stream().filter(p -> "COMPLETED".equals(p.getStatus())).count());
        summary.put("overduePayments", paymentWithTypeRepository.findByCourseId(courseId)
                .stream().filter(p -> "OVERDUE".equals(p.getStatus())).count());
        return summary;
    }
}
