package br.dev.estudos.contas;

import java.math.BigDecimal;
import java.util.List;

public final class Carteira {
    public BigDecimal saldoConsolidado(List<? extends Conta> contas) {
        // Polimorfismo: a mesma chamada funciona para tipos distintos de Conta.
        BigDecimal total = BigDecimal.ZERO;
        for (Conta conta : contas) total = total.add(conta.saldo());
        return total;
    }
    public static void main(String[] args) {
        Conta corrente = new ContaCorrente("Paulo", new BigDecimal("100"), new BigDecimal("50"));
        Conta poupanca = new ContaPoupanca("Paulo", new BigDecimal("200"));
        corrente.sacar(new BigDecimal("120"));
        System.out.println("Consolidado: " + new Carteira().saldoConsolidado(List.of(corrente, poupanca)));
    }
}
