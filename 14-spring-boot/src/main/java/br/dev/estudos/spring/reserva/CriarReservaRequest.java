package br.dev.estudos.spring.reserva;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CriarReservaRequest(
        @NotBlank String sala,
        @NotBlank String responsavel,
        @NotNull LocalDateTime inicio,
        @NotNull LocalDateTime fim) { }
