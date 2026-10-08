package br.dev.estudos.biblioteca;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {
    @Test void controlaDisponibilidadeEDevolucao() {
        var biblioteca = new Biblioteca(new CatalogoEmMemoria(List.of(new Livro("123", "Java"))));
        var hoje = LocalDate.of(2026, 10, 8);
        assertEquals(hoje.plusDays(14), biblioteca.emprestar("123", "Ana", hoje).devolucaoPrevista());
        assertThrows(IllegalStateException.class, () -> biblioteca.emprestar("123", "Bia", hoje));
        biblioteca.devolver("123");
        assertDoesNotThrow(() -> biblioteca.emprestar("123", "Bia", hoje));
    }
}
