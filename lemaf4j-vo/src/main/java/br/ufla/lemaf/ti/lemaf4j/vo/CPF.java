package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.AbstractStringValueObject;
import br.ufla.lemaf.ti.lemaf4j.common.Contract;
import br.ufla.lemaf.ti.lemaf4j.Formattable;
import br.ufla.lemaf.ti.lemaf4j.converters.CPFConverter;
import br.ufla.lemaf.ti.lemaf4j.formatters.CPFFormatter;
import br.ufla.lemaf.ti.lemaf4j.validators.CPFValidator;

import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Value Object representativo de um CPF.
 * (Cadastro de Pessoa Física)
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Immutable
@XmlJavaTypeAdapter(CPFConverter.class)
public final class CPF extends AbstractStringValueObject implements Formattable<String> {

    private static final long serialVersionUID = 1L;

    private static CPFFormatter formatter = new CPFFormatter();
    private static CPFValidator validator = new CPFValidator();

    private String codigo;

    /**
     * Construtor protected para deserialização.
     */
    protected CPF() {
        super();
    }

    /**
     * Construtor padrão contendo o código do CPF.
     *
     * @param codigoCpf o código do CPF
     */
    public CPF(@NotNull final String codigoCpf) {
        super();
        Contract.requireArgNotNull("CPF", codigoCpf);
        Contract.requireArgNotEmpty("CPF", codigoCpf);

        validator.assertValid("CPF", codigoCpf);

        if (formatter.isFormatted(codigoCpf)) {
            this.codigo = formatter.unformat(codigoCpf);
        } else {
            this.codigo = codigoCpf;
        }
    }

    @Override
    public String unformatted() {
        return asBaseType();
    }

    @Override
    public String formatted() {
        return formatter.format(codigo);
    }

    @Override
    public String asBaseType() {
        return codigo;
    }

    @Override
    public String toString() {
        return asBaseType();
    }

}
