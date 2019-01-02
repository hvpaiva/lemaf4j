package br.ufla.lemaf.ti.lemaf4j.common.errors;

import br.ufla.lemaf.ti.lemaf4j.common.messaging.ErrorType;

/**
 * Representa erros referêntes ao CPF.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum CPFError implements ErrorType {
    DIGITOS_VERIFICADORES_INVALIDOS, DIGITOS_INVALIDOS, DIGITOS_REPETIDOS, FORMATO_INVALIDO, CPF_INVALIDO
}
