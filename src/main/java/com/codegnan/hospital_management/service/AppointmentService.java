package com.codegnan.hospital_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codegnan.hospital_management.dto.AppointmentRequestDTO;
import com.codegnan.hospital_management.dto.AppointmentResponseDTO;
import com.codegnan.hospital_management.model.Appointment;
import com.codegnan.hospital_management.model.Doctor;
import com.codegnan.hospital_management.model.Patient;
import com.codegnan.hospital_management.repo.AppointmentRepository;
import com.codegnan.hospital_management.repo.DoctorRepository;
import com.codegnan.hospital_management.repo.PatientRepository;


@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public AppointmentService(
            AppointmentRepository appointmentRepo,
            PatientRepository patientRepo,
            DoctorRepository doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO dto) {

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setStatus("BOOKED");

        Appointment saved = appointmentRepo.save(appointment);

        AppointmentResponseDTO response = new AppointmentResponseDTO();
        response.setAppointmentId(saved.getId());
        response.setPatientName(patient.getName());
        response.setDoctorName(doctor.getName());
        response.setSpecialization(doctor.getSpecialization());
        response.setAppointmentDate(saved.getAppointmentDate());
        response.setStatus(saved.getStatus());

        return response;
    }

    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepo.findAll().stream().map(a -> {
            AppointmentResponseDTO dto = new AppointmentResponseDTO();
            dto.setAppointmentId(a.getId());
            dto.setPatientName(a.getPatient().getName());
            dto.setDoctorName(a.getDoctor().getName());
            dto.setSpecialization(a.getDoctor().getSpecialization());
            dto.setAppointmentDate(a.getAppointmentDate());
            dto.setStatus(a.getStatus());
            return dto;
        }).toList();
    }
}
