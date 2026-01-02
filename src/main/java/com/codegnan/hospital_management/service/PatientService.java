package com.codegnan.hospital_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codegnan.hospital_management.dto.PatientRequestDTO;
import com.codegnan.hospital_management.dto.PatientResponseDTO;
import com.codegnan.hospital_management.model.Patient;
import com.codegnan.hospital_management.repo.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public PatientResponseDTO addPatient(PatientRequestDTO dto) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setPhone(dto.getPhone());

        Patient saved = repo.save(patient);

        PatientResponseDTO response = new PatientResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setAge(saved.getAge());
        response.setGender(saved.getGender());
        response.setPhone(saved.getPhone());

        return response;
    }

    public List<PatientResponseDTO> getAllPatients() {
        return repo.findAll().stream().map(p -> {
            PatientResponseDTO dto = new PatientResponseDTO();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setAge(p.getAge());
            dto.setGender(p.getGender());
            dto.setPhone(p.getPhone());
            return dto;
        }).toList();
    }

    public void deletePatient(Long id) {
        repo.deleteById(id);
    }
}

