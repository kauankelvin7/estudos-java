package br.dev.estudos.spring.reserva;

public final class ConflitoReservaException extends RuntimeException {
    public ConflitoReservaException(String sala) { super("A sala '" + sala + "' já está reservada neste intervalo"); }
}
