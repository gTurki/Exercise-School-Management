package com.example.exerciseschoolmanagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Course name must not be empty")
    @Size(min = 2, max = 30, message = "Course name must be between 2 and 30 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher teacher;

    @ManyToMany
    @JsonIgnore
    private Set<Student> students;
}