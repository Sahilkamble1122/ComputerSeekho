package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.StudentMaster;

public interface StudentMasterService {
    
    // Create operations
    StudentMaster saveStudent(StudentMaster student);
    List<StudentMaster> saveAllStudents(List<StudentMaster> students);
    
    // Read operations
    List<StudentMaster> getAllStudents();
    Optional<StudentMaster> getStudentById(Integer studentId);
    List<StudentMaster> getStudentsByName(String studentName);
    List<StudentMaster> getStudentsByEmail(String email);
    List<StudentMaster> getStudentsByMobile(Long mobile);
    List<StudentMaster> getStudentsByGender(String gender);
    List<StudentMaster> getStudentsByQualification(String qualification);
    List<StudentMaster> getStudentsByBatchId(Integer batchId);
    List<StudentMaster> getStudentsByCourseId(Integer courseId);
    List<StudentMaster> getStudentsByNameContaining(String studentName);
    List<StudentMaster> getStudentsByEmailContaining(String email);
    List<StudentMaster> getStudentsByEmailDomain(String domain);
    Long countStudentsByCourseId(Integer courseId);
    Long countStudentsByBatchId(Integer batchId);
    
    // Update operations
    StudentMaster updateStudent(Integer studentId, StudentMaster studentDetails);
    StudentMaster updateStudentName(Integer studentId, String studentName);
    StudentMaster updateStudentEmail(Integer studentId, String email);
    StudentMaster updateStudentMobile(Integer studentId, Long mobile);
    StudentMaster updateStudentAddress(Integer studentId, String address);
    StudentMaster updateStudentQualification(Integer studentId, String qualification);
    StudentMaster updateStudentPlacementStatus(Integer studentId, Boolean isPlaced);
    StudentMaster updateStudentPendingFees(Integer studentId, Double pendingFees);
    
    // Delete operations
    void deleteStudent(Integer studentId);
    void deleteStudentsByBatchId(Integer batchId);
    void deleteStudentsByCourseId(Integer courseId);
    void deleteAllStudents();
    
    // Business logic operations
    List<StudentMaster> searchStudents(String searchTerm);
    List<StudentMaster> getStudentsByDateRangeAndCourse(String startDate, String endDate, Integer courseId);
    List<StudentMaster> getStudentsWithPagination(int page, int size);
    List<StudentMaster> getStudentsSortedByName();
    List<StudentMaster> getStudentsSortedByEmail();
    List<StudentMaster> getStudentsSortedByBatchId();
    Optional<StudentMaster> authenticateStudent(String username, String password);
    List<StudentMaster> getStudentsByEmailPattern(String pattern);
    
    // Placement status operations
    List<StudentMaster> getStudentsByPlacementStatus(Boolean isPlaced);
    List<StudentMaster> getPlacedStudents();
    List<StudentMaster> getUnplacedStudents();
    Long countPlacedStudents();
    Long countUnplacedStudents();
    List<StudentMaster> getStudentsByPlacementStatusAndCourse(Boolean isPlaced, Integer courseId);
    List<StudentMaster> getStudentsByPlacementStatusAndBatch(Boolean isPlaced, Integer batchId);
} 