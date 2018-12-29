package br.ufla.lemaf.ti.lemaf4j;

import java.io.Serializable;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Classe abstrata base para criação de
 * value objects do tipo base {@link UUID}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class AbstractUuidValueObject implements
        ValueObjectWithBaseType<UUID>,
        Comparable<AbstractUuidValueObject>,
        Serializable {

    private static final long serialVersionUID = 1L;

    private static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-\" + \"[0-9a-f]{4}-[0-9a-f]{12}$";

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

        final var other = (AbstractUuidValueObject) obj;
        return asBaseType().equals(other.asBaseType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int compareTo(final AbstractUuidValueObject other) {
        return asBaseType().compareTo(other.asBaseType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<UUID> getBaseType() {
        return UUID.class;
    }

    /**
     * Verifica se dada String é um padrão válido UUID.
     *
     * @param value Valor a se checar. Um valor <code>null</code>
     *              retornará <code>false</code>.
     * @return <code>true</code> se for válido, senão <code>false</code>
     */
    public static boolean isValid(final String value) {
        if (value == null) return false;

        return Pattern.matches(UUID_PATTERN, value);
    }

}
