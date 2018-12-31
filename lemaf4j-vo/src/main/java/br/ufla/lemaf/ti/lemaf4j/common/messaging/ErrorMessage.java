package br.ufla.lemaf.ti.lemaf4j.common.messaging;

/**
 * Implementação simplificada.
 * Classe imutável que armazena a mensagem
 * da validação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class ErrorMessage implements ValidationMessage {

    private final String message;

    /**
     * Construtor padrão de ErrorMessage.
     *
     * @param message A mensagem de validação
     */
    public ErrorMessage(String message) {
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return message();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String message() {
        return message;
    }
}
