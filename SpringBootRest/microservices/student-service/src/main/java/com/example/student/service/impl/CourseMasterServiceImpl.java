package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.CourseMaster;
import com.example.demo.repository.CourseMasterRepository;
import com.example.demo.service.CourseMasterService;

@Service
public class CourseMasterServiceImpl implements CourseMasterService {

    @Autowired
    private CourseMasterRepository courseMasterRepository;

    // Create operations
    @Override
    public CourseMaster saveCourse(CourseMaster course) {
        return courseMasterRepository.save(course);
    }

    @Override
    public List<CourseMaster> saveAllCourses(List<CourseMaster> courses) {
        return courseMasterRepository.saveAll(courses);
    }

    // Read operations
    @Override
    public List<CourseMaster> getAllCourses() {
        return courseMasterRepository.findAll();
    }

    @Override
    public Optional<CourseMaster> getCourseById(Integer courseId) {
        return courseMasterRepository.findById(courseId);
    }

    @Override
    public List<CourseMaster> getCoursesByName(String courseName) {
        return courseMasterRepository.findByCourseNameContainingIgnoreCase(courseName);
    }

    @Override
    public List<CourseMaster> getCoursesByDescription(String description) {
        // Use a custom query or filter in service since method doesn't exist
        List<CourseMaster> allCourses = courseMasterRepository.findAll();
        return allCourses.stream()
                .filter(course -> course.getCourseDescription() != null && 
                                course.getCourseDescription().toLowerCase().contains(description.toLowerCase()))
                .toList();
    }

    @Override
    public List<CourseMaster> getCoursesByAgeGroup(String ageGroup) {
        return courseMasterRepository.findByAgeGrpType(ageGroup);
    }

    @Override
    public List<CourseMaster> getActiveCourses() {
        return courseMasterRepository.findByCourseIsActiveTrue();
    }

    @Override
    public List<CourseMaster> getCoursesByNameContaining(String courseName) {
        return courseMasterRepository.findByCourseNameContainingIgnoreCase(courseName);
    }

    @Override
    public List<CourseMaster> getCoursesByAgeGroupContaining(String ageGroup) {
        // Use a custom query or filter in service since method doesn't exist
        List<CourseMaster> allCourses = courseMasterRepository.findAll();
        return allCourses.stream()
                .filter(course -> course.getAgeGrpType() != null && 
                                course.getAgeGrpType().toLowerCase().contains(ageGroup.toLowerCase()))
                .toList();
    }

    @Override
    public List<CourseMaster> getCoursesByDurationRange(Integer minDuration, Integer maxDuration) {
        return courseMasterRepository.findByCourseDurationBetween(minDuration, maxDuration);
    }

    @Override
    public Long countActiveCourses() {
        return courseMasterRepository.countActiveCourses();
    }

    @Override
    public Long countCoursesByAgeGroup(String ageGroup) {
        // Use a custom query or filter in service since method doesn't exist
        List<CourseMaster> allCourses = courseMasterRepository.findAll();
        return allCourses.stream()
                .filter(course -> course.getAgeGrpType() != null && 
                                course.getAgeGrpType().equals(ageGroup))
                .count();
    }

    // Update operations
    @Override
    public CourseMaster updateCourse(Integer courseId, CourseMaster courseDetails) {
        Optional<CourseMaster> existingCourse = courseMasterRepository.findById(courseId);
        if (existingCourse.isPresent()) {
            CourseMaster course = existingCourse.get();
            course.setCourseName(courseDetails.getCourseName());
            course.setCourseDescription(courseDetails.getCourseDescription());
            course.setCourseDuration(courseDetails.getCourseDuration());
            course.setCourseSyllabus(courseDetails.getCourseSyllabus());
            course.setAgeGrpType(courseDetails.getAgeGrpType());
            course.setCourseIsActive(courseDetails.getCourseIsActive());
            course.setCoverPhoto(courseDetails.getCoverPhoto());
            course.setVideoId(courseDetails.getVideoId());
            course.setUpdatedDate(java.time.LocalDateTime.now());
            return courseMasterRepository.save(course);
        }
        throw new RuntimeException("Course not found with id: " + courseId);
    }

    @Override
    public CourseMaster updateCourseStatus(Integer courseId, Boolean isActive) {
        Optional<CourseMaster> existingCourse = courseMasterRepository.findById(courseId);
        if (existingCourse.isPresent()) {
            CourseMaster course = existingCourse.get();
            course.setCourseIsActive(isActive);
            course.setUpdatedDate(java.time.LocalDateTime.now());
            return courseMasterRepository.save(course);
        }
        throw new RuntimeException("Course not found with id: " + courseId);
    }

    @Override
    public CourseMaster updateCourseName(Integer courseId, String courseName) {
        Optional<CourseMaster> existingCourse = courseMasterRepository.findById(courseId);
        if (existingCourse.isPresent()) {
            CourseMaster course = existingCourse.get();
            course.setCourseName(courseName);
            course.setUpdatedDate(java.time.LocalDateTime.now());
            return courseMasterRepository.save(course);
        }
        throw new RuntimeException("Course not found with id: " + courseId);
    }

    @Override
    public CourseMaster updateCourseDescription(Integer courseId, String description) {
        Optional<CourseMaster> existingCourse = courseMasterRepository.findById(courseId);
        if (existingCourse.isPresent()) {
            CourseMaster course = existingCourse.get();
            course.setCourseDescription(description);
            course.setUpdatedDate(java.time.LocalDateTime.now());
            return courseMasterRepository.save(course);
        }
        throw new RuntimeException("Course not found with id: " + courseId);
    }

    @Override
    public CourseMaster updateCourseDuration(Integer courseId, Integer duration) {
        Optional<CourseMaster> existingCourse = courseMasterRepository.findById(courseId);
        if (existingCourse.isPresent()) {
            CourseMaster course = existingCourse.get();
            course.setCourseDuration(duration);
            course.setUpdatedDate(java.time.LocalDateTime.now());
            return courseMasterRepository.save(course);
        }
        throw new RuntimeException("Course not found with id: " + courseId);
    }

    @Override
    public CourseMaster updateCourseSyllabus(Integer courseId, String syllabus) {
        Optional<CourseMaster> existingCourse = courseMasterRepository.findById(courseId);
        if (existingCourse.isPresent()) {
            CourseMaster course = existingCourse.get();
            course.setCourseSyllabus(syllabus);
            course.setUpdatedDate(java.time.LocalDateTime.now());
            return courseMasterRepository.save(course);
        }
        throw new RuntimeException("Course not found with id: " + courseId);
    }

    // Delete operations
    @Override
    public void deleteCourse(Integer courseId) {
        if (courseMasterRepository.existsById(courseId)) {
            courseMasterRepository.deleteById(courseId);
        } else {
            throw new RuntimeException("Course not found with id: " + courseId);
        }
    }

    @Override
    public void deleteCoursesByAgeGroup(String ageGroup) {
        List<CourseMaster> courses = courseMasterRepository.findByAgeGrpType(ageGroup);
        courseMasterRepository.deleteAll(courses);
    }

    @Override
    public void deleteInactiveCourses() {
        // Filter inactive courses in service since method doesn't exist
        List<CourseMaster> allCourses = courseMasterRepository.findAll();
        List<CourseMaster> inactiveCourses = allCourses.stream()
                .filter(course -> !course.getCourseIsActive())
                .toList();
        courseMasterRepository.deleteAll(inactiveCourses);
    }

    @Override
    public void deleteAllCourses() {
        courseMasterRepository.deleteAll();
    }

    // Business logic operations
    @Override
    public List<CourseMaster> searchCourses(String searchTerm) {
        return courseMasterRepository.findBySyllabusKeyword(searchTerm);
    }

    @Override
    public List<CourseMaster> getCoursesByDurationAndAgeGroup(Integer duration, String ageGroup) {
        // Filter in service since method doesn't exist
        List<CourseMaster> allCourses = courseMasterRepository.findAll();
        return allCourses.stream()
                .filter(course -> course.getCourseDuration() != null && 
                                course.getCourseDuration().equals(duration) &&
                                course.getAgeGrpType() != null && 
                                course.getAgeGrpType().equals(ageGroup))
                .toList();
    }

    @Override
    public List<CourseMaster> getCoursesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseMaster> coursePage = courseMasterRepository.findAll(pageable);
        return coursePage.getContent();
    }

    @Override
    public List<CourseMaster> getCoursesSortedByName() {
        return courseMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "courseName"));
    }

    @Override
    public List<CourseMaster> getCoursesSortedByDuration() {
        return courseMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "courseDuration"));
    }

    @Override
    public List<CourseMaster> getCoursesSortedByAgeGroup() {
        return courseMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "ageGrpType"));
    }

    @Override
    public List<CourseMaster> getCoursesWithVideoContent() {
        return courseMasterRepository.findByVideoIdIsNotNull();
    }

    @Override
    public List<CourseMaster> getCoursesBySyllabusKeyword(String keyword) {
        return courseMasterRepository.findBySyllabusKeyword(keyword);
    }
} 