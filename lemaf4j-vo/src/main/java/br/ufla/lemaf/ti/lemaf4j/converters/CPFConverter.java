package br.ufla.lemaf.ti.lemaf4j.converters;

import br.ufla.lemaf.ti.lemaf4j.AbstractValueObjectConverter;
import br.ufla.lemaf.ti.lemaf4j.validators.CPFValidator;
import br.ufla.lemaf.ti.lemaf4j.vo.CPF;

import javax.annotation.concurrent.ThreadSafe;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Conversor do VO CPF de/para String.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@ThreadSafe
@Converter(autoApply = true)
public class CPFConverter
        extends AbstractValueObjectConverter<String, CPF>
        implements AttributeConverter<CPF, String> {

    private CPFValidator validator;

    /**
     * Construtor padrão do CPFConverter.
     * Injeta a dependência de CPFValidador.
     */
    public CPFConverter() {
        this.validator = new CPFValidator();
    }

    @Override
    public Class<String> getBaseTypeClass() {
        return String.class;
    }

    @Override
    public final Class<CPF> getValueObjectClass() {
        return CPF.class;
    }

    @Override
    public final boolean canBeConverted(final String value) {
        return validator.isValid(value);
    }

    @Override
    public final CPF toVO(final String value) {
        if (value == null) return null;

        return new CPF(value);
    }

    @Override
    public final String fromVO(final CPF value) {
        if (value == null) return null;

        return value.toString();
    }
}
