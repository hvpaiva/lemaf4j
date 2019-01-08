package br.ufla.lemaf.ti.lemaf4j;

import java.io.Serializable;

/**
 * Classe abstrata base para Value Object.
 *
 * @param <B> O tipo base
 * @author Highlander Paiva
 * @since 1.0
 */
abstract class AbstractValueObject<B> implements ValueObjectWithBaseType<B>, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return asBaseType().hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		final var other = (AbstractValueObject) obj;
		return asBaseType().equals(other.asBaseType());
	}
}
