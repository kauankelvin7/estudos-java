package br.dev.estudos.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/** Regra de negócio sem dependência do JavaFX: executável em teste headless. */
public final class QuadroTarefas {
    private final AtomicLong sequencia = new AtomicLong();
    private final List<Tarefa> tarefas = new ArrayList<>();

    public Tarefa adicionar(String titulo) {
        Tarefa tarefa = new Tarefa(sequencia.incrementAndGet(), titulo, false);
        tarefas.add(tarefa);
        return tarefa;
    }
    public void concluir(long id) {
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).id() == id) {
                tarefas.set(i, tarefas.get(i).concluir());
                return;
            }
        }
        throw new IllegalArgumentException("Tarefa não encontrada: " + id);
    }
    public List<Tarefa> listar() { return List.copyOf(tarefas); }
}
