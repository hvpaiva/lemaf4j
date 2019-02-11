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

	private static CNPJFormatter cnpjFormatter = new CNPJFormatter();
	private static CNPJValidator validator = new CNPJValidator();

	private String codigoCNPJ;

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

		this.codigoCNPJ = cnpjFormatter.unformat(codigoCnpj);
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
		return cnpjFormatter.format(codigoCNPJ);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String asBaseType() {
		return codigoCNPJ;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return asBaseType();
	}

}
