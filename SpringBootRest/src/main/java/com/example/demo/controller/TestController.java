package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BatchMaster;
import com.example.demo.model.CourseMaster;
import com.example.demo.service.BatchMasterService;
import com.example.demo.service.CourseMasterService;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {

    @Autowired
    private CourseMasterService courseMasterService;

    @Autowired
    private BatchMasterService batchMasterService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseMaster>> testGetAllCourses() {
        try {
            List<CourseMaster> courses = courseMasterService.getAllCourses();
            System.out.println("Test: Found " + courses.size() + " courses");
            courses.forEach(course -> 
                System.out.println("Course: " + course.getCourseId() + " - " + course.getCourseName())
            );
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            System.err.println("Error fetching courses: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/batches")
    public ResponseEntity<List<BatchMaster>> testGetAllBatches() {
        try {
            List<BatchMaster> batches = batchMasterService.getAllBatches();
            System.out.println("Test: Found " + batches.size() + " batches");
            batches.forEach(batch -> 
                System.out.println("Batch: " + batch.getBatchId() + " - " + batch.getBatchName())
            );
            return ResponseEntity.ok(batches);
        } catch (Exception e) {
            System.err.println("Error fetching batches: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/batches/active")
    public ResponseEntity<List<BatchMaster>> testGetActiveBatches() {
        try {
            List<BatchMaster> batches = batchMasterService.getActiveBatches();
            System.out.println("Test: Found " + batches.size() + " active batches");
            batches.forEach(batch -> 
                System.out.println("Active Batch: " + batch.getBatchId() + " - " + batch.getBatchName())
            );
            return ResponseEntity.ok(batches);
        } catch (Exception e) {
            System.err.println("Error fetching active batches: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
} 