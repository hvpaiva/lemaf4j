package br.ufla.lemaf.ti.lemaf4j.common;

/**
 * Converte um Value Object em seu tipo
 * base e vice-versa.
 *
 * @param <BASE_TYPE> O tipo básico
 * @param <VO_TYPE> O tipo concreto do VO
 * @author Highlander Paiva
 * @since 1.0
 */
public interface ValueObjectConverter<BASE_TYPE, VO_TYPE extends ValueObjectWithBaseType<BASE_TYPE>> {

    /**
     * Retorna a classe do tipo que será convertido.
     *
     * @return Tipo do outro objeto
     */
    Class<BASE_TYPE> getBaseTypeClass();

    /**
     * Retorna a classe concreta do value object.
     *
     * @return O tipo do Value Object
     */
    Class<VO_TYPE> getValueObjectClass();

    /**
     * Verifica se o valor dado pode ser convertido em um
     * value object usando o factory.
     * Um parâmetro <code>null</code> irá retornar <code>false</code>.
     *
     * @param value Valor a se checar
     * @return <code>true</code> se o valor pode ser convertido,
     *         senão <code>false</code>
     */
    boolean isValid(BASE_TYPE value);

    /**
     * Converte um tipo básico em um Value Object.
     * Um parâmetro <code>null</code> irá retornar
     * <code>null</code>.
     *
     * @param value A representação do VO como tipo básico
     * @return O value object
     */
    VO_TYPE toVO(BASE_TYPE value);

    /**
     * Converte um Value Object em tipo básico.
     * Um parâmetro <code>null</code> irá retornar
     * <code>null</code>.
     *
     * @param value O value object
     * @return  A representação do VO como tipo básico
     */
    BASE_TYPE fromVO(VO_TYPE value);

}
