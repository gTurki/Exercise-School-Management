package com.example.exerciseschoolmanagement.Service;

import com.example.exerciseschoolmanagement.Api.ApiException;
import com.example.exerciseschoolmanagement.Entity.Course;
import com.example.exerciseschoolmanagement.Entity.Student;
import com.example.exerciseschoolmanagement.Entity.Teacher;
import com.example.exerciseschoolmanagement.Repository.CourseRepository;
import com.example.exerciseschoolmanagement.Repository.StudentRepository;
import com.example.exerciseschoolmanagement.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Integer teacherId, Course course) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public void updateCourse(Integer id, Course course) {
        Course oldCourse = courseRepository.findCourseById(id);
        if (oldCourse == null) {
            throw new ApiException("Course not found");
        }
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }

    public void deleteCourse(Integer id) {
        Course course = courseRepository.findCourseById(id);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        courseRepository.delete(course);
    }

    public String getTeacherNameByCourseId(Integer courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        if (course.getTeacher() == null) {
            throw new ApiException("This course has no teacher");
        }
        return course.getTeacher().getName();
    }

    public void assignStudentToCourse(Integer courseId, Integer studentId) {
        Course course = courseRepository.findCourseById(courseId);
        Student student = studentRepository.findStudentById(studentId);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        if (student == null) {
            throw new ApiException("Student not found");
        }
        if (course.getStudents().contains(student)) {
            throw new ApiException("Student is already in this course");
        }

        course.getStudents().add(student);
        student.getCourses().add(course);
        courseRepository.save(course);
    }

    public Set<Student> getStudentsByCourseId(Integer courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        return course.getStudents();
    }
}