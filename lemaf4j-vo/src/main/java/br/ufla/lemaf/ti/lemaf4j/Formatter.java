package br.ufla.lemaf.ti.lemaf4j;

/**
 * Formatter fornece interfaces de
 * formatação, transformando cadeia de caracteres
 * em formatados ou não.
 *
 * @param <B> O tipo básico do formatador
 * @author Highlander Paiva
 * @since 1.0
 */
public interface Formatter<B> {

    /**
     * Formata uma cadeia de caracteres.
     *
     * @param value Valor a se formatar
     * @return Valor formatado
     * @throws IllegalArgumentException Caso argumento seja nulo ou não
     *                                  represente propriamente um
     *                                  valor sem formato
     */
    B format(B value);

    /**
     * Remove a formatação de uma cadeia de caracteres.
     *
     * @param value Valor a se formatar
     * @return Valor formatado
     * @throws IllegalArgumentException Caso argumento seja nulo ou não
     *                                  represente propriamente um
     *                                  valor formatado
     */
    B unformat(B value);

    /**
     * Verifica se uma cadeia está no formato
     * com o qual o formatador trabalha, formatado.
     *
     * @param value Valor a ser verificado
     * @return <code>true</code>, se estiver de acordo com o formato
     */
    boolean isFormatted(B value);

    /**
     * Verifica se uma cadeia está no formato
     * com o qual o formatador trabalha, mas
     * desformatado.
     *
     * @param value Valor a ser verificado
     * @return <code>true</code>, se estiver de acordo com o formato
     */
    boolean isNotFormatted(B value);

}
