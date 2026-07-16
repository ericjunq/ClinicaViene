package com.example.VieneClinica.dto;

import com.example.VieneClinica.enums.CategoriaAtendimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProcedimentoDTO(
        @NotBlank @Size(max = 170) String descricao,
        @NotNull CategoriaAtendimento categoriaAtendimento
        ) {
}
