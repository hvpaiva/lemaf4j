package br.ufla.lemaf.ti.lemaf4j.common.errors;

import br.ufla.lemaf.ti.lemaf4j.common.messaging.ErrorType;

/**
 * Representa erros referêntes à biblioteca em si.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum Error implements ErrorType {
    CLASSE_UTILITARIA_NAO_PODE_SER_INSTANCIADA, VALOR_MINIMO_NAO_ALCANCADO, VALOR_MAXIMO_ATINGIDO
}
