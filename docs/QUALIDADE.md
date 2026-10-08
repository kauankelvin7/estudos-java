# Critérios de qualidade

## Padrões adotados

- Classes e interfaces em `PascalCase`, métodos e variáveis em `camelCase`, constantes em `UPPER_SNAKE_CASE`.
- Identificadores com vocabulário do domínio; evitar nomes genéricos como `Utils`, `Manager` e `Data` sem responsabilidade clara.
- Campos `private` e objetos imutáveis quando possível; preferir injeção por construtor nas bordas.
- Valores monetários com `BigDecimal`, nunca `double` para cálculos financeiros.
- Validação de entradas na fronteira da API **e** proteção das invariantes na regra de negócio.
- `PreparedStatement` para parâmetros SQL; transações com rollback explícito em cenários de múltiplas operações.
- `try-with-resources` para conexões, cursores e arquivos; não suprimir exceções silenciosamente.
- Comentários explicam decisões e armadilhas, não repetem a linha de código.
- Métodos com uma responsabilidade principal; sem acoplamento entre módulos.

## Testes

O Maven Surefire executa testes de unidade e testes de integração leves com H2. O banco relacional e Hibernate usam H2 em memória, o que elimina dependência de infraestrutura no CI. MongoDB precisa estar em execução para a demonstração interativa, mas a suíte de compilação/testes não depende de serviço externo.

## Verificação

```bash
./mvnw clean verify
./mvnw -pl 14-spring-boot spring-boot:run
```

## Revisão antes de contribuir

- `README` do módulo documenta como rodar e qual problema resolve.
- Casos de sucesso e erros relevantes têm teste.
- Nenhum segredo, diretório `target/` ou arquivo de banco local foi versionado.
- APIs públicas têm nomes precisos e mensagem de erro compreensível.
