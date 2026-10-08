# Banco de dados relacional

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Transferir estoque entre produtos com garantia de atomicidade.

## Conceitos praticados

SQL DDL e DML; modelo com restrições CHECK; JDBC; DataSource; PreparedStatement; ResultSet; transação, commit e rollback.

## Decisão de implementação

O método `transferirEstoque` usa uma transação única: se o destino não existir ou o estoque for insuficiente, a primeira operação é desfeita.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 11-banco-relacional test
./mvnw -pl 11-banco-relacional exec:java -Dexec.mainClass=br.dev.estudos.jdbc.Demonstracao
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

H2 em memória é adequado a demonstrações; bancos reais exigem migrações versionadas, índices adequados, controle de isolamento e testes de concorrência.

## Exercícios de evolução

01. Crie tabela movimento_estoque para auditoria.
02. Teste violação de chave primária.
03. Compare isolamento READ_COMMITTED com outro nível.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
