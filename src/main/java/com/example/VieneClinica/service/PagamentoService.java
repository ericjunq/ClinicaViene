package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.pagamento.PagamentoDTO;
import com.example.VieneClinica.entity.Pagamento;
import com.example.VieneClinica.entity.Procedimento;
import com.example.VieneClinica.enums.StatusPagamento;
import com.example.VieneClinica.mapper.PagamentoMapper;
import com.example.VieneClinica.repository.PagamentoRepository;
import com.example.VieneClinica.repository.ProcedimentoRepository;
import com.example.VieneClinica.utils.Auxiliar;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DEV')")
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final ProcedimentoRepository procedimentoRepository;


    public Pagamento criarPagamento(Long idProcedimento, PagamentoDTO dto){

        Procedimento procedimento = procedimentoRepository.findById(idProcedimento)
                .orElseThrow(()-> new EntityNotFoundException("Procedimento não encontrado"));

        Pagamento pagamento = pagamentoMapper.toEntity(dto);


        pagamento.setProcedimento(procedimento);

        // Valor total
        pagamento.setValorTotal(Auxiliar.porcentagemFormaPagamento(dto.valor(), pagamento));

        // Desconto e valor final
        if (dto.desconto() != null){
            pagamento.setValorFinal(dto.valor().subtract(dto.desconto()));
        } else {
            pagamento.setValorFinal(dto.valor());
        }
        // Valor final da clinica e do profissional
        pagamento.setValorProfissional(Auxiliar.valorProfissional(pagamento.getValorFinal(), procedimento));
        pagamento.setValorClinica(pagamento.getValorFinal().subtract(pagamento.getValorProfissional()));

        pagamento.setStatusPagamento(StatusPagamento.PENDENTE);

        // Vincula pagamento ao procedimento
        procedimento.setPagamento(pagamento);

        pagamentoRepository.save(pagamento);
        procedimentoRepository.save(procedimento);

        return pagamento;
    }
}
