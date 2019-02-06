package br.ufla.lemaf.ti.lemaf4j.common;

import br.ufla.lemaf.ti.lemaf4j.common.errors.Error;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.ErrorType;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.MessageProducer;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.SimpleMessageProducer;

import javax.validation.constraints.NotNull;

/**
 * Classe utilitária para asserções em
 * objetos.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class Contract {

    private static MessageProducer messageProducer;

    /**
     * Construtor privado para evitar instanciação.
     */
    private Contract() { }

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
                    constructErrorMessage(Error.ARGUMENTO_NULO, name)
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
                    constructErrorMessage(Error.ARGUMENTO_VAZIO, name)
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
                    constructErrorMessage(
                            Error.TAMANHO_MAXIMO_ATINGIDO,
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
                    constructErrorMessage(
                            Error.TAMANHO_MINIMO_NAO_ALCANCADO,
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
                                     final long max) {
        if (value > max) {
            throw new ConstraintViolationException(
                    constructErrorMessage(Error.VALOR_MAXIMO_ATINGIDO, name, max, value)
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
                                     final long min) {
        if (value < min) {
            throw new ConstraintViolationException(
                    constructErrorMessage(Error.VALOR_MINIMO_NAO_ALCANCADO, name, min, value)
            );
        }
    }

    /**
     * Adiciona um MensageProducer personalizado.
     *
     * @param producer O MessageProducer
     */
    public static void setMessageProducer(MessageProducer producer) {
        messageProducer = producer;
    }

    /**
     * Remove MensageProducer personalizado.
     */
    public static void removeMessageProducer() {
        messageProducer = null;
    }

    /**
     * Constrói as mensagens de Erro.
     * Caso o message producer seja nulo, ele criará o
     * SimpleMessageProducer.
     *
     * @param error O tipo de erro
     * @param args  Os argumentos
     * @return A String representando o erro
     */
    private static String constructErrorMessage(ErrorType error, Object... args) {
        if (messageProducer == null)
            return new SimpleMessageProducer().messageOf(error).message();

        return messageProducer.messageOf(error, args).message();
    }

}
