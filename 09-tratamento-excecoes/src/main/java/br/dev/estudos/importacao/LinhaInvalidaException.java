package br.dev.estudos.importacao;

public final class LinhaInvalidaException extends RuntimeException {
    public LinhaInvalidaException(int numero, String motivo, Throwable causa) {
        super("Linha " + numero + ": " + motivo, causa);
    }
}
