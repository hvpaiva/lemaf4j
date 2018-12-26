package br.ufla.lemaf.ti.lemaf4j.common;

import javax.annotation.Nullable;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe utilitária para asserções em
 * objetos.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class Contract {

    private static Validator validator;

    /**
     * Injeta o validator para ser usado para o contract validation.
     * Este método não é thread safe. Portanto deve ser chamado
     * apenas uma vez por aplicação durante a inicialização.
     *
     * @param newValidator Adiciona o validator
     */
    public static void setValidator(final Validator newValidator) {
        validator = newValidator;
    }

    /**
     * Retorna o validador usado para validação do contrato.
     * Este método NÃO é thread-safe - Isso pode levar a uma
     * inicialização simultânea do validador se ainda não estiver definido.
     *
     * @return instância atual - Se o validator não estiver criado,
     * uma instância padrão será criada
     */
    public static Validator getValidator() {
        if (validator == null) {
            validator = Validation
                    .buildDefaultValidatorFactory()
                    .getValidator();
        }
        return validator;
    }

    /**
     * Construtor privado para evitar instanciação.
     */
    private Contract() {
        throw new UnsupportedOperationException(
                "Não se deve criar uma instância de uma classe utilitária!"
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
                                         final Object value) throws ConstraintViolationException {
        if (value == null) {
            throw new ConstraintViolationException(
                    "O argumento '" + name + "' não pode ser nulo."
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
    public static void requireArgNotEmpty(@NotNull final String name, final String value) throws ConstraintViolationException {
        requireArgNotNull(name, value);
        if (value.length() < 1) {
            throw new ConstraintViolationException(
                    "O argumento '" + name + "' não pode ser vazio!"
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
                                           final int max) throws ConstraintViolationException {
        if (value.length() > max) {
            throw new ConstraintViolationException(
                    "O tamanho máximo para o argumento '"
                            + name + "' é "
                            + max + ", mas foi: "
                            + value.length()
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
                                           final int min) throws ConstraintViolationException {
        if (value.length() < min) {
            throw new ConstraintViolationException(
                    "O tamanho mínimo para o argumento '"
                            + name + "' é "
                            + min + ", mas foi: "
                            + value.length()
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
                    "O valor máximo para o argumento '"
                            + name + "' é "
                            + max + ", mas foi: "
                            + value
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
                    "O valor mínimo para o argumento '"
                            + name + "' é "
                            + min + ", mas foi: "
                            + value
            );
        }
    }

    /**
     * Confere se dado valor é válido.
     *
     * @param validator Validator a se usar
     * @param value     Valor a se checar
     * @param groups    Grupo ou lista de grupos alvos para validação
     *                  (o padrão é {@link Default})
     * @throws ConstraintViolationException O valor é inválido
     */
    public static void requireValid(@NotNull final Validator validator,
                                    @NotNull final Object value,
                                    @Nullable final Class<?>... groups) throws ConstraintViolationException {

        final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(value);
        if (constraintViolations.size() > 0) {
            final StringBuffer sb = new StringBuffer();
            for (final ConstraintViolation<Object> constraintViolation : constraintViolations) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(
                        "[" + constraintViolation.getPropertyPath()
                        + "] " + constraintViolation.getMessage() + " {"
                        + constraintViolation.getInvalidValue() + "}"
                );
            }
            throw new ConstraintViolationException(sb.toString(), constraintViolations);
        }

    }

    /**
     * Checa se dado valor é válido usando o validator padrão.
     *
     * @param value  Valor a se checar
     * @param groups Grupo ou lista de grupos alvos para validação
     *               (o padrão é {@link Default})
     * @throws ConstraintViolationException O valor é inválido
     */
    public static void requireValid(@NotNull final Object value,
                                    @Nullable final Class<?>... groups) throws ConstraintViolationException {
        requireValid(getValidator(), value, groups);
    }

    /**
     * Valida dado objeto.
     *
     * @param validator Validator a se usar
     * @param value     Valor a se validar
     * @param groups    Grupo ou lista de grupos alvos para validação
     *                  (o padrão é {@link Default})
     * @param <TYPE>    Tipo do objeto validado
     * @return Lista de constraint violations
     */
    @NotNull
    public static <TYPE> Set<ConstraintViolation<TYPE>> validate(@NotNull final Validator validator,
                                                                 @Nullable final TYPE value,
                                                                 @Nullable final Class<?>... groups) {
        if (value == null) {
            return new HashSet<ConstraintViolation<TYPE>>();
        }
        return validator.validate(value, groups);
    }

    /**
     * Valida dado objeto usando o validator padrão.
     *
     * @param value     Valor a se validar
     * @param groups    Grupo ou lista de grupos alvos para validação
     *                  (o padrão é {@link Default})
     * @param <TYPE>    Tipo do objeto validado
     * @return Lista de constraint violations
     */
    @NotNull
    public static <TYPE> Set<ConstraintViolation<TYPE>> validate(@Nullable final TYPE value,
                                                                 @Nullable final Class<?>... groups) {
        return validate(getValidator(), value, groups);
    }

}
