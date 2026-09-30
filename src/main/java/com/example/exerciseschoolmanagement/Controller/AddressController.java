package com.example.exerciseschoolmanagement.Controller;

import com.example.exerciseschoolmanagement.Api.ApiResponse;
import com.example.exerciseschoolmanagement.DTO.AddressDTO;
import com.example.exerciseschoolmanagement.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody @Valid AddressDTO addressDTO) {
        addressService.addAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Address added successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAddress(@RequestBody @Valid AddressDTO addressDTO) {
        addressService.updateAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Address updated successfully"));
    }

    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer teacherId) {
        addressService.deleteAddress(teacherId);
        return ResponseEntity.status(200).body(new ApiResponse("Address deleted successfully"));
    }
}