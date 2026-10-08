package br.dev.estudos.lambdas;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public final class PoliticasDeEntrega {
    public record Entrega(String codigo, String destino, int distanciaKm, boolean expressa) { }

    public List<String> organizar(List<Entrega> entregas, int distanciaMaxima) {
        if (distanciaMaxima < 0) throw new IllegalArgumentException("Distância inválida");
        Predicate<Entrega> dentroDaArea = e -> e.distanciaKm() <= distanciaMaxima;
        Function<Entrega, String> etiqueta = e -> e.codigo() + " -> " + e.destino();
        Comparator<Entrega> ordem = Comparator.comparing(Entrega::expressa).reversed()
                .thenComparingInt(Entrega::distanciaKm);
        return entregas.stream()
                .filter(dentroDaArea.and(e -> e.distanciaKm() >= 0))
                .sorted(ordem)
                .map(etiqueta)
                .toList();
    }

    public static void main(String[] args) {
        var entregas = List.of(new Entrega("PK-1", "Centro", 5, false),
                new Entrega("PK-2", "Hospital", 10, true));
        System.out.println(new PoliticasDeEntrega().organizar(entregas, 20));
    }
}
