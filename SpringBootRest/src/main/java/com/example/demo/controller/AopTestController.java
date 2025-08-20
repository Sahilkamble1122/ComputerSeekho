package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.annotation.LogExecutionTime;
import com.example.demo.annotation.AuditLog;
import com.example.demo.annotation.ValidateInput;
import com.example.demo.annotation.Cacheable;
import com.example.demo.aspect.CachingAspect;

import java.util.HashMap;
import java.util.Map;

/**
 * Test Controller to demonstrate AOP features
 */
@RestController
@RequestMapping("/api/aop-test")
@CrossOrigin(origins = "*")
public class AopTestController {

    @Autowired
    private CachingAspect cachingAspect;

    /**
     * Test method with all AOP annotations
     */
    @GetMapping("/demo")
    @LogExecutionTime("AOP Demo Method")
    @AuditLog(action = "TEST_AOP", module = "AOP_DEMO", logRequest = true, logResponse = true)
    @ValidateInput
    @Cacheable(key = "demo", ttl = 60)
    public ResponseEntity<Map<String, Object>> demoMethod(@RequestParam String name) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello " + name + "! This is AOP in action!");
        response.put("timestamp", System.currentTimeMillis());
        response.put("aopFeatures", "Logging, Auditing, Validation, Caching");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Test method with execution time logging
     */
    @GetMapping("/performance")
    @LogExecutionTime("Performance Test")
    public ResponseEntity<Map<String, Object>> performanceTest() throws InterruptedException {
        // Simulate some processing time
        Thread.sleep(1000);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Performance test completed");
        response.put("processingTime", "1000ms (simulated)");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Test method with caching
     */
    @GetMapping("/cache-test")
    @Cacheable(key = "cache-test:{#id}", ttl = 300)
    public ResponseEntity<Map<String, Object>> cacheTest(@RequestParam Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("message", "This result is cached for 5 minutes");
        response.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(response);
    }

    /**
     * Test method with validation
     */
    @PostMapping("/validation-test")
    @ValidateInput
    public ResponseEntity<Map<String, Object>> validationTest(@RequestBody Map<String, Object> data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Validation passed!");
        response.put("receivedData", data);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Test method with audit logging
     */
    @PostMapping("/audit-test")
    @AuditLog(action = "CREATE_TEST_DATA", module = "AOP_TESTING")
    public ResponseEntity<Map<String, Object>> auditTest(@RequestBody Map<String, Object> data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Audit log created for this operation");
        response.put("data", data);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get cache statistics
     */
    @GetMapping("/cache-stats")
    public ResponseEntity<String> getCacheStats() {
        return ResponseEntity.ok(cachingAspect.getCacheStats());
    }

    /**
     * Clear cache
     */
    @DeleteMapping("/cache")
    public ResponseEntity<String> clearCache() {
        cachingAspect.clearCache();
        return ResponseEntity.ok("Cache cleared successfully");
    }

    /**
     * Test method that throws exception (for testing exception handling)
     */
    @GetMapping("/exception-test")
    @LogExecutionTime("Exception Test")
    @AuditLog(action = "TEST_EXCEPTION", module = "AOP_TESTING")
    public ResponseEntity<String> exceptionTest(@RequestParam boolean shouldThrow) {
        if (shouldThrow) {
            throw new RuntimeException("This is a test exception for AOP exception handling");
        }
        return ResponseEntity.ok("No exception thrown");
    }
}
