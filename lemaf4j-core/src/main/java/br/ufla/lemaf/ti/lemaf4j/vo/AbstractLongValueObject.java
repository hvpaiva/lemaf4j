package br.ufla.lemaf.ti.lemaf4j.vo;

import java.io.Serializable;

/**
 * Classe abstrata base para value objects que sobrescrevem {@link Object#hashCode()}
 * e {@link Object#equals(Object)} e implementam um {@link Comparable} baseado no
 * método {@link #asBaseType()}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class AbstractLongValueObject implements
        ValueObjectWithBaseType<Long>,
        Comparable<AbstractLongValueObject>,
        Serializable {

    private static final long serialVersionUID = 1000L;

    @Override
    public final int hashCode() {
        return asBaseType().hashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractLongValueObject other = (AbstractLongValueObject) obj;
        return asBaseType().equals(other.asBaseType());
    }

    @Override
    public final int compareTo(final AbstractLongValueObject other) {
        return asBaseType().compareTo(other.asBaseType());
    }

    @Override
    public final Class<Long> getBaseType() {
        return Long.class;
    }
}
