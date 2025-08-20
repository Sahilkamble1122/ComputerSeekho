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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.StaffMaster;
import com.example.demo.service.StaffMasterService;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class StaffMasterController {

    @Autowired
    private StaffMasterService staffMasterService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<StaffMaster> createStaff(@RequestBody StaffMaster staff) {
        try {
            StaffMaster savedStaff = staffMasterService.saveStaff(staff);
            return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<StaffMaster>> createMultipleStaff(@RequestBody List<StaffMaster> staffList) {
        try {
            List<StaffMaster> savedStaff = staffMasterService.saveAllStaff(staffList);
            return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<StaffMaster> registerStaff(
            @RequestParam("staffName") String staffName,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "staffAddress", required = false) String staffAddress,
            @RequestParam(value = "staffGender", required = false) String staffGender,
            @RequestParam(value = "staffMobile", required = false) Long staffMobile,
            @RequestParam(value = "staffEmail", required = false) String staffEmail,
            @RequestParam(value = "staffUsername", required = false) String staffUsername,
            @RequestParam(value = "staffPassword", required = false) String staffPassword,
            @RequestParam(value = "staffRole", required = false) String staffRole
    ) {
        try {
            StaffMaster staff = new StaffMaster();
            staff.setStaffName(staffName);
            staff.setStaffAddress(staffAddress);
            staff.setStaffGender(staffGender);
            staff.setStaffMobile(staffMobile);
            staff.setStaffEmail(staffEmail);
            staff.setStaffUsername(staffUsername);
            staff.setStaffPassword(staffPassword);
            staff.setStaffRole(staffRole);

            // Handle photo upload if provided
            if (photo != null && !photo.isEmpty()) {
                String projectRoot = System.getProperty("user.dir");
                String uploadsDir = projectRoot + "/uploads";
                java.nio.file.Files.createDirectories(java.nio.file.Paths.get(uploadsDir));
                String originalFilename = photo.getOriginalFilename();
                String fileExtension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String uniqueFilename = System.currentTimeMillis() + "_" +
                        (originalFilename != null ? originalFilename.replaceAll("[^a-zA-Z0-9.\\-_]", "_") : "photo") +
                        fileExtension;
                String filePath = uploadsDir + "/" + uniqueFilename;
                java.io.File file = new java.io.File(filePath);
                photo.transferTo(file);
                String relativePath = "uploads/" + uniqueFilename;
                staff.setPhotoUrl(relativePath);
            }

            StaffMaster savedStaff = staffMasterService.saveStaff(staff);
            return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/form")
    public ResponseEntity<StaffMaster> createStaffFromForm(
            @RequestParam("staffName") String staffName,
            @RequestParam("staffAddress") String staffAddress,
            @RequestParam("staffGender") String staffGender,
            @RequestParam("staffMobile") Long staffMobile,
            @RequestParam("staffEmail") String staffEmail,
            @RequestParam("staffPassword") String staffPassword,
            @RequestParam("staffUsername") String staffUsername,
            @RequestParam("staffRole") String staffRole,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {
        try {
            System.out.println("🎯 Received staff registration request:");
            System.out.println("Name: " + staffName);
            System.out.println("Email: " + staffEmail);
            System.out.println("Role: " + staffRole);
            System.out.println("Photo: " + (photo != null ? photo.getOriginalFilename() : "None"));
            StaffMaster staff = new StaffMaster();
            staff.setStaffName(staffName);
            staff.setStaffAddress(staffAddress);
            staff.setStaffGender(staffGender);
            staff.setStaffMobile(staffMobile);
            staff.setStaffEmail(staffEmail);
            staff.setStaffPassword(staffPassword);
            staff.setStaffUsername(staffUsername);
            staff.setStaffRole(staffRole);

            // Handle photo upload if provided
            if (photo != null && !photo.isEmpty()) {
                try {
                    String projectRoot = System.getProperty("user.dir");
                    String uploadsDir = projectRoot + "/uploads";
                    java.nio.file.Files.createDirectories(java.nio.file.Paths.get(uploadsDir));
                    String originalFilename = photo.getOriginalFilename();
                    String fileExtension = "";
                    if (originalFilename != null && originalFilename.contains(".")) {
                        fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                    }
                    String uniqueFilename = System.currentTimeMillis() + "_" +
                            (originalFilename != null ? originalFilename.replaceAll("[^a-zA-Z0-9.\\-_]", "_") : "photo") +
                            fileExtension;
                    String filePath = uploadsDir + "/" + uniqueFilename;
                    java.io.File file = new java.io.File(filePath);
                    photo.transferTo(file);
                    String relativePath = "uploads/" + uniqueFilename;
                    staff.setPhotoUrl(relativePath);
                    System.out.println("📸 Photo saved successfully: " + filePath);
                    System.out.println("📁 Project root: " + projectRoot);
                    System.out.println("📁 Uploads directory: " + uploadsDir);
                } catch (Exception e) {
                    System.err.println("❌ Error saving photo: " + e.getMessage());
                    e.printStackTrace();
                    staff.setPhotoUrl(null);
                }
            }

            StaffMaster savedStaff = staffMasterService.saveStaff(staff);
            System.out.println("✅ Staff registered successfully with ID: " + savedStaff.getStaffId());
            return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error creating staff: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<StaffMaster>> getAllStaff() {
        try {
            List<StaffMaster> staff = staffMasterService.getAllStaff();
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<StaffMaster> getStaffById(@PathVariable Integer staffId) {
        try {
            Optional<StaffMaster> staff = staffMasterService.getStaffById(staffId);
            return staff.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name/{staffName}")
    public ResponseEntity<List<StaffMaster>> getStaffByName(@PathVariable String staffName) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffByName(staffName);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<List<StaffMaster>> getStaffByEmail(@PathVariable String email) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffByEmail(email);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/mobile/{mobile}")
    public ResponseEntity<List<StaffMaster>> getStaffByMobile(@PathVariable Long mobile) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffByMobile(mobile);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/department/{department}")
    public ResponseEntity<List<StaffMaster>> getStaffByDepartment(@PathVariable String department) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffByDepartment(department);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/designation/{designation}")
    public ResponseEntity<List<StaffMaster>> getStaffByDesignation(@PathVariable String designation) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffByDesignation(designation);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<StaffMaster>> getStaffByStatus(@PathVariable String status) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffByStatus(status);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name-contains/{staffName}")
    public ResponseEntity<List<StaffMaster>> getStaffByNameContaining(@PathVariable String staffName) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffByNameContaining(staffName);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/email-contains/{email}")
    public ResponseEntity<List<StaffMaster>> getStaffByEmailContaining(@PathVariable String email) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffByEmailContaining(email);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/department/{department}")
    public ResponseEntity<Long> countStaffByDepartment(@PathVariable String department) {
        try {
            Long count = staffMasterService.countStaffByDepartment(department);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/designation/{designation}")
    public ResponseEntity<Long> countStaffByDesignation(@PathVariable String designation) {
        try {
            Long count = staffMasterService.countStaffByDesignation(designation);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{staffId}")
    public ResponseEntity<StaffMaster> updateStaff(@PathVariable Integer staffId, @RequestBody StaffMaster staffDetails) {
        try {
            StaffMaster updatedStaff = staffMasterService.updateStaff(staffId, staffDetails);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{staffId}/name")
    public ResponseEntity<StaffMaster> updateStaffName(@PathVariable Integer staffId, @RequestParam String staffName) {
        try {
            StaffMaster updatedStaff = staffMasterService.updateStaffName(staffId, staffName);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{staffId}/email")
    public ResponseEntity<StaffMaster> updateStaffEmail(@PathVariable Integer staffId, @RequestParam String email) {
        try {
            StaffMaster updatedStaff = staffMasterService.updateStaffEmail(staffId, email);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{staffId}/mobile")
    public ResponseEntity<StaffMaster> updateStaffMobile(@PathVariable Integer staffId, @RequestParam Long mobile) {
        try {
            StaffMaster updatedStaff = staffMasterService.updateStaffMobile(staffId, mobile);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{staffId}/department")
    public ResponseEntity<StaffMaster> updateStaffDepartment(@PathVariable Integer staffId, @RequestParam String department) {
        try {
            StaffMaster updatedStaff = staffMasterService.updateStaffDepartment(staffId, department);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{staffId}/designation")
    public ResponseEntity<StaffMaster> updateStaffDesignation(@PathVariable Integer staffId, @RequestParam String designation) {
        try {
            StaffMaster updatedStaff = staffMasterService.updateStaffDesignation(staffId, designation);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{staffId}/status")
    public ResponseEntity<StaffMaster> updateStaffStatus(@PathVariable Integer staffId, @RequestParam String status) {
        try {
            StaffMaster updatedStaff = staffMasterService.updateStaffStatus(staffId, status);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Integer staffId) {
        try {
            staffMasterService.deleteStaff(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/department/{department}")
    public ResponseEntity<Void> deleteStaffByDepartment(@PathVariable String department) {
        try {
            staffMasterService.deleteStaffByDepartment(department);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/designation/{designation}")
    public ResponseEntity<Void> deleteStaffByDesignation(@PathVariable String designation) {
        try {
            staffMasterService.deleteStaffByDesignation(designation);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deleteStaffByStatus(@PathVariable String status) {
        try {
            staffMasterService.deleteStaffByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllStaff() {
        try {
            staffMasterService.deleteAllStaff();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<StaffMaster>> searchStaff(@PathVariable String searchTerm) {
        try {
            List<StaffMaster> staff = staffMasterService.searchStaff(searchTerm);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/department-designation")
    public ResponseEntity<List<StaffMaster>> getStaffByDepartmentAndDesignation(
            @RequestParam String department,
            @RequestParam String designation) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffByDepartmentAndDesignation(department, designation);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<StaffMaster>> getStaffWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffWithPagination(page, size);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/name")
    public ResponseEntity<List<StaffMaster>> getStaffSortedByName() {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffSortedByName();
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/department")
    public ResponseEntity<List<StaffMaster>> getStaffSortedByDepartment() {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffSortedByDepartment();
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/designation")
    public ResponseEntity<List<StaffMaster>> getStaffSortedByDesignation() {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffSortedByDesignation();
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<StaffMaster> authenticateStaff(@RequestParam String username, @RequestParam String password) {
        try {
            Optional<StaffMaster> staff = staffMasterService.authenticateStaff(username, password);
            return staff.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<StaffMaster>> getActiveStaff() {
        try {
            List<StaffMaster> staff = staffMasterService.getActiveStaff();
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<StaffMaster>> getInactiveStaff() {
        try {
            List<StaffMaster> staff = staffMasterService.getInactiveStaff();
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/email-domain/{domain}")
    public ResponseEntity<List<StaffMaster>> getStaffByEmailDomain(@PathVariable String domain) {
        try {
            List<StaffMaster> staff = staffMasterService.getStaffByEmailDomain(domain);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 