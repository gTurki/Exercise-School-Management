package com.example.exerciseschoolmanagement.Repository;

import com.example.exerciseschoolmanagement.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findStudentById(Integer id);
}