package br.dev.estudos.controle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PoliticaDePontuacaoTest {
    private final PoliticaDePontuacao politica = new PoliticaDePontuacao();
    @Test void ignoraMesSemCompras() { assertEquals(37, politica.calcularPontos(new int[]{1, 0, 4, 5})); }
    @Test void calculaFaixas() { assertEquals("ouro", politica.faixa(100)); assertEquals("prata", politica.faixa(40)); }
    @Test void invalidaComprasNegativas() { assertThrows(IllegalArgumentException.class, () -> politica.calcularPontos(new int[]{1, -1})); }
}
