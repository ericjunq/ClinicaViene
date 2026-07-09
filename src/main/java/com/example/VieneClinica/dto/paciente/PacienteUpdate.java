package com.example.VieneClinica.dto.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record PacienteUpdate(
        @Size(min = 11, max = 11) String telefone,
        @Email @Size (max = 80) String email,
        @Size(max = 150) String endereco
) {
}
