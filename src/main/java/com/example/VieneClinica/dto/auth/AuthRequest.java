package com.example.VieneClinica.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank @Size(min = 11, max = 11) String cpf,
        @NotBlank String senha
) {
}
