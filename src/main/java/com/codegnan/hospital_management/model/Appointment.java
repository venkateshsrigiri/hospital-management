package com.codegnan.hospital_management.model;

import jakarta.persistence.*;

import java.time.LocalDate;

import com.codegnan.hospital_management.model.Doctor;
import com.codegnan.hospital_management.model.Patient;
import com.codegnan.hospital_management.security.AppUser;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Patient patient;

    @ManyToOne(optional = false)
    private Doctor doctor;

    // 🔥 IMPORTANT FIX
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private AppUser user;

    private LocalDate appointmentDate;

    private String status;

    // ===== Getters & Setters =====

    public Long getId() { return id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
