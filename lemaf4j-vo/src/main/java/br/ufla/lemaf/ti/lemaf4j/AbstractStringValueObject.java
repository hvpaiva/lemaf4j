package br.ufla.lemaf.ti.lemaf4j;

import java.io.Serializable;

/**
 * Classe abstrata base para criação de
 * value objects do tipo base {@link String}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class AbstractStringValueObject implements
        ValueObjectWithBaseType<String>,
        Comparable<AbstractStringValueObject>,
        Serializable {

    private static final long serialVersionUID = 1000L;

    @Override
    public final int hashCode() {
        return asBaseType().hashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final var other = (AbstractStringValueObject) obj;
        return asBaseType().equals(other.asBaseType());
    }

    @Override
    public final int compareTo(final AbstractStringValueObject other) {
        return this.asBaseType().compareTo(other.asBaseType());
    }

    @Override
    public final Class<String> getBaseType() {
        return String.class;
    }

    /**
     * Retorna o tamanho da cadeia
     * de caracteres de de um VO
     * de tipo base String.
     *
     * @return A quantidade de caracteres
     */
    public final int length() {
        return asBaseType().length();
    }
}

