# JPA (Hibernate)

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Persistir clientes e assinaturas, relacionando entidades sem escrever SQL manualmente.

## Conceitos praticados

Anotações `@Entity`, `@Id`, `@ManyToOne`, `@JoinColumn`; EntityManagerFactory; contexto de persistência; JPQL e transação.

## Decisão de implementação

`Assinatura` referencia `Cliente` com `FetchType.LAZY`, e a consulta usa `join fetch` para acessar o cliente sem depender de sessão aberta depois.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 13-jpa-hibernate test
./mvnw -pl 13-jpa-hibernate exec:java -Dexec.mainClass=br.dev.estudos.jpa.Demonstracao
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

`create-drop` é apenas para desenvolvimento/testes. Em produção, prefira migrações e configuração externa de conexão; avalie problemas N+1 e lazy loading.

## Exercícios de evolução

01. Consulte assinaturas por e-mail.
02. Adicione enum de status com `@Enumerated`.
03. Mostre um teste que falhe ao violar UNIQUE do e-mail.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
