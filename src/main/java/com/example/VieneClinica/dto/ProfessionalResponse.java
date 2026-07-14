package com.example.VieneClinica.dto;

import com.example.VieneClinica.enums.AreaAtendimento;

import java.time.OffsetDateTime;

public record ProfessionalResponse(
        Long id,
        String nomeCompleto,
        AreaAtendimento areaAtendimento,
        String registroProfissional,
        OffsetDateTime createdAt
) {
}
