package br.ufla.lemaf.ti.lemaf4j.common.errors;

import br.ufla.lemaf.ti.lemaf4j.common.messaging.ErrorType;

/**
 * Representa erros referêntes ao UserName.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum UserNameError implements ErrorType {
    USERNAME_INVALIDO, TAMANHO_MAXIMO_EXCEDIDO, TAMANHO_MINIMO_NAO_ATINGIDO
}
