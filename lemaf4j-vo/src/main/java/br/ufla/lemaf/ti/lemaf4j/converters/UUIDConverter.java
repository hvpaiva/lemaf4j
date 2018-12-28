package br.ufla.lemaf.ti.lemaf4j.converters;

import javax.annotation.concurrent.ThreadSafe;
import javax.persistence.AttributeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.UUID;

/**
 * Converte {@link UUID} para String e vice-versa.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@ThreadSafe
public final class UUIDConverter
        extends XmlAdapter<String, UUID>
        implements AttributeConverter<UUID, String> {

    @Override
    public String marshal(final UUID value) throws Exception {
        return convertToDatabaseColumn(value);
    }

    @Override
    public UUID unmarshal(final String value) throws Exception {
        if (value == null) return null;

        return UUID.fromString(value);
    }

    @Override
    public String convertToDatabaseColumn(final UUID value) {
        if (value == null) return null;

        return value.toString();
    }

    @Override
    public UUID convertToEntityAttribute(final String value) {
        return UUID.fromString(value);
    }

}
