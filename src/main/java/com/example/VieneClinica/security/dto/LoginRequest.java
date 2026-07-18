package com.example.VieneClinica.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotNull @Size(min = 11, max = 11) String cpf,
        @NotBlank @Size(min = 8) String senha
) {
}
