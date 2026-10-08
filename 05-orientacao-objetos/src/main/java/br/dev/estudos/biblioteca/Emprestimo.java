package br.dev.estudos.biblioteca;

import java.time.LocalDate;

public record Emprestimo(Livro livro, String leitor, LocalDate devolucaoPrevista) {
    public Emprestimo {
        if (livro == null || leitor == null || leitor.isBlank() || devolucaoPrevista == null)
            throw new IllegalArgumentException("Empréstimo incompleto");
    }
}
