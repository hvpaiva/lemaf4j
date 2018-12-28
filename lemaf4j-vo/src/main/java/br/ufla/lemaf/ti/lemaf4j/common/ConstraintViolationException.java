package br.ufla.lemaf.ti.lemaf4j.common;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.Set;

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
     * Construtor com mensagem de erro e
     * constraint violations.
     *
     * @param message              A mensagem
     * @param constraintViolations Constraint violations.
     */
    ConstraintViolationException(final String message,
                                 final Set<ConstraintViolation<Object>> constraintViolations) {
        super(message);
        this.constraintViolations = constraintViolations;
    }

    /**
     * Retorna as constraint violations.
     *
     * @return Set imutável de constraint violations ou
     * Set vazio se não houver constraint violations.
     */
    public Set<ConstraintViolation<Object>> getConstraintViolations() {
        if (constraintViolations == null) return Collections.emptySet();

        return Collections.unmodifiableSet(constraintViolations);
    }

}
