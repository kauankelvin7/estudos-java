package br.dev.estudos.biblioteca;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/** Composição: catálogo e controle de empréstimos colaboram sem herança artificial. */
public final class Biblioteca {
    private final Catalogo catalogo;
    private final Set<String> emprestados = new HashSet<>();

    public Biblioteca(Catalogo catalogo) { this.catalogo = java.util.Objects.requireNonNull(catalogo); }
    public Emprestimo emprestar(String isbn, String leitor, LocalDate hoje) {
        Livro livro = catalogo.procurar(isbn).orElseThrow(() -> new IllegalArgumentException("ISBN inexistente"));
        if (leitor == null || leitor.isBlank() || hoje == null) throw new IllegalArgumentException("Leitor e data obrigatórios");
        if (!emprestados.add(isbn)) throw new IllegalStateException("Livro já emprestado");
        return new Emprestimo(livro, leitor, hoje.plusDays(14));
    }
    public void devolver(String isbn) {
        if (!emprestados.remove(isbn)) throw new IllegalStateException("Livro não está emprestado");
    }

    public static void main(String[] args) {
        var biblioteca = new Biblioteca(new CatalogoEmMemoria(java.util.List.of(new Livro("978-01", "Arquitetura de Software"))));
        System.out.println(biblioteca.emprestar("978-01", "Marina", LocalDate.of(2026, 10, 8)));
    }
}
