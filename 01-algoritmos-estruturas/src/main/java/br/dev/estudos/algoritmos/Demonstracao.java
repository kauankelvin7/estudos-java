package br.dev.estudos.algoritmos;

public final class Demonstracao {
    public static void main(String[] args) {
        var fila = new FilaDeChamados();
        fila.adicionar("SUP-204", 2);
        fila.adicionar("SUP-205", 5);
        System.out.println("Primeiro atendimento: " + fila.atender().protocolo());

        var rede = new RedeDeRotas();
        rede.conectar("Centro", "Universidade");
        rede.conectar("Universidade", "Hospital");
        rede.conectar("Centro", "Terminal");
        System.out.println("Rota: " + rede.menorCaminho("Centro", "Hospital"));
        System.out.println("Posição encontrada: " + BuscaBinaria.encontrar(new int[]{3, 8, 12, 19}, 12));
    }
}
