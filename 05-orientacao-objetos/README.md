# Orientação a objetos

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Administrar acervo, empréstimos e devoluções sem amarrar o controle de empréstimos a uma tecnologia de armazenamento.

## Conceitos praticados

Objetos de domínio (`Livro`, `Emprestimo`); abstração por interface (`Catalogo`); composição (`Biblioteca` usa `Catalogo`); implementação em memória.

## Decisão de implementação

Uma interface pequena permite trocar o catálogo para JDBC ou Mongo sem alterar as regras da biblioteca. A entidade de empréstimo mantém a data prevista.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 05-orientacao-objetos test
./mvnw -pl 05-orientacao-objetos exec:java -Dexec.mainClass=br.dev.estudos.biblioteca.Biblioteca
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

A política de empréstimos considera um exemplar por ISBN e não registra histórico. Evite usar herança onde composição expressa melhor a relação.

## Exercícios de evolução

01. Permita três exemplares do mesmo ISBN.
02. Adicione prazo configurável por categoria de leitor.
03. Substitua o catálogo em memória por um fake nos testes.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
