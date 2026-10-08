package br.dev.estudos.contas;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarteiraTest {
    @Test void chequeEspecialEhPermitidoSomenteNaCorrente() {
        Conta corrente = new ContaCorrente("A", new BigDecimal("20"), new BigDecimal("30"));
        Conta poupanca = new ContaPoupanca("B", new BigDecimal("20"));
        corrente.sacar(new BigDecimal("40"));
        assertEquals(new BigDecimal("-20"), corrente.saldo());
        assertThrows(IllegalStateException.class, () -> poupanca.sacar(new BigDecimal("40")));
        assertEquals(new BigDecimal("0"), new Carteira().saldoConsolidado(List.of(corrente, poupanca)));
    }
}
