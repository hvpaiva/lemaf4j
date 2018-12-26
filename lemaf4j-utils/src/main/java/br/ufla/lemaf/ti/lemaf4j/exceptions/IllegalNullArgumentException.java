package br.ufla.lemaf.ti.lemaf4j.exceptions;

/**
 * Lançado para indicar que o método llançou um
 * ilegal <code>null</code> argumento.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class IllegalNullArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    /** Nome do argumento que causou a exceção. */
    private final String argument;

    /**
     * Construtor com o nome do argumento.
     *
     * @param argument Nome do argumento que causou a exceção.
     */
    public IllegalNullArgumentException(final String argument) {
        super("The argument '" + argument + "' cannot be null");
        this.argument = argument;
    }

    /**
     * Retorna o nome do argumento que causou a exceção.
     *
     * @return O nome do argumento
     */
    public final String getArgument() {
        return argument;
    }

}
