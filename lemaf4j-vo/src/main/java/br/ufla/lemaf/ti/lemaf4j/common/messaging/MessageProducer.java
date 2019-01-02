package br.ufla.lemaf.ti.lemaf4j.common.messaging;

/**
 * São responsáveis por construir ou buscar
 * as mensagens de validação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface MessageProducer {

    /**
     * Busca a mensagem de validação de
     * um tipo de erro.
     *
     * @param errorType Um tipo de erro
     * @return A mensagem associada ao erro
     */
    ValidationMessage messageOf(ErrorType errorType);

    /**
     * Busca a mensagem de validação de
     * um tipo de erro.
     *
     * @param errorType Um tipo de erro
     * @param args      Argumentos para a mensagem
     * @return A mensagem associada ao erro
     */
    ValidationMessage messageOf(ErrorType errorType, Object... args);
}
