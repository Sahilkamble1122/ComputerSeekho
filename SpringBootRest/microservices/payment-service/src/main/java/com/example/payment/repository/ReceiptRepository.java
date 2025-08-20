package com.example.demo.repository;

import com.example.demo.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    
    // Find receipts by payment ID
    @Query("SELECT r FROM Receipt r WHERE r.paymentId = :paymentId")
    List<Receipt> findByPaymentId(@Param("paymentId") Integer paymentId);
    
    // Find receipts by amount range
    @Query("SELECT r FROM Receipt r WHERE r.receiptAmount BETWEEN :minAmount AND :maxAmount")
    List<Receipt> findByReceiptAmountBetween(@Param("minAmount") Double minAmount, 
                                            @Param("maxAmount") Double maxAmount);
    
    // Find receipts by date range
    @Query("SELECT r FROM Receipt r WHERE r.receiptDate BETWEEN :startDate AND :endDate")
    List<Receipt> findByReceiptDateBetween(@Param("startDate") LocalDate startDate, 
                                          @Param("endDate") LocalDate endDate);
    
    // Count receipts by payment ID
    @Query("SELECT COUNT(r) FROM Receipt r WHERE r.paymentId = :paymentId")
    Long countByPaymentId(@Param("paymentId") Integer paymentId);
    
    // Count receipts by date range
    @Query("SELECT COUNT(r) FROM Receipt r WHERE r.receiptDate BETWEEN :startDate AND :endDate")
    Long countByDateRange(@Param("startDate") LocalDate startDate, 
                          @Param("endDate") LocalDate endDate);
    
    // Sum total amount by payment ID
    @Query("SELECT COALESCE(SUM(r.receiptAmount), 0.0) FROM Receipt r WHERE r.paymentId = :paymentId")
    Double sumAmountByPaymentId(@Param("paymentId") Integer paymentId);
    
    // Sum total amount by date range
    @Query("SELECT COALESCE(SUM(r.receiptAmount), 0.0) FROM Receipt r WHERE r.receiptDate BETWEEN :startDate AND :endDate")
    Double sumAmountByDateRange(@Param("startDate") LocalDate startDate, 
                                @Param("endDate") LocalDate endDate);
}
