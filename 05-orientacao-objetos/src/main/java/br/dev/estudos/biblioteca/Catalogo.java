package br.dev.estudos.biblioteca;

import java.util.Optional;

/** Contrato: a biblioteca não depende de como os livros são armazenados. */
public interface Catalogo {
    Optional<Livro> procurar(String isbn);
}
