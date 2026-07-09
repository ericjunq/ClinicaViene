package com.example.VieneClinica.dto.usuario;

import jakarta.validation.constraints.Email;

public record UsuarioUpdate(
        @Email String email,
        String senha,
        String telefone
) {
}
