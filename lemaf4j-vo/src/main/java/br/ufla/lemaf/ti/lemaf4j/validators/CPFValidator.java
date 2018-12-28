package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.ValueObjectValidator;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.formatters.CPFFormatter;
import br.ufla.lemaf.ti.lemaf4j.utils.DigitoPara;
import br.ufla.lemaf.ti.lemaf4j.utils.Error;
import br.ufla.lemaf.ti.lemaf4j.utils.ErrorMessageFactory;
import br.ufla.lemaf.ti.lemaf4j.utils.ValidationMessage;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * O validator de CPF.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class CPFValidator implements ValueObjectValidator<String> {

    private static CPFFormatter formatter = new CPFFormatter();

    private static final Integer CPF_MAX_LENGTH_UNFORMATTED = 11;

    @Override
    public void assertValid(@NotNull final String name,
                            @NotNull final String valor) {
        if (!isValid(valor))
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.ARGUMENTO_INVALIDO, name, valor)
            );
    }

    @Override
    public boolean isValid(final String valor) {
        return invalidMessagesFor(valor).isEmpty();
    }

    /**
     * Valida o CPF e retorna uma lista de erros.
     *
     * @param cpf O CPF a se validar
     * @return Uma lista de erros de CPF
     */
    public List<ValidationMessage> invalidMessagesFor(String cpf) {
        if (cpf == null) throw new IllegalArgumentException(
                Error.ARGUMENTO_NULO.message()
        );

        List<ValidationMessage> errors = new ArrayList<>();

        if (!formatter.isFormatted(cpf) || !formatter.canBeFormatted(cpf))
            errors.add(Error.INVALID_CPF_FORMAT);

        String unformattedCPF;
        try {
            if (formatter.isFormatted(cpf)) {

                unformattedCPF = formatter.unformat(cpf);

            } else {

                unformattedCPF = cpf;

            }

        } catch (IllegalArgumentException e) {

            errors.add(Error.INVALID_CPF_DIGITS);
            return errors;

        }

        if (unformattedCPF.length() != CPF_MAX_LENGTH_UNFORMATTED
                || !unformattedCPF.matches("[0-9]*"))
            errors.add(Error.INVALID_CPF_DIGITS);

        if (hasAllRepeatedDigits(unformattedCPF))
            errors.add(Error.REPEATED_CPF_DIGITS);

        var cpfSemDigito = unformattedCPF.substring(0, unformattedCPF.length() - 2);
        var digitos = unformattedCPF.substring(unformattedCPF.length() - 2);

        var digitosCalculados = calculaDigitos(cpfSemDigito);

        if (!digitos.equals(digitosCalculados))
            errors.add(Error.INVALID_CPF_CHECK_DIGITS);

        return errors;
    }

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
     * Confere se o CPF possui dígitos repetidos.
     *
     * @param cpf A string de CPF.
     * @return <code>true</code> se todos os dígitos
     * forem repetidos
     */
    private boolean hasAllRepeatedDigits(String cpf) {
        return cpf
                .chars()
                .anyMatch(charCPF ->
                        cpf.charAt(charCPF) != cpf.charAt(0)
                );
    }
}
