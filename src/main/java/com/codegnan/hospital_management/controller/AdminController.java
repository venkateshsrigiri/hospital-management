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

import com.codegnan.hospital_management.model.Appointment;
import com.codegnan.hospital_management.model.Doctor;
import com.codegnan.hospital_management.model.Patient;
import com.codegnan.hospital_management.repo.AppointmentRepository;
import com.codegnan.hospital_management.repo.DoctorRepository;
import com.codegnan.hospital_management.repo.PatientRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;
    private final AppointmentRepository appointmentRepo;

    public AdminController(
            DoctorRepository doctorRepo,
            PatientRepository patientRepo,
            AppointmentRepository appointmentRepo) {
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.appointmentRepo = appointmentRepo;
    }

    // ===== DOCTORS =====
    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorRepo.findAll();
    }

    @PostMapping("/doctors")
    public Doctor addDoctor(@RequestBody Doctor d) {
        return doctorRepo.save(d);
    }

    @PutMapping("/doctors/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor d) {
        Doctor doc = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doc.setName(d.getName());
        doc.setDepartment(d.getDepartment());
        return doctorRepo.save(doc);
    }

    @DeleteMapping("/doctors/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorRepo.deleteById(id);
    }

    // ===== PATIENTS =====
    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientRepo.findAll();
    }

    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient p) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setName(p.getName());
        patient.setAge(p.getAge());
        patient.setGender(p.getGender());
        patient.setPhone(p.getPhone());
        return patientRepo.save(patient);
    }

    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientRepo.deleteById(id);
    }

    // ===== APPOINTMENTS =====
    @GetMapping("/appointments")
    public List<Appointment> getAppointments() {
        return appointmentRepo.findAll();
    }

    @PutMapping("/appointments/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment a) {
        Appointment appt = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appt.setAppointmentDate(a.getAppointmentDate());
        appt.setStatus(a.getStatus());
        return appointmentRepo.save(appt);
    }

    @DeleteMapping("/appointments/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentRepo.deleteById(id);
    }
}
