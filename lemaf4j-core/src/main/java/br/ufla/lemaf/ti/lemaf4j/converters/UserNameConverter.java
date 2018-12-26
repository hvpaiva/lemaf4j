package br.ufla.lemaf.ti.lemaf4j.converters;

import br.ufla.lemaf.ti.lemaf4j.validators.UserNameStrValidator;
import br.ufla.lemaf.ti.lemaf4j.common.AbstractValueObjectConverter;
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

    @Override
    public Class<String> getBaseTypeClass() {
        return String.class;
    }

    @Override
    public final Class<UserName> getValueObjectClass() {
        return UserName.class;
    }

    @Override
    public final boolean isValid(final String value) {
        return UserNameStrValidator.isValid(value);
    }

    @Override
    public final UserName toVO(final String value) {
        if (value == null) {
            return null;
        }
        return new UserName(value);
    }

    @Override
    public final String fromVO(final UserName value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }


}
