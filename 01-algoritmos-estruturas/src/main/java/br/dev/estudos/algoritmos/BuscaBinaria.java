package br.dev.estudos.algoritmos;

public final class BuscaBinaria {
    private BuscaBinaria() { }

    /** A lista precisa estar ordenada em ordem crescente. O retorno é -1 se não encontrar. */
    public static int encontrar(int[] ordenados, int alvo) {
        int esquerda = 0;
        int direita = ordenados.length - 1;
        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2; // evita overflow na soma dos índices
            if (ordenados[meio] == alvo) return meio;
            if (ordenados[meio] < alvo) esquerda = meio + 1;
            else direita = meio - 1;
        }
        return -1;
    }
}
