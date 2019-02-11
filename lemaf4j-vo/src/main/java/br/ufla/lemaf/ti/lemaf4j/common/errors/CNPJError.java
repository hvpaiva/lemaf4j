package br.ufla.lemaf.ti.lemaf4j.common.errors;

import br.ufla.lemaf.ti.lemaf4j.common.messaging.ErrorType;

/**
 * Representa erros referêntes ao CNPJ.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public enum CNPJError implements ErrorType {
	CNPJ_INVALIDO, DIGITOS_INVALIDOS, FORMATO_INVALIDO, DIGITOS_REPETIDOS, DIGITOS_VERIFICADORES_INVALIDOS
}
