package com.codegnan.hospital_management.service;

import com.codegnan.hospital_management.dto.*;
import com.codegnan.hospital_management.model.*;
import com.codegnan.hospital_management.repo.*;

import com.codegnan.hospital_management.security.AppUser;
import com.codegnan.hospital_management.security.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            UserRepository userRepository) {

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    // ================= BOOK APPOINTMENT =================
    public AppointmentResponseDTO bookAppointment(
            AppointmentRequestDTO dto,
            Authentication authentication) {

        // 🔐 Logged-in user
        Long userId = Long.parseLong(authentication.getName());

        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 👨‍⚕️ Doctor
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // 🧍 CREATE PATIENT (FIX)
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setPhone(dto.getPhone());
        patientRepository.save(patient);

        // 📅 Appointment
        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setStatus("BOOKED");

        appointmentRepository.save(appointment);

        return mapToResponse(appointment);
    }

    // ================= MY APPOINTMENTS =================
    public List<AppointmentResponseDTO> getMyAppointments(Authentication authentication) {

        Long userId = Long.parseLong(authentication.getName());

        return appointmentRepository.findByUser_Id(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList()); // ✅ Java 8/11 safe
    }

    // ================= MAPPER =================
    private AppointmentResponseDTO mapToResponse(Appointment a) {

        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setDoctorName(a.getDoctor().getName());
        dto.setDepartment(a.getDoctor().getDepartment());
        dto.setAppointmentDate(a.getAppointmentDate());
        dto.setStatus(a.getStatus());

        return dto;
    }
}
