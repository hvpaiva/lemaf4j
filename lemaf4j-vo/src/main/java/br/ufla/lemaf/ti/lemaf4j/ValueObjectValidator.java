package br.ufla.lemaf.ti.lemaf4j;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;

/**
 * Interface de validação para VOs.
 *
 * @param <B> O tipo base do VO
 * @author Highlander Paiva
 * @since 1.0
 */
public interface ValueObjectValidator<B> {

    /**
     * Confere se o argumento é válido,
     * lançando uma exceção em caso negativo.
     *
     * @param name O nome do argumento, para mensagem de erro
     * @param value O valor do argumento
     * @throws ConstraintViolationException Quando o argumento não é válido
     */
    void assertValid(String name, B value) throws ConstraintViolationException;

    /**
     * Confere se o valor dado é um endereço válido
     * de email.
     *
     * @param value Valor a se checar
     * @return Retorna <code>true</code> se for um email válido ou
     *         retornará <code>false</code> se não for.
     *         Se o argumento for <code>null</code> retornará
     *         <code>false</code>
     */
    boolean isValid(B value);
}
