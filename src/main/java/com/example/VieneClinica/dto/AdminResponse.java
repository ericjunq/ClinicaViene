package com.example.VieneClinica.dto;

import java.time.OffsetDateTime;

public record AdminResponse(
        Long id,
        String nomeCompleto,
        OffsetDateTime createdAt
) {
}
