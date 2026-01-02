package com.codegnan.hospital_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codegnan.hospital_management.dto.DoctorRequestDTO;
import com.codegnan.hospital_management.dto.DoctorResponseDTO;
import com.codegnan.hospital_management.model.Doctor;
import com.codegnan.hospital_management.repo.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepo;

    public DoctorService(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public DoctorResponseDTO addDoctor(DoctorRequestDTO dto) {

        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());

        Doctor saved = doctorRepo.save(doctor);

        DoctorResponseDTO response = new DoctorResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setSpecialization(saved.getSpecialization());

        return response;
    }

    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorRepo.findAll().stream().map(d -> {
            DoctorResponseDTO dto = new DoctorResponseDTO();
            dto.setId(d.getId());
            dto.setName(d.getName());
            dto.setSpecialization(d.getSpecialization());
            return dto;
        }).toList();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
}

