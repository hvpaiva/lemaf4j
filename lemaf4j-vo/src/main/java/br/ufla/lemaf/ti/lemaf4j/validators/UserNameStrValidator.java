package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.types.UserNameStr;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.common.Error;
import br.ufla.lemaf.ti.lemaf4j.common.ErrorMessageFactory;
import br.ufla.lemaf.ti.lemaf4j.vo.UserName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

/**
 * Validator do VO de {@link UserName}.
 * Também é usado para o types validator
 * de {@link UserNameStr}
 * UserName precisa ter:
 * - Entre 3-20 caractere
 * - Letras em caixa-baixa (a-z)
 * - Números (0-9)
 * - Hífens (-)
 * - Underscore (_)
 * - Não começar com hífem, underscore ou número
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class UserNameStrValidator implements ConstraintValidator<UserNameStr, String> {

    private static final Integer MAX_LENGTH = 20;
    private static final Integer MIN_LENGTH = 3;

    private static final Pattern PATTERN = Pattern.compile("[a-z][0-9a-z_\\-]*");

    @Override
    public final void initialize(final UserNameStr constraintAnnotation) {
    }

    @Override
    public final boolean isValid(final String value, final ConstraintValidatorContext context) {
        return isValid(value);
    }

    /**
     * Confere se dada string é um id de usuário esperado.
     *
     * @param value Valor a se checar
     * @return Retorna <code>true</code> se for um id válido
     *         de usuário, senão retornará <code>false</code>.
     *         Se o argumento for <code>null</code> retornará
     *         <code>false</code>
     */
    public static boolean isValid(final String value) {
        if ((value == null)
                || (value.isEmpty())
                || (value.length() < MIN_LENGTH)
                || (value.length() > MAX_LENGTH)) return false;

        return PATTERN.matcher(value).matches();
    }

    /**
     * Confere se o argumento é válido,
     * lançando uma exceção em caso negativo.
     *
     * @param name O nome do valor para a mensagem de erro
     * @param value Valor a se checar
     * @throws ConstraintViolationException
     *             O valor não é válido
     */
    public static void requireArgValid(@NotNull final String name,
                                @NotNull final String value) throws ConstraintViolationException {
        if (!isValid(value)) {
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.ARGUMENTO_INVALIDO, name, value)
            );
        }
    }
}
