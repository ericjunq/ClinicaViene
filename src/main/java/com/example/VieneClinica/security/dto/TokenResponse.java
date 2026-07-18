package com.example.VieneClinica.security.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
