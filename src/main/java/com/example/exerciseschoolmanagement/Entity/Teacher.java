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
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotNull(message = "Age must not be null")
    @Min(value = 22, message = "Age must be at least 22")
    @Max(value = 70, message = "Age must be at most 70")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotNull(message = "Salary must not be null")
    @Positive(message = "Salary must be a positive number")
    @Column(columnDefinition = "double not null")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher", orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> courses;
}