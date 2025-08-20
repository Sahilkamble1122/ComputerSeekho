package com.example.demo.repository;

import com.example.demo.model.PaymentWithTypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentWithTypeRepository extends JpaRepository<PaymentWithTypeDTO, Integer> {
    
    // Find payments by student ID
    @Query("SELECT p FROM PaymentWithTypeDTO p WHERE p.studentId = :studentId")
    List<PaymentWithTypeDTO> findByStudentId(@Param("studentId") Integer studentId);
    
    // Find payments by course ID
    @Query("SELECT p FROM PaymentWithTypeDTO p WHERE p.courseId = :courseId")
    List<PaymentWithTypeDTO> findByCourseId(@Param("courseId") Integer courseId);
    
    // Find payments by batch ID
    @Query("SELECT p FROM PaymentWithTypeDTO p WHERE p.batchId = :batchId")
    List<PaymentWithTypeDTO> findByBatchId(@Param("batchId") Integer batchId);
    
    // Find payments by payment type ID
    @Query("SELECT p FROM PaymentWithTypeDTO p WHERE p.paymentTypeId = :paymentTypeId")
    List<PaymentWithTypeDTO> findByPaymentTypeId(@Param("paymentTypeId") Integer paymentTypeId);
    
    // Find payments by status
    @Query("SELECT p FROM PaymentWithTypeDTO p WHERE p.status = :status")
    List<PaymentWithTypeDTO> findByStatus(@Param("status") String status);
    
    // Find payments by date range
    @Query("SELECT p FROM PaymentWithTypeDTO p WHERE p.paymentDate BETWEEN :startDate AND :endDate")
    List<PaymentWithTypeDTO> findByPaymentDateBetween(@Param("startDate") LocalDate startDate, 
                                                      @Param("endDate") LocalDate endDate);
    
    // Find payments by amount range
    @Query("SELECT p FROM PaymentWithTypeDTO p WHERE p.amount BETWEEN :minAmount AND :maxAmount")
    List<PaymentWithTypeDTO> findByAmountBetween(@Param("minAmount") Double minAmount, 
                                                @Param("maxAmount") Double maxAmount);
    
    // Count payments by student ID
    @Query("SELECT COUNT(p) FROM PaymentWithTypeDTO p WHERE p.studentId = :studentId")
    Long countByStudentId(@Param("studentId") Integer studentId);
    
    // Count payments by status
    @Query("SELECT COUNT(p) FROM PaymentWithTypeDTO p WHERE p.status = :status")
    Long countByStatus(@Param("status") String status);
    
    // Sum total amount by student ID
    @Query("SELECT COALESCE(SUM(p.amount), 0.0) FROM PaymentWithTypeDTO p WHERE p.studentId = :studentId")
    Double sumAmountByStudentId(@Param("studentId") Integer studentId);
    
    // Sum total amount by course ID
    @Query("SELECT COALESCE(SUM(p.amount), 0.0) FROM PaymentWithTypeDTO p WHERE p.courseId = :courseId")
    Double sumAmountByCourseId(@Param("courseId") Integer courseId);
    
    // Sum total amount by batch ID
    @Query("SELECT COALESCE(SUM(p.amount), 0.0) FROM PaymentWithTypeDTO p WHERE p.batchId = :batchId")
    Double sumAmountByBatchId(@Param("batchId") Integer batchId);
    
    // Find pending payments (status = 'PENDING')
    @Query("SELECT p FROM PaymentWithTypeDTO p WHERE p.status = 'PENDING'")
    List<PaymentWithTypeDTO> findPendingPayments();
    
    // Find completed payments (status = 'COMPLETED')
    @Query("SELECT p FROM PaymentWithTypeDTO p WHERE p.status = 'COMPLETED'")
    List<PaymentWithTypeDTO> findCompletedPayments();
    
    // Find overdue payments (status = 'OVERDUE')
    @Query("SELECT p FROM PaymentWithTypeDTO p WHERE p.status = 'OVERDUE'")
    List<PaymentWithTypeDTO> findOverduePayments();
}
