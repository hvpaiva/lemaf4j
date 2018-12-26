package br.ufla.lemaf.ti.lemaf4j.common;

/**
 * Erros comuns da aplicação e suas mensagems.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum Error {
    ARGUMENTO_INVALIDO("O argumento '%s' não é válido: '%d'"),
    ARGUMENTO_NULO("O argumento '%s' não pode ser nulo!"),
    ARGUMENTO_VAZIO("O argumento '%s' não pode ser vazio!"),
    ARGUMENTO_MAX_LENGTH("O tamanho máximo para o argumento '%s' é %d, mas foi: %f"),
    ARGUMENTO_MIN_LENGTH("O tamanho mínimo para o argumento '%s' é %d, mas foi: %f"),
    VALOR_MAX_LENGTH("O valor máximo para o argumento '%s' é %d, mas foi: %f"),
    VALOR_MIN_LENGTH("O valor mínimo para o argumento '%s' é %d, mas foi: %f"),
    MULTIPLE_EMAIL("O argumento 'emailAddress' não é um único endereço de email: '%s'"),
    INVALID_EMAIL("O argumento 'emailAddress' não é válido: %s");

    private String message;

    Error(String message) {
        this.message = message;
    }

    /**
     * Retorna a mensagem do erro.
     *
     * @return A mensagem
     */
    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return message();
    }
}
