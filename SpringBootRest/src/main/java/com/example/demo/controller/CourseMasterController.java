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

import com.example.demo.model.CourseMaster;
import com.example.demo.service.CourseMasterService;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseMasterController {

    @Autowired
    private CourseMasterService courseMasterService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<CourseMaster> createCourse(@RequestBody CourseMaster course) {
        try {
            CourseMaster savedCourse = courseMasterService.saveCourse(course);
            return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<CourseMaster>> createMultipleCourses(@RequestBody List<CourseMaster> courses) {
        try {
            List<CourseMaster> savedCourses = courseMasterService.saveAllCourses(courses);
            return new ResponseEntity<>(savedCourses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<CourseMaster>> getAllCourses() {
        try {
            List<CourseMaster> courses = courseMasterService.getAllCourses();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseMaster> getCourseById(@PathVariable Integer courseId) {
        try {
            Optional<CourseMaster> course = courseMasterService.getCourseById(courseId);
            return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name/{courseName}")
    public ResponseEntity<List<CourseMaster>> getCoursesByName(@PathVariable String courseName) {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesByName(courseName);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/description/{description}")
    public ResponseEntity<List<CourseMaster>> getCoursesByDescription(@PathVariable String description) {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesByDescription(description);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/age-group/{ageGroup}")
    public ResponseEntity<List<CourseMaster>> getCoursesByAgeGroup(@PathVariable String ageGroup) {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesByAgeGroup(ageGroup);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<CourseMaster>> getActiveCourses() {
        try {
            List<CourseMaster> courses = courseMasterService.getActiveCourses();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name-contains/{courseName}")
    public ResponseEntity<List<CourseMaster>> getCoursesByNameContaining(@PathVariable String courseName) {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesByNameContaining(courseName);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/age-group-contains/{ageGroup}")
    public ResponseEntity<List<CourseMaster>> getCoursesByAgeGroupContaining(@PathVariable String ageGroup) {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesByAgeGroupContaining(ageGroup);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/duration-range")
    public ResponseEntity<List<CourseMaster>> getCoursesByDurationRange(
            @RequestParam Integer minDuration,
            @RequestParam Integer maxDuration) {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesByDurationRange(minDuration, maxDuration);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/active")
    public ResponseEntity<Long> countActiveCourses() {
        try {
            Long count = courseMasterService.countActiveCourses();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/age-group/{ageGroup}")
    public ResponseEntity<Long> countCoursesByAgeGroup(@PathVariable String ageGroup) {
        try {
            Long count = courseMasterService.countCoursesByAgeGroup(ageGroup);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseMaster> updateCourse(@PathVariable Integer courseId, @RequestBody CourseMaster courseDetails) {
        try {
            CourseMaster updatedCourse = courseMasterService.updateCourse(courseId, courseDetails);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{courseId}/status")
    public ResponseEntity<CourseMaster> updateCourseStatus(@PathVariable Integer courseId, @RequestParam Boolean isActive) {
        try {
            CourseMaster updatedCourse = courseMasterService.updateCourseStatus(courseId, isActive);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{courseId}/name")
    public ResponseEntity<CourseMaster> updateCourseName(@PathVariable Integer courseId, @RequestParam String courseName) {
        try {
            CourseMaster updatedCourse = courseMasterService.updateCourseName(courseId, courseName);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{courseId}/description")
    public ResponseEntity<CourseMaster> updateCourseDescription(@PathVariable Integer courseId, @RequestParam String description) {
        try {
            CourseMaster updatedCourse = courseMasterService.updateCourseDescription(courseId, description);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{courseId}/duration")
    public ResponseEntity<CourseMaster> updateCourseDuration(@PathVariable Integer courseId, @RequestParam Integer duration) {
        try {
            CourseMaster updatedCourse = courseMasterService.updateCourseDuration(courseId, duration);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{courseId}/syllabus")
    public ResponseEntity<CourseMaster> updateCourseSyllabus(@PathVariable Integer courseId, @RequestParam String syllabus) {
        try {
            CourseMaster updatedCourse = courseMasterService.updateCourseSyllabus(courseId, syllabus);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer courseId) {
        try {
            courseMasterService.deleteCourse(courseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/age-group/{ageGroup}")
    public ResponseEntity<Void> deleteCoursesByAgeGroup(@PathVariable String ageGroup) {
        try {
            courseMasterService.deleteCoursesByAgeGroup(ageGroup);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/inactive")
    public ResponseEntity<Void> deleteInactiveCourses() {
        try {
            courseMasterService.deleteInactiveCourses();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllCourses() {
        try {
            courseMasterService.deleteAllCourses();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<CourseMaster>> searchCourses(@PathVariable String searchTerm) {
        try {
            List<CourseMaster> courses = courseMasterService.searchCourses(searchTerm);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/duration-age-group")
    public ResponseEntity<List<CourseMaster>> getCoursesByDurationAndAgeGroup(
            @RequestParam Integer duration,
            @RequestParam String ageGroup) {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesByDurationAndAgeGroup(duration, ageGroup);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<CourseMaster>> getCoursesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesWithPagination(page, size);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/name")
    public ResponseEntity<List<CourseMaster>> getCoursesSortedByName() {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesSortedByName();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/duration")
    public ResponseEntity<List<CourseMaster>> getCoursesSortedByDuration() {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesSortedByDuration();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/age-group")
    public ResponseEntity<List<CourseMaster>> getCoursesSortedByAgeGroup() {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesSortedByAgeGroup();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/with-video")
    public ResponseEntity<List<CourseMaster>> getCoursesWithVideoContent() {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesWithVideoContent();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/syllabus/{keyword}")
    public ResponseEntity<List<CourseMaster>> getCoursesBySyllabusKeyword(@PathVariable String keyword) {
        try {
            List<CourseMaster> courses = courseMasterService.getCoursesBySyllabusKeyword(keyword);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // ===========================================
    // DUPLICATE DETECTION AND REMOVAL OPERATIONS
    // ===========================================
    
    @GetMapping("/duplicates/name")
    public ResponseEntity<List<CourseMaster>> findDuplicateCoursesByName() {
        try {
            List<CourseMaster> duplicates = courseMasterService.findDuplicateCoursesByName();
            return new ResponseEntity<>(duplicates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/duplicates/name-description")
    public ResponseEntity<List<CourseMaster>> findDuplicateCoursesByNameAndDescription() {
        try {
            List<CourseMaster> duplicates = courseMasterService.findDuplicateCoursesByNameAndDescription();
            return new ResponseEntity<>(duplicates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/duplicates/name-description-duration")
    public ResponseEntity<List<CourseMaster>> findDuplicateCoursesByNameDescriptionAndDuration() {
        try {
            List<CourseMaster> duplicates = courseMasterService.findDuplicateCoursesByNameDescriptionAndDuration();
            return new ResponseEntity<>(duplicates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/duplicates/exact")
    public ResponseEntity<List<CourseMaster>> findExactDuplicateCourses() {
        try {
            List<CourseMaster> duplicates = courseMasterService.findExactDuplicateCourses();
            return new ResponseEntity<>(duplicates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/duplicates/count/name")
    public ResponseEntity<Long> countDuplicateCoursesByName() {
        try {
            Long count = courseMasterService.countDuplicateCoursesByName();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/duplicates/count/name-description")
    public ResponseEntity<Long> countDuplicateCoursesByNameAndDescription() {
        try {
            Long count = courseMasterService.countDuplicateCoursesByNameAndDescription();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/duplicates/name")
    public ResponseEntity<Void> removeDuplicateCoursesByName() {
        try {
            courseMasterService.removeDuplicateCoursesByName();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/duplicates/name-description")
    public ResponseEntity<Void> removeDuplicateCoursesByNameAndDescription() {
        try {
            courseMasterService.removeDuplicateCoursesByNameAndDescription();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/duplicates/name-description-duration")
    public ResponseEntity<Void> removeDuplicateCoursesByNameDescriptionAndDuration() {
        try {
            courseMasterService.removeDuplicateCoursesByNameDescriptionAndDuration();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/duplicates/all")
    public ResponseEntity<Void> removeAllDuplicates() {
        try {
            courseMasterService.removeAllDuplicates();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/duplicates/find-and-remove/name")
    public ResponseEntity<List<CourseMaster>> findAndRemoveDuplicatesByName() {
        try {
            List<CourseMaster> removedDuplicates = courseMasterService.findAndRemoveDuplicatesByName();
            return new ResponseEntity<>(removedDuplicates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/duplicates/find-and-remove/name-description")
    public ResponseEntity<List<CourseMaster>> findAndRemoveDuplicatesByNameAndDescription() {
        try {
            List<CourseMaster> removedDuplicates = courseMasterService.findAndRemoveDuplicatesByNameAndDescription();
            return new ResponseEntity<>(removedDuplicates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/duplicates/find-and-remove/name-description-duration")
    public ResponseEntity<List<CourseMaster>> findAndRemoveDuplicatesByNameDescriptionAndDuration() {
        try {
            List<CourseMaster> removedDuplicates = courseMasterService.findAndRemoveDuplicatesByNameDescriptionAndDuration();
            return new ResponseEntity<>(removedDuplicates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/duplicates/find-and-remove/all")
    public ResponseEntity<List<CourseMaster>> findAndRemoveAllDuplicates() {
        try {
            List<CourseMaster> removedDuplicates = courseMasterService.findAndRemoveAllDuplicates();
            return new ResponseEntity<>(removedDuplicates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 