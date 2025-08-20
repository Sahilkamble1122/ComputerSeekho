package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.StudentMaster;
import com.example.demo.repository.StudentMasterRepository;
import com.example.demo.service.StudentMasterService;

@Service
public class StudentMasterServiceImpl implements StudentMasterService {

    @Autowired
    private StudentMasterRepository studentMasterRepository;

    // CREATE OPERATIONS
    @Override
    @CacheEvict(value = "students", allEntries = true)
    public StudentMaster saveStudent(StudentMaster student) {
        return studentMasterRepository.save(student);
    }

    @Override
    @CacheEvict(value = "students", allEntries = true)
    public List<StudentMaster> saveAllStudents(List<StudentMaster> students) {
        return studentMasterRepository.saveAll(students);
    }

    // READ OPERATIONS
    @Override
    @Cacheable(value = "students", key = "'all'")
    public List<StudentMaster> getAllStudents() {
        return studentMasterRepository.findAll();
    }

    @Override
    @Cacheable(value = "students", key = "#studentId")
    public Optional<StudentMaster> getStudentById(Integer studentId) {
        return studentMasterRepository.findById(studentId);
    }

    @Override
    @Cacheable(value = "students", key = "'name_' + #studentName")
    public List<StudentMaster> getStudentsByName(String studentName) {
        return studentMasterRepository.findByStudentNameContainingIgnoreCase(studentName);
    }

    @Override
    @Cacheable(value = "students", key = "'email_' + #email")
    public List<StudentMaster> getStudentsByEmail(String email) {
        return studentMasterRepository.findByStudentEmail(email);
    }

    @Override
    @Cacheable(value = "students", key = "'mobile_' + #mobile")
    public List<StudentMaster> getStudentsByMobile(Long mobile) {
        return studentMasterRepository.findByStudentMobile(mobile);
    }

    @Override
    @Cacheable(value = "students", key = "'gender_' + #gender")
    public List<StudentMaster> getStudentsByGender(String gender) {
        return studentMasterRepository.findByStudentGender(gender);
    }

    @Override
    @Cacheable(value = "students", key = "'qualification_' + #qualification")
    public List<StudentMaster> getStudentsByQualification(String qualification) {
        return studentMasterRepository.findByStudentQualification(qualification);
    }

    @Override
    @Cacheable(value = "students", key = "'batch_' + #batchId")
    public List<StudentMaster> getStudentsByBatchId(Integer batchId) {
        return studentMasterRepository.findByBatchId(batchId);
    }

    @Override
    @Cacheable(value = "students", key = "'course_' + #courseId")
    public List<StudentMaster> getStudentsByCourseId(Integer courseId) {
        return studentMasterRepository.findByCourseId(courseId);
    }

    @Override
    @Cacheable(value = "students", key = "'nameContaining_' + #studentName")
    public List<StudentMaster> getStudentsByNameContaining(String studentName) {
        return studentMasterRepository.findByStudentNameContainingIgnoreCase(studentName);
    }

    @Override
    @Cacheable(value = "students", key = "'emailContaining_' + #email")
    public List<StudentMaster> getStudentsByEmailContaining(String email) {
        return studentMasterRepository.findByStudentEmailContainingIgnoreCase(email);
    }

    @Override
    @Cacheable(value = "students", key = "'emailDomain_' + #domain")
    public List<StudentMaster> getStudentsByEmailDomain(String domain) {
        return studentMasterRepository.findByStudentEmailEndingWith(domain);
    }

    @Override
    @Cacheable(value = "students", key = "'count_course_' + #courseId")
    public Long countStudentsByCourseId(Integer courseId) {
        return studentMasterRepository.countStudentsByCourse(courseId);
    }

    @Override
    @Cacheable(value = "students", key = "'count_batch_' + #batchId")
    public Long countStudentsByBatchId(Integer batchId) {
        return studentMasterRepository.countStudentsByBatch(batchId);
    }

    // UPDATE OPERATIONS
    @Override
    @CachePut(value = "students", key = "#studentId")
    @CacheEvict(value = "students", allEntries = true)
    public StudentMaster updateStudent(Integer studentId, StudentMaster studentDetails) {
        StudentMaster existingStudent = studentMasterRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        
        existingStudent.setStudentName(studentDetails.getStudentName());
        existingStudent.setStudentAddress(studentDetails.getStudentAddress());
        existingStudent.setStudentGender(studentDetails.getStudentGender());
        existingStudent.setPhotoUrl(studentDetails.getPhotoUrl());
        existingStudent.setStudentDob(studentDetails.getStudentDob());
        existingStudent.setStudentQualification(studentDetails.getStudentQualification());
        existingStudent.setStudentMobile(studentDetails.getStudentMobile());
        existingStudent.setStudentEmail(studentDetails.getStudentEmail());
        existingStudent.setBatchId(studentDetails.getBatchId());
        existingStudent.setCourseId(studentDetails.getCourseId());
        existingStudent.setStudentPassword(studentDetails.getStudentPassword());
        existingStudent.setStudentUsername(studentDetails.getStudentUsername());
        existingStudent.setIsPlaced(studentDetails.getIsPlaced());
        existingStudent.setUpdatedDate(java.time.LocalDateTime.now());
        
        return studentMasterRepository.save(existingStudent);
    }

    @Override
    @CachePut(value = "students", key = "#studentId")
    @CacheEvict(value = "students", allEntries = true)
    public StudentMaster updateStudentName(Integer studentId, String studentName) {
        StudentMaster student = studentMasterRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        student.setStudentName(studentName);
        student.setUpdatedDate(java.time.LocalDateTime.now());
        return studentMasterRepository.save(student);
    }

    @Override
    @CachePut(value = "students", key = "#studentId")
    @CacheEvict(value = "students", allEntries = true)
    public StudentMaster updateStudentEmail(Integer studentId, String email) {
        StudentMaster student = studentMasterRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        student.setStudentEmail(email);
        student.setUpdatedDate(java.time.LocalDateTime.now());
        return studentMasterRepository.save(student);
    }

    @Override
    @CachePut(value = "students", key = "#studentId")
    @CacheEvict(value = "students", allEntries = true)
    public StudentMaster updateStudentMobile(Integer studentId, Long mobile) {
        StudentMaster student = studentMasterRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        student.setStudentMobile(mobile);
        student.setUpdatedDate(java.time.LocalDateTime.now());
        return studentMasterRepository.save(student);
    }

    @Override
    @CachePut(value = "students", key = "#studentId")
    @CacheEvict(value = "students", allEntries = true)
    public StudentMaster updateStudentAddress(Integer studentId, String address) {
        StudentMaster student = studentMasterRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        student.setStudentAddress(address);
        student.setUpdatedDate(java.time.LocalDateTime.now());
        return studentMasterRepository.save(student);
    }

    @Override
    @CachePut(value = "students", key = "#studentId")
    @CacheEvict(value = "students", allEntries = true)
    public StudentMaster updateStudentQualification(Integer studentId, String qualification) {
        StudentMaster student = studentMasterRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        student.setStudentQualification(qualification);
        student.setUpdatedDate(java.time.LocalDateTime.now());
        return studentMasterRepository.save(student);
    }

    @Override
    @CachePut(value = "students", key = "#studentId")
    @CacheEvict(value = "students", allEntries = true)
    public StudentMaster updateStudentPlacementStatus(Integer studentId, Boolean isPlaced) {
        StudentMaster student = studentMasterRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        student.setIsPlaced(isPlaced);
        student.setUpdatedDate(java.time.LocalDateTime.now());
        return studentMasterRepository.save(student);
    }

    @Override
    @CachePut(value = "students", key = "#studentId")
    @CacheEvict(value = "students", allEntries = true)
    public StudentMaster updateStudentPendingFees(Integer studentId, Double pendingFees) {
        StudentMaster student = studentMasterRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        student.setPendingFees(pendingFees);
        student.setUpdatedDate(java.time.LocalDateTime.now());
        return studentMasterRepository.save(student);
    }

    // DELETE OPERATIONS
    @Override
    @CacheEvict(value = "students", allEntries = true)
    public void deleteStudent(Integer studentId) {
        if (!studentMasterRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found with id: " + studentId);
        }
        studentMasterRepository.deleteById(studentId);
    }

    @Override
    @CacheEvict(value = "students", allEntries = true)
    public void deleteStudentsByBatchId(Integer batchId) {
        List<StudentMaster> students = studentMasterRepository.findByBatchId(batchId);
        studentMasterRepository.deleteAll(students);
    }

    @Override
    @CacheEvict(value = "students", allEntries = true)
    public void deleteStudentsByCourseId(Integer courseId) {
        List<StudentMaster> students = studentMasterRepository.findByCourseId(courseId);
        studentMasterRepository.deleteAll(students);
    }

    @Override
    @CacheEvict(value = "students", allEntries = true)
    public void deleteAllStudents() {
        studentMasterRepository.deleteAll();
    }

    // BUSINESS LOGIC OPERATIONS
    @Override
    @Cacheable(value = "students", key = "'search_' + #searchTerm")
    public List<StudentMaster> searchStudents(String searchTerm) {
        return studentMasterRepository.findByNameOrEmailOrMobile(searchTerm);
    }

    @Override
    @Cacheable(value = "students", key = "'dateRange_' + #startDate + '_' + #endDate + '_' + #courseId")
    public List<StudentMaster> getStudentsByDateRangeAndCourse(String startDate, String endDate, Integer courseId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return studentMasterRepository.findByDobRange(start, end);
    }

    @Override
    public List<StudentMaster> getStudentsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentMaster> studentPage = studentMasterRepository.findAll(pageable);
        return studentPage.getContent();
    }

    @Override
    @Cacheable(value = "students", key = "'sorted_name'")
    public List<StudentMaster> getStudentsSortedByName() {
        return studentMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "studentName"));
    }

    @Override
    @Cacheable(value = "students", key = "'sorted_email'")
    public List<StudentMaster> getStudentsSortedByEmail() {
        return studentMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "studentEmail"));
    }

    @Override
    @Cacheable(value = "students", key = "'sorted_batch'")
    public List<StudentMaster> getStudentsSortedByBatchId() {
        return studentMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "batchId"));
    }

    @Override
    public Optional<StudentMaster> authenticateStudent(String username, String password) {
        return studentMasterRepository.findByStudentUsernameAndStudentPassword(username, password);
    }

    @Override
    @Cacheable(value = "students", key = "'emailPattern_' + #pattern")
    public List<StudentMaster> getStudentsByEmailPattern(String pattern) {
        return studentMasterRepository.findByEmailPattern(pattern);
    }

    // Placement status operations
    @Override
    @Cacheable(value = "students", key = "'placement_' + #isPlaced")
    public List<StudentMaster> getStudentsByPlacementStatus(Boolean isPlaced) {
        return studentMasterRepository.findByIsPlaced(isPlaced);
    }

    @Override
    @Cacheable(value = "students", key = "'placed'")
    public List<StudentMaster> getPlacedStudents() {
        return studentMasterRepository.findByIsPlacedTrue();
    }

    @Override
    @Cacheable(value = "students", key = "'unplaced'")
    public List<StudentMaster> getUnplacedStudents() {
        return studentMasterRepository.findByIsPlacedFalse();
    }

    @Override
    @Cacheable(value = "students", key = "'count_placed'")
    public Long countPlacedStudents() {
        return studentMasterRepository.countByIsPlacedTrue();
    }

    @Override
    @Cacheable(value = "students", key = "'count_unplaced'")
    public Long countUnplacedStudents() {
        return studentMasterRepository.countByIsPlacedFalse();
    }

    @Override
    @Cacheable(value = "students", key = "'placement_course_' + #isPlaced + '_' + #courseId")
    public List<StudentMaster> getStudentsByPlacementStatusAndCourse(Boolean isPlaced, Integer courseId) {
        return studentMasterRepository.findByIsPlacedAndCourseId(isPlaced, courseId);
    }

    @Override
    @Cacheable(value = "students", key = "'placement_batch_' + #isPlaced + '_' + #batchId")
    public List<StudentMaster> getStudentsByPlacementStatusAndBatch(Boolean isPlaced, Integer batchId) {
        return studentMasterRepository.findByIsPlacedAndBatchId(isPlaced, batchId);
    }
} 