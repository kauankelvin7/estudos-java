package br.dev.estudos.contas;

import java.math.BigDecimal;

public final class ContaPoupanca extends Conta {
    public ContaPoupanca(String titular, BigDecimal saldo) { super(titular, saldo); }
    @Override protected BigDecimal limiteDisponivel() { return saldo(); }
}
