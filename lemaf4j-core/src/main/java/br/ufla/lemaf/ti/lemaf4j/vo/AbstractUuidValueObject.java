package br.ufla.lemaf.ti.lemaf4j.vo;

import java.io.Serializable;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Classe abstrata base para value objects que sobrescrevem {@link Object#hashCode()}
 * e {@link Object#equals(Object)} e implementam um {@link Comparable} baseado no
 * método {@link #asBaseType()}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class AbstractUuidValueObject implements
        ValueObjectWithBaseType<UUID>,
        Comparable<AbstractUuidValueObject>,
        Serializable {

    private static final long serialVersionUID = 1000L;

    private static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-\" + \"[0-9a-f]{4}-[0-9a-f]{12}$";

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
        final AbstractUuidValueObject other = (AbstractUuidValueObject) obj;
        return asBaseType().equals(other.asBaseType());
    }

    @Override
    public final int compareTo(final AbstractUuidValueObject other) {
        return asBaseType().compareTo(other.asBaseType());
    }

    @Override
    public final Class<UUID> getBaseType() {
        return UUID.class;
    }

    /**
     * Verifica se dada String é um padrão válido UUID.
     *
     * @param value Valor a se checar. Um valor <code>null</code>
     *              retornará <code>true</code>.
     * @return <code>true</code> se for válido, senão <code>false</code>
     */
    public static boolean isValid(final String value) {
        if (value == null) {
            return true;
        }
        return Pattern.matches(UUID_PATTERN, value);
    }

}
