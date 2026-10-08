package br.dev.estudos.mongo;

import com.mongodb.client.MongoClients;
import java.math.BigDecimal;
import java.util.List;

public final class Demonstracao {
    public static void main(String[] args) {
        String uri = System.getenv().getOrDefault("MONGODB_URI", "mongodb://127.0.0.1:27017");
        try (var cliente = MongoClients.create(uri)) {
            var colecao = cliente.getDatabase("estudos_java").getCollection("pedidos");
            var repositorio = new MongoPedidoRepository(colecao);
            String codigo = "DEMO-" + java.util.UUID.randomUUID();
            repositorio.salvar(new PedidoDocumento(codigo, "Clara", List.of(
                    new PedidoDocumento.Item("LIV-7", 2, new BigDecimal("37.90"))), "ABERTO"));
            repositorio.atualizarStatus(codigo, "PAGO");
            System.out.println(repositorio.buscar(codigo).orElseThrow());
        }
    }
}
