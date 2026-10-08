package br.dev.estudos.contas;

import java.math.BigDecimal;

public final class ContaCorrente extends Conta {
    private final BigDecimal chequeEspecial;
    public ContaCorrente(String titular, BigDecimal saldo, BigDecimal chequeEspecial) {
        super(titular, saldo);
        if (chequeEspecial == null || chequeEspecial.signum() < 0) throw new IllegalArgumentException("Limite inválido");
        this.chequeEspecial = chequeEspecial;
    }
    @Override protected BigDecimal limiteDisponivel() { return saldo().add(chequeEspecial); }
}
