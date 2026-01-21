package com.codegnan.hospital_management.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codegnan.hospital_management.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByPhone(String phone);
}
