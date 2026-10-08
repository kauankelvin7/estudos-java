package br.dev.estudos.mongo;

import java.math.BigDecimal;
import java.util.List;

/** Documento agregado: itens pertencem ao pedido e podem ser lidos juntos. */
public record PedidoDocumento(String codigo, String cliente, List<Item> itens, String status) {
    public record Item(String sku, int quantidade, BigDecimal valorUnitario) {
        public Item {
            if (sku == null || sku.isBlank() || quantidade <= 0 || valorUnitario == null
                    || valorUnitario.signum() < 0 || valorUnitario.scale() > 2)
                throw new IllegalArgumentException("Item inválido");
        }
    }
    public PedidoDocumento {
        if (codigo == null || codigo.isBlank() || cliente == null || cliente.isBlank()
                || itens == null || itens.isEmpty() || !List.of("ABERTO", "PAGO", "CANCELADO").contains(status))
            throw new IllegalArgumentException("Documento de pedido incompleto");
        itens = List.copyOf(itens);
    }
    public BigDecimal total() {
        return itens.stream().map(i -> i.valorUnitario().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
