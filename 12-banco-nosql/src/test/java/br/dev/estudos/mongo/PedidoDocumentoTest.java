package br.dev.estudos.mongo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoDocumentoTest {
    @Test void totalEProtecaoDaLista() {
        var itens = new ArrayList<PedidoDocumento.Item>();
        itens.add(new PedidoDocumento.Item("SKU", 3, new BigDecimal("12.50")));
        var documento = new PedidoDocumento("PED-1", "Ana", itens, "ABERTO");
        itens.clear();
        assertEquals(new BigDecimal("37.50"), documento.total());
        assertEquals(1, documento.itens().size());
    }
}
