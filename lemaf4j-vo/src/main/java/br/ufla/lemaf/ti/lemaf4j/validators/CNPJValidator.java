package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.ValueObjectValidator;
import br.ufla.lemaf.ti.lemaf4j.common.CadastroPessoa;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.common.errors.CNPJError;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.MessageProducer;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.SimpleMessageProducer;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.ValidationMessage;
import br.ufla.lemaf.ti.lemaf4j.formatters.CNPJFormatter;
import br.ufla.lemaf.ti.lemaf4j.utils.DigitoPara;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * O validator de CNPJ.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class CNPJValidator implements ValueObjectValidator<String>, CadastroPessoa {

	private final CNPJFormatter formatter;

	private final MessageProducer messageProducer;

	/**
	 * Construtor padrão de validador de CNPJ.
	 * Este utiliza, por padrão, um {@linkplain SimpleMessageProducer}
	 * para geração de mensagens.
	 */
	public CNPJValidator() {
		this(new SimpleMessageProducer());
	}

	/**
	 * Construtor do Validador de CNPJ.
	 *
	 * @param messageProducer produtor de mensagem de erro
	 */
	public CNPJValidator(MessageProducer messageProducer) {
		this.messageProducer = messageProducer;
		this.formatter = new CNPJFormatter();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void assertValid(final String value) {
		if (!isValid(value)) {
			throw new ConstraintViolationException(
					messageProducer
						.messageOf(CNPJError.CNPJ_INVALIDO, value)
						.toString()
			);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(final String value) {
		return invalidMessagesFor(value).isEmpty();
	}


	// - Calculos internos

	private static final Integer DIGITO_DEZ_CNPJ = 10;
	private static final Integer DIGITO_ONZE_CNPJ = 11;

	/**
	 * Faz o cálculo dos digitos usando a lógica de CNPJ.
	 *
	 * @param cnpjSemDigito O CNPJ sem os dígitos
	 * @return String os dois dígitos calculados
	 */
	private String calculaDigitos(String cnpjSemDigito) {
		DigitoPara digitoPara = new DigitoPara(cnpjSemDigito);
		digitoPara
				.complementarAoModulo()
				.trocandoPorSeEncontrar(
						"0",
						DIGITO_DEZ_CNPJ,
						DIGITO_ONZE_CNPJ
				)
				.mod(DIGITO_ONZE_CNPJ);

		var digito1 = digitoPara.calcula();

		digitoPara.addDigito(digito1);
		var digito2 = digitoPara.calcula();

		return digito1 + digito2;
	}

	/**
	 * Valida o CPF e retorna uma lista de erros.
	 *
	 * @param cnpj O CPF a se validar
	 * @return Uma lista de erros de CPF
	 */
	public String invalidMessagesFor(String cnpj) {
		List<ValidationMessage> errors = new ArrayList<>();
		if (cnpj == null || cnpj.isEmpty()) {
			errors.add(messageProducer.messageOf(CNPJError.DIGITOS_INVALIDOS));

		} else {

			if (!formatter.isFormatted(cnpj) && !formatter.isNotFormatted(cnpj)) {
				errors.add(messageProducer.messageOf(CNPJError.FORMATO_INVALIDO));

			} else {

				if (hasAllRepeatedDigits(cnpj, this.formatter))
					errors.add(messageProducer.messageOf(CNPJError.DIGITOS_REPETIDOS));

				var digitosCalculados = calculaDigitos(semDigitosVerificadores(cnpj, this.formatter));
				var digitos = digitosVerificadoresDe(cnpj, this.formatter);
				if (!digitos.equals(digitosCalculados))
					errors.add(messageProducer.messageOf(CNPJError.DIGITOS_VERIFICADORES_INVALIDOS));
			}

		}

		return StringUtils.join(errors, "; \n");

	}
}
