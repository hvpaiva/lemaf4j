package br.ufla.lemaf.ti.lemaf4j;

/**
 * Classe abstrata base para criação de
 * value objects do tipo base {@link Long}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class AbstractLongValueObject
        extends AbstractValueObject<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<Long> getBaseType() {
        return Long.class;
    }
}
