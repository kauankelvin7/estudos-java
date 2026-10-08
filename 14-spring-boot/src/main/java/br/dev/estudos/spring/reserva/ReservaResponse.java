package br.dev.estudos.spring.reserva;

import java.time.LocalDateTime;

public record ReservaResponse(Long id, String sala, String responsavel,
                              LocalDateTime inicio, LocalDateTime fim) {
    public static ReservaResponse de(Reserva reserva) {
        return new ReservaResponse(reserva.getId(), reserva.getSala(), reserva.getResponsavel(),
                reserva.getInicio(), reserva.getFim());
    }
}
