package br.ufla.lemaf.ti.lemaf4j.common;

import br.ufla.lemaf.ti.lemaf4j.Formatter;

/**
 * Interface compartilhada de CPF/CNPJ.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface CadastroPessoa {

	/**
	 * Confere se o CNPJ/CPF possui dígitos repetidos.
	 *
	 * @param cadastro A string de CNPJ/CPF.
	 * @param formatter O formatador do tipo
	 * @return <code>true</code> se todos os dígitos
	 * forem repetidos
	 */
	default boolean hasAllRepeatedDigits(String cadastro, Formatter<String> formatter) {
		var desformatado = formatter.unformat(cadastro);

		for (int i = 1; i < desformatado.length(); i++) {
			if (desformatado.charAt(i) != desformatado.charAt(0)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Retorna os dígitos verificadores de um CNPJ/CPF.
	 *
	 * @param cadastro O CNPJ ou CPF
	 * @param formatter O formatador do tipo
	 * @return Os dígitos verificadores
	 */
	default String digitosVerificadoresDe(final String cadastro, final Formatter<String> formatter) {
		var desformatado = formatter.unformat(cadastro);

		return desformatado.substring(desformatado.length() - 2);
	}

	/**
	 * O CNPJ/CPF sem seus dígitos verificadores e desformatado.
	 *
	 * @param cadastro O CNPJ/CPF
	 * @param formatter O formatador do tipo
	 * @return O CNPJ/CPF sem os dígitos
	 */
	default String semDigitosVerificadores(final String cadastro, final Formatter<String> formatter) {
		var desformatado = formatter.unformat(cadastro);

		return desformatado.substring(0, desformatado.length() - 2);
	}
}
