package br.dev.estudos.biblioteca;

public record Livro(String isbn, String titulo) {
    public Livro {
        if (isbn == null || isbn.isBlank() || titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("ISBN e título obrigatórios");
    }
}
