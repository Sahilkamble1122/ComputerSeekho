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

import com.example.demo.model.StudentMaster;
import com.example.demo.service.StudentMasterService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentMasterController {

    @Autowired
    private StudentMasterService studentMasterService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<StudentMaster> createStudent(@RequestBody StudentMaster student) {
        try {
            StudentMaster savedStudent = studentMasterService.saveStudent(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/form")
    public ResponseEntity<StudentMaster> createStudentFromForm(
            @RequestParam("studentName") String studentName,
            @RequestParam("studentAddress") String studentAddress,
            @RequestParam("studentGender") String studentGender,
            @RequestParam("studentDob") String studentDob,
            @RequestParam("studentQualification") String studentQualification,
            @RequestParam("studentMobile") String studentMobile,
            @RequestParam("studentEmail") String studentEmail,
            @RequestParam("courseId") Integer courseId,
            @RequestParam("studentPassword") String studentPassword,
            @RequestParam("studentUsername") String studentUsername,
            @RequestParam("batchId") Integer batchId,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {
        try {
            System.out.println("🎯 Received student registration request:");
            System.out.println("Name: " + studentName);
            System.out.println("Email: " + studentEmail);
            System.out.println("Course ID: " + courseId);
            System.out.println("Batch ID: " + batchId);
            System.out.println("Photo: " + (photo != null ? photo.getOriginalFilename() : "None"));
            StudentMaster student = new StudentMaster();
            student.setStudentName(studentName);
            student.setStudentAddress(studentAddress);
            student.setStudentGender(studentGender);
            student.setStudentDob(java.time.LocalDate.parse(studentDob.split("T")[0]));
            student.setStudentQualification(studentQualification);
            student.setStudentMobile(Long.parseLong(studentMobile));
            student.setStudentEmail(studentEmail);
            student.setCourseId(courseId);
            student.setStudentPassword(studentPassword);
            student.setStudentUsername(studentUsername);
            student.setBatchId(batchId);
            
            // Handle photo upload if provided
            if (photo != null && !photo.isEmpty()) {
                try {
                    // Get the project root directory (where the application is running from)
                    String projectRoot = System.getProperty("user.dir");
                    String uploadsDir = projectRoot + "/uploads";
                    
                    // Create uploads directory if it doesn't exist
                    java.nio.file.Files.createDirectories(java.nio.file.Paths.get(uploadsDir));
                    
                    // Generate a unique filename to avoid conflicts
                    String originalFilename = photo.getOriginalFilename();
                    String fileExtension = "";
                    if (originalFilename != null && originalFilename.contains(".")) {
                        fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                    }
                    String uniqueFilename = System.currentTimeMillis() + "_" + 
                        (originalFilename != null ? originalFilename.replaceAll("[^a-zA-Z0-9.\\-_]", "_") : "photo") + 
                        fileExtension;
                    
                    // Save the file to uploads folder
                    String filePath = uploadsDir + "/" + uniqueFilename;
                    java.io.File file = new java.io.File(filePath);
                    photo.transferTo(file);
                    
                    // Store the relative file path in the database (for consistency)
                    String relativePath = "uploads/" + uniqueFilename;
                    student.setPhotoUrl(relativePath);
                    
                    System.out.println("📸 Photo saved successfully: " + filePath);
                    System.out.println("📁 Project root: " + projectRoot);
                    System.out.println("📁 Uploads directory: " + uploadsDir);
                } catch (Exception e) {
                    System.err.println("❌ Error saving photo: " + e.getMessage());
                    e.printStackTrace();
                    // Continue without photo if there's an error
                    student.setPhotoUrl(null);
                }
            }
            
            StudentMaster savedStudent = studentMasterService.saveStudent(student);
            System.out.println("✅ Student registered successfully with ID: " + savedStudent.getStudentId());
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error creating student: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<StudentMaster>> createMultipleStudents(@RequestBody List<StudentMaster> students) {
        try {
            List<StudentMaster> savedStudents = studentMasterService.saveAllStudents(students);
            return new ResponseEntity<>(savedStudents, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<StudentMaster>> getAllStudents() {
        try {
            List<StudentMaster> students = studentMasterService.getAllStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentMaster> getStudentById(@PathVariable Integer studentId) {
        try {
            Optional<StudentMaster> student = studentMasterService.getStudentById(studentId);
            return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name/{studentName}")
    public ResponseEntity<List<StudentMaster>> getStudentsByName(@PathVariable String studentName) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByName(studentName);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<List<StudentMaster>> getStudentsByEmail(@PathVariable String email) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByEmail(email);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/mobile/{mobile}")
    public ResponseEntity<List<StudentMaster>> getStudentsByMobile(@PathVariable Long mobile) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByMobile(mobile);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/gender/{gender}")
    public ResponseEntity<List<StudentMaster>> getStudentsByGender(@PathVariable String gender) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByGender(gender);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/qualification/{qualification}")
    public ResponseEntity<List<StudentMaster>> getStudentsByQualification(@PathVariable String qualification) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByQualification(qualification);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<StudentMaster>> getStudentsByBatchId(@PathVariable Integer batchId) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByBatchId(batchId);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentMaster>> getStudentsByCourseId(@PathVariable Integer courseId) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByCourseId(courseId);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name-contains/{studentName}")
    public ResponseEntity<List<StudentMaster>> getStudentsByNameContaining(@PathVariable String studentName) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByNameContaining(studentName);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/email-contains/{email}")
    public ResponseEntity<List<StudentMaster>> getStudentsByEmailContaining(@PathVariable String email) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByEmailContaining(email);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/email-domain/{domain}")
    public ResponseEntity<List<StudentMaster>> getStudentsByEmailDomain(@PathVariable String domain) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByEmailDomain(domain);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/course/{courseId}")
    public ResponseEntity<Long> countStudentsByCourseId(@PathVariable Integer courseId) {
        try {
            Long count = studentMasterService.countStudentsByCourseId(courseId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/batch/{batchId}")
    public ResponseEntity<Long> countStudentsByBatchId(@PathVariable Integer batchId) {
        try {
            Long count = studentMasterService.countStudentsByBatchId(batchId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentMaster> updateStudent(@PathVariable Integer studentId, @RequestBody StudentMaster studentDetails) {
        try {
            StudentMaster updatedStudent = studentMasterService.updateStudent(studentId, studentDetails);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{studentId}/name")
    public ResponseEntity<StudentMaster> updateStudentName(@PathVariable Integer studentId, @RequestParam String studentName) {
        try {
            StudentMaster updatedStudent = studentMasterService.updateStudentName(studentId, studentName);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{studentId}/email")
    public ResponseEntity<StudentMaster> updateStudentEmail(@PathVariable Integer studentId, @RequestParam String email) {
        try {
            StudentMaster updatedStudent = studentMasterService.updateStudentEmail(studentId, email);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{studentId}/mobile")
    public ResponseEntity<StudentMaster> updateStudentMobile(@PathVariable Integer studentId, @RequestParam Long mobile) {
        try {
            StudentMaster updatedStudent = studentMasterService.updateStudentMobile(studentId, mobile);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{studentId}/address")
    public ResponseEntity<StudentMaster> updateStudentAddress(@PathVariable Integer studentId, @RequestParam String address) {
        try {
            StudentMaster updatedStudent = studentMasterService.updateStudentAddress(studentId, address);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{studentId}/qualification")
    public ResponseEntity<StudentMaster> updateStudentQualification(@PathVariable Integer studentId, @RequestParam String qualification) {
        try {
            StudentMaster updatedStudent = studentMasterService.updateStudentQualification(studentId, qualification);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{studentId}/pending-fees")
    public ResponseEntity<StudentMaster> updateStudentPendingFees(@PathVariable Integer studentId, @RequestParam Double pendingFees) {
        try {
            StudentMaster updatedStudent = studentMasterService.updateStudentPendingFees(studentId, pendingFees);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer studentId) {
        try {
            studentMasterService.deleteStudent(studentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/batch/{batchId}")
    public ResponseEntity<Void> deleteStudentsByBatchId(@PathVariable Integer batchId) {
        try {
            studentMasterService.deleteStudentsByBatchId(batchId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Void> deleteStudentsByCourseId(@PathVariable Integer courseId) {
        try {
            studentMasterService.deleteStudentsByCourseId(courseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllStudents() {
        try {
            studentMasterService.deleteAllStudents();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<StudentMaster>> searchStudents(@PathVariable String searchTerm) {
        try {
            List<StudentMaster> students = studentMasterService.searchStudents(searchTerm);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/date-range")
    public ResponseEntity<List<StudentMaster>> getStudentsByDateRangeAndCourse(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Integer courseId) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByDateRangeAndCourse(startDate, endDate, courseId);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<StudentMaster>> getStudentsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsWithPagination(page, size);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/name")
    public ResponseEntity<List<StudentMaster>> getStudentsSortedByName() {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsSortedByName();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/email")
    public ResponseEntity<List<StudentMaster>> getStudentsSortedByEmail() {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsSortedByEmail();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/batch")
    public ResponseEntity<List<StudentMaster>> getStudentsSortedByBatchId() {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsSortedByBatchId();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<StudentMaster> authenticateStudent(@RequestParam String username, @RequestParam String password) {
        try {
            Optional<StudentMaster> student = studentMasterService.authenticateStudent(username, password);
            return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/email-pattern/{pattern}")
    public ResponseEntity<List<StudentMaster>> getStudentsByEmailPattern(@PathVariable String pattern) {
        try {
            List<StudentMaster> students = studentMasterService.getStudentsByEmailPattern(pattern);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Student API is working!");
    }
} 