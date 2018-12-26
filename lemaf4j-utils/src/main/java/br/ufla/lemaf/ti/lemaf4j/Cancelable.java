package br.ufla.lemaf.ti.lemaf4j;

/**
 * Uma unidade de trabalho que é cancelável.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface Cancelable {

    /**
     * Tenta cancelar uma unidade de trabalho.
     */
    public void cancel();

    /**
     * Retorna se a unidade de trabalho foi cancelada.
     *
     * @return Se foi cancelada <code>true</code> senão <code>false</code>
     */
    public boolean isCanceled();

}
