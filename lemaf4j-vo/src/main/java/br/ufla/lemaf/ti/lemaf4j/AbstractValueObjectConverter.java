package br.ufla.lemaf.ti.lemaf4j;

import javax.persistence.AttributeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Classe base para a construção
 * de conversores de Value Object.
 *
 * @param <B> Tipo base para converter o VO para ou de
 * @param <V> O tipo concreto do VO
 */
public abstract class AbstractValueObjectConverter
        <B, V extends ValueObjectWithBaseType<B>>
        extends XmlAdapter<B, V>
        implements AttributeConverter<V, B>, ValueObjectConverter<B, V> {

    /**
     * {@inheritDoc}
     */
    @Override
    public final B marshal(final V value) throws Exception {
        return fromVO(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final V unmarshal(final B value) throws Exception {
        return toVO(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final B convertToDatabaseColumn(final V value) {
        return fromVO(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final V convertToEntityAttribute(final B value) {
        return toVO(value);
    }
}
