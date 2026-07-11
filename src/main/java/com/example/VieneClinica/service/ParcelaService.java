package com.example.VieneClinica.service;

import com.example.VieneClinica.entity.Pagamento;
import com.example.VieneClinica.entity.Parcela;
import com.example.VieneClinica.enums.StatusPagamento;
import com.example.VieneClinica.repository.PagamentoRepository;
import com.example.VieneClinica.repository.ParcelaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PostLoad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class ParcelaService {

    private final PagamentoRepository pagamentoRepository;
    private final ParcelaRepository parcelaRepository;

    public Parcela definirParcelas(Long pagamentoId, OffsetDateTime vencimento){
        Pagamento pagamento = pagamentoRepository.findById(pagamentoId)
                .orElseThrow(()-> new EntityNotFoundException("Pagamento não encontrado"));

        Parcela parcela = new Parcela();
        parcela.setPagamento(pagamento);
        // Calcular valor por parcela
        BigDecimal quantidadeParcelas = new BigDecimal(pagamento.getQuantidadeParcelas());
        parcela.setValor(pagamento.getValorFinal().divide(quantidadeParcelas, 2, RoundingMode.HALF_EVEN));

        parcela.setDataVencimento(vencimento);
        parcela.setStatusPagamento(StatusPagamento.PENDENTE);

        parcelaRepository.save(parcela);
        return parcela;
    }

    public void pagarParcela(Long parcelaId){
        Parcela parcela = parcelaRepository.findById(parcelaId)
                .orElseThrow(()-> new EntityNotFoundException("Parcela inválida ou não encontrada"));

        if (parcela.getStatusPagamento() == StatusPagamento.CONCLUIDO){
            throw new IllegalStateException("Essa parcela já foi paga");
        }

        parcela.setStatusPagamento(StatusPagamento.CONCLUIDO);
        parcela.setDataPagamento(OffsetDateTime.now());
        parcelaRepository.save(parcela);
    }


    public void parcelaVencida(Parcela parcela){
        if (parcela.getStatusPagamento() != StatusPagamento.CONCLUIDO &&
                parcela.getDataVencimento().isBefore(OffsetDateTime.now())){
            parcela.setStatusPagamento(StatusPagamento.ATRASADO);
        }
    }


}
