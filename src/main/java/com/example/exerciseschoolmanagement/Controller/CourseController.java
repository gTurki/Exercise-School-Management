package com.example.exerciseschoolmanagement.Controller;

import com.example.exerciseschoolmanagement.Api.ApiResponse;
import com.example.exerciseschoolmanagement.Entity.Course;
import com.example.exerciseschoolmanagement.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    @PostMapping("/add/{teacherId}")
    public ResponseEntity<?> addCourse(@PathVariable Integer teacherId, @RequestBody @Valid Course course) {
        courseService.addCourse(teacherId, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer id, @RequestBody @Valid Course course) {
        courseService.updateCourse(id, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
    }

    @GetMapping("/teacher-name/{courseId}")
    public ResponseEntity<?> getTeacherName(@PathVariable Integer courseId) {
        return ResponseEntity.status(200).body(new ApiResponse(courseService.getTeacherNameByCourseId(courseId)));
    }

    @PutMapping("/{courseId}/assign/{studentId}")
    public ResponseEntity<?> assignStudent(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        courseService.assignStudentToCourse(courseId, studentId);
        return ResponseEntity.status(200).body(new ApiResponse("Student assigned to course successfully"));
    }

    @GetMapping("/students/{courseId}")
    public ResponseEntity<?> getStudents(@PathVariable Integer courseId) {
        return ResponseEntity.status(200).body(courseService.getStudentsByCourseId(courseId));
    }
}