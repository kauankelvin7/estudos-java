# Encapsulamento, herança, polimorfismo e abstração

[← Voltar ao sumário](../README.md) · [Roteiro](../docs/ROTEIRO.md)

## Problema

Simular saques e depósitos em conta corrente e poupança com restrições diferentes.

## Conceitos praticados

Abstração por classe `Conta`; implementação por herança de `ContaCorrente` e `ContaPoupanca`; polimorfismo em `Carteira`; estado privado.

## Decisão de implementação

`Conta.sacar` centraliza a validação e delega apenas o cálculo do limite disponível. O saldo só muda por métodos que mantêm o contrato.

## Organização

- `src/main/java`: regras de domínio e demonstração executável.
- `src/test/java`: testes de comportamento e casos de erro.
- `src/main/resources`: recursos usados em execução, quando aplicável.

## Executar

Na raiz do repositório, com JDK 21 e Maven Wrapper:

```bash
./mvnw -pl 06-pilares-poo test
./mvnw -pl 06-pilares-poo exec:java -Dexec.mainClass=br.dev.estudos.contas.Carteira
```

No Windows, troque `./mvnw` por `.\mvnw.cmd`.

## Cuidados e limites

Saldo negativo só é permitido dentro do cheque especial. Taxas e lançamentos contábeis não fazem parte do exemplo.

## Exercícios de evolução

01. Adicione tarifa ao saque da conta corrente.
02. Evite transações de valor zero e teste o erro.
03. Discuta quando substituir herança por estratégia com interfaces.

## Perguntas para revisão

- Por que essa abordagem foi escolhida em vez de uma alternativa mais simples?
- O que acontece com entradas inválidas e quais casos os testes cobrem?
- Qual alteração seria necessária para usar a solução em um sistema real?
