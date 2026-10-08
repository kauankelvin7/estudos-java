package br.dev.estudos.carrinho;

import java.math.BigDecimal;

public record ItemCarrinho(String sku, String nome, int quantidade, BigDecimal precoUnitario) {
    public ItemCarrinho {
        if (sku == null || sku.isBlank() || nome == null || nome.isBlank())
            throw new IllegalArgumentException("Identificação do produto obrigatória");
        if (quantidade <= 0 || precoUnitario == null || precoUnitario.signum() < 0)
            throw new IllegalArgumentException("Quantidade e preço inválidos");
    }
    public BigDecimal subtotal() { return precoUnitario.multiply(BigDecimal.valueOf(quantidade)); }
}
