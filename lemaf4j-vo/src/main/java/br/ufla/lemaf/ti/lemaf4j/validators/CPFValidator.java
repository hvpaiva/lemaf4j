package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.ValueObjectValidator;
import br.ufla.lemaf.ti.lemaf4j.common.CadastroPessoa;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.common.errors.CPFError;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.MessageProducer;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.SimpleMessageProducer;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.ValidationMessage;
import br.ufla.lemaf.ti.lemaf4j.formatters.CPFFormatter;
import br.ufla.lemaf.ti.lemaf4j.utils.DigitoPara;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * O validator de CPF.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class CPFValidator implements ValueObjectValidator<String>, CadastroPessoa {

    private final CPFFormatter formatter;


    private final MessageProducer messageProducer;

    /**
     * Construtor padrão de validador de CPF.
     * Este utiliza, por padrão, um {@linkplain SimpleMessageProducer}
     * para geração de mensagens.
     */
    public CPFValidator() {
        this(new SimpleMessageProducer());
    }

    /**
     * Construtor do Validador de CPF.
     *
     * @param messageProducer produtor de mensagem de erro
     */
    public CPFValidator(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
        this.formatter = new CPFFormatter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String valor) {
        return invalidMessagesFor(valor).isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assertValid(@NotNull final String valor) {
        if (!isValid(valor))
            throw new ConstraintViolationException(
                    messageProducer
                            .messageOf(CPFError.CPF_INVALIDO, valor)
                            .toString()
            );
    }

    // - Calculos internos

    private static final Integer MULTIPLICADOR_INICIAL_DIGITO_CPF = 2;
    private static final Integer MULTIPLICADOR_FINAL_DIGITO_CPF = 11;

    private static final Integer DIGITO_DEZ_CPF = 10;
    private static final Integer DIGITO_ONZE_CPF = 11;

    /**
     * Faz o cálculo dos digitos usando a lógica de CPF.
     *
     * @param cpfSemDigito O CPF sem os dígitos
     * @return String os dois dígitos calculados
     */
    private String calculaDigitos(String cpfSemDigito) {
        DigitoPara digitoPara = new DigitoPara(cpfSemDigito);
        digitoPara
                .comMultiplicadoresDeAte(
                        MULTIPLICADOR_INICIAL_DIGITO_CPF,
                        MULTIPLICADOR_FINAL_DIGITO_CPF
                )
                .complementarAoModulo()
                .trocandoPorSeEncontrar(
                        "0",
                        DIGITO_DEZ_CPF,
                        DIGITO_ONZE_CPF
                )
                .mod(null);

        var digito1 = digitoPara.calcula();

        digitoPara.addDigito(digito1);
        var digito2 = digitoPara.calcula();

        return digito1 + digito2;
    }

    /**
     * Valida o CPF e retorna uma lista de erros.
     *
     * @param cpf O CPF a se validar
     * @return Uma lista de erros de CPF
     */
    public String invalidMessagesFor(String cpf) {
        List<ValidationMessage> errors = new ArrayList<>();
        if (cpf == null || cpf.isEmpty()) {
            errors.add(messageProducer.messageOf(CPFError.DIGITOS_INVALIDOS));

        } else {

            if (!formatter.isFormatted(cpf) && !formatter.isNotFormatted(cpf)) {
                errors.add(messageProducer.messageOf(CPFError.FORMATO_INVALIDO));

            } else {

                if (hasAllRepeatedDigits(cpf, this.formatter))
                    errors.add(messageProducer.messageOf(CPFError.DIGITOS_REPETIDOS));

                var digitosCalculados = calculaDigitos(semDigitosVerificadores(cpf, this.formatter));
                var digitos = digitosVerificadoresDe(cpf, this.formatter);
                if (!digitos.equals(digitosCalculados))
                    errors.add(messageProducer.messageOf(CPFError.DIGITOS_VERIFICADORES_INVALIDOS));
            }

        }

        return StringUtils.join(errors, "; \n");

    }
}
