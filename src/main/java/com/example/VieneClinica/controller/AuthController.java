package com.example.VieneClinica.controller;

import com.example.VieneClinica.entity.User;
import com.example.VieneClinica.repository.RefreshTokenRepository;
import com.example.VieneClinica.repository.UserRepository;
import com.example.VieneClinica.security.dto.LoginRequest;
import com.example.VieneClinica.security.dto.RefreshRequest;
import com.example.VieneClinica.security.dto.TokenResponse;
import com.example.VieneClinica.security.jwt.JwtService;
import com.example.VieneClinica.security.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login (@RequestBody LoginRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.cpf(), request.senha())
        );

        User user = userRepository.findByPersonCpf(request.cpf())
                .orElseThrow(()-> new BadCredentialsException("Crendenciais inválidas"));

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        refreshTokenService.store(user, refreshToken);

        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh (@RequestBody RefreshRequest request){

        return ResponseEntity.ok(refreshTokenService.rotate(request.refreshToken()));

    }

    @PostMapping("/logout")
    public ResponseEntity<void> logout (@RequestBody RefreshRequest request){

        refreshTokenService.revoke(request.refreshToken());
        return ResponseEntity.noContent().build();
    }
}
