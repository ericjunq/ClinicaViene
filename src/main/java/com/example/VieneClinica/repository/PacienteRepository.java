package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByCpf(String cpf);
}
