package br.ufla.lemaf.ti.lemaf4j;

import java.io.Serializable;

/**
 * Classe abstrata base para criação de
 * value objects do tipo base {@link Integer}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class AbstractIntegerValueObject implements
        ValueObjectWithBaseType<Integer>,
        Comparable<AbstractIntegerValueObject>,
        Serializable {

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

        final var other = (AbstractIntegerValueObject) obj;
        return asBaseType().equals(other.asBaseType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int compareTo(final AbstractIntegerValueObject other) {
        return asBaseType().compareTo(other.asBaseType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<Integer> getBaseType() {
        return Integer.class;
    }
}
