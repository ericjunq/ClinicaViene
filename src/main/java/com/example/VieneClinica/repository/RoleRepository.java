package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.Role;
import com.example.VieneClinica.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(RoleName role);
}
