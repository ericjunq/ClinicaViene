package com.example.VieneClinica.dto;

import com.example.VieneClinica.enums.AreaAtendimento;
import com.example.VieneClinica.enums.RoleName;

import java.time.OffsetDateTime;

public record ProfessionalResponse(
        Long id,
        String nomeCompleto,
        RoleName roleName,
        AreaAtendimento areaAtendimento,
        String registroProfissional,
        OffsetDateTime createdAt
) {
}