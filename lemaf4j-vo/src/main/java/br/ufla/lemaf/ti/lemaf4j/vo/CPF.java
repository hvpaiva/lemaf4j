package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.AbstractStringValueObject;
import br.ufla.lemaf.ti.lemaf4j.Formattable;
import br.ufla.lemaf.ti.lemaf4j.common.Contract;
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

    private static CPFFormatter cpfFormatter = new CPFFormatter();
    private static CPFValidator validator = new CPFValidator();

    private String codigoCPF;

    /**
     * Construtor padrão contendo o código do CPF.
     *
     * @param codigoCpf o código do CPF
     */
    public CPF(@NotNull final String codigoCpf) {
        super();
        Contract.requireArgNotNull("CPF", codigoCpf);
        Contract.requireArgNotEmpty("CPF", codigoCpf);

        validator.assertValid(codigoCpf);

        this.codigoCPF = cpfFormatter.unformat(codigoCpf);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String unformatted() {
        return asBaseType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatted() {
        return cpfFormatter.format(codigoCPF);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String asBaseType() {
        return codigoCPF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return asBaseType();
    }

}
