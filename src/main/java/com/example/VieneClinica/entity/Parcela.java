package com.example.VieneClinica.entity;

import com.example.VieneClinica.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parcelas")
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

    @Column(nullable = false)
    private BigDecimal valor;

    private OffsetDateTime dataPagamento;

    private OffsetDateTime dataVencimento;

    @Column(nullable = false)
    private StatusPagamento statusPagamento;

}
