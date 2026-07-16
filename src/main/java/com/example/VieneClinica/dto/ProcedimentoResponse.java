package com.example.VieneClinica.dto;

import com.example.VieneClinica.enums.CategoriaAtendimento;

import java.time.OffsetDateTime;

public record ProcedimentoResponse(
        Long procedimentoId,
        Long prontuarioId,
        Long professionalId,
        String descricao,
        CategoriaAtendimento categoriaAtendimento,
        OffsetDateTime createdAt
) {
}
