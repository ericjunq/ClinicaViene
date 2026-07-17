package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.PagamentoDTO;
import com.example.VieneClinica.dto.PagamentoResponse;
import com.example.VieneClinica.entity.Pagamento;
import com.example.VieneClinica.entity.Procedimento;
import com.example.VieneClinica.enums.StatusPagamento;
import com.example.VieneClinica.mapper.PagamentoMapper;
import com.example.VieneClinica.repository.PagamentoRepository;
import com.example.VieneClinica.repository.ProcedimentoRepository;
import com.example.VieneClinica.utils.AjusteValoresPagamento;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final ProcedimentoRepository procedimentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final PagamentoRepository pagamentoRepository;
    private final AjusteValoresPagamento ajusteValoresPagamento;

    public PagamentoResponse criarPagamento(Long procedimentoId, PagamentoDTO dto){

        Procedimento procedimento = procedimentoRepository.findById(procedimentoId)
                .orElseThrow(()-> new EntityNotFoundException("Procedimento não encontrado"));

        Pagamento pagamento = pagamentoMapper.toEntity(dto);
        pagamento.setProcedimento(procedimento);
        ajusteValoresPagamento.porcentagemFormaPagamento(pagamento);
        ajusteValoresPagamento.calcularValorProfissional(procedimento, pagamento);
        pagamento.setStatusPagamento(StatusPagamento.PENDENTE);
        pagamentoRepository.save(pagamento);

        procedimento.setPagamento(pagamento);
        procedimentoRepository.save(procedimento);

        return pagamentoMapper.toResponse(pagamento);
    }
}
