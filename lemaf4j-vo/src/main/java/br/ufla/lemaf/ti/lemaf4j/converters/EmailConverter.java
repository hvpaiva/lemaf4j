package br.ufla.lemaf.ti.lemaf4j.converters;

import br.ufla.lemaf.ti.lemaf4j.AbstractValueObjectConverter;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.common.errors.EmailError;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.MessageProducer;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.SimpleMessageProducer;
import br.ufla.lemaf.ti.lemaf4j.validators.EmailValidator;
import br.ufla.lemaf.ti.lemaf4j.vo.Email;

import javax.annotation.concurrent.ThreadSafe;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.Converter;
import javax.validation.constraints.NotNull;

/**
 * Conversor do VO Email de/para String.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@ThreadSafe
@Converter(autoApply = true)
public class EmailConverter
        extends AbstractValueObjectConverter<String, Email> {

    private EmailValidator validator;
    private static MessageProducer producer;

    /**
     * Construtor de EmailConverter.
     * Injeta a dependência de EmailAddressValidador, e
     * usa um producer personalizado.
     *
     * @param messageProducer O producer de mensagem
     */
    public EmailConverter(MessageProducer messageProducer) {
        setProducer(messageProducer);
        this.validator = new EmailValidator();
    }

    /**
     * Construtor padrão do EmailConverter.
     * Injeta a dependência de EmailAddressValidador, e
     * cria o SimpleMessageProducer como padrão.
     */
    public EmailConverter() {
        this(new SimpleMessageProducer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<String> getBaseTypeClass() {
        return String.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<Email> getValueObjectClass() {
        return Email.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean canBeConverted(final String value) {
        return validator.isValid(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Email toVO(final String value) {
        if (value == null) return null;

        return new Email(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String fromVO(final Email value) {
        if (value == null) return null;

        return value.toString();
    }

    /**
     * Converte a String de email para o tipo InternetAddress.
     *
     * @param value A String de email
     * @return O email do tipo InternetAddress
     */
    public static InternetAddress toInternetAddress(@NotNull final String value) {

        final var trimmedLowerCaseValue = value.trim().toLowerCase();
        try {

            final var addr = InternetAddress.parse(trimmedLowerCaseValue, false);

            if (addr.length != 1) {
                throw new ConstraintViolationException(
                        producer
                                .messageOf(EmailError.MULTIPLOS_EMAILS, value)
                                .message()
                );
            }
            return addr[0];

        } catch (final AddressException ex) {

            throw new ConstraintViolationException(
                    producer
                            .messageOf(EmailError.EMAIL_INVALIDO, value)
                            .message()
            );
        }

    }

    /**
     * Setter static de producer.
     *
     * @param producer O producer
     */
    public static void setProducer(MessageProducer producer) {
        EmailConverter.producer = producer;
    }
}
