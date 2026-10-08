package br.dev.estudos.algoritmos;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/** Atendimento prioritário: maior urgência primeiro; empate pela ordem de chegada. */
public final class FilaDeChamados {
    public record Chamado(String protocolo, int urgencia, long chegada) {
        public Chamado {
            if (protocolo == null || protocolo.isBlank()) throw new IllegalArgumentException("Protocolo obrigatório");
            if (urgencia < 1 || urgencia > 5) throw new IllegalArgumentException("Urgência deve estar entre 1 e 5");
        }
    }

    private final PriorityQueue<Chamado> pendentes = new PriorityQueue<>(
            Comparator.comparingInt(Chamado::urgencia).reversed()
                    .thenComparingLong(Chamado::chegada));
    private long contador;

    public void adicionar(String protocolo, int urgencia) {
        pendentes.add(new Chamado(protocolo, urgencia, contador++));
    }

    public Chamado atender() {
        Chamado proximo = pendentes.poll();
        if (proximo == null) throw new NoSuchElementException("Não há chamados pendentes");
        return proximo;
    }

    public int tamanho() { return pendentes.size(); }
}
