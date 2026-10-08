package br.dev.estudos.contas;

import java.math.BigDecimal;
import java.util.Objects;

/** Abstração: cada tipo define a política de saque sem expor a manipulação do saldo. */
public abstract class Conta {
    private final String titular;
    private BigDecimal saldo;

    protected Conta(String titular, BigDecimal saldoInicial) {
        if (titular == null || titular.isBlank() || saldoInicial == null || saldoInicial.signum() < 0)
            throw new IllegalArgumentException("Titular ou saldo inicial inválido");
        this.titular = titular;
        this.saldo = saldoInicial;
    }
    public final String titular() { return titular; }
    public final BigDecimal saldo() { return saldo; }
    public final void depositar(BigDecimal valor) { validarValor(valor); saldo = saldo.add(valor); }
    public final void sacar(BigDecimal valor) {
        validarValor(valor);
        if (valor.compareTo(limiteDisponivel()) > 0) throw new IllegalStateException("Saldo insuficiente");
        saldo = saldo.subtract(valor);
    }
    protected abstract BigDecimal limiteDisponivel();
    protected static void validarValor(BigDecimal valor) {
        if (Objects.isNull(valor) || valor.signum() <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
    }
}
