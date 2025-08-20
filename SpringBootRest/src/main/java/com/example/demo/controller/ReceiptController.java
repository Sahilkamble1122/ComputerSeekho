package com.example.demo.controller;

import com.example.demo.model.Receipt;
import com.example.demo.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/receipts")
@CrossOrigin(origins = "*")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<Receipt> createReceipt(@RequestBody Receipt receipt) {
        try {
            Receipt savedReceipt = receiptService.saveReceipt(receipt);
            return new ResponseEntity<>(savedReceipt, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Receipt> addReceipt(@RequestBody Receipt receipt) {
        try {
            Receipt savedReceipt = receiptService.saveReceipt(receipt);
            return new ResponseEntity<>(savedReceipt, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Receipt>> createMultipleReceipts(@RequestBody List<Receipt> receipts) {
        try {
            List<Receipt> savedReceipts = receiptService.saveAllReceipts(receipts);
            return new ResponseEntity<>(savedReceipts, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        try {
            List<Receipt> receipts = receiptService.getAllReceipts();
            return new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Receipt>> getAllReceiptsAlternative() {
        try {
            List<Receipt> receipts = receiptService.getAllReceipts();
            return new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable Integer id) {
        try {
            Optional<Receipt> receipt = receiptService.getReceiptById(id);
            return receipt.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{id}")
    public ResponseEntity<Receipt> updateReceipt(@PathVariable Integer id, @RequestBody Receipt receipt) {
        try {
            Receipt updatedReceipt = receiptService.updateReceipt(id, receipt);
            return new ResponseEntity<>(updatedReceipt, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Receipt> updateReceiptAlternative(@PathVariable Integer id, @RequestBody Receipt receipt) {
        try {
            Receipt updatedReceipt = receiptService.updateReceipt(id, receipt);
            return new ResponseEntity<>(updatedReceipt, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable Integer id) {
        try {
            receiptService.deleteReceipt(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReceiptAlternative(@PathVariable Integer id) {
        try {
            receiptService.deleteReceipt(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<List<Receipt>> getReceiptsByPaymentId(@PathVariable Integer paymentId) {
        try {
            List<Receipt> receipts = receiptService.getReceiptsByPaymentId(paymentId);
            return new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/amount-range")
    public ResponseEntity<List<Receipt>> getReceiptsByAmountRange(
            @RequestParam Double minAmount,
            @RequestParam Double maxAmount) {
        try {
            List<Receipt> receipts = receiptService.getReceiptsByAmountRange(minAmount, maxAmount);
            return new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Receipt>> getReceiptsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            List<Receipt> receipts = receiptService.getReceiptsByDateRange(startDate, endDate);
            return new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/total-amount/payment/{paymentId}")
    public ResponseEntity<Double> getTotalAmountByPaymentId(@PathVariable Integer paymentId) {
        try {
            Double totalAmount = receiptService.getTotalAmountByPaymentId(paymentId);
            return new ResponseEntity<>(totalAmount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/payment/{paymentId}")
    public ResponseEntity<Long> countReceiptsByPaymentId(@PathVariable Integer paymentId) {
        try {
            Long count = receiptService.countReceiptsByPaymentId(paymentId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/total-amount/date-range")
    public ResponseEntity<Double> getTotalAmountByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            Double totalAmount = receiptService.getTotalAmountByDateRange(startDate, endDate);
            return new ResponseEntity<>(totalAmount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/date-range")
    public ResponseEntity<Long> countReceiptsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            Long count = receiptService.countReceiptsByDateRange(startDate, endDate);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // PATCH OPERATIONS
    // ===========================================

    @PatchMapping("/{id}/amount")
    public ResponseEntity<Receipt> updateReceiptAmount(@PathVariable Integer id, 
                                                      @RequestParam Double amount) {
        try {
            Receipt updatedReceipt = receiptService.updateReceiptAmount(id, amount);
            return new ResponseEntity<>(updatedReceipt, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/number")
    public ResponseEntity<Receipt> updateReceiptNumber(@PathVariable Integer id, 
                                                      @RequestParam String receiptNumber) {
        try {
            Receipt updatedReceipt = receiptService.updateReceiptNumber(id, receiptNumber);
            return new ResponseEntity<>(updatedReceipt, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
