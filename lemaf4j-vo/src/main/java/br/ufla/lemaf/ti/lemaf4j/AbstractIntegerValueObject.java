package br.ufla.lemaf.ti.lemaf4j;

/**
 * Classe abstrata base para criação de
 * value objects do tipo base {@link Integer}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class AbstractIntegerValueObject
        extends AbstractValueObject<Integer> {

    private static final long serialVersionUID = 10L;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<Integer> getBaseType() {
        return Integer.class;
    }
}
