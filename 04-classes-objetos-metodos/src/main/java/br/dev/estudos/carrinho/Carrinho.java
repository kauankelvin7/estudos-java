package br.dev.estudos.carrinho;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Mantém a lista encapsulada; quem usa o carrinho recebe apenas uma cópia imutável. */
public final class Carrinho {
    private final List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionar(ItemCarrinho item) {
        if (item == null) throw new IllegalArgumentException("Item obrigatório");
        for (ItemCarrinho existente : itens) {
            if (existente.sku().equals(item.sku()))
                throw new IllegalArgumentException("SKU já está no carrinho");
        }
        itens.add(item);
    }
    public void remover(String sku) { itens.removeIf(item -> item.sku().equals(sku)); }
    public List<ItemCarrinho> itens() { return List.copyOf(itens); }
    public BigDecimal total() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemCarrinho item : itens) total = total.add(item.subtotal());
        return total;
    }

    public static void main(String[] args) {
        var carrinho = new Carrinho();
        carrinho.adicionar(new ItemCarrinho("LIV-01", "Java Efetivo", 2, new BigDecimal("99.90")));
        System.out.println("Total: R$ " + carrinho.total());
    }
}
