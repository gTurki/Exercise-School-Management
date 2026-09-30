package com.example.exerciseschoolmanagement.Service;

import com.example.exerciseschoolmanagement.Api.ApiException;
import com.example.exerciseschoolmanagement.DTO.AddressDTO;
import com.example.exerciseschoolmanagement.Entity.Address;
import com.example.exerciseschoolmanagement.Entity.Teacher;
import com.example.exerciseschoolmanagement.Repository.AddressRepository;
import com.example.exerciseschoolmanagement.Repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public void addAddress(AddressDTO addressDTO) {
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacher_id());
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        if (teacher.getAddress() != null) {
            throw new ApiException("Teacher already has an address");
        }

        Address address = new Address(null, addressDTO.getArea(), addressDTO.getStreet(),
                addressDTO.getBuildingNumber(), teacher);

        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO) {
        Address address = addressRepository.findAddressById(addressDTO.getTeacher_id());
        if (address == null) {
            throw new ApiException("Address not found");
        }

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());

        addressRepository.save(address);
    }

    public void deleteAddress(Integer teacherId) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        if (teacher.getAddress() == null) {
            throw new ApiException("Teacher has no address");
        }

        teacher.setAddress(null);
        teacherRepository.save(teacher);
    }
}