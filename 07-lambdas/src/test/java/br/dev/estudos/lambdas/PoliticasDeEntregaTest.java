package br.dev.estudos.lambdas;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PoliticasDeEntregaTest {
    @Test void filtraEOrdenaEntregas() {
        var p = new PoliticasDeEntrega();
        var entregas = List.of(new PoliticasDeEntrega.Entrega("A", "Centro", 2, false),
                new PoliticasDeEntrega.Entrega("B", "Hospital", 7, true),
                new PoliticasDeEntrega.Entrega("C", "Interior", 50, true));
        assertEquals(List.of("B -> Hospital", "A -> Centro"), p.organizar(entregas, 10));
    }
}
