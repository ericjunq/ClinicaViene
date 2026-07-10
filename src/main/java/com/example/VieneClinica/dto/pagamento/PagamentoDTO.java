package com.example.VieneClinica.dto.pagamento;

import com.example.VieneClinica.enums.FormaPagamento;
import com.example.VieneClinica.enums.StatusPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record PagamentoDTO(
        @NotNull @Positive BigDecimal valor,
        @PositiveOrZero BigDecimal desconto,
        @NotNull FormaPagamento formaPagamento,
        @PositiveOrZero Integer quantidadeParcelas
        ) {
}
