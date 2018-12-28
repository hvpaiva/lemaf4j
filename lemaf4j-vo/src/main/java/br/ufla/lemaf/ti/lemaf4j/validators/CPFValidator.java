package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.ValueObjectValidator;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.utils.Error;
import br.ufla.lemaf.ti.lemaf4j.utils.ErrorMessageFactory;

import javax.validation.constraints.NotNull;

/**
 * O validator de CPF.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class CPFValidator implements ValueObjectValidator<String> {

    @Override
    public void assertValid(@NotNull final String name,
                            @NotNull final String valor) {
        if (!isValid(valor))
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.ARGUMENTO_INVALIDO, name, valor)
            );
    }

    @Override
    public boolean isValid(final String valor) {
        // TODO
        return false;
    }
}
