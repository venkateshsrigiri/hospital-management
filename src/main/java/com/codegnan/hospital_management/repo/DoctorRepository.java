package com.codegnan.hospital_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codegnan.hospital_management.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    
}
