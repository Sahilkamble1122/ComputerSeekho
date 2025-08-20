package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
} 