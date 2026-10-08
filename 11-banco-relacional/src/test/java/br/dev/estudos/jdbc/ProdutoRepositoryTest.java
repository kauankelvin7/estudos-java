package br.dev.estudos.jdbc;

import java.math.BigDecimal;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoRepositoryTest {
    private ProdutoRepository produtos;
    @BeforeEach void preparar() throws Exception {
        var fonte = new JdbcDataSource();
        fonte.setURL("jdbc:h2:mem:teste_" + java.util.UUID.randomUUID() + ";DB_CLOSE_DELAY=-1");
        produtos = new ProdutoRepository(fonte);
        produtos.criarTabela();
        produtos.inserir(new ProdutoRepository.Produto(1, "Origem", 5, new BigDecimal("10.00")));
        produtos.inserir(new ProdutoRepository.Produto(2, "Destino", 0, new BigDecimal("10.00")));
    }
    @Test void transferenciaEhAtomica() throws Exception {
        produtos.transferirEstoque(1, 2, 3);
        assertEquals(2, produtos.buscar(1).orElseThrow().estoque());
        assertEquals(3, produtos.buscar(2).orElseThrow().estoque());
    }
    @Test void fazRollbackSeDestinoNaoExistir() throws Exception {
        assertThrows(IllegalStateException.class, () -> produtos.transferirEstoque(1, 999, 2));
        assertEquals(5, produtos.buscar(1).orElseThrow().estoque());
    }
    @Test void evitaEstoqueNegativo() throws Exception {
        assertThrows(IllegalStateException.class, () -> produtos.transferirEstoque(1, 2, 8));
        assertEquals(5, produtos.buscar(1).orElseThrow().estoque());
    }
}
