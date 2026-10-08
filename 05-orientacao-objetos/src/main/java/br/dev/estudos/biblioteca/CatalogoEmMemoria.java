package br.dev.estudos.biblioteca;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

public final class CatalogoEmMemoria implements Catalogo {
    private final Map<String, Livro> livros;
    public CatalogoEmMemoria(java.util.List<Livro> livros) {
        Map<String, Livro> porIsbn = new HashMap<>();
        for (Livro livro : livros) {
            if (porIsbn.putIfAbsent(livro.isbn(), livro) != null)
                throw new IllegalArgumentException("ISBN duplicado: " + livro.isbn());
        }
        this.livros = Map.copyOf(porIsbn);
    }
    @Override public Optional<Livro> procurar(String isbn) { return Optional.ofNullable(livros.get(isbn)); }
}
