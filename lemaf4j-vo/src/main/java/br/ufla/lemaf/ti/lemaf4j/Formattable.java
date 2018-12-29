package br.ufla.lemaf.ti.lemaf4j;

/**
 * Provém uma forma formatada e desformatada
 * de si mesmo.
 *
 * @param <B> O tipo base em que o VO
 *            formatado/desformatado é entregue.
 *            Normalmente tipos comuns do Java,
 *            como String, Integer ou Long.
 * @author Highlander Paiva
 * @since 1.0
 */
public interface Formattable<B> {

    /**
     * Retorna sua representação desformatada.
     *
     * @return O valor desformatado
     */
    B unformatted();

    /**
     * Retorna sua representação formatado.
     *
     * @return O valor formatado
     */
    B formatted();

}
