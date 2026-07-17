package com.example.VieneClinica.dto;

import com.example.VieneClinica.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record PagamentoDTO (
        @NotNull @PositiveOrZero BigDecimal valor,
        @PositiveOrZero BigDecimal desconto,
        @NotNull FormaPagamento formaPagamento,
        @Positive Integer totalParcelas
        ){
}
