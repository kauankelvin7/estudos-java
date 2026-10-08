# Classes, objetos e métodos

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Montar um carrinho de compras com itens, quantidades, preços e proteção contra SKU repetido.

## Conceitos praticados

Classe de responsabilidade definida; `record` imutável; construtores compactos; métodos de instância; encapsulamento da coleção.

## Decisão de implementação

A classe `Carrinho` oferece operações de negócio e devolve uma cópia imutável com `List.copyOf`. O registro `ItemCarrinho` valida seus próprios campos.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 04-classes-objetos-metodos test
./mvnw -pl 04-classes-objetos-metodos exec:java -Dexec.mainClass=br.dev.estudos.carrinho.Carrinho
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

Uma solução de produção trataria atualização de quantidade, descontos, concorrência e persistência; aqui a ênfase é o ciclo de vida dos objetos.

## Exercícios de evolução

01. Implemente alterarQuantidade(sku, novaQuantidade).
02. Adicione um cupom sem modificar a estrutura interna de ItemCarrinho.
03. Teste uma tentativa de quantidade zero.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
