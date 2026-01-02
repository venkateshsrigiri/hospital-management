package com.codegnan.hospital_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.hospital_management.dto.AppointmentRequestDTO;
import com.codegnan.hospital_management.dto.AppointmentResponseDTO;
import com.codegnan.hospital_management.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin("*")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public AppointmentResponseDTO book(@RequestBody AppointmentRequestDTO dto) {
        return service.bookAppointment(dto);
    }

    @GetMapping
    public List<AppointmentResponseDTO> getAll() {
        return service.getAllAppointments();
    }
}
