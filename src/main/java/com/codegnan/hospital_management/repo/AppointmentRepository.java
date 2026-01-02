package com.codegnan.hospital_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codegnan.hospital_management.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
}
