package com.example.VieneClinica.security.jwt;

import com.example.VieneClinica.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties properties;

    public Key getKey(){
        return Keys.hmacShaKeyFor(properties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public String gerarAcessToken(Usuario usuario){
        return Jwts.builder()
                .subject(usuario.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+properties.getAccessExpiration()))
                .signWith(getKey())
                .compact();

    }

    public String gerarRefreshToken(Usuario usuario){
        return Jwts.builder()
                .subject(usuario.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+properties.getRefreshExpiration()))
                .signWith(getKey())
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    public Date getExpiration(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    public boolean isExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean isValid(String token, Usuario usuario){
        try {
           Claims claims = extractClaims(token);
           return claims.getSubject().equals(usuario.getUsername()) && claims.getExpiration().after(new Date());
        } catch (ExpiredJwtException e){
            return false;
        }

    }
}