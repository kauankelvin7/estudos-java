# Stream API

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Consolidar faturamento de vendas por categoria e identificar vendedores acima de uma meta.

## Conceitos praticados

Pipeline `stream` com `groupingBy`, `reducing`, `filter`, `map`, `sorted`, `toList`; função de agregação com BigDecimal.

## Decisão de implementação

As operações são declarativas e não modificam a lista de entrada. O agrupamento concentra a regra de soma em um `Collector`.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 08-stream-api test
./mvnw -pl 08-stream-api exec:java -Dexec.mainClass=br.dev.estudos.streams.RelatorioVendas
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

Streams não garantem benefício de performance em qualquer caso; evite paralelismo por padrão, especialmente com operações com efeitos colaterais.

## Exercícios de evolução

01. Adicione relatório de unidades por categoria.
02. Gere ranking por faturamento decrescente.
03. Compare implementação com um loop tradicional.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
