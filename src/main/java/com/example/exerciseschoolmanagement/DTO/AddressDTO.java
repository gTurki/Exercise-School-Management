package com.example.exerciseschoolmanagement.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    @NotNull(message = "Teacher id must not be null")
    private Integer teacher_id;

    @NotEmpty(message = "Area must not be empty")
    @Size(min = 3, max = 30, message = "Area must be between 3 and 30 characters")
    private String area;

    @NotEmpty(message = "Street must not be empty")
    @Size(min = 3, max = 30, message = "Street must be between 3 and 30 characters")
    private String street;

    @NotNull(message = "Building number must not be null")
    @Positive(message = "Building number must be a positive number")
    private Integer buildingNumber;
}