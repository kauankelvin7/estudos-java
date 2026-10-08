# Estruturas de controle

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Pontuar compras de clientes por mês e determinar o nível do programa de fidelidade.

## Conceitos praticados

Laço `for`, `continue`, validações com `if`, retornos antecipados e `switch` moderno.

## Decisão de implementação

O cálculo percorre o histórico uma única vez: tempo O(n) e memória O(1).

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 03-estruturas-controle test
./mvnw -pl 03-estruturas-controle exec:java -Dexec.mainClass=br.dev.estudos.controle.PoliticaDePontuacao
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

Históricos negativos não fazem sentido no contrato deste exemplo; são rejeitados em vez de corrigidos silenciosamente.

## Exercícios de evolução

01. Exija ao menos três meses de histórico.
02. Proponha uma regra de bônus trimestral.
03. Compare uma versão usando `while` com a implementação atual.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
