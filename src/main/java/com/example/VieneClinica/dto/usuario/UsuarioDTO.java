package com.example.VieneClinica.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        @NotBlank @Size(min = 10, max = 120) String nomeCompleto,
        @NotBlank @Email @Size(max = 80) String email,
        @NotBlank @Size(min = 8, max = 40) String senha,
        @NotBlank @Size(min = 11, max = 11) String cpf,
        @NotBlank @Size(min = 11, max = 11) String telefone,
        @NotBlank @Size(min = 7, max = 15) String cro
) {
}
