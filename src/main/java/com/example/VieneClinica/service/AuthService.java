package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.auth.AuthRequest;
import com.example.VieneClinica.dto.auth.AuthResponse;
import com.example.VieneClinica.dto.auth.RefreshRequest;
import com.example.VieneClinica.entity.RefreshToken;
import com.example.VieneClinica.entity.Usuario;
import com.example.VieneClinica.repository.RefreshTokenRepository;
import com.example.VieneClinica.repository.UsuarioRepository;
import com.example.VieneClinica.security.jwt.JwtProperties;
import com.example.VieneClinica.security.jwt.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    // Método auxiliar para criar token e salvar no banco
    public void salvarToken(Usuario usuario, String refreshToken){
        RefreshToken refresh = new RefreshToken();
        refresh.setToken(refreshToken);
        refresh.setUsuario(usuario);
        refresh.setRevoked(false);
        refresh.setExpiresAt(OffsetDateTime.now().plus(Duration.ofMillis(jwtProperties.getRefreshExpiration())));

        refreshTokenRepository.save(refresh);
    }

    // Método para login + uso do salvarToken
    public AuthResponse login(AuthRequest request){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.cpf(), request.senha())
        );

        Usuario usuario = (Usuario) authenticate.getPrincipal();

        String accessToken = jwtService.gerarAcessToken(usuario);
        String refreshToken = jwtService.gerarRefreshToken(usuario);

        salvarToken(usuario, refreshToken);

        return new AuthResponse(accessToken, refreshToken);
    }

    // Método para validar Token
    private RefreshToken validarToken(String refreshToken){
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(()-> new RuntimeException("Refresh token inválido"));

        if (token.isRevoked()){
            revokeAllUserTokens(token.getUsuario());

            throw new RuntimeException("Token já utilizado");
        }

        if (token.getExpiresAt().isBefore(OffsetDateTime.now())){
            throw new RuntimeException("Token expirado");
        }
        return token;
    }

    // Validação do refreshToken
    private void revokeAllUserTokens(Usuario usuario){
        List<RefreshToken> tokens = refreshTokenRepository.findAllByUsuario(usuario);
        tokens.forEach(token->token.setRevoked(true));

        refreshTokenRepository.saveAll(tokens);
    }

    public AuthResponse refresh(RefreshRequest request){
        RefreshToken refreshToken = validarToken(request.refreshToken());

        Usuario usuario = refreshToken.getUsuario();

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);

        String newAccessToken = jwtService.gerarAcessToken(usuario);
        String newRefreshToken = jwtService.gerarRefreshToken(usuario);
        salvarToken(usuario, newRefreshToken);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }

    //Logout
    public void logout(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> new EntityNotFoundException("Token não encontrado"));

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }
}
