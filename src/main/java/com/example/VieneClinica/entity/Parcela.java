package com.example.VieneClinica.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private OffsetDateTime dataPagamento;

    @Column(nullable = false)
    private OffsetDateTime dataVencimento;

    @Column(nullable = false)
    private boolean parcelaPaga;

}
