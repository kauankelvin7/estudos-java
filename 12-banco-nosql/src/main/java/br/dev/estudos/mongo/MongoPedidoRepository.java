package br.dev.estudos.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public final class MongoPedidoRepository {
    private final MongoCollection<Document> colecao;
    public MongoPedidoRepository(MongoCollection<Document> colecao) {
        this.colecao = colecao;
        colecao.createIndex(Indexes.ascending("codigo"), new IndexOptions().unique(true));
    }
    public void salvar(PedidoDocumento pedido) {
        List<Document> itens = pedido.itens().stream().map(i -> new Document("sku", i.sku())
                .append("quantidade", i.quantidade())
                .append("valorCentavos", i.valorUnitario().movePointRight(2).longValueExact())).toList();
        colecao.insertOne(new Document("codigo", pedido.codigo()).append("cliente", pedido.cliente())
                .append("itens", itens).append("status", pedido.status()));
    }
    public Optional<PedidoDocumento> buscar(String codigo) {
        Document dado = colecao.find(Filters.eq("codigo", codigo)).first();
        if (dado == null) return Optional.empty();
        List<PedidoDocumento.Item> itens = dado.getList("itens", Document.class).stream()
                .map(item -> new PedidoDocumento.Item(item.getString("sku"), item.getInteger("quantidade"),
                        BigDecimal.valueOf(item.getLong("valorCentavos"), 2))).toList();
        return Optional.of(new PedidoDocumento(dado.getString("codigo"), dado.getString("cliente"), itens,
                dado.getString("status")));
    }
    public boolean atualizarStatus(String codigo, String novoStatus) {
        if (!List.of("ABERTO", "PAGO", "CANCELADO").contains(novoStatus))
            throw new IllegalArgumentException("Status desconhecido");
        // Update atômico no documento, sem leitura prévia sujeita a disputa.
        return colecao.updateOne(Filters.eq("codigo", codigo), Updates.set("status", novoStatus)).getMatchedCount() == 1;
    }
}
