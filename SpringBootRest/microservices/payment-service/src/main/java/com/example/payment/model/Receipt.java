package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "receipt_master")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Integer receiptId;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "receipt_date")
    private java.time.LocalDate receiptDate;

    @Column(name = "receipt_amount")
    private Double receiptAmount;

    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "created_date")
    private java.time.LocalDateTime createdDate;

    @Column(name = "updated_date")
    private java.time.LocalDateTime updatedDate;

    // Getters and Setters
    public Integer getReceiptId() {
        return receiptId;
    }
    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }
    public String getReceiptNumber() {
        return receiptNumber;
    }
    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
    public java.time.LocalDate getReceiptDate() {
        return receiptDate;
    }
    public void setReceiptDate(java.time.LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }
    public Double getReceiptAmount() {
        return receiptAmount;
    }
    public void setReceiptAmount(Double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }
    public Integer getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }
    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public java.time.LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(java.time.LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public java.time.LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(java.time.LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
