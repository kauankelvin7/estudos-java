# JavaFX

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Criar um quadro de tarefas desktop com formulário, listagem e conclusão de itens.

## Conceitos praticados

JavaFX Application, Stage, Scene, VBox/HBox, eventos, ListView, CSS e serviço Java puro.

## Decisão de implementação

A UI conhece `QuadroTarefas`; o serviço não conhece `javafx.*`. Assim, a regra pode ser testada sem abrir janela e a tela pode evoluir separadamente.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 10-javafx test
./mvnw -pl 10-javafx javafx:run
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

É necessária uma sessão gráfica local; não execute `javafx:run` em CI headless. O exemplo guarda tarefas apenas em memória.

## Exercícios de evolução

01. Permita excluir tarefas concluídas.
02. Adicione filtro de status no ListView.
03. Persista os itens em arquivo JSON mantendo a UI desacoplada.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
