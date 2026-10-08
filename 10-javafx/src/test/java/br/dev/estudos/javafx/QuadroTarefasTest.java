package br.dev.estudos.javafx;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuadroTarefasTest {
    @Test void adicionaEConcluiSemAbrirJanela() {
        var quadro = new QuadroTarefas();
        var tarefa = quadro.adicionar("Revisar concorrência");
        assertFalse(tarefa.concluida());
        quadro.concluir(tarefa.id());
        assertTrue(quadro.listar().getFirst().concluida());
        assertThrows(IllegalArgumentException.class, () -> quadro.adicionar(" "));
    }
}
