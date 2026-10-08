# Lambdas

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Priorizar entregas expressas e filtrar destinos cobertos por um serviço de logística.

## Conceitos praticados

Interfaces funcionais `Predicate`, `Function` e `Comparator`; referências a métodos; composição com `and`; lambda para mapeamento.

## Decisão de implementação

Políticas curtas de filtro/ordenação permanecem próximas de quem as utiliza. `Comparator` determina primeiro prioridade expressa e depois distância.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 07-lambdas test
./mvnw -pl 07-lambdas exec:java -Dexec.mainClass=br.dev.estudos.lambdas.PoliticasDeEntrega
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

Lambdas não substituem classes de domínio complexas. Regras extensas merecem nomes e testes próprios.

## Exercícios de evolução

01. Extraia um Predicate para entregas fora da área.
02. Use `Comparator.comparingInt` em ordem inversa.
03. Compare legibilidade com classes anônimas.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
