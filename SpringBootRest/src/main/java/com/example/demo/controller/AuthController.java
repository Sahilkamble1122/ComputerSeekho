package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.StaffMaster;
import com.example.demo.model.StudentMaster;
import com.example.demo.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
        try {
            AuthResponse response = authService.authenticate(authRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        try {
            String token = refreshToken.replace("Bearer ", "");
            AuthResponse response = authService.refreshToken(token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register/student")
    public ResponseEntity<StudentMaster> registerStudent(@Valid @RequestBody StudentMaster student) {
        try {
            StudentMaster registeredStudent = authService.registerStudent(student);
            return ResponseEntity.ok(registeredStudent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register/staff")
    public ResponseEntity<StaffMaster> registerStaff(@Valid @RequestBody StaffMaster staff) {
        try {
            StaffMaster registeredStaff = authService.registerStaff(staff);
            return ResponseEntity.ok(registeredStaff);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestHeader("Authorization") String token) {
        try {
            String jwtToken = token.replace("Bearer ", "");
            boolean isValid = authService.validateToken(jwtToken);
            String username = authService.getUsernameFromToken(jwtToken);
            
            Map<String, Object> response = new HashMap<>();
            response.put("valid", isValid);
            response.put("username", username);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("valid", false);
            response.put("error", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/public/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Authentication service is running");
        return ResponseEntity.ok(response);
    }
} 