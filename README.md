# LEMAF4J

Alguns processos são comuns à todas as aplicações Java do LEMAF.
Esta biblioteca visa padronizar e centralizar tais processos fornecendo classes utilitárias
e abstrações lógicas comuns e documentadas.

## Módulos

### LEMAF4J-VO

Módulo de Value Objects, contem implementações e utilitários comuns 
de VOs:

#### Packages:

- vo: Package contendo as classes VOs em si
- types: Annotations dos VOs para anotar e validar o modelo
- validations: Classes com validadores dos VOs
- converters: Conversores dos VOs para seus tipos básicos
- formatters: Formata/Desformata os VOs que possuem formatação padrão
- common: Package contendo utilitários para a biblioteca

### LEMAF4J-DDD

Módulo com implementações básicas para aplicar o Domain Driven Design.

*WIP*

### LEMAF4J-UTILS

Módulo com utilitários gerais padrões.

*WIP*

### LEMAF4J-UNIT

Módulo contendo classes utilitárias para testes unitários.

*WIP*