package br.ufla.lemaf.ti.lemaf4j;

import javax.validation.constraints.NotNull;

/**
 * Value Object que podem ser expressados em um tipo mais genérico.
 * Geralmente, estes tipos são os tipos básicos de Java, como
 * String ou valores numéricos (Long, Integer, ...).
 *
 * @param <B> Tipo base que representa este Value Object
 * @author Highlander Paiva
 * @since 1.0
 */
interface ValueObjectWithBaseType<B> extends ValueObject {

    /**
     * Retorna o tipo básico usado para representar este value object.
     * Normalmente é algo como String ou outros tipos básicos do Java.
     *
     * @return Um tipo que é menos restrito que a VO em si.
     */
    @NotNull
    Class<B> getBaseType();

    /**
     * Retorna o objeto na sua representação do tipo
     * básico.
     *
     * @return Value object convertido.
     */
    @NotNull
    B asBaseType();

}
