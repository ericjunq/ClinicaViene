package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.pagamento.PagamentoDTO;
import com.example.VieneClinica.entity.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public Pagamento toEntity(PagamentoDTO dto){
        Pagamento pagamento = new Pagamento();
        pagamento.setValorTotal(dto.valor());
        pagamento.setDesconto(dto.desconto());
        pagamento.setFormaPagamento(dto.formaPagamento());
        pagamento.setQuantidadeParcelas(dto.quantidadeParcelas());

        return pagamento;
    }
}
