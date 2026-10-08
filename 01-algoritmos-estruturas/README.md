# Algoritmos e estruturas de dados

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Organizar chamados de suporte por urgência e descobrir rotas curtas entre estações.

## Conceitos praticados

PriorityQueue com Comparator; grafo por lista de adjacências (Map/Set); busca binária iterativa.

## Decisão de implementação

A fila remove em O(log n), consulta de tamanho O(1); BFS visita O(V+E) e usa O(V) memória; busca binária O(log n) e O(1) espaço.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 01-algoritmos-estruturas test
./mvnw -pl 01-algoritmos-estruturas exec:java -Dexec.mainClass=br.dev.estudos.algoritmos.Demonstracao
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

Um heap não mantém os elementos globalmente ordenados; o comparador de chamados desempata pela chegada. BFS só fornece menor número de conexões em grafos **sem peso**. A busca binária exige array já ordenado.

## Exercícios de evolução

01. Implemente a remoção de um chamado pelo protocolo.
02. Modifique a rede para rotas dirigidas e compare resultados.
03. Adicione testes para grafos desconexos e origem igual ao destino.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
