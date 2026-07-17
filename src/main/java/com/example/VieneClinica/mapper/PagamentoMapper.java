package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.PagamentoDTO;
import com.example.VieneClinica.dto.PagamentoResponse;
import com.example.VieneClinica.entity.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public Pagamento toEntity(PagamentoDTO dto){
        Pagamento pagamento = new Pagamento();
        pagamento.setValorFinalLiquido(dto.valor());
        pagamento.setDesconto(dto.desconto());
        pagamento.setFormaPagamento(dto.formaPagamento());
        pagamento.setTotalParcelas(dto.totalParcelas());

        return pagamento;
    }

    public PagamentoResponse toResponse (Pagamento pagamento){
        return new PagamentoResponse(
            pagamento.getValorFinalBruto(),
                pagamento.getDesconto(),
                pagamento.getValorFinalLiquido(),
                pagamento.getValorClinica(),
                pagamento.getValorProfissional(),
                pagamento.getFormaPagamento(),
                pagamento.getTotalParcelas()
        );
    }
}
