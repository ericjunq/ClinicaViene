package com.example.VieneClinica.dto;

import com.example.VieneClinica.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProfessionalDTO(
    @NotNull Categoria categoria,
    @NotBlank String registroProfissional
    )
{}

