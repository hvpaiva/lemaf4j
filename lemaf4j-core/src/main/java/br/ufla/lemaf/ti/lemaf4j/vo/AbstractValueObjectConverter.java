package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.vo.ValueObjectConverter;
import br.ufla.lemaf.ti.lemaf4j.vo.ValueObjectWithBaseType;

import javax.persistence.AttributeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Classe base para os conversores de Value Object.
 *
 * @param <BASE_TYPE> Tipo base para converter o VO para ou de
 * @param <VO_TYPE> O tipo concreto do VO
 */
public abstract class AbstractValueObjectConverter<BASE_TYPE, VO_TYPE extends ValueObjectWithBaseType<BASE_TYPE>>
        extends XmlAdapter<BASE_TYPE, VO_TYPE>
        implements AttributeConverter<VO_TYPE, BASE_TYPE>, ValueObjectConverter<BASE_TYPE, VO_TYPE> {

    @Override
    public final BASE_TYPE marshal(final VO_TYPE value) throws Exception {
        return fromVO(value);
    }

    @Override
    public final VO_TYPE unmarshal(final BASE_TYPE value) throws Exception {
        return toVO(value);
    }

    @Override
    public final BASE_TYPE convertToDatabaseColumn(final VO_TYPE value) {
        return fromVO(value);
    }

    @Override
    public final VO_TYPE convertToEntityAttribute(final BASE_TYPE value) {
        return toVO(value);
    }
}
