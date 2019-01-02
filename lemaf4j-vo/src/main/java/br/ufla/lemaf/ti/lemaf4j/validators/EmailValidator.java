package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.ValueObjectValidator;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.common.errors.EmailError;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.MessageProducer;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.SimpleMessageProducer;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.constraints.NotNull;

/**
 * Validator de Email.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class EmailValidator implements ValueObjectValidator<String> {

    private final MessageProducer messageProducer;

    /**
     * Construtor padrão de validador de Email.
     * Este utiliza, por padrão, um {@linkplain SimpleMessageProducer}
     * para geração de mensagens.
     */
    public EmailValidator() {
        this(new SimpleMessageProducer());
    }

    /**
     * Construtor do Validador de Email.
     *
     * @param messageProducer produtor de mensagem de erro
     */
    public EmailValidator(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assertValid(@NotNull final String value) {

        if (!isValid(value)) {
            throw new ConstraintViolationException(
                    messageProducer
                            .messageOf(EmailError.EMAIL_INVALIDO, value)
                            .toString()
            );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String value) {
        if (value == null || value.isEmpty()) return false;

        try {

            InternetAddress.parse(value, true);

            return true;

        } catch (final AddressException ex) {

            return false;

        }
    }

}
