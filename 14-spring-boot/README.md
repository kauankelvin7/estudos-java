# Spring Boot

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Criar uma API para reserva de salas, sem permitir agendamentos conflitantes.

## Conceitos praticados

REST Controller, request/response DTOs, Bean Validation, Service, Spring Data JPA, H2, @Transactional, exception handler e MockMvc.

## Decisão de implementação

A API retorna 201 com Location na criação, 409 em conflito de horário, 400 para entrada inválida, 404 para item inexistente e 204 na exclusão. A regra usa intervalos semiabertos [início, fim).

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 14-spring-boot test
./mvnw -pl 14-spring-boot spring-boot:run
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

### Contrato HTTP

| Método | Recurso | Resultado esperado |
|---|---|---|
| POST | `/api/reservas` | 201 e `Location` |
| GET | `/api/reservas` | 200, lista |
| GET | `/api/reservas/{id}` | 200 ou 404 |
| DELETE | `/api/reservas/{id}` | 204 ou 404 |

```bash
curl -X POST http://localhost:8080/api/reservas \
  -H 'Content-Type: application/json' \
  -d '{"sala":"Sala 2","responsavel":"Marina","inicio":"2030-05-02T09:00:00","fim":"2030-05-02T10:00:00"}'
curl http://localhost:8080/api/reservas
```

**Nota sobre concorrência:** o exemplo detecta sobreposições em operação sequencial, mas não garante exclusão mútua entre transações simultâneas. Não use como agenda de produção sem resolver esse ponto.

## Cuidados e limites

A verificação de conflito é feita antes do `save`; chamadas simultâneas podem sofrer race condition. Uma versão de produção precisa lock/controle transacional e autenticação, migrações e configuração segura.

## Exercícios de evolução

01. Adicione paginação com Pageable.
02. Trate concorrência com lock explícito e teste concorrente.
03. Gere contrato OpenAPI e substitua H2 por PostgreSQL usando migração.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
