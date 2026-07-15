package com.example.VieneClinica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record PersonDTO(
        @NotBlank @Size(max = 140) String nomeCompleto,
        @NotBlank @Email @Size(max = 100) String email,
        @NotBlank @Size(min = 11, max = 11) String cpf,
        @NotBlank String telefone,
        @NotBlank @Size(max = 160) String endereco,
        @NotNull OffsetDateTime dataNascimento
        //AreaAtendimento areaAtendimento,
        //String registroProfissional
        ) {
}
