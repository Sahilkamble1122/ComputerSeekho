package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_with_type")
public class PaymentWithTypeDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;
    
    @Column(name = "payment_type_id")
    private Integer paymentTypeId;
    
    @Column(name = "payment_type_desc")
    private String paymentTypeDesc;
    
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    
    @Column(name = "student_id")
    private Integer studentId;
    
    @Column(name = "course_id")
    private Integer courseId;
    
    @Column(name = "batch_id")
    private Integer batchId;
    
    @Column(name = "amount")
    private Double amount;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public PaymentWithTypeDTO() {}
    
    // Parameterized constructor
    public PaymentWithTypeDTO(Integer paymentId, Integer paymentTypeId, String paymentTypeDesc,
                             LocalDate paymentDate, Integer studentId, Integer courseId,
                             Integer batchId, Double amount, String status,
                             LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.paymentId = paymentId;
        this.paymentTypeId = paymentTypeId;
        this.paymentTypeDesc = paymentTypeDesc;
        this.paymentDate = paymentDate;
        this.studentId = studentId;
        this.courseId = courseId;
        this.batchId = batchId;
        this.amount = amount;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    
    // Getters and Setters
    public Integer getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }
    
    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }
    
    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }
    
    public String getPaymentTypeDesc() {
        return paymentTypeDesc;
    }
    
    public void setPaymentTypeDesc(String paymentTypeDesc) {
        this.paymentTypeDesc = paymentTypeDesc;
    }
    
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public Integer getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    public Integer getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    public Integer getBatchId() {
        return batchId;
    }
    
    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    @Override
    public String toString() {
        return "PaymentWithTypeDTO{" +
                "paymentId=" + paymentId +
                ", paymentTypeId=" + paymentTypeId +
                ", paymentTypeDesc='" + paymentTypeDesc + '\'' +
                ", paymentDate=" + paymentDate +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", batchId=" + batchId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
} 