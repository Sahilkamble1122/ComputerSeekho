package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.CourseMaster;

public interface CourseMasterService {
    
    // Create operations
    CourseMaster saveCourse(CourseMaster course);
    List<CourseMaster> saveAllCourses(List<CourseMaster> courses);
    
    // Read operations
    List<CourseMaster> getAllCourses();
    Optional<CourseMaster> getCourseById(Integer courseId);
    List<CourseMaster> getCoursesByName(String courseName);
    List<CourseMaster> getCoursesByDescription(String description);
    List<CourseMaster> getCoursesByAgeGroup(String ageGroup);
    List<CourseMaster> getActiveCourses();
    List<CourseMaster> getCoursesByNameContaining(String courseName);
    List<CourseMaster> getCoursesByAgeGroupContaining(String ageGroup);
    List<CourseMaster> getCoursesByDurationRange(Integer minDuration, Integer maxDuration);
    Long countActiveCourses();
    Long countCoursesByAgeGroup(String ageGroup);
    
    // Update operations
    CourseMaster updateCourse(Integer courseId, CourseMaster courseDetails);
    CourseMaster updateCourseStatus(Integer courseId, Boolean isActive);
    CourseMaster updateCourseName(Integer courseId, String courseName);
    CourseMaster updateCourseDescription(Integer courseId, String description);
    CourseMaster updateCourseDuration(Integer courseId, Integer duration);
    CourseMaster updateCourseSyllabus(Integer courseId, String syllabus);
    
    // Delete operations
    void deleteCourse(Integer courseId);
    void deleteCoursesByAgeGroup(String ageGroup);
    void deleteInactiveCourses();
    void deleteAllCourses();
    
    // Business logic operations
    List<CourseMaster> searchCourses(String searchTerm);
    List<CourseMaster> getCoursesByDurationAndAgeGroup(Integer duration, String ageGroup);
    List<CourseMaster> getCoursesWithPagination(int page, int size);
    List<CourseMaster> getCoursesSortedByName();
    List<CourseMaster> getCoursesSortedByDuration();
    List<CourseMaster> getCoursesSortedByAgeGroup();
    List<CourseMaster> getCoursesWithVideoContent();
    List<CourseMaster> getCoursesBySyllabusKeyword(String keyword);
} 