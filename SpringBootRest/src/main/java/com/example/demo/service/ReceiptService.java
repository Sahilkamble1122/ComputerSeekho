package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Receipt;

public interface ReceiptService {
    
    // Create operations
    Receipt saveReceipt(Receipt receipt);
    List<Receipt> saveAllReceipts(List<Receipt> receipts);
    
    // Read operations
    List<Receipt> getAllReceipts();
    Optional<Receipt> getReceiptById(Integer id);
    List<Receipt> getReceiptsByPaymentId(Integer paymentId);
    List<Receipt> getReceiptsByAmountRange(Double minAmount, Double maxAmount);
    List<Receipt> getReceiptsByDateRange(String startDate, String endDate);
    
    // Update operations
    Receipt updateReceipt(Integer id, Receipt receipt);
    Receipt updateReceiptAmount(Integer id, Double amount);
    Receipt updateReceiptNumber(Integer id, String receiptNumber);
    
    // Delete operations
    void deleteReceipt(Integer id);
    void deleteReceiptsByPaymentId(Integer paymentId);
    
    // Business logic operations
    Double getTotalAmountByPaymentId(Integer paymentId);
    Long countReceiptsByPaymentId(Integer paymentId);
    Double getTotalAmountByDateRange(String startDate, String endDate);
    Long countReceiptsByDateRange(String startDate, String endDate);
}
