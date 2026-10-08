package br.dev.estudos.streams;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RelatorioVendas {
    public record Venda(String vendedor, String categoria, int quantidade, BigDecimal preco) {
        public Venda {
            if (vendedor == null || vendedor.isBlank() || categoria == null || categoria.isBlank()
                    || quantidade <= 0 || preco == null || preco.signum() < 0)
                throw new IllegalArgumentException("Venda inválida");
        }
        public BigDecimal valor() { return preco.multiply(BigDecimal.valueOf(quantidade)); }
    }

    /** Retorna um mapa novo: o relatório não modifica a coleção recebida. */
    public Map<String, BigDecimal> faturamentoPorCategoria(List<Venda> vendas) {
        return vendas.stream().collect(Collectors.groupingBy(Venda::categoria,
                Collectors.reducing(BigDecimal.ZERO, Venda::valor, BigDecimal::add)));
    }
    public List<String> vendedoresComFaturamentoMinimo(List<Venda> vendas, BigDecimal minimo) {
        Map<String, BigDecimal> porVendedor = vendas.stream().collect(Collectors.groupingBy(Venda::vendedor,
                Collectors.reducing(BigDecimal.ZERO, Venda::valor, BigDecimal::add)));
        return porVendedor.entrySet().stream()
                .filter(entry -> entry.getValue().compareTo(minimo) >= 0)
                .map(Map.Entry::getKey).sorted().toList();
    }
    public static void main(String[] args) {
        var vendas = List.of(new Venda("Ana", "Livros", 2, new BigDecimal("45.00")),
                new Venda("Caio", "Livros", 1, new BigDecimal("20.00")));
        System.out.println(new RelatorioVendas().faturamentoPorCategoria(vendas));
    }
}
