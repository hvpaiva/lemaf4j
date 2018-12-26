package br.ufla.lemaf.ti.lemaf4j.common;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.Set;

/**
 * O contrato checado foi violado.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class ConstraintViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Set<ConstraintViolation<Object>> constraintViolations;

    /**
     * Construtor com mensagem de erro.
     *
     * @param message A mensagem
     */
    public ConstraintViolationException(final String message) {
        super(message);
        this.constraintViolations = null;
    }

    /**
     * Construtor com mensagem de erro e constraint violations.
     *
     * @param message A mensagem
     * @param constraintViolations Constraint violations.
     */
    ConstraintViolationException(final String message,
                                        final Set<ConstraintViolation<Object>> constraintViolations) {
        super(message);
        this.constraintViolations = constraintViolations;
    }

    /**
     * Returna as constraint violations.
     *
     * @return Set imutável de constraint violations ou
     *         <code>null</code> se apenas uma mensagem
     *         é acessível.
     */
    public final Set<ConstraintViolation<Object>> getConstraintViolations() {
        if (constraintViolations == null) {
            return null;
        }
        return Collections.unmodifiableSet(constraintViolations);
    }

}