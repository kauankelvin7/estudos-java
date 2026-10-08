package br.dev.estudos.carrinho;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarrinhoTest {
    @Test void somaItensComPrecisao() {
        var carrinho = new Carrinho();
        carrinho.adicionar(new ItemCarrinho("01", "Livro", 2, new BigDecimal("19.90")));
        assertEquals(new BigDecimal("39.80"), carrinho.total());
        assertThrows(UnsupportedOperationException.class, () -> carrinho.itens().clear());
    }
    @Test void naoAceitaSkuDuplicado() {
        var carrinho = new Carrinho();
        var item = new ItemCarrinho("01", "Livro", 1, BigDecimal.ONE);
        carrinho.adicionar(item);
        assertThrows(IllegalArgumentException.class, () -> carrinho.adicionar(item));
    }
}
