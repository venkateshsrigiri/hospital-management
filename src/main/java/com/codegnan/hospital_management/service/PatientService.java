package com.codegnan.hospital_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codegnan.hospital_management.dto.PatientRequestDTO;
import com.codegnan.hospital_management.dto.PatientResponseDTO;
import com.codegnan.hospital_management.model.Doctor;
import com.codegnan.hospital_management.model.Patient;
import com.codegnan.hospital_management.repo.DoctorRepository;
import com.codegnan.hospital_management.repo.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public PatientService(PatientRepository patientRepo, DoctorRepository doctorRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    public PatientResponseDTO addPatient(PatientRequestDTO dto) {
        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient p = new Patient();
        p.setName(dto.getName());
        p.setAge(dto.getAge());
        p.setGender(dto.getGender());
        p.setPhone(dto.getPhone());
        p.setDoctor(doctor);

        return map(patientRepo.save(p));
    }

    public List<PatientResponseDTO> getAllPatients() {
        return patientRepo.findAll().stream().map(this::map).toList();
    }

    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO dto) {
        Patient p = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        p.setName(dto.getName());
        p.setAge(dto.getAge());
        p.setGender(dto.getGender());
        p.setPhone(dto.getPhone());
        p.setDoctor(doctor);

        return map(patientRepo.save(p));
    }

    public void deletePatient(Long id) {
        patientRepo.deleteById(id);
    }

    private PatientResponseDTO map(Patient p) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setAge(p.getAge());
        dto.setGender(p.getGender());
        dto.setPhone(p.getPhone());
        dto.setDoctorId(p.getDoctor().getId());
        dto.setDoctorName(p.getDoctor().getName());
        dto.setDepartment(p.getDoctor().getDepartment());
        return dto;
    }
}
