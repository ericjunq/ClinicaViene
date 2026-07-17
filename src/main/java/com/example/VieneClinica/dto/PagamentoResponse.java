package com.example.VieneClinica.dto;

import com.example.VieneClinica.enums.FormaPagamento;

import java.math.BigDecimal;

public record PagamentoResponse(
        BigDecimal valorFinalBruto,
        BigDecimal desconto,
        BigDecimal valorFinalLiquido,
        BigDecimal valorClinica,
        BigDecimal valorProfissional,
        FormaPagamento formaPagamento,
        Integer totalParcelas
) {
}
