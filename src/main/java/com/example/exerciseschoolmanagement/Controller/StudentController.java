package com.example.exerciseschoolmanagement.Controller;

import com.example.exerciseschoolmanagement.Api.ApiResponse;
import com.example.exerciseschoolmanagement.Entity.Student;
import com.example.exerciseschoolmanagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody @Valid Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
    }

    @PutMapping("/change-major/{studentId}/{major}")
    public ResponseEntity<?> changeMajor(@PathVariable Integer studentId, @PathVariable String major) {
        studentService.changeMajor(studentId, major);
        return ResponseEntity.status(200).body(new ApiResponse("Major changed and all courses dropped"));
    }
}