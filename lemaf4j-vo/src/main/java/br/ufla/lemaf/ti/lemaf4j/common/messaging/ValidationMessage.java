package br.ufla.lemaf.ti.lemaf4j.common.messaging;

/**
 * Mensagens de erro sobre a validação de
 * um objeto devem implementar essa interface.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface ValidationMessage {

    /**
     * Busca a mensagem de validação.
     *
     * @return mensagem de validação armazenda.
     */
    String message();
}
