package com.codegnan.hospital_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.codegnan.hospital_management.dto.SignUpRequestDTO;
import com.codegnan.hospital_management.security.AppUser;
import com.codegnan.hospital_management.security.Role;
import com.codegnan.hospital_management.security.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequestDTO dto) {

        if (dto.getUsername() == null || dto.getPassword() == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Username and password are required");
        }

        List<AppUser> users = repo.findAllByUsername(dto.getUsername());

        for (AppUser u : users) {
            if (encoder.matches(dto.getPassword(), u.getPassword())) {
                return ResponseEntity
                        .badRequest()
                        .body("Same username and password already exists");
            }
        }

        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(Role.USER);

        repo.save(user);

        return ResponseEntity.ok("Signup successful");
    }
}
