package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.ValueObjectValidator;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.utils.Error;
import br.ufla.lemaf.ti.lemaf4j.utils.ErrorMessageFactory;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.constraints.NotNull;

/**
 * Validator de Email.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class EmailAddressValidator implements ValueObjectValidator<String> {

    @Override
    public void assertValid(@NotNull final String name,
                            @NotNull final String value) {

        if (!isValid(value)) {
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.ARGUMENTO_INVALIDO, name, value)
            );
        }
    }

    @Override
    public boolean isValid(final String value) {
        if (value == null || value.isEmpty()) return false;

        try {

            InternetAddress.parse(value, false);

            return true;

        } catch (final AddressException ex) {

            return false;

        }
    }

}
