package com.example.VieneClinica.dto;

import com.example.VieneClinica.enums.RoleName;

import java.time.OffsetDateTime;

public record AdminResponse(
        Long id,
        String nomeCompleto,
        RoleName roleName,
        OffsetDateTime createdAt

) {
}
