package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.Pagamento;
import com.example.VieneClinica.entity.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
    List<Parcela> findAllByPagamento (Pagamento pagamento);
}
