package br.ufla.lemaf.ti.lemaf4j.exceptions;

/**
 * @author Highlander Paiva
 * @since 1.0
 */
public final class LockingFailedException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construtor com mensagem.
     *
     * @param message Mensagem de erro
     */
    public LockingFailedException(final String message) {
        super(message);
    }

    /**
     * Construtor com mensagem.
     *
     * @param message Mensagem de erro
     * @param cause Erro original
     */
    public LockingFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
