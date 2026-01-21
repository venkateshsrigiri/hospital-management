package com.codegnan.hospital_management.repo;

import com.codegnan.hospital_management.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // 🔥 IMPORTANT FIX
    List<Appointment> findByUser_Id(Long userId);
}
