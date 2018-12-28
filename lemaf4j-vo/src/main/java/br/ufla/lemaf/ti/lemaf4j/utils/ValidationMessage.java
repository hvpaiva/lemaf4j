package br.ufla.lemaf.ti.lemaf4j.utils;

/**
 * Mensagens de erro sobre a validação de
 * um objeto devem implementar essa interface.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface ValidationMessage {

    /**
     * @return mensagem de validação armazenda.
     */
    String message();
}
