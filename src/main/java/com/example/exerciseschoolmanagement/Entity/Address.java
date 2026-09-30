package com.example.exerciseschoolmanagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(30) not null")
    private String area;

    @Column(columnDefinition = "varchar(30) not null")
    private String street;

    @Column(columnDefinition = "int not null")
    private Integer buildingNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Teacher teacher;
}