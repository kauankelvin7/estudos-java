# Tratamento de exceções

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Ler arquivo simples de produtos e indicar com precisão onde a importação falhou.

## Conceitos praticados

`IOException` checked; `LinhaInvalidaException` unchecked com causa; `try-with-resources`; validação por linha; `Path` e UTF-8.

## Decisão de implementação

A camada de arquivo comunica falhas de IO ao chamador; falhas de conteúdo são enriquecidas com número de linha e preservam a causa original.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 09-tratamento-excecoes test
./mvnw -pl 09-tratamento-excecoes exec:java -Dexec.mainClass=br.dev.estudos.importacao.ImportadorProdutos
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

A demonstração requer caminho de arquivo como argumento (formato de cada linha: `sku;preco`). Um exemplo válido está em [`exemplos/produtos.csv`](exemplos/produtos.csv).

## Cuidados e limites

O formato `sku;preco` é uma simplificação didática: não aceita campos CSV entre aspas. Para CSV completo, use parser especializado.

## Exercícios de evolução

01. Valide SKUs repetidos no arquivo.
02. Acumule erros de múltiplas linhas e apresente resumo.
03. Diferencie tratamento de IOException e falha de regra de negócio.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
