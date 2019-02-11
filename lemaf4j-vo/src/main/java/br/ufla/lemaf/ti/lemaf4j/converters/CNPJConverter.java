package br.ufla.lemaf.ti.lemaf4j.converters;

import br.ufla.lemaf.ti.lemaf4j.AbstractValueObjectConverter;
import br.ufla.lemaf.ti.lemaf4j.validators.CNPJValidator;
import br.ufla.lemaf.ti.lemaf4j.vo.CNPJ;

import javax.annotation.concurrent.ThreadSafe;
import javax.persistence.Converter;

/**
 * Conversor do VO CNPJ de/para String.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@ThreadSafe
@Converter(autoApply = true)
public class CNPJConverter
		extends AbstractValueObjectConverter<String, CNPJ> {

	private CNPJValidator validator;

	/**
	 * Construtor padrão do CNPJConverter.
	 * Injeta a dependência de CNPJValidador.
	 */
	public CNPJConverter() {
		this.validator = new CNPJValidator();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<String> getBaseTypeClass() {
		return String.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Class<CNPJ> getValueObjectClass() {
		return CNPJ.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean canBeConverted(final String value) {
		return validator.isValid(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CNPJ toVO(final String value) {
		if (value == null) return null;

		return new CNPJ(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fromVO(final CNPJ value) {
		if (value == null) return null;

		return value.toString();
	}

}
