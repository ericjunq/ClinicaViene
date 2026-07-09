package com.example.VieneClinica.dto.auth;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
