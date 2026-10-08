package br.dev.estudos.fundamentos;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraEntregaTest {
    private final CalculadoraEntrega servico = new CalculadoraEntrega();
    @Test void freteEhGratuitoAPartirDoLimite() {
        assertEquals(new BigDecimal("0.00"), servico.calcular(new BigDecimal("200.00"), CalculadoraEntrega.Regiao.REMOTA, false));
    }
    @Test void aplicaRegiaoCorreta() {
        assertEquals(new BigDecimal("22.50"), servico.calcular(new BigDecimal("50.00"), CalculadoraEntrega.Regiao.INTERIOR, false));
    }
    @Test void rejeitaSubtotalNegativo() {
        assertThrows(IllegalArgumentException.class, () -> servico.calcular(new BigDecimal("-1"), CalculadoraEntrega.Regiao.CAPITAL, false));
    }
}
