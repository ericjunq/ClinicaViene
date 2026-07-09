package com.example.VieneClinica.dto.procedimento;

import com.example.VieneClinica.enums.CategoriaAtendimento;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record ProcedimentoResponse(
        Long id,
        String descricao,
        Long prontuarioId,
        Long usuarioId,
        CategoriaAtendimento categoriaAtendimento,
        OffsetDateTime createdAt
) {
}
