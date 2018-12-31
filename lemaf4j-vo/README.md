# LEMAF4j-VO

O módulo lemaf4j-vo oferece soluções práticas para se trabalhar com Value Objects.

Value Objects são objetos imutáveis cuja principal característica é a de serem definidos por suas propriedades. Isto é, um VO, por definição, é igual à outro VO quando o valor de suas propriedades são iguais, independentemente de serem de instâncias diferentes.

Por exemplo, um CPF é um Value Object. Um objeto CPF deve ser imutável, já que se permitirmos que um CPF seja modificado, ele na verdade será outro CPF. Outro ponto é que o CPF que contenha a mesma propriedade numeroCPF será igual à outro objeto CPF de outra instância, mas com mesmo valor de numeroCPF, já que o que define o CPF é seu valor e não sua instância ou qualquer outro identificador. 

## Por que utilizar um Value Object?

Como um VO sempre será uma representação de uma propriedade, pode ser intuitivo apenas declará-lo em seu tipo primitivo. Isto é, ao invés de uma classe CPF, apenas criamos uma String cpf.

No entanto, assim, perderemos algumas vantagens do VO:

- Comportamento: Um VO pode ter métodos explícitos criando um conceito, isto é, um CPF pode ter métodos como getNumeroFormatado() ou getNumeroDesformatado(), desta forma a responsabilidade do comportamento do CPF fica encapsulada à classe CPF, evitando a programação procedural com utils.

- Validação: Um VO pode ter regras para existir. Um CPF possui regras para ser considerado válido, e tais regras podem ser assumidas no construtor do VO de CPF para que o mesmo só seja instanciado de forma válida. Logo, apenas com a existência da instância do VO de CPF já o assumimos válido, evitando assim a necessidade de validação indireta por meio de utils que aconteceria se fosse uma String.

- Imutabilidade: Ainda que os wrappers dos tipos primitivos sejam imutáveis, eles são reatribuiveis. Nada impediria de você retribuir a qualquer momento o valor de seu CPF. Modificando o estado do objeto. Um VO não pode ser retribuído.

- Conceito: Em um modelo de domínio, uma classe Ponto, por exemplo, é bem mais representativa que dois Integers x e y.

- Contexto: Um inteiro representando um PIN pode sofrer operações aritméticas que não fariam sentido pro contexto de um PIN.

## Objetivos do módulo

O módulo Lemaf4j-vo possui dois objetivos primários:

- Fornecer implementações dos Value Objects mais comuns.

- Fornecer uma estrutura base para criação de Value Objects personalizados.

## Os Value Objects

- CPF
- CNPJ
- CEP
- UserName
- Email
- NumeroTelefone
- NumeroCAR
- CodigoAssentamento

Todos os VOs acompanham classes auxiliares:
- Validators, que são responsáveis por assegurar a validade da representação primitiva do VO;
- Converters que ajudam o mecanismo de persistência à converter o VO para seu tipo primitivo e vice-versa, e;
- Formatters (quando é o caso) que formatam ou desformatam o valor para os padrões estabelecidos.

Obs.: Lembrando que o próprio VO possui implementado tais métodos, sendo tais classes auxiliares apenas um bônus.

## Personalizando

Em muitos casos, será necessário criar Value Objects próprios. E para isso a biblioteca disponibiliza facilitadores.

### Abstract Value Objects