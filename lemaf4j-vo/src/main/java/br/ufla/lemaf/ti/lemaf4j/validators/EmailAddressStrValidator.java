package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.common.Error;
import br.ufla.lemaf.ti.lemaf4j.common.ErrorMessageFactory;
import br.ufla.lemaf.ti.lemaf4j.types.EmailAddressStr;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

public final class EmailAddressStrValidator implements ConstraintValidator<EmailAddressStr, String> {

    @Override
    public final void initialize(final EmailAddressStr annotation) {
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return isValid(value);
    }

    /**
     * Confere se o argumento é válido,
     * lançando uma exceção em caso negativo.
     *
     * @param name O nome do argumento, para mensagem de erro
     * @param value O valor do argumento
     * @throws ConstraintViolationException Quando o argumento não é válido
     */
    public static void requiredArgValid(@NotNull final String name,
                                @NotNull final String value) throws ConstraintViolationException {

        if (!isValid(value)) {
            throw new ConstraintViolationException(
                    ErrorMessageFactory.of(Error.ARGUMENTO_INVALIDO, name, value)
            );
        }
    }

    /**
     * Confere se o valor dado é um endereço válido
     * de email.
     *
     * @param value Valor a se checar
     * @return Retorna <code>true</code> se for um email válido ou
     *         retornará <code>false</code> se não for.
     *         Se o argumento for <code>null</code> retornará
     *         <code>false</code>
     */
    public static boolean isValid(final String value) {
        if (value == null || value.isEmpty()) return false;

        try {

            InternetAddress.parse(value, false);

            return true;

        } catch (final AddressException ex) {

            return false;

        }
    }

}
