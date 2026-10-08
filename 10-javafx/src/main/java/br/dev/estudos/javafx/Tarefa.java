package br.dev.estudos.javafx;

public record Tarefa(long id, String titulo, boolean concluida) {
    public Tarefa {
        if (id <= 0 || titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("ID e título obrigatórios");
    }
    public Tarefa concluir() { return new Tarefa(id, titulo, true); }
    @Override public String toString() { return (concluida ? "✓ " : "○ ") + titulo; }
}
