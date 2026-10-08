package br.dev.estudos.algoritmos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.NoSuchElementException;

class EstruturasTest {
    @Test void prioridadeVenceChegadaMasEmpateMantemOrdem() {
        var fila = new FilaDeChamados();
        fila.adicionar("A", 1); fila.adicionar("B", 4); fila.adicionar("C", 4);
        assertEquals("B", fila.atender().protocolo());
        assertEquals("C", fila.atender().protocolo());
        assertEquals("A", fila.atender().protocolo());
        assertThrows(NoSuchElementException.class, fila::atender);
    }
    @Test void bfsEncontraMenosArestasOuNenhumCaminho() {
        var rede = new RedeDeRotas();
        rede.conectar("A", "B"); rede.conectar("B", "C"); rede.conectar("A", "C");
        assertEquals(List.of("A", "C"), rede.menorCaminho("A", "C"));
        assertEquals(List.of(), rede.menorCaminho("A", "Z"));
    }
    @Test void buscaBinariaRespeitaBordas() {
        assertEquals(0, BuscaBinaria.encontrar(new int[]{1, 4, 8}, 1));
        assertEquals(-1, BuscaBinaria.encontrar(new int[]{1, 4, 8}, 5));
        assertEquals(-1, BuscaBinaria.encontrar(new int[]{}, 5));
    }
}
