package br.dev.estudos.spring.reserva;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas", indexes = @Index(name = "idx_reservas_sala_inicio", columnList = "sala,inicio"))
public class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 90)
    private String sala;
    @Column(nullable = false, length = 120)
    private String responsavel;
    @Column(nullable = false)
    private LocalDateTime inicio;
    @Column(nullable = false)
    private LocalDateTime fim;

    protected Reserva() { }
    public Reserva(String sala, String responsavel, LocalDateTime inicio, LocalDateTime fim) {
        if (sala == null || sala.isBlank() || responsavel == null || responsavel.isBlank()
                || inicio == null || fim == null || !inicio.isBefore(fim))
            throw new IllegalArgumentException("Dados de reserva inválidos");
        this.sala = sala.strip();
        this.responsavel = responsavel.strip();
        this.inicio = inicio;
        this.fim = fim;
    }
    public Long getId() { return id; }
    public String getSala() { return sala; }
    public String getResponsavel() { return responsavel; }
    public LocalDateTime getInicio() { return inicio; }
    public LocalDateTime getFim() { return fim; }
}
