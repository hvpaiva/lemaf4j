package br.ufla.lemaf.ti.lemaf4j.exceptions;

public final class InvokeMethodFailedException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with error message.
     *
     * @param message
     *            Message.
     */
    public InvokeMethodFailedException(final String message) {
        super(message);
    }

    /**
     * Constructor with error message and cause.
     *
     * @param message
     *            Message.
     * @param cause
     *            Exception that caused the failure.
     */
    public InvokeMethodFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
