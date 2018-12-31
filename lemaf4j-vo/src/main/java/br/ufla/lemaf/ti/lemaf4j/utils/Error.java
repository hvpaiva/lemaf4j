package br.ufla.lemaf.ti.lemaf4j.utils;

import br.ufla.lemaf.ti.lemaf4j.common.messaging.ValidationMessage;

/**
 * Erros comuns da aplicação e suas mensagems.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum Error implements ValidationMessage {
    ARGUMENTO_INVALIDO("O argumento '%s' não é válido: '%s'"),
    ARGUMENTO_NULO("O argumento '%s' não pode ser nulo!"),
    ARGUMENTO_VAZIO("O argumento '%s' não pode ser vazio!"),
    ARGUMENTO_MAX_LENGTH("O tamanho máximo para o argumento '%s' é %s, mas foi: %s"),
    ARGUMENTO_MIN_LENGTH("O tamanho mínimo para o argumento '%s' é %s, mas foi: %s"),
    VALOR_MAX_LENGTH("O valor máximo para o argumento '%s' é %s, mas foi: %s"),
    VALOR_MIN_LENGTH("O valor mínimo para o argumento '%s' é %s, mas foi: %s"),
    MULTIPLE_EMAIL("O argumento 'emailAddress' não é um único endereço de email: '%s'"),
    INVALID_EMAIL("O argumento 'emailAddress' não é válido: %s"),
    NAO_FORMATADO("O valor não está propriamente formatado."),
    INTANCIAR_CLASSE_UTILITARIA("Não se deve criar uma instância de uma classe utilitária!"),
    INVALID_CPF_FORMAT("O CPF não possui formatos válidos!"),
    INVALID_CPF_DIGITS("O CPF não possui dígitos válidos!"),
    REPEATED_CPF_DIGITS("Um CPF não pode ser composto de dígitos repetidos!"),
    INVALID_CPF_CHECK_DIGITS("Os dígitos verificadores do CPF não batem!");

    private String message;

    /**
     * Construtor padrão do
     * enum de erros.
     *
     * @param message A mensagem do erro
     */
    Error(String message) {
        this.message = message;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String message() {
        return message;
    }
}
