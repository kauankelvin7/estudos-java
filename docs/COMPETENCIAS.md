# Mapa de competências e evidências

Este documento relaciona o assunto estudado ao **código que o demonstra**. É um guia de revisão técnica, não uma declaração de experiência profissional em produção.

| Competência | Evidência no repositório | Decisão que você deve conseguir explicar |
|---|---|---|
| Algoritmos e estruturas de dados | `01-algoritmos-estruturas`: `FilaDeChamados`, `RedeDeRotas`, `BuscaBinaria` | Heap para prioridade; BFS para rota sem peso; requisito de ordenação para busca binária. |
| Java essencial | `02-fundamentos-java`: `CalculadoraEntrega` | Por que dinheiro pede `BigDecimal`, e quando usar `enum`. |
| Controle de fluxo | `03-estruturas-controle`: `PoliticaDePontuacao` | Quando fazer retornos antecipados e por que validar antes de calcular. |
| Classes e métodos | `04-classes-objetos-metodos`: `Carrinho`, `ItemCarrinho` | Diferença entre expor coleção e retornar cópia imutável. |
| Orientação a objetos | `05-orientacao-objetos`: `Catalogo`, `Biblioteca` | Por que usar colaboração e interface antes de criar heranças. |
| Pilares de POO | `06-pilares-poo`: `Conta` e subclasses | Como proteger saldo e variar regras de saque por polimorfismo. |
| Lambdas | `07-lambdas`: `PoliticasDeEntrega` | `Predicate`, `Comparator` e `Function` em situações distintas. |
| Stream API | `08-stream-api`: `RelatorioVendas` | Diferença entre transformação, filtro e redução; efeitos de imutabilidade. |
| Exceções | `09-tratamento-excecoes`: `ImportadorProdutos` | Falha de IO versus dado inválido, contexto do erro e fechamento de recursos. |
| JavaFX | `10-javafx`: `TarefasApp`, `QuadroTarefas` | Como testar regra sem inicializar interface gráfica. |
| SQL/JDBC | `11-banco-relacional`: `ProdutoRepository` | Atomicidade, restrições do banco, SQL parametrizado e rollback. |
| NoSQL | `12-banco-nosql`: `MongoPedidoRepository` | Quando embutir itens e como documentar o agregado. |
| JPA/Hibernate | `13-jpa-hibernate`: entidades e `AssinaturaService` | Contexto de persistência, lazy loading, `join fetch` e transação. |
| Spring Boot | `14-spring-boot`: controller, service, repository, DTO e testes | Separação de responsabilidades, validação, status HTTP e limitação de concorrência. |

## Como demonstrar o trabalho sem exagerar

Mostre o teste que reprova uma entrada inválida antes de mostrar o método que a corrige. Explique o motivo da modelagem, a escolha da estrutura de dados e uma alternativa viável. Não afirme que estes exemplos estão prontos para produção; destaque precisamente quais ajustes seriam necessários.

## Exercício de apresentação (10–15 minutos)

1. Explique a estrutura Maven e rode um teste isolado.
2. Mostre a transferência de estoque do JDBC e o teste de rollback.
3. Compare o modelo relacional de assinaturas com o documento de pedido do MongoDB.
4. Execute a API de reservas, envie um POST válido e outro conflitante.
5. Encerre com a melhoria de concorrência necessária na API e como pretende testá-la.
