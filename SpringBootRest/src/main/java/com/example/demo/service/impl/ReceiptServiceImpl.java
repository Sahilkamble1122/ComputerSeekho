package com.example.demo.service.impl;

import com.example.demo.model.Receipt;
import com.example.demo.repository.ReceiptRepository;
import com.example.demo.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    // Create operations
    @Override
    public Receipt saveReceipt(Receipt receipt) {
        if (receipt.getCreatedDate() == null) {
            receipt.setCreatedDate(java.time.LocalDateTime.now());
        }
        if (receipt.getUpdatedDate() == null) {
            receipt.setUpdatedDate(java.time.LocalDateTime.now());
        }
        return receiptRepository.save(receipt);
    }

    @Override
    public List<Receipt> saveAllReceipts(List<Receipt> receipts) {
        receipts.forEach(receipt -> {
            if (receipt.getCreatedDate() == null) {
                receipt.setCreatedDate(java.time.LocalDateTime.now());
            }
            if (receipt.getUpdatedDate() == null) {
                receipt.setUpdatedDate(java.time.LocalDateTime.now());
            }
        });
        return receiptRepository.saveAll(receipts);
    }

    // Read operations
    @Override
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public Optional<Receipt> getReceiptById(Integer id) {
        return receiptRepository.findById(id);
    }

    @Override
    public List<Receipt> getReceiptsByPaymentId(Integer paymentId) {
        return receiptRepository.findByPaymentId(paymentId);
    }

    @Override
    public List<Receipt> getReceiptsByAmountRange(Double minAmount, Double maxAmount) {
        return receiptRepository.findByReceiptAmountBetween(minAmount, maxAmount);
    }

    @Override
    public List<Receipt> getReceiptsByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return receiptRepository.findByReceiptDateBetween(start, end);
    }

    // Update operations
    @Override
    public Receipt updateReceipt(Integer id, Receipt receipt) {
        Optional<Receipt> existingReceipt = receiptRepository.findById(id);
        if (existingReceipt.isPresent()) {
            Receipt receiptToUpdate = existingReceipt.get();
            receiptToUpdate.setReceiptNumber(receipt.getReceiptNumber());
            receiptToUpdate.setReceiptDate(receipt.getReceiptDate());
            receiptToUpdate.setReceiptAmount(receipt.getReceiptAmount());
            receiptToUpdate.setPaymentId(receipt.getPaymentId());
            receiptToUpdate.setUpdatedDate(java.time.LocalDateTime.now());
            return receiptRepository.save(receiptToUpdate);
        }
        throw new RuntimeException("Receipt not found with id: " + id);
    }

    @Override
    public Receipt updateReceiptAmount(Integer id, Double amount) {
        Optional<Receipt> existingReceipt = receiptRepository.findById(id);
        if (existingReceipt.isPresent()) {
            Receipt receipt = existingReceipt.get();
            receipt.setReceiptAmount(amount);
            receipt.setUpdatedDate(java.time.LocalDateTime.now());
            return receiptRepository.save(receipt);
        }
        throw new RuntimeException("Receipt not found with id: " + id);
    }

    @Override
    public Receipt updateReceiptNumber(Integer id, String receiptNumber) {
        Optional<Receipt> existingReceipt = receiptRepository.findById(id);
        if (existingReceipt.isPresent()) {
            Receipt receipt = existingReceipt.get();
            receipt.setReceiptNumber(receiptNumber);
            receipt.setUpdatedDate(java.time.LocalDateTime.now());
            return receiptRepository.save(receipt);
        }
        throw new RuntimeException("Receipt not found with id: " + id);
    }

    // Delete operations
    @Override
    public void deleteReceipt(Integer id) {
        if (!receiptRepository.existsById(id)) {
            throw new RuntimeException("Receipt not found with id: " + id);
        }
        receiptRepository.deleteById(id);
    }

    @Override
    public void deleteReceiptsByPaymentId(Integer paymentId) {
        List<Receipt> receiptsToDelete = receiptRepository.findByPaymentId(paymentId);
        receiptRepository.deleteAll(receiptsToDelete);
    }

    // Business logic operations
    @Override
    public Double getTotalAmountByPaymentId(Integer paymentId) {
        return receiptRepository.sumAmountByPaymentId(paymentId);
    }

    @Override
    public Long countReceiptsByPaymentId(Integer paymentId) {
        return receiptRepository.countByPaymentId(paymentId);
    }

    @Override
    public Double getTotalAmountByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return receiptRepository.sumAmountByDateRange(start, end);
    }

    @Override
    public Long countReceiptsByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return receiptRepository.countByDateRange(start, end);
    }
}
