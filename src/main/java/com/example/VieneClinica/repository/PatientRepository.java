package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
