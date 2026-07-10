package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    boolean existsByDataSessao(OffsetDateTime dataSessao);
    Optional<Sessao> findByDataSessao(OffsetDateTime dataSessao);
}
