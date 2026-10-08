package br.dev.estudos.jpa;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "assinaturas")
public class Assinatura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal mensalidade;

    protected Assinatura() { }
    public Assinatura(Cliente cliente, BigDecimal mensalidade) {
        if (cliente == null || mensalidade == null || mensalidade.signum() <= 0)
            throw new IllegalArgumentException("Cliente e mensalidade inválidos");
        this.cliente = cliente;
        this.mensalidade = mensalidade;
    }
    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public BigDecimal getMensalidade() { return mensalidade; }
}
