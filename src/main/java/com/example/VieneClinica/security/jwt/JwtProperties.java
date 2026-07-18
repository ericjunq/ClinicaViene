package com.example.VieneClinica.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtProperties {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-expiration-ms}")
    private Long accessExpirationMs;

    @Value("${jwt.refresh-expiration-ms}")
    private Long refreshExpirationMs;

}
