package com.example.VieneClinica.dto.procedimento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UrlDocumento(
        @NotBlank @Size(max = 200) String urlDocumento
) {}
