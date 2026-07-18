package com.example.VieneClinica.security.service;

import com.example.VieneClinica.entity.RefreshToken;
import com.example.VieneClinica.entity.User;
import com.example.VieneClinica.enums.TokenType;
import com.example.VieneClinica.repository.RefreshTokenRepository;
import com.example.VieneClinica.security.dto.TokenResponse;
import com.example.VieneClinica.security.jwt.JwtProperties;
import com.example.VieneClinica.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final JwtProperties properties;

    public void store(User user, String rawToken){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setTokenHash(jwtService.hashToken(rawToken));
        refreshToken.setExpiresAt(OffsetDateTime.now()
                .plus(Duration.ofMillis(properties.getRefreshExpirationMs())));
        refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public TokenResponse rotate(String rawToken){
        if (!jwtService.isTokenValid(rawToken, TokenType.REFRESH)){
            throw new BadCredentialsException("Refresh token inválido ou expirado");
        }

        String hash = jwtService.hashToken(rawToken);

        RefreshToken stored = refreshTokenRepository.findByTokenHash(hash)
                .orElseThrow(()-> new BadCredentialsException("Refresh token não encontrado"));

        if (stored.isRevoked()){
            refreshTokenRepository.revokedAllByUser(stored.getUser());
            throw new SecurityException("Reuso de refresh token detectado. Todas as sessões foram revogadas");
        }

        User user = stored.getUser();
        String newAcessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        RefreshToken refreshEntity = new RefreshToken();
        refreshEntity.setUser(user);
        refreshEntity.setTokenHash(jwtService.hashToken(newRefreshToken));
        refreshEntity.setExpiresAt(OffsetDateTime.now().plus(
                Duration.ofMillis(properties.getRefreshExpirationMs())));
        refreshTokenRepository.save(refreshEntity);

        stored.setRevoked(true);
        stored.setReplacedByToken(refreshEntity);
        refreshTokenRepository.save(stored);

        return new TokenResponse(newAcessToken, newRefreshToken);
    }

    public void revoke(String rawToken){
        String hash = jwtService.hashToken(rawToken);
        refreshTokenRepository.findByTokenHash(hash)
                .ifPresent(t -> {
                    t.setRevoked(true);
                    refreshTokenRepository.save(t);
                }
                );
    }
}
