package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.ValueObjectValidator;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.common.errors.UserNameError;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.MessageProducer;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.SimpleMessageProducer;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.ValidationMessage;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Validator do VO de UserName.
 * UserName precisa ter:
 * - Entre 3-20 caractere
 * - Letras em caixa-baixa (a-z)
 * - Números (0-9)
 * - Hífens (-)
 * - Underscore (_)
 * - Não começar com hífem, underscore ou número
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class UserNameValidator implements ValueObjectValidator<String> {

    private static final Integer MAX_LENGTH = 20;
    private static final Integer MIN_LENGTH = 3;

    private static final Pattern PATTERN = Pattern.compile("[a-z][0-9a-z_\\-]*");

    private final MessageProducer messageProducer;

    /**
     * Construtor padrão de validador de UserName.
     * Este utiliza, por padrão, um {@linkplain SimpleMessageProducer}
     * para geração de mensagens.
     */
    public UserNameValidator() {
        this(new SimpleMessageProducer());
    }

    /**
     * Construtor do Validador de UserName.
     *
     * @param messageProducer produtor de mensagem de erro
     */
    public UserNameValidator(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String value) {
        return invalidMessagesFor(value).isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assertValid(@NotNull final String value) {
        if (!isValid(value))
            throw new ConstraintViolationException(
                    messageProducer
                            .messageOf(UserNameError.USERNAME_INVALIDO, value)
                            .toString()
            );
    }

    /**
     * Valida o Username e retorna uma lista de erros.
     *
     * @param username O username a se validar
     * @return Uma lista de erros do username
     */
    public List<ValidationMessage> invalidMessagesFor(String username) {
        List<ValidationMessage> errors = new ArrayList<>();
        if (username == null || username.isEmpty()) {
            errors.add(messageProducer.messageOf(UserNameError.USERNAME_INVALIDO));

        } else {

            if (username.length() > MAX_LENGTH)
                errors.add(messageProducer.messageOf(UserNameError.TAMANHO_MAXIMO_EXCEDIDO, username.length()));

            if (username.length() < MIN_LENGTH)
                errors.add(messageProducer.messageOf(UserNameError.TAMANHO_MINIMO_NAO_ATINGIDO, username.length()));

            if (!PATTERN.matcher(username.toLowerCase()).matches())
                errors.add(messageProducer.messageOf(UserNameError.USERNAME_INVALIDO, username));

        }

        return errors;

    }
}
