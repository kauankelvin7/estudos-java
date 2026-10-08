package br.dev.estudos.streams;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RelatorioVendasTest {
    private final RelatorioVendas relatorio = new RelatorioVendas();
    @Test void agrupaSemPerderCentavos() {
        var vendas = List.of(new RelatorioVendas.Venda("Ana", "Livros", 2, new BigDecimal("19.90")),
                new RelatorioVendas.Venda("Ana", "Livros", 1, new BigDecimal("9.90")));
        assertEquals(new BigDecimal("49.70"), relatorio.faturamentoPorCategoria(vendas).get("Livros"));
        assertEquals(List.of("Ana"), relatorio.vendedoresComFaturamentoMinimo(vendas, new BigDecimal("40")));
    }
    @Test void colecaoVaziaProduzRelatorioVazio() { assertTrue(relatorio.faturamentoPorCategoria(List.of()).isEmpty()); }
}
