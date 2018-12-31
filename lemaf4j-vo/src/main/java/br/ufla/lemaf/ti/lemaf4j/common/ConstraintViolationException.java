package br.ufla.lemaf.ti.lemaf4j.common;

/**
 * Exceção de validade.
 * Lançada quando se espera um comportamento
 * de um objeto, e o mesmo não é atendido.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class ConstraintViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construtor com mensagem de erro.
     *
     * @param message A mensagem
     */
    public ConstraintViolationException(final String message) {
        super(message);
    }

}
