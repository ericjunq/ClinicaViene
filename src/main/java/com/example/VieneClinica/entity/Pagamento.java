package com.example.VieneClinica.entity;

import com.example.VieneClinica.entity.enums.FormaPagamento;
import com.example.VieneClinica.entity.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToOne
    @JoinColumn(name = "procedimento_id", nullable = false, unique = true)
    private Procedimento procedimento;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    private BigDecimal desconto;

    @Column(nullable = false)
    private BigDecimal valorFinal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento statusPagamento;

    @Column(nullable = false)
    private Integer quantidadeParcelas;

    @OneToMany(mappedBy = "pagamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parcela> parcelas = new ArrayList<>();

    public void atualizarStatus(){
        long total = parcelas.size();
        long pagas = parcelas.stream()
                .filter(Parcela::isParcelaPaga)
                .count();

        if (pagas == 0){
            this.statusPagamento = StatusPagamento.PENDENTE;
        } else if (pagas < total){
            this.statusPagamento = StatusPagamento.PARCIAL;
        } else {
            this.statusPagamento = StatusPagamento.CONCLUIDO;
        }
    }
}
