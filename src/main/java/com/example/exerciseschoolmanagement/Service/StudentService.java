package com.example.exerciseschoolmanagement.Service;

import com.example.exerciseschoolmanagement.Api.ApiException;
import com.example.exerciseschoolmanagement.Entity.Course;
import com.example.exerciseschoolmanagement.Entity.Student;
import com.example.exerciseschoolmanagement.Repository.CourseRepository;
import com.example.exerciseschoolmanagement.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Integer id, Student student) {
        Student oldStudent = studentRepository.findStudentById(id);
        if (oldStudent == null) {
            throw new ApiException("Student not found");
        }
        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setMajor(student.getMajor());
        studentRepository.save(oldStudent);
    }

    public void deleteStudent(Integer id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("Student not found");
        }
        dropAllCourses(student);
        studentRepository.delete(student);
    }

    public void changeMajor(Integer studentId, String major) {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            throw new ApiException("Student not found");
        }
        if (major == null || major.isBlank()) {
            throw new ApiException("Major must not be empty");
        }

        student.setMajor(major);
        dropAllCourses(student);
        studentRepository.save(student);
    }

    private void dropAllCourses(Student student) {
        for (Course course : student.getCourses()) {
            course.getStudents().remove(student);
            courseRepository.save(course);
        }
        student.getCourses().clear();
    }
}