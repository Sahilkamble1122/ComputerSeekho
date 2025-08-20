package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.StaffMaster;
import com.example.demo.model.StudentMaster;
import com.example.demo.repository.StaffMasterRepository;
import com.example.demo.repository.StudentMasterRepository;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenUtil;

@Service
public class AuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private StudentMasterRepository studentMasterRepository;

    @Autowired
    private StaffMasterRepository staffMasterRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            // First try to find as student
            Optional<StudentMaster> student = studentMasterRepository.findByStudentUsername(authRequest.getUsername());
            if (student.isPresent()) {
                // Check if password matches (for existing users with plain text passwords)
                if (student.get().getStudentPassword().equals(authRequest.getPassword()) || 
                    passwordEncoder.matches(authRequest.getPassword(), student.get().getStudentPassword())) {
                    
                    UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
                    String token = jwtTokenUtil.generateToken(userDetails);
                    String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
                    
                    return new AuthResponse(
                        token,
                        refreshToken,
                        userDetails.getUsername(),
                        "STUDENT",
                        86400000L,
                        student.get().getStudentName(),
                        student.get().getPhotoUrl()
                    );
                }
            }

            // Then try to find as staff
            Optional<StaffMaster> staff = staffMasterRepository.findByStaffUsername(authRequest.getUsername());
            if (staff.isPresent()) {
                // Check if password matches (for existing users with plain text passwords)
                if (staff.get().getStaffPassword().equals(authRequest.getPassword()) || 
                    passwordEncoder.matches(authRequest.getPassword(), staff.get().getStaffPassword())) {
                    
                    UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
                    String token = jwtTokenUtil.generateToken(userDetails);
                    String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
                    return new AuthResponse(
                        token,
                        refreshToken,
                        userDetails.getUsername(),
                        "STAFF",
                        86400000L,
                        staff.get().getStaffName(),
                        staff.get().getPhotoUrl()
                    );

                }
            }

            throw new RuntimeException("Invalid username or password");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }

    public AuthResponse refreshToken(String refreshToken) {
        try {
            if (jwtTokenUtil.validateToken(refreshToken)) {
                String username = jwtTokenUtil.extractUsername(refreshToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                
                String newToken = jwtTokenUtil.generateToken(userDetails);
                String newRefreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

                String role = userDetails.getAuthorities().stream()
                        .findFirst()
                        .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                        .orElse("USER");

                String name = null;
                String imgPath = null;
                if(role.equals("STUDENT")){
                    name = studentMasterRepository.findByStudentUsername(username).get().getStudentName();
                    imgPath = studentMasterRepository.findByStudentUsername(username).get().getPhotoUrl();
                }else if(role.equals("STAFF")){
                    name = staffMasterRepository.findByStaffUsername(username).get().getStaffName();
                    imgPath = staffMasterRepository.findByStaffUsername(username).get().getPhotoUrl();
                }

                return new AuthResponse(newToken, newRefreshToken, username, role, 86400000L, name, imgPath);
            }
            throw new RuntimeException("Invalid refresh token");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Token refresh failed: " + e.getMessage());
        }
    }

    public boolean validateToken(String token) {
        return jwtTokenUtil.validateToken(token);
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenUtil.extractUsername(token);
    }

    public StudentMaster registerStudent(StudentMaster student) {
        // Check if username already exists
        if (studentMasterRepository.findByStudentUsername(student.getStudentUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Encode password
        student.setStudentPassword(passwordEncoder.encode(student.getStudentPassword()));
        
        return studentMasterRepository.save(student);
    }

    public StaffMaster registerStaff(StaffMaster staff) {
        // Check if username already exists
        if (staffMasterRepository.findByStaffUsername(staff.getStaffUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Encode password
        staff.setStaffPassword(passwordEncoder.encode(staff.getStaffPassword()));
        
        return staffMasterRepository.save(staff);
    }
} 