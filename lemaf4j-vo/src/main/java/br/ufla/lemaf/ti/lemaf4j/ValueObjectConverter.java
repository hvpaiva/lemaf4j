package br.ufla.lemaf.ti.lemaf4j;

/**
 * Interface de conversão entre o tipo do
 * Value Object e seu tipo básico.
 *
 * @param <B> O tipo básico
 * @param <V> O tipo concreto do VO
 * @author Highlander Paiva
 * @since 1.0
 */
interface ValueObjectConverter<B, V extends ValueObjectWithBaseType<B>> {

    /**
     * Retorna a classe do tipo que será convertido.
     *
     * @return Tipo do outro objeto
     */
    Class<B> getBaseTypeClass();

    /**
     * Retorna a classe concreta do value object.
     *
     * @return O tipo do Value Object
     */
    Class<V> getValueObjectClass();

    /**
     * Verifica se o valor dado pode ser convertido em um
     * value object usando o factory.
     * Um parâmetro <code>null</code> irá retornar <code>false</code>.
     *
     * @param value Valor a se checar
     * @return <code>true</code> se o valor pode ser convertido,
     * senão <code>false</code>
     */
    boolean canBeConverted(B value);

    /**
     * Converte um tipo básico em um Value Object.
     * Um parâmetro <code>null</code> irá retornar
     * <code>null</code>.
     *
     * @param value A representação do VO como tipo básico
     * @return O value object
     */
    V toVO(B value);

    /**
     * Converte um Value Object em tipo básico.
     * Um parâmetro <code>null</code> irá retornar
     * <code>null</code>.
     *
     * @param value O value object
     * @return A representação do VO como tipo básico
     */
    B fromVO(V value);

}
