package com.codegnan.hospital_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.hospital_management.dto.PatientRequestDTO;
import com.codegnan.hospital_management.dto.PatientResponseDTO;
import com.codegnan.hospital_management.service.PatientService;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin("*")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public List<PatientResponseDTO> getAllPatients() {
        return service.getAllPatients();
    }

    @PostMapping
    public PatientResponseDTO addPatient(@RequestBody PatientRequestDTO dto) {
        return service.addPatient(dto);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
    }
    @PutMapping("/{id}")
public PatientResponseDTO updatePatient(
        @PathVariable Long id,
        @RequestBody PatientRequestDTO dto) {
    return service.updatePatient(id, dto);
}

}
