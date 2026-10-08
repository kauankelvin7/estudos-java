package br.dev.estudos.spring.reserva;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Os intervalos se sobrepõem quando inicioExistente < fimNovo E fimExistente > inicioNovo.
    boolean existsBySalaIgnoreCaseAndInicioLessThanAndFimGreaterThan(
            String sala, LocalDateTime fimNovo, LocalDateTime inicioNovo);
}
