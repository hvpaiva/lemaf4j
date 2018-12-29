package br.ufla.lemaf.ti.lemaf4j.converters;

import br.ufla.lemaf.ti.lemaf4j.validators.UserNameValidator;
import br.ufla.lemaf.ti.lemaf4j.AbstractValueObjectConverter;
import br.ufla.lemaf.ti.lemaf4j.vo.UserName;

import javax.annotation.concurrent.ThreadSafe;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Conversor do VO UserName de/para String.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@ThreadSafe
@Converter(autoApply = true)
public final class UserNameConverter
        extends AbstractValueObjectConverter<String, UserName>
        implements AttributeConverter<UserName, String> {

    private UserNameValidator validator;

    /**
     * Construtor padrão do UserNameConverter.
     * Injeta a dependência de UserNameValidador.
     */
    public UserNameConverter() {
        this.validator = new UserNameValidator();
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
    public Class<UserName> getValueObjectClass() {
        return UserName.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBeConverted(final String value) {
        return validator.isValid(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserName toVO(final String value) {
        if (value == null) return null;

        return new UserName(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String fromVO(final UserName value) {
        if (value == null) return null;

        return value.toString();
    }


}
