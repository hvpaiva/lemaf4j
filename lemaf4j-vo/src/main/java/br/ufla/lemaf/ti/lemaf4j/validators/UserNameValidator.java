package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.ValueObjectValidator;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.utils.Error;
import br.ufla.lemaf.ti.lemaf4j.utils.ErrorMessageFactory;

import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

/**
 * Validator do VO de UserName.
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
public final class UserNameValidator implements ValueObjectValidator<String> {

    private static final Integer MAX_LENGTH = 20;
    private static final Integer MIN_LENGTH = 3;

    private static final Pattern PATTERN = Pattern.compile("[a-z][0-9a-z_\\-]*");

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String value) {
        if (value == null
                || value.isEmpty()
                || value.length() < MIN_LENGTH
                || value.length() > MAX_LENGTH) return false;

        return PATTERN.matcher(value.toLowerCase()).matches();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assertValid(@NotNull final String name,
                            @NotNull final String value) {
        if (!isValid(value))
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.ARGUMENTO_INVALIDO, name, value)
            );
    }

    /**
     * Confere so o valor do Username é válido, lançando exceção
     * {@link ConstraintViolationException} se não for.
     *
     * @param valor O valor a se validar
     */
    public void assertValid(@NotNull final String valor) {
        assertValid("Username", valor);
    }
}
