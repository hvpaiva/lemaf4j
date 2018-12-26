package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.common.Contract;
import br.ufla.lemaf.ti.lemaf4j.vo.AsStringCapable;
import br.ufla.lemaf.ti.lemaf4j.vo.ValueOfCapable;

import javax.persistence.AttributeConverter;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converte um tipo em String e vice-versa.
 *
 * @param <TYPE> O tipo para se converter
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class ValueObjectStringConverter<TYPE extends AsStringCapable>
        extends XmlAdapter<String, TYPE>
        implements AttributeConverter<TYPE, String> {

    private final ValueOfCapable<TYPE> voc;

    /**
     * Injeção de dependência.
     *
     * @param voc Provém um método
     *            {@link ValueOfCapable#valueOf(String)}
     */
    public ValueObjectStringConverter(@NotNull final ValueOfCapable<TYPE> voc) {
        super();
        Contract.requireArgNotNull("voc", voc);
        this.voc = voc;
    }

    @Override
    public final TYPE unmarshal(final String value) {
        return voc.valueOf(value);
    }

    @Override
    public final String marshal(final TYPE value) {
        if (value == null) {
            return null;
        }
        return value.asString();
    }

    @Override
    public final String convertToDatabaseColumn(final TYPE value) {
        return marshal(value);
    }

    @Override
    public final TYPE convertToEntityAttribute(final String value) {
        return unmarshal(value);
    }

}
