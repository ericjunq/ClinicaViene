package com.example.VieneClinica.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtProperties {

    @Value("${jwt.secret_key}")
    private String secretKey;

    @Value("${jwt.access_token_expires}")
    private Long accessExpiration;

    @Value("${jwt.refresh_token_expires}")
    private Long refreshExpiration;
}
