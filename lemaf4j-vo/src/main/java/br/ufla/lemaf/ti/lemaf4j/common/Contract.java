package br.ufla.lemaf.ti.lemaf4j.common;

import br.ufla.lemaf.ti.lemaf4j.utils.Error;
import br.ufla.lemaf.ti.lemaf4j.utils.ErrorMessageFactory;

import javax.validation.constraints.NotNull;

/**
 * Classe utilitária para asserções em
 * objetos.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class Contract {

    /**
     * Construtor privado para evitar instanciação.
     */
    protected Contract() {
        throw new UnsupportedOperationException(
                Error.INTANCIAR_CLASSE_UTILITARIA.message()
        );
    }

    /**
     * Confere se o valor não é <code>null</code>.
     *
     * @param name  Nome do valor para a mensagem de erro
     * @param value Valor a se checar
     * @throws ConstraintViolationException O valor foi nulo
     */
    public static void requireArgNotNull(@NotNull final String name,
                                         final Object value) {
        if (value == null) {
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.ARGUMENTO_NULO, name)
            );
        }
    }

    /**
     * Confere se o valor não é <code>null</code> ou vazio.
     * Um espaço único é considerado um valor válido.
     * Checks if the value is not <code>null</code> or empty. A single space is considered a valid value.
     *
     * @param name  O nome do valor para a mensagem de erro
     * @param value Valor a se checar
     * @throws ConstraintViolationException O valor foi nulo ou vazio
     */
    public static void requireArgNotEmpty(@NotNull final String name,
                                          final String value) {
        requireArgNotNull(name, value);
        if (value.length() < 1) {
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.ARGUMENTO_VAZIO, name)
            );
        }
    }

    /**
     * Confere se o tamanho do valor não é maior que o máximo apresentado.
     *
     * @param name  O nome do valor para a mensagem de erro
     * @param value Valor a se checar
     * @param max   Tamanho máximo (inclusive)
     * @throws ConstraintViolationException O tamanho foi maior que <code>max</code>
     */
    public static void requireArgMaxLength(@NotNull final String name,
                                           @NotNull final String value,
                                           final int max) {
        if (value.length() > max) {
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(
                            Error.ARGUMENTO_MAX_LENGTH,
                            name,
                            max,
                            value.length()
                    )
            );
        }
    }

    /**
     * Confere se o tamanho do valor não é menor que o mínimo apresentado.
     *
     * @param name  O nome do valor para a mensagem de erro
     * @param value Valor a se checar
     * @param min   Tamanho mínimo (inclusive)
     * @throws ConstraintViolationException O tamanho foi menor que <code>min</code>
     */
    public static void requireArgMinLength(@NotNull final String name,
                                           @NotNull final String value,
                                           final int min) {
        if (value.length() < min) {
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(
                            Error.ARGUMENTO_MIN_LENGTH,
                            name,
                            min,
                            value.length()
                    )
            );
        }
    }

    /**
     * Confere se o valor não é maior que o máximo apresentado.
     *
     * @param name  O nome do valor para a mensagem de erro
     * @param value Valor a se checar
     * @param max   Valor máximo (inclusive)
     * @throws ConstraintViolationException O valor foi maior que <code>max</code>
     */
    public static void requireArgMax(@NotNull final String name,
                                     @NotNull final long value,
                                     final long max) throws ConstraintViolationException {
        if (value > max) {
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.VALOR_MAX_LENGTH, name, max, value)
            );
        }
    }

    /**
     * Confere se o valor não é menor que o mínimo apresentado.
     *
     * @param name  O nome do valor para a mensagem de erro
     * @param value Valor a se checar
     * @param min   Valor mínimo (inclusive)
     * @throws ConstraintViolationException O valor foi menor que <code>min</code>
     */
    public static void requireArgMin(@NotNull final String name,
                                     @NotNull final long value,
                                     final long min) throws ConstraintViolationException {
        if (value < min) {
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.VALOR_MIN_LENGTH, name, min, value)
            );
        }
    }

}
