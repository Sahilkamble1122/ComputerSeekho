package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PaymentTypeMaster;
import com.example.demo.service.PaymentTypeMasterService;

@RestController
@RequestMapping("/api/payment-types")
@CrossOrigin(origins = "*")
public class PaymentTypeMasterController {

    @Autowired
    private PaymentTypeMasterService paymentTypeMasterService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<PaymentTypeMaster> createPaymentType(@RequestBody PaymentTypeMaster paymentType) {
        try {
            PaymentTypeMaster savedPaymentType = paymentTypeMasterService.savePaymentType(paymentType);
            return new ResponseEntity<>(savedPaymentType, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<PaymentTypeMaster>> createMultiplePaymentTypes(@RequestBody List<PaymentTypeMaster> paymentTypes) {
        try {
            List<PaymentTypeMaster> savedPaymentTypes = paymentTypeMasterService.saveAllPaymentTypes(paymentTypes);
            return new ResponseEntity<>(savedPaymentTypes, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<PaymentTypeMaster>> getAllPaymentTypes() {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.getAllPaymentTypes();
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{paymentTypeId}")
    public ResponseEntity<PaymentTypeMaster> getPaymentTypeById(@PathVariable Integer paymentTypeId) {
        try {
            Optional<PaymentTypeMaster> paymentType = paymentTypeMasterService.getPaymentTypeById(paymentTypeId);
            return paymentType.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name/{paymentTypeName}")
    public ResponseEntity<List<PaymentTypeMaster>> getPaymentTypesByName(@PathVariable String paymentTypeName) {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.getPaymentTypesByName(paymentTypeName);
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/description/{description}")
    public ResponseEntity<List<PaymentTypeMaster>> getPaymentTypesByDescription(@PathVariable String description) {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.getPaymentTypesByDescription(description);
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<PaymentTypeMaster>> getPaymentTypesByStatus(@PathVariable String status) {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.getPaymentTypesByStatus(status);
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name-contains/{paymentTypeName}")
    public ResponseEntity<List<PaymentTypeMaster>> getPaymentTypesByNameContaining(@PathVariable String paymentTypeName) {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.getPaymentTypesByNameContaining(paymentTypeName);
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<PaymentTypeMaster>> getActivePaymentTypes() {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.getActivePaymentTypes();
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/active")
    public ResponseEntity<Long> countActivePaymentTypes() {
        try {
            Long count = paymentTypeMasterService.countActivePaymentTypes();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{paymentTypeId}")
    public ResponseEntity<PaymentTypeMaster> updatePaymentType(@PathVariable Integer paymentTypeId, @RequestBody PaymentTypeMaster paymentTypeDetails) {
        try {
            PaymentTypeMaster updatedPaymentType = paymentTypeMasterService.updatePaymentType(paymentTypeId, paymentTypeDetails);
            return new ResponseEntity<>(updatedPaymentType, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{paymentTypeId}/name")
    public ResponseEntity<PaymentTypeMaster> updatePaymentTypeName(@PathVariable Integer paymentTypeId, @RequestParam String paymentTypeName) {
        try {
            PaymentTypeMaster updatedPaymentType = paymentTypeMasterService.updatePaymentTypeName(paymentTypeId, paymentTypeName);
            return new ResponseEntity<>(updatedPaymentType, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{paymentTypeId}/description")
    public ResponseEntity<PaymentTypeMaster> updatePaymentTypeDescription(@PathVariable Integer paymentTypeId, @RequestParam String description) {
        try {
            PaymentTypeMaster updatedPaymentType = paymentTypeMasterService.updatePaymentTypeDescription(paymentTypeId, description);
            return new ResponseEntity<>(updatedPaymentType, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{paymentTypeId}/status")
    public ResponseEntity<PaymentTypeMaster> updatePaymentTypeStatus(@PathVariable Integer paymentTypeId, @RequestParam String status) {
        try {
            PaymentTypeMaster updatedPaymentType = paymentTypeMasterService.updatePaymentTypeStatus(paymentTypeId, status);
            return new ResponseEntity<>(updatedPaymentType, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{paymentTypeId}")
    public ResponseEntity<Void> deletePaymentType(@PathVariable Integer paymentTypeId) {
        try {
            paymentTypeMasterService.deletePaymentType(paymentTypeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deletePaymentTypesByStatus(@PathVariable String status) {
        try {
            paymentTypeMasterService.deletePaymentTypesByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/inactive")
    public ResponseEntity<Void> deleteInactivePaymentTypes() {
        try {
            paymentTypeMasterService.deleteInactivePaymentTypes();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllPaymentTypes() {
        try {
            paymentTypeMasterService.deleteAllPaymentTypes();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<PaymentTypeMaster>> searchPaymentTypes(@PathVariable String searchTerm) {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.searchPaymentTypes(searchTerm);
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<PaymentTypeMaster>> getPaymentTypesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.getPaymentTypesWithPagination(page, size);
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/name")
    public ResponseEntity<List<PaymentTypeMaster>> getPaymentTypesSortedByName() {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.getPaymentTypesSortedByName();
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/status")
    public ResponseEntity<List<PaymentTypeMaster>> getPaymentTypesSortedByStatus() {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.getPaymentTypesSortedByStatus();
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/popular")
    public ResponseEntity<List<PaymentTypeMaster>> getPopularPaymentTypes() {
        try {
            List<PaymentTypeMaster> paymentTypes = paymentTypeMasterService.getPopularPaymentTypes();
            return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 