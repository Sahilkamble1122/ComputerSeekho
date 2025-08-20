package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.CourseMaster;

@Repository
public interface CourseMasterRepository extends JpaRepository<CourseMaster, Integer> {
    
    // Find courses by name (case-insensitive)
    List<CourseMaster> findByCourseNameContainingIgnoreCase(String courseName);
    
    // Find active courses
    List<CourseMaster> findByCourseIsActiveTrue();
    
    // Find courses by age group type
    List<CourseMaster> findByAgeGrpType(String ageGrpType);
    
    // Find courses by duration range
    List<CourseMaster> findByCourseDurationBetween(Integer minDuration, Integer maxDuration);
    
    // Find courses by name and active status
    List<CourseMaster> findByCourseNameContainingIgnoreCaseAndCourseIsActiveTrue(String courseName);
    
    // Find course by name (exact match)
    Optional<CourseMaster> findByCourseName(String courseName);
    
    // Find courses with video content
    List<CourseMaster> findByVideoIdIsNotNull();
    
    // Custom query to find courses with specific syllabus keywords
    @Query("SELECT c FROM CourseMaster c WHERE c.courseSyllabus LIKE %:keyword%")
    List<CourseMaster> findBySyllabusKeyword(@Param("keyword") String keyword);
    
    // Custom query to count active courses
    @Query("SELECT COUNT(c) FROM CourseMaster c WHERE c.courseIsActive = true")
    Long countActiveCourses();
    
    // Custom query to find courses by duration greater than specified
    @Query("SELECT c FROM CourseMaster c WHERE c.courseDuration > :duration")
    List<CourseMaster> findByDurationGreaterThan(@Param("duration") Integer duration);
    
    // Custom query to find duplicate courses by name
    @Query("SELECT c FROM CourseMaster c WHERE c.courseName IN " +
           "(SELECT c2.courseName FROM CourseMaster c2 GROUP BY c2.courseName HAVING COUNT(c2.courseName) > 1)")
    List<CourseMaster> findDuplicateCoursesByName();
    
    // Custom query to find duplicate courses by name and description
    @Query("SELECT c FROM CourseMaster c WHERE (c.courseName, c.courseDescription) IN " +
           "(SELECT c2.courseName, c2.courseDescription FROM CourseMaster c2 " +
           "GROUP BY c2.courseName, c2.courseDescription HAVING COUNT(*) > 1)")
    List<CourseMaster> findDuplicateCoursesByNameAndDescription();
    
    // Custom query to find duplicate courses by name, description, and duration
    @Query("SELECT c FROM CourseMaster c WHERE (c.courseName, c.courseDescription, c.courseDuration) IN " +
           "(SELECT c2.courseName, c2.courseDescription, c2.courseDuration FROM CourseMaster c2 " +
           "GROUP BY c2.courseName, c2.courseDescription, c2.courseDuration HAVING COUNT(*) > 1)")
    List<CourseMaster> findDuplicateCoursesByNameDescriptionAndDuration();
    
    // Custom query to delete duplicate courses keeping the one with lowest ID
    @Modifying
    @Transactional
    @Query("DELETE FROM CourseMaster c WHERE c.courseId NOT IN " +
           "(SELECT MIN(c2.courseId) FROM CourseMaster c2 GROUP BY c2.courseName)")
    void deleteDuplicateCoursesByNameKeepLowestId();
    
    // Custom query to delete duplicate courses by name and description keeping the one with lowest ID
    @Modifying
    @Transactional
    @Query("DELETE FROM CourseMaster c WHERE c.courseId NOT IN " +
           "(SELECT MIN(c2.courseId) FROM CourseMaster c2 GROUP BY c2.courseName, c2.courseDescription)")
    void deleteDuplicateCoursesByNameAndDescriptionKeepLowestId();
    
    // Custom query to delete duplicate courses by name, description, and duration keeping the one with lowest ID
    @Modifying
    @Transactional
    @Query("DELETE FROM CourseMaster c WHERE c.courseId NOT IN " +
           "(SELECT MIN(c2.courseId) FROM CourseMaster c2 GROUP BY c2.courseName, c2.courseDescription, c2.courseDuration)")
    void deleteDuplicateCoursesByNameDescriptionAndDurationKeepLowestId();
    
    // Custom query to find courses with exact same content (all fields except ID and timestamps)
    @Query("SELECT c FROM CourseMaster c WHERE (c.courseName, c.courseDescription, c.courseDuration, " +
           "c.courseSyllabus, c.ageGrpType, c.courseIsActive, c.coverPhoto, c.videoId) IN " +
           "(SELECT c2.courseName, c2.courseDescription, c2.courseDuration, c2.courseSyllabus, " +
           "c2.ageGrpType, c2.courseIsActive, c2.coverPhoto, c2.videoId FROM CourseMaster c2 " +
           "GROUP BY c2.courseName, c2.courseDescription, c2.courseDuration, c2.courseSyllabus, " +
           "c2.ageGrpType, c2.courseIsActive, c2.coverPhoto, c2.videoId HAVING COUNT(*) > 1)")
    List<CourseMaster> findExactDuplicateCourses();
    
    // Custom query to count duplicate courses by name
    @Query("SELECT COUNT(c) FROM CourseMaster c WHERE c.courseName IN " +
           "(SELECT c2.courseName FROM CourseMaster c2 GROUP BY c2.courseName HAVING COUNT(c2.courseName) > 1)")
    Long countDuplicateCoursesByName();
    
    // Custom query to count duplicate courses by name and description
    @Query("SELECT COUNT(c) FROM CourseMaster c WHERE (c.courseName, c.courseDescription) IN " +
           "(SELECT c2.courseName, c2.courseDescription FROM CourseMaster c2 " +
           "GROUP BY c2.courseName, c2.courseDescription HAVING COUNT(*) > 1)")
    Long countDuplicateCoursesByNameAndDescription();
} 