package com.codegnan.hospital_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codegnan.hospital_management.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
