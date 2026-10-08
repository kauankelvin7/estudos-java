# Fundamentos da linguagem Java

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Calcular valor de frete a partir de região, subtotal e benefício Premium.

## Conceitos praticados

Variáveis tipadas; enum; constantes; operadores; métodos; BigDecimal e comparação por compareTo; switch expression.

## Decisão de implementação

`BigDecimal` com construtor de string evita a imprecisão introduzida por valores decimais `double`. `compareTo` avalia valor numérico, independentemente da escala.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 02-fundamentos-java test
./mvnw -pl 02-fundamentos-java exec:java -Dexec.mainClass=br.dev.estudos.fundamentos.CalculadoraEntrega
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

Não foi construída aplicação web: o foco está na linguagem e na preservação de precisão monetária.

## Exercícios de evolução

01. Adicione desconto para clientes recorrentes.
02. Formate a saída com `NumberFormat` pt-BR.
03. Teste a fronteira em R$ 199,99 e R$ 200,00.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
