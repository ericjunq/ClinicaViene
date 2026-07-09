package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.RefreshToken;
import com.example.VieneClinica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByUsuario(Usuario usuario);
}
