package com.example.exerciseschoolmanagement.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotNull(message = "Age must not be null")
    @Positive(message = "Age must be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Major must not be empty")
    @Size(min = 2, max = 30, message = "Major must be between 2 and 30 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String major;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}