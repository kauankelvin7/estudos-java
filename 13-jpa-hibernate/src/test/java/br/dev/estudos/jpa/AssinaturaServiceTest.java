package br.dev.estudos.jpa;

import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AssinaturaServiceTest {
    @Test void persisteClienteEAssinaturaNaMesmaTransacao() {
        try (var fabrica = Persistence.createEntityManagerFactory("estudos-jpa")) {
            var servico = new AssinaturaService(fabrica);
            assertNotNull(servico.contratar("Sofia", "sofia@example.com", new BigDecimal("49.90")));
            assertEquals(1, servico.listarAssinaturas().size());
            assertEquals("Sofia", servico.listarAssinaturas().getFirst().getCliente().getNome());
        }
    }
    @Test void dadosInvalidosNaoSaoPersistidos() {
        try (var fabrica = Persistence.createEntityManagerFactory("estudos-jpa")) {
            var servico = new AssinaturaService(fabrica);
            assertThrows(IllegalArgumentException.class,
                    () -> servico.contratar("", "e@example.com", new BigDecimal("20.00")));
            assertTrue(servico.listarAssinaturas().isEmpty());
        }
    }
}
