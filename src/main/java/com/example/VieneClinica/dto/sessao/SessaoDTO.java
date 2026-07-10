package com.example.VieneClinica.dto.sessao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record SessaoDTO(
        @NotNull OffsetDateTime dataSessao,
        @Size(max = 200) String descricao
        ) {
}
