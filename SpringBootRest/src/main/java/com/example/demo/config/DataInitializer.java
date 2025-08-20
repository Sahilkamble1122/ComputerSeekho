package com.example.demo.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.BatchMaster;
import com.example.demo.model.CourseMaster;
import com.example.demo.service.BatchMasterService;
import com.example.demo.service.CourseMasterService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CourseMasterService courseMasterService;

    @Autowired
    private BatchMasterService batchMasterService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Data Initializer Starting ===");
        
        // Check if courses exist
        if (courseMasterService.getAllCourses().isEmpty()) {
            System.out.println("No courses found. Creating sample courses...");
            createSampleCourses();
        } else {
            System.out.println("Courses already exist in database.");
        }

        // Check if batches exist
        if (batchMasterService.getAllBatches().isEmpty()) {
            System.out.println("No batches found. Creating sample batches...");
            createSampleBatches();
        } else {
            System.out.println("Batches already exist in database.");
        }

        System.out.println("=== Data Initializer Completed ===");
    }

    private void createSampleCourses() {
        try {
            // Create sample courses
            CourseMaster course1 = new CourseMaster();
            course1.setCourseName("Java Programming");
            course1.setCourseDescription("Learn Java programming from basics to advanced");
            course1.setCourseDuration(3);
            course1.setCourseSyllabus("Java Basics, OOP, Collections, Spring Boot");
            course1.setAgeGrpType("18-25");
            course1.setCourseIsActive(true);
            courseMasterService.saveCourse(course1);

            CourseMaster course2 = new CourseMaster();
            course2.setCourseName("Python Development");
            course2.setCourseDescription("Master Python programming and web development");
            course2.setCourseDuration(4);
            course2.setCourseSyllabus("Python Basics, Django, Flask, Data Science");
            course2.setAgeGrpType("18-30");
            course2.setCourseIsActive(true);
            courseMasterService.saveCourse(course2);

            CourseMaster course3 = new CourseMaster();
            course3.setCourseName("Web Development");
            course3.setCourseDescription("Full-stack web development with modern technologies");
            course3.setCourseDuration(6);
            course3.setCourseSyllabus("HTML, CSS, JavaScript, React, Node.js");
            course3.setAgeGrpType("18-35");
            course3.setCourseIsActive(true);
            courseMasterService.saveCourse(course3);

            System.out.println("Sample courses created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating sample courses: " + e.getMessage());
        }
    }

    private void createSampleBatches() {
        try {
            // Get the first course for batch creation
            var courses = courseMasterService.getAllCourses();
            if (courses.isEmpty()) {
                System.out.println("No courses available for batch creation.");
                return;
            }

            Integer courseId = courses.get(0).getCourseId();

            // Create sample batches
            BatchMaster batch1 = new BatchMaster();
            batch1.setBatchName("Morning Java Batch");
            batch1.setBatchStartTime(LocalTime.of(9, 0));
            batch1.setBatchEndTime(LocalTime.of(11, 0));
            batch1.setCourseId(courseId);
            batch1.setPresentationDate(LocalDateTime.now().plusDays(30));
            batch1.setCourseFees(15000);
            batch1.setCourseFeesFrom(LocalDate.now());
            batch1.setCourseFeesTo(LocalDate.now().plusMonths(3));
            batch1.setBatchIsActive(true);
            batch1.setStaffId(1);
            batchMasterService.saveBatch(batch1);

            BatchMaster batch2 = new BatchMaster();
            batch2.setBatchName("Evening Java Batch");
            batch2.setBatchStartTime(LocalTime.of(18, 0));
            batch2.setBatchEndTime(LocalTime.of(20, 0));
            batch2.setCourseId(courseId);
            batch2.setPresentationDate(LocalDateTime.now().plusDays(45));
            batch2.setCourseFees(15000);
            batch2.setCourseFeesFrom(LocalDate.now());
            batch2.setCourseFeesTo(LocalDate.now().plusMonths(3));
            batch2.setBatchIsActive(true);
            batch2.setStaffId(1);
            batchMasterService.saveBatch(batch2);

            if (courses.size() > 1) {
                Integer courseId2 = courses.get(1).getCourseId();
                
                BatchMaster batch3 = new BatchMaster();
                batch3.setBatchName("Python Weekend Batch");
                batch3.setBatchStartTime(LocalTime.of(10, 0));
                batch3.setBatchEndTime(LocalTime.of(13, 0));
                batch3.setCourseId(courseId2);
                batch3.setPresentationDate(LocalDateTime.now().plusDays(60));
                batch3.setCourseFees(20000);
                batch3.setCourseFeesFrom(LocalDate.now());
                batch3.setCourseFeesTo(LocalDate.now().plusMonths(4));
                batch3.setBatchIsActive(true);
                batch3.setStaffId(2);
                batchMasterService.saveBatch(batch3);
            }

            System.out.println("Sample batches created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating sample batches: " + e.getMessage());
        }
    }
} 