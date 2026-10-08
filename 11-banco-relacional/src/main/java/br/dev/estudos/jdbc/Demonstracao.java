package br.dev.estudos.jdbc;

import java.math.BigDecimal;
import org.h2.jdbcx.JdbcDataSource;

public final class Demonstracao {
    public static void main(String[] args) throws Exception {
        var fonte = new JdbcDataSource();
        fonte.setURL("jdbc:h2:mem:demojdbc;DB_CLOSE_DELAY=-1");
        var produtos = new ProdutoRepository(fonte);
        produtos.criarTabela();
        produtos.inserir(new ProdutoRepository.Produto(1, "Caderno", 20, new BigDecimal("18.90")));
        produtos.inserir(new ProdutoRepository.Produto(2, "Caneta", 2, new BigDecimal("4.50")));
        produtos.transferirEstoque(1, 2, 4);
        System.out.println(produtos.buscar(1).orElseThrow());
        System.out.println(produtos.buscar(2).orElseThrow());
    }
}
