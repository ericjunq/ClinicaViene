package com.example.VieneClinica.dto.usuario;

import java.time.OffsetDateTime;

public record UsuarioResponse(
        Long id,
        String nomeCompleto,
        OffsetDateTime createdAt
) {
}
