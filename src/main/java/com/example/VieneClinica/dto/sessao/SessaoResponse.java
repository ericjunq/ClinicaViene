package com.example.VieneClinica.dto.sessao;

import com.example.VieneClinica.enums.StatusSessao;

import java.time.OffsetDateTime;

public record SessaoResponse(
        Long id,
        Long usuarioId,
        Long pacienteId,
        OffsetDateTime dataSessao,
        String descricao,
        StatusSessao status
) {
}
