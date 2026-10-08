package br.dev.estudos.algoritmos;

import java.util.*;

/** Grafo não ponderado: BFS encontra o trajeto com menos conexões. */
public final class RedeDeRotas {
    private final Map<String, Set<String>> vizinhos = new HashMap<>();

    public void conectar(String origem, String destino) {
        if (origem == null || destino == null || origem.isBlank() || destino.isBlank())
            throw new IllegalArgumentException("Estações inválidas");
        vizinhos.computeIfAbsent(origem, ignored -> new LinkedHashSet<>()).add(destino);
        vizinhos.computeIfAbsent(destino, ignored -> new LinkedHashSet<>()).add(origem);
    }

    public List<String> menorCaminho(String origem, String destino) {
        if (!vizinhos.containsKey(origem) || !vizinhos.containsKey(destino)) return List.of();
        Deque<String> fila = new ArrayDeque<>();
        Map<String, String> anterior = new HashMap<>();
        fila.add(origem);
        anterior.put(origem, null);
        while (!fila.isEmpty()) {
            String atual = fila.remove();
            if (atual.equals(destino)) {
                LinkedList<String> caminho = new LinkedList<>();
                for (String cursor = destino; cursor != null; cursor = anterior.get(cursor))
                    caminho.addFirst(cursor);
                return List.copyOf(caminho);
            }
            for (String vizinho : vizinhos.get(atual)) {
                if (!anterior.containsKey(vizinho)) {
                    anterior.put(vizinho, atual);
                    fila.add(vizinho);
                }
            }
        }
        return List.of();
    }
}
