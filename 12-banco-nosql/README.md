# Banco de dados não relacional (NoSQL)

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Armazenar pedidos em MongoDB com itens incorporados ao documento.

## Conceitos praticados

Documento agregado; coleções; BSON; índice único; consulta por filtro; update atômico; representação monetária em centavos.

## Decisão de implementação

Itens ficam embutidos porque são consultados com o pedido. O índice de `codigo` evita duplicidade e o update altera o status diretamente no documento.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 12-banco-nosql test
./mvnw -pl 12-banco-nosql exec:java -Dexec.mainClass=br.dev.estudos.mongo.Demonstracao
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

> **MongoDB local:** `docker compose -f 12-banco-nosql/compose.yaml up -d`, depois execute a demonstração. A conexão padrão é `mongodb://127.0.0.1:27017`; use `MONGODB_URI` se precisar alterar. A operação cria dados de exemplo na coleção `estudos_java.pedidos`.

## Cuidados e limites

O MongoDB deve estar rodando; o teste de modelo não exige servidor. O valor em centavos exige escala de 2 casas; para sistemas reais, explicite a política de arredondamento e transições de status válidas.

## Exercícios de evolução

01. Restrinja transições ABERTO -> PAGO e ABERTO -> CANCELADO.
02. Crie índices para consultas por cliente e data.
03. Implemente testes de integração com Mongo local separado do CI básico.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
