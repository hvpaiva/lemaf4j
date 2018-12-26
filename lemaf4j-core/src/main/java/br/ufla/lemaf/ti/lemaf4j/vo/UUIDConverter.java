package br.ufla.lemaf.ti.lemaf4j.vo;

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
    public final String marshal(final UUID value) throws Exception {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @Override
    public final UUID unmarshal(final String value) throws Exception {
        if (value == null) {
            return null;
        }
        return UUID.fromString(value);
    }

    @Override
    public final String convertToDatabaseColumn(final UUID value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @Override
    public final UUID convertToEntityAttribute(final String value) {
        return UUID.fromString(value);
    }

}
