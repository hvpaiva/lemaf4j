package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.AbstractStringValueObject;
import br.ufla.lemaf.ti.lemaf4j.Formattable;
import br.ufla.lemaf.ti.lemaf4j.common.Contract;
import br.ufla.lemaf.ti.lemaf4j.converters.CNPJConverter;
import br.ufla.lemaf.ti.lemaf4j.formatters.CNPJFormatter;
import br.ufla.lemaf.ti.lemaf4j.validators.CNPJValidator;

import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * VO de CNPJ.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Immutable
@XmlJavaTypeAdapter(CNPJConverter.class)
public class CNPJ extends AbstractStringValueObject implements Formattable<String> {

	private static final long serialVersionUID = 1L;

	private static CNPJFormatter formatter = new CNPJFormatter();
	private static CNPJValidator validator = new CNPJValidator();

	private String codigo;

	/**
	 * Construtor padrão de CNPJ.
	 *
	 * @param codigoCnpj O código do CNPJ
	 */
	public CNPJ(@NotNull final String codigoCnpj) {
		super();
		Contract.requireArgNotNull("CNPJ", codigoCnpj);
		Contract.requireArgNotEmpty("CNPJ", codigoCnpj);

		validator.assertValid(codigoCnpj);

		this.codigo = formatter.unformat(codigoCnpj);
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
		return formatter.format(codigo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String asBaseType() {
		return codigo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return asBaseType();
	}

}
