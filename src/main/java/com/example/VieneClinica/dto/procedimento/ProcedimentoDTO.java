package com.example.VieneClinica.dto.procedimento;

import com.example.VieneClinica.enums.CategoriaAtendimento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProcedimentoDTO(
        @NotNull @Size(min = 10, max=250) String descricao,
        @NotNull CategoriaAtendimento categoriaAtendimento
) {
}
