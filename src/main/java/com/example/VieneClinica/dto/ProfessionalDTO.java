package com.example.VieneClinica.dto;

import com.example.VieneClinica.enums.AreaAtendimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessionalDTO(
    @NotNull AreaAtendimento areaAtendimento,
    @NotBlank String registroProfissional
    )
{}

