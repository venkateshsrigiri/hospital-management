package com.codegnan.hospital_management.security;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    List<AppUser> findAllByUsername(String username);

    boolean existsByUsernameAndRole(String username, Role role);
}
