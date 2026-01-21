package com.codegnan.hospital_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.codegnan.hospital_management.dto.DoctorResponseDTO;
import com.codegnan.hospital_management.dto.PatientResponseDTO;
import com.codegnan.hospital_management.service.DoctorService;
import com.codegnan.hospital_management.service.PatientService;

@RestController
@RequestMapping("/api/lookup")
@CrossOrigin("*")
public class LookupController {

    private final PatientService patientService;
    private final DoctorService doctorService;

    public LookupController(PatientService p, DoctorService d) {
        this.patientService = p;
        this.doctorService = d;
    }

    @GetMapping("/patients")
    public List<PatientResponseDTO> patients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/doctors")
    public List<DoctorResponseDTO> doctors() {
        return doctorService.getAllDoctors();
    }
}
