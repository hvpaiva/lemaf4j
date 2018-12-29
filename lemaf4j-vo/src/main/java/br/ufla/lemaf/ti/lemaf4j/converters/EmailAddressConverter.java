package br.ufla.lemaf.ti.lemaf4j.converters;

import br.ufla.lemaf.ti.lemaf4j.AbstractValueObjectConverter;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.utils.Error;
import br.ufla.lemaf.ti.lemaf4j.utils.ErrorMessageFactory;
import br.ufla.lemaf.ti.lemaf4j.validators.EmailAddressValidator;
import br.ufla.lemaf.ti.lemaf4j.vo.EmailAddress;

import javax.annotation.concurrent.ThreadSafe;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.validation.constraints.NotNull;

/**
 * Conversor do VO EmailAddress de/para String.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@ThreadSafe
@Converter(autoApply = true)
public class EmailAddressConverter
        extends AbstractValueObjectConverter<String, EmailAddress>
        implements AttributeConverter<EmailAddress, String> {

    private EmailAddressValidator validator;

    /**
     * Construtor padrão do EmailAddressConverter.
     * Injeta a dependência de EmailAddressValidador.
     */
    public EmailAddressConverter() {
        this.validator = new EmailAddressValidator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<String> getBaseTypeClass() {
        return String.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<EmailAddress> getValueObjectClass() {
        return EmailAddress.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean canBeConverted(final String value) {
        return validator.isValid(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EmailAddress toVO(final String value) {
        if (value == null) return null;

        return new EmailAddress(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String fromVO(final EmailAddress value) {
        if (value == null) return null;

        return value.toString();
    }

    /**
     * Converte a String de email para o tipo InternetAddress.
     *
     * @param value A String de email
     * @return O email do tipo InternetAddress
     */
    public static InternetAddress toInternetAddress(@NotNull final String value) {

        final var trimmedLowerCaseValue = value.trim().toLowerCase();
        try {

            final var addr = InternetAddress.parse(trimmedLowerCaseValue, false);

            if (addr.length != 1) {
                throw new ConstraintViolationException(
                        ErrorMessageFactory.of(Error.MULTIPLE_EMAIL, trimmedLowerCaseValue)
                );
            }
            return addr[0];

        } catch (final AddressException ex) {

            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.INVALID_EMAIL, trimmedLowerCaseValue)
            );
        }

    }

}
