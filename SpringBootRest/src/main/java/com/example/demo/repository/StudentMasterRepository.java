package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StudentMaster;

@Repository
public interface StudentMasterRepository extends JpaRepository<StudentMaster, Integer> {
    
    // Find students by name (case-insensitive)
    List<StudentMaster> findByStudentNameContainingIgnoreCase(String studentName);
    
    // Find students by email
    List<StudentMaster> findByStudentEmail(String email);
    
    // Find students by email containing (case-insensitive)
    List<StudentMaster> findByStudentEmailContainingIgnoreCase(String email);
    
    // Find students by mobile number
    List<StudentMaster> findByStudentMobile(Long mobile);
    
    // Find students by gender
    List<StudentMaster> findByStudentGender(String gender);
    
    // Find students by qualification
    List<StudentMaster> findByStudentQualification(String qualification);
    
    // Find students by batch ID
    List<StudentMaster> findByBatchId(Integer batchId);
    
    // Find students by course ID
    List<StudentMaster> findByCourseId(Integer courseId);
    
    // Find students by username
    Optional<StudentMaster> findByStudentUsername(String username);
    
    // Find students by username and password (for login)
    Optional<StudentMaster> findByStudentUsernameAndStudentPassword(String username, String password);
    
    // Find students by name and mobile
    Optional<StudentMaster> findByStudentNameAndStudentMobile(String studentName, Long mobile);
    
    // Find students by email and mobile
    Optional<StudentMaster> findByStudentEmailAndStudentMobile(String email, Long mobile);
    
    // Find students by batch and course
    List<StudentMaster> findByBatchIdAndCourseId(Integer batchId, Integer courseId);
    
    // Find students by qualification containing
    List<StudentMaster> findByStudentQualificationContainingIgnoreCase(String qualification);
    
    // Find students by email domain
    List<StudentMaster> findByStudentEmailEndingWith(String domain);
    
    // Custom query to find students by date of birth range
    @Query("SELECT s FROM StudentMaster s WHERE s.studentDob BETWEEN :startDate AND :endDate")
    List<StudentMaster> findByDobRange(@Param("startDate") LocalDate startDate, 
                                      @Param("endDate") LocalDate endDate);
    
    // Custom query to count students by course
    @Query("SELECT COUNT(s) FROM StudentMaster s WHERE s.courseId = :courseId")
    Long countStudentsByCourse(@Param("courseId") Integer courseId);
    
    // Custom query to count students by batch
    @Query("SELECT COUNT(s) FROM StudentMaster s WHERE s.batchId = :batchId")
    Long countStudentsByBatch(@Param("batchId") Integer batchId);
    
    // Custom query to find students by name or email
    @Query("SELECT s FROM StudentMaster s WHERE s.studentName LIKE %:searchTerm% OR s.studentEmail LIKE %:searchTerm%")
    List<StudentMaster> findByNameOrEmail(@Param("searchTerm") String searchTerm);
    
    // Custom query to find students with specific qualification
    @Query("SELECT s FROM StudentMaster s WHERE s.studentQualification LIKE %:qualification%")
    List<StudentMaster> findByQualificationPattern(@Param("qualification") String qualification);
    
    // Custom query to find students by email pattern
    @Query("SELECT s FROM StudentMaster s WHERE s.studentEmail LIKE %:emailPattern%")
    List<StudentMaster> findByEmailPattern(@Param("emailPattern") String emailPattern);
    
    // Custom query to find students by name, email, or mobile
    @Query("SELECT s FROM StudentMaster s WHERE s.studentName LIKE %:searchTerm% OR s.studentEmail LIKE %:searchTerm% OR CAST(s.studentMobile AS string) LIKE %:searchTerm%")
    List<StudentMaster> findByNameOrEmailOrMobile(@Param("searchTerm") String searchTerm);
    
    // Custom query to count students by email domain
    @Query("SELECT COUNT(s) FROM StudentMaster s WHERE s.studentEmail LIKE %:domain")
    Long countStudentsByEmailDomain(@Param("domain") String domain);
    
    // Find students by placement status
    List<StudentMaster> findByIsPlaced(Boolean isPlaced);
    
    // Find placed students
    List<StudentMaster> findByIsPlacedTrue();
    
    // Find unplaced students
    List<StudentMaster> findByIsPlacedFalse();
    
    // Count placed students
    Long countByIsPlacedTrue();
    
    // Count unplaced students
    Long countByIsPlacedFalse();
    
    // Find students by placement status and course
    List<StudentMaster> findByIsPlacedAndCourseId(Boolean isPlaced, Integer courseId);
    
    // Find students by placement status and batch
    List<StudentMaster> findByIsPlacedAndBatchId(Boolean isPlaced, Integer batchId);
} 