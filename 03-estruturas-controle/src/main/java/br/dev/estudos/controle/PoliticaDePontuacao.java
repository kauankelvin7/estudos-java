package br.dev.estudos.controle;

public final class PoliticaDePontuacao {
    /** Interrompe a leitura no primeiro mês inválido; não contabiliza meses posteriores. */
    public int calcularPontos(int[] comprasMensais) {
        if (comprasMensais == null) throw new IllegalArgumentException("Histórico ausente");
        int pontos = 0;
        for (int compras : comprasMensais) {
            if (compras < 0) throw new IllegalArgumentException("Compras não podem ser negativas");
            if (compras == 0) continue;
            pontos += switch (compras) {
                case 1, 2 -> 5;
                case 3, 4 -> 12;
                default -> 20;
            };
        }
        return pontos;
    }

    public String faixa(int pontos) {
        if (pontos < 0) throw new IllegalArgumentException("Pontuação inválida");
        if (pontos >= 100) return "ouro";
        if (pontos >= 40) return "prata";
        return "bronze";
    }

    public static void main(String[] args) {
        var politica = new PoliticaDePontuacao();
        int pontos = politica.calcularPontos(new int[]{1, 0, 4, 7});
        System.out.println(pontos + " pontos: " + politica.faixa(pontos));
    }
}
