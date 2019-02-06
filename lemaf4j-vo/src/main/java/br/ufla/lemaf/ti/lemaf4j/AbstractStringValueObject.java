package br.ufla.lemaf.ti.lemaf4j;

/**
 * Classe abstrata base para criação de
 * value objects do tipo base {@link String}.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public abstract class AbstractStringValueObject
        extends AbstractValueObject<String> {

    private static final long serialVersionUID = 100L;

    /**
     * {@inheritDoc}
     */
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

