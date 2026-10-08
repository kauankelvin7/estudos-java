package br.dev.estudos.jpa;

import jakarta.persistence.Persistence;
import java.math.BigDecimal;

public final class Demonstracao {
    public static void main(String[] args) {
        try (var fabrica = Persistence.createEntityManagerFactory("estudos-jpa")) {
            var servico = new AssinaturaService(fabrica);
            servico.contratar("Ana", "ana@example.com", new BigDecimal("29.90"));
            servico.listarAssinaturas().forEach(a -> System.out.println(a.getCliente().getNome()
                    + " -> R$ " + a.getMensalidade()));
        }
    }
}
