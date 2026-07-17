package com.example.VieneClinica.utils;

import com.example.VieneClinica.entity.Pagamento;
import com.example.VieneClinica.entity.Procedimento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class AjusteValoresPagamento {

    public void porcentagemFormaPagamento(Pagamento pagamento) {

        BigDecimal porcentagem = BigDecimal.ZERO;

        switch (pagamento.getFormaPagamento()){
            case PIX -> porcentagem = new BigDecimal("3");
            case BOLETO -> porcentagem = new BigDecimal("5");
            case DEBITO -> porcentagem = new BigDecimal("2.39");
            case CREDITO -> {
                if (pagamento.getTotalParcelas() == 1){
                    porcentagem = new BigDecimal("4.99");
                } else {
                    porcentagem = new BigDecimal("5.99");
                }
            }
        }

        BigDecimal cem = new BigDecimal("100");

        BigDecimal acrescimo = pagamento.getValorFinalLiquido()
                .multiply(porcentagem).divide(cem, 2, RoundingMode.HALF_EVEN);

        pagamento.setValorFinalBruto(pagamento.getValorFinalLiquido().add(acrescimo));
    }

    public void calcularValorProfissional(Procedimento procedimento, Pagamento pagamento) {

        BigDecimal porcentagem = BigDecimal.ZERO;

        switch (procedimento.getCategoriaAtendimento()) {
            case ORTODONTIA ->
                    porcentagem = new BigDecimal("50");

            case ENDODONTIA ->
                    porcentagem = new BigDecimal("50");

            case CLINICA ->
                    porcentagem = new BigDecimal("30");

            case PROTESE ->
                    porcentagem = new BigDecimal("40");

            case FACETA ->
                    porcentagem = new BigDecimal("60");

            case HARMONIZACAO_FACIAL ->
                    porcentagem = new BigDecimal("70");

            case IMPLANTE ->
                    porcentagem = new BigDecimal("60");

            case CIRURGIA ->
                    porcentagem = new BigDecimal("40");
        }

        BigDecimal cem = new BigDecimal("100");

        BigDecimal valorProfissional = pagamento.getValorFinalLiquido()
                .multiply(porcentagem)
                .divide(cem, 2, RoundingMode.HALF_EVEN);

        pagamento.setValorProfissional(valorProfissional);
        pagamento.setValorClinica(pagamento.getValorFinalLiquido().subtract(pagamento.getValorProfissional()));
    }
}