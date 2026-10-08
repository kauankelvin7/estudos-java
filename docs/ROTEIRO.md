# Roteiro de estudos

## Trilha sugerida

| Fase | Módulos | Meta verificável |
|---|---|---|
| Base | 01–03 | Escrever soluções com tipos, condicionais, loops e estruturas de dados. |
| Modelagem | 04–06 | Projetar objetos que garantem suas próprias regras e contratos. |
| Java moderno | 07–09 | Transformar coleções, compor funções e lidar com falhas previsíveis. |
| Interface e persistência | 10–13 | Separar interface, serviço, consultas SQL e mapeamento ORM. |
| Aplicação web | 14 | Criar e testar endpoints HTTP com validação e persistência. |

## Rotina para cada módulo

1. Leia `README.md` e execute a demonstração.
2. Explique o domínio em voz alta: quais entradas, saídas e invariantes?
3. Leia os testes e escreva um teste de borda adicional.
4. Resolva os desafios propostos **antes** de consultar a implementação existente.
5. Registre no Git o que mudou e qual comportamento foi comprovado.

## Checklist de domínio do assunto

- [ ] Consigo executar o exemplo sem copiar saídas prontas.
- [ ] Entendo por que a classe ou estrutura foi escolhida.
- [ ] Sei descrever o principal caso de falha.
- [ ] Sou capaz de estender o comportamento mantendo os testes verdes.

## Projeto final sugerido

Evolua a API de reservas do módulo 14 com paginação, controle de concorrência e migrações de banco. Avalie quando JDBC é preferível a ORM e justifique a escolha em um ADR (Architecture Decision Record).
