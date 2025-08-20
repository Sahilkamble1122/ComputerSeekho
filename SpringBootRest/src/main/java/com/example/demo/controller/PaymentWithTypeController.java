package com.example.demo.controller;

import com.example.demo.model.PaymentWithTypeDTO;
import com.example.demo.service.PaymentWithTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment-with-type")
@CrossOrigin(origins = "*")
public class PaymentWithTypeController {

    @Autowired
    private PaymentWithTypeService paymentWithTypeService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<PaymentWithTypeDTO> createPaymentWithType(@RequestBody PaymentWithTypeDTO paymentWithTypeDTO) {
        try {
            PaymentWithTypeDTO savedPayment = paymentWithTypeService.savePaymentWithType(paymentWithTypeDTO);
            return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<PaymentWithTypeDTO>> createMultiplePaymentsWithType(@RequestBody List<PaymentWithTypeDTO> payments) {
        try {
            List<PaymentWithTypeDTO> savedPayments = paymentWithTypeService.saveAllPaymentsWithType(payments);
            return new ResponseEntity<>(savedPayments, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<PaymentWithTypeDTO>> getAllPaymentsWithType() {
        try {
            List<PaymentWithTypeDTO> payments = paymentWithTypeService.getAllPaymentsWithType();
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentWithTypeDTO> getPaymentWithTypeById(@PathVariable Integer paymentId) {
        try {
            Optional<PaymentWithTypeDTO> payment = paymentWithTypeService.getPaymentWithTypeById(paymentId);
            return payment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<PaymentWithTypeDTO>> getPaymentsByStudentId(@PathVariable Integer studentId) {
        try {
            List<PaymentWithTypeDTO> payments = paymentWithTypeService.getPaymentsByStudentId(studentId);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<PaymentWithTypeDTO>> getPaymentsByCourseId(@PathVariable Integer courseId) {
        try {
            List<PaymentWithTypeDTO> payments = paymentWithTypeService.getPaymentsByCourseId(courseId);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<PaymentWithTypeDTO>> getPaymentsByBatchId(@PathVariable Integer batchId) {
        try {
            List<PaymentWithTypeDTO> payments = paymentWithTypeService.getPaymentsByBatchId(batchId);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/type/{paymentTypeId}")
    public ResponseEntity<List<PaymentWithTypeDTO>> getPaymentsByTypeId(@PathVariable Integer paymentTypeId) {
        try {
            List<PaymentWithTypeDTO> payments = paymentWithTypeService.getPaymentsByTypeId(paymentTypeId);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentWithTypeDTO>> getPaymentsByStatus(@PathVariable String status) {
        try {
            List<PaymentWithTypeDTO> payments = paymentWithTypeService.getPaymentsByStatus(status);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PaymentWithTypeDTO>> getPaymentsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            List<PaymentWithTypeDTO> payments = paymentWithTypeService.getPaymentsByDateRange(startDate, endDate);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/amount-range")
    public ResponseEntity<List<PaymentWithTypeDTO>> getPaymentsByAmountRange(
            @RequestParam Double minAmount,
            @RequestParam Double maxAmount) {
        try {
            List<PaymentWithTypeDTO> payments = paymentWithTypeService.getPaymentsByAmountRange(minAmount, maxAmount);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentWithTypeDTO> updatePaymentWithType(@PathVariable Integer paymentId, 
                                                                   @RequestBody PaymentWithTypeDTO paymentWithTypeDTO) {
        try {
            PaymentWithTypeDTO updatedPayment = paymentWithTypeService.updatePaymentWithType(paymentId, paymentWithTypeDTO);
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @PatchMapping("/{paymentId}/status")
    public ResponseEntity<PaymentWithTypeDTO> updatePaymentStatus(@PathVariable Integer paymentId, 
                                                                 @RequestParam String status) {
        try {
            PaymentWithTypeDTO updatedPayment = paymentWithTypeService.updatePaymentStatus(paymentId, status);
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{paymentId}/amount")
    public ResponseEntity<PaymentWithTypeDTO> updatePaymentAmount(@PathVariable Integer paymentId, 
                                                                 @RequestParam Double amount) {
        try {
            PaymentWithTypeDTO updatedPayment = paymentWithTypeService.updatePaymentAmount(paymentId, amount);
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{paymentId}/type")
    public ResponseEntity<PaymentWithTypeDTO> updatePaymentType(@PathVariable Integer paymentId, 
                                                                @RequestParam Integer paymentTypeId) {
        try {
            PaymentWithTypeDTO updatedPayment = paymentWithTypeService.updatePaymentType(paymentId, paymentTypeId);
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePaymentWithType(@PathVariable Integer paymentId) {
        try {
            paymentWithTypeService.deletePaymentWithType(paymentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deletePaymentsByStudentId(@PathVariable Integer studentId) {
        try {
            paymentWithTypeService.deletePaymentsByStudentId(studentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deletePaymentsByStatus(@PathVariable String status) {
        try {
            paymentWithTypeService.deletePaymentsByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/total-amount/student/{studentId}")
    public ResponseEntity<Double> getTotalAmountByStudentId(@PathVariable Integer studentId) {
        try {
            Double totalAmount = paymentWithTypeService.getTotalAmountByStudentId(studentId);
            return new ResponseEntity<>(totalAmount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/total-amount/course/{courseId}")
    public ResponseEntity<Double> getTotalAmountByCourseId(@PathVariable Integer courseId) {
        try {
            Double totalAmount = paymentWithTypeService.getTotalAmountByCourseId(courseId);
            return new ResponseEntity<>(totalAmount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/total-amount/batch/{batchId}")
    public ResponseEntity<Double> getTotalAmountByBatchId(@PathVariable Integer batchId) {
        try {
            Double totalAmount = paymentWithTypeService.getTotalAmountByBatchId(batchId);
            return new ResponseEntity<>(totalAmount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/student/{studentId}")
    public ResponseEntity<Long> countPaymentsByStudentId(@PathVariable Integer studentId) {
        try {
            Long count = paymentWithTypeService.countPaymentsByStudentId(studentId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countPaymentsByStatus(@PathVariable String status) {
        try {
            Long count = paymentWithTypeService.countPaymentsByStatus(status);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pending-payments")
    public ResponseEntity<List<PaymentWithTypeDTO>> getPendingPayments() {
        try {
            List<PaymentWithTypeDTO> pendingPayments = paymentWithTypeService.getPendingPayments();
            return new ResponseEntity<>(pendingPayments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/completed-payments")
    public ResponseEntity<List<PaymentWithTypeDTO>> getCompletedPayments() {
        try {
            List<PaymentWithTypeDTO> completedPayments = paymentWithTypeService.getCompletedPayments();
            return new ResponseEntity<>(completedPayments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/overdue-payments")
    public ResponseEntity<List<PaymentWithTypeDTO>> getOverduePayments() {
        try {
            List<PaymentWithTypeDTO> overduePayments = paymentWithTypeService.getOverduePayments();
            return new ResponseEntity<>(overduePayments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/summary/student/{studentId}")
    public ResponseEntity<Object> getPaymentSummaryByStudentId(@PathVariable Integer studentId) {
        try {
            Object summary = paymentWithTypeService.getPaymentSummaryByStudentId(studentId);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/summary/course/{courseId}")
    public ResponseEntity<Object> getPaymentSummaryByCourseId(@PathVariable Integer courseId) {
        try {
            Object summary = paymentWithTypeService.getPaymentSummaryByCourseId(courseId);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
