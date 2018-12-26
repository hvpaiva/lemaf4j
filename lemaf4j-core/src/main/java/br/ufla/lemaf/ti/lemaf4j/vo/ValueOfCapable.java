package br.ufla.lemaf.ti.lemaf4j.vo;

import javax.annotation.Nullable;

/**
 * Pode converter uma String em um tipo.
 *
 * @param <T> O tipo
 * @author Highlander Paiva
 * @since 1.0
 */
public interface ValueOfCapable<T> {

    /**
     * Converte a string para o tipo.
     *
     * @param value O valor a se converter.
     *              <code>null</code> retornará <code>null</code>
     * @return O valor convertido
     */
    T valueOf(@Nullable String value);

}
