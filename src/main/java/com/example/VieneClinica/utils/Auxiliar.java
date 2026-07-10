package com.example.VieneClinica.utils;

import com.example.VieneClinica.entity.Pagamento;
import com.example.VieneClinica.entity.Procedimento;
import com.example.VieneClinica.enums.CategoriaAtendimento;
import com.example.VieneClinica.enums.FormaPagamento;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Auxiliar {

    public static BigDecimal valorProfissional(
            BigDecimal valor, Procedimento procedimento
    ){
        BigDecimal cem = new BigDecimal("100");
        BigDecimal valorFinal = new BigDecimal(BigInteger.ZERO);

        if (procedimento.getCategoriaAtendimento() == CategoriaAtendimento.ORTODONTIA){
            BigDecimal porc = new BigDecimal("50");
            valorFinal = valor.multiply(porc).divide(cem, 2, RoundingMode.HALF_EVEN);
        }

        else if (procedimento.getCategoriaAtendimento() == CategoriaAtendimento.ENDODONTIA){
            BigDecimal porc = new BigDecimal("50");
            valorFinal = valor.multiply(porc).divide(cem, 2, RoundingMode.HALF_EVEN);
        }

        else if (procedimento.getCategoriaAtendimento() == CategoriaAtendimento.CLINICA){
            BigDecimal porc = new BigDecimal("30");
            valorFinal = valor.multiply(porc).divide(cem, 2, RoundingMode.HALF_EVEN);
        }
        else if (procedimento.getCategoriaAtendimento() == CategoriaAtendimento.PROTESE){
            BigDecimal porc = new BigDecimal("40");
            valorFinal = valor.multiply(porc).divide(cem, 2, RoundingMode.HALF_EVEN);
        }
        else if (procedimento.getCategoriaAtendimento() == CategoriaAtendimento.FACETA){
            BigDecimal porc = new BigDecimal("60");
            valorFinal = valor.multiply(porc).divide(cem, 2, RoundingMode.HALF_EVEN);
        }
        else if (procedimento.getCategoriaAtendimento() == CategoriaAtendimento.HARMONIZACAO_FACIAL){
            BigDecimal porc = new BigDecimal("70");
            valorFinal = valor.multiply(porc).divide(cem, 2, RoundingMode.HALF_EVEN);
        }
        else if (procedimento.getCategoriaAtendimento() == CategoriaAtendimento.IMPLANTE){
            BigDecimal porc = new BigDecimal("60");
            valorFinal = valor.multiply(porc).divide(cem, 2, RoundingMode.HALF_EVEN);
        }
        else if (procedimento.getCategoriaAtendimento() == CategoriaAtendimento.CIRURGIA){
            BigDecimal porc = new BigDecimal("40");
            valorFinal = valor.multiply(porc).divide(cem, 2, RoundingMode.HALF_EVEN);
        }

        return valorFinal;

    }

    public static BigDecimal porcentagemFormaPagamento(BigDecimal valor, Pagamento pagamento){
        BigDecimal cem = new BigDecimal("100");
        BigDecimal valorFinal = BigDecimal.ZERO;

        if (pagamento.getFormaPagamento() == FormaPagamento.PIX){
            BigDecimal porc = new BigDecimal("3");
            valorFinal = valor.multiply(porc).divide(cem, 2, RoundingMode.HALF_EVEN);
        }

        if (pagamento.getFormaPagamento() == FormaPagamento.BOLETO){
            BigDecimal porc = new BigDecimal("5");
            valorFinal = valor.multiply(porc).divide(cem, 2, RoundingMode.HALF_EVEN);
        }

        return valorFinal;

    }
}
