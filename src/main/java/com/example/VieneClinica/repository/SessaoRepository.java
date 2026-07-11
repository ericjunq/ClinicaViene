package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    boolean existsByDataSessao(OffsetDateTime dataSessao);
    Sessao findByDataSessao(OffsetDateTime dataSessao);
}
