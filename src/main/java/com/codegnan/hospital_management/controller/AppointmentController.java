package com.codegnan.hospital_management.controller;

import com.codegnan.hospital_management.dto.*;
import com.codegnan.hospital_management.service.AppointmentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public AppointmentResponseDTO bookAppointment(
            @RequestBody AppointmentRequestDTO dto,
            Authentication authentication) {

        return appointmentService.bookAppointment(dto, authentication);
    }

    @GetMapping("/my")
    public List<AppointmentResponseDTO> myAppointments(Authentication authentication) {
        return appointmentService.getMyAppointments(authentication);
    }
}
