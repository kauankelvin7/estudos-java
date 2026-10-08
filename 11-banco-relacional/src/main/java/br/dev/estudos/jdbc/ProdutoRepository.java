package br.dev.estudos.jdbc;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

public final class ProdutoRepository {
    public record Produto(long id, String nome, int estoque, BigDecimal preco) { }
    private final DataSource dados;
    public ProdutoRepository(DataSource dados) { this.dados = dados; }

    public void criarTabela() throws SQLException {
        try (var conexao = dados.getConnection(); var comando = conexao.createStatement()) {
            comando.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS produto (
                        id BIGINT PRIMARY KEY,
                        nome VARCHAR(150) NOT NULL,
                        estoque INTEGER NOT NULL CHECK (estoque >= 0),
                        preco DECIMAL(12,2) NOT NULL CHECK (preco >= 0)
                    )
                    """);
        }
    }
    public void inserir(Produto produto) throws SQLException {
        try (var conexao = dados.getConnection();
             var comando = conexao.prepareStatement("INSERT INTO produto (id, nome, estoque, preco) VALUES (?, ?, ?, ?)")) {
            comando.setLong(1, produto.id());
            comando.setString(2, produto.nome());
            comando.setInt(3, produto.estoque());
            comando.setBigDecimal(4, produto.preco());
            comando.executeUpdate();
        }
    }
    public Optional<Produto> buscar(long id) throws SQLException {
        try (var conexao = dados.getConnection();
             var comando = conexao.prepareStatement("SELECT id, nome, estoque, preco FROM produto WHERE id = ?")) {
            comando.setLong(1, id); // Parâmetro vinculado: nada de concatenar entrada do usuário no SQL.
            try (var resultado = comando.executeQuery()) {
                if (!resultado.next()) return Optional.empty();
                return Optional.of(new Produto(resultado.getLong("id"), resultado.getString("nome"),
                        resultado.getInt("estoque"), resultado.getBigDecimal("preco")));
            }
        }
    }
    public void transferirEstoque(long origem, long destino, int quantidade) throws SQLException {
        if (quantidade <= 0 || origem == destino) throw new IllegalArgumentException("Transferência inválida");
        try (var conexao = dados.getConnection()) {
            conexao.setAutoCommit(false);
            try (var retirar = conexao.prepareStatement(
                        "UPDATE produto SET estoque = estoque - ? WHERE id = ? AND estoque >= ?");
                 var adicionar = conexao.prepareStatement("UPDATE produto SET estoque = estoque + ? WHERE id = ?")) {
                retirar.setInt(1, quantidade); retirar.setLong(2, origem); retirar.setInt(3, quantidade);
                if (retirar.executeUpdate() != 1) throw new IllegalStateException("Origem ausente ou estoque insuficiente");
                adicionar.setInt(1, quantidade); adicionar.setLong(2, destino);
                if (adicionar.executeUpdate() != 1) throw new IllegalStateException("Destino inexistente");
                conexao.commit();
            } catch (SQLException | RuntimeException erro) {
                conexao.rollback(); // Se uma das duas operações falha, nenhuma alteração permanece.
                throw erro;
            }
        }
    }
}
