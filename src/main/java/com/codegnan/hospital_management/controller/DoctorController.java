    package com.codegnan.hospital_management.controller;

    import java.util.List;

    import org.springframework.web.bind.annotation.CrossOrigin;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import com.codegnan.hospital_management.dto.DoctorRequestDTO;
    import com.codegnan.hospital_management.dto.DoctorResponseDTO;
    import com.codegnan.hospital_management.service.DoctorService;

    @RestController
    @RequestMapping("/api/doctors")
    @CrossOrigin("*")
    public class DoctorController {

        private final DoctorService service;

        public DoctorController(DoctorService service) {
            this.service = service;
        }

        @PostMapping
        public DoctorResponseDTO addDoctor(@RequestBody DoctorRequestDTO dto) {
            return service.addDoctor(dto);
        }

        @GetMapping
        public List<DoctorResponseDTO> getAllDoctors() {
            return service.getAllDoctors();
        }
    }

