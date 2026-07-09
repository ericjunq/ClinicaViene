package com.example.VieneClinica.dto.paciente;

import java.time.OffsetDateTime;

public record PacienteResponse(
        Long id,
        String nomeCompleto,
        OffsetDateTime createdAt
) {
}
