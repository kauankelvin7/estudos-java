package br.dev.estudos.fundamentos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CalculadoraEntrega {
    public enum Regiao { CAPITAL, INTERIOR, REMOTA }
    private static final BigDecimal FRETE_GRATIS = new BigDecimal("200.00");

    public BigDecimal calcular(BigDecimal subtotal, Regiao regiao, boolean clientePremium) {
        if (subtotal == null || subtotal.signum() < 0 || regiao == null)
            throw new IllegalArgumentException("Subtotal e região devem ser válidos");
        if (clientePremium || subtotal.compareTo(FRETE_GRATIS) >= 0)
            return BigDecimal.ZERO.setScale(2);

        // BigDecimal evita os erros de arredondamento de ponto flutuante em dinheiro.
        BigDecimal frete = switch (regiao) {
            case CAPITAL -> new BigDecimal("12.90");
            case INTERIOR -> new BigDecimal("22.50");
            case REMOTA -> new BigDecimal("39.90");
        };
        return frete.setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {
        var calculadora = new CalculadoraEntrega();
        System.out.println("Frete: R$ " + calculadora.calcular(new BigDecimal("149.90"), Regiao.INTERIOR, false));
    }
}
