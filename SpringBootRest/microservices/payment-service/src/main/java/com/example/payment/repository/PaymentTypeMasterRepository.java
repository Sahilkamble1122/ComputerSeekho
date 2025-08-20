package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PaymentTypeMaster;

@Repository
public interface PaymentTypeMasterRepository extends JpaRepository<PaymentTypeMaster, Integer> {
    
    // Basic find operations
    List<PaymentTypeMaster> findByPaymentTypeDesc(String paymentTypeDesc);
    List<PaymentTypeMaster> findByPaymentTypeDescContainingIgnoreCase(String paymentTypeDesc);
} 