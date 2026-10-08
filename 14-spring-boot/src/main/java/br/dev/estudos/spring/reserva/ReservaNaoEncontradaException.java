package br.dev.estudos.spring.reserva;

public final class ReservaNaoEncontradaException extends RuntimeException {
    public ReservaNaoEncontradaException(long id) { super("Reserva " + id + " não encontrada"); }
}
