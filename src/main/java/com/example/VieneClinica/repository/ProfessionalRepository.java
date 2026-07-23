package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Optional<Professional> findByUserPersonCpf(String cpf);
}
