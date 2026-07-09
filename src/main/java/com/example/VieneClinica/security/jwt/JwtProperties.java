package com.example.VieneClinica.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtProperties {

    @Value("${SECRET_KEY}")
    private String secretKey;

    @Value("${ACCESS_TOKEN_EXPIRES}")
    private Long accessExpiration;

    @Value("${REFRESH_TOKEN_EXPIRES}")
    private Long refreshExpiration;
}
