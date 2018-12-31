package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.AbstractStringValueObject;
import br.ufla.lemaf.ti.lemaf4j.common.Contract;
import br.ufla.lemaf.ti.lemaf4j.converters.EmailConverter;
import br.ufla.lemaf.ti.lemaf4j.validators.EmailValidator;

import javax.annotation.concurrent.Immutable;
import javax.mail.internet.InternetAddress;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * VO representando um endereço válido de e-mail.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Immutable
@XmlJavaTypeAdapter(EmailConverter.class)
public final class Email extends AbstractStringValueObject {

    private static final long serialVersionUID = 1L;

    private static EmailValidator validator = new EmailValidator();

    private InternetAddress emailStr;

    /**
     * Construtor protected para deserialização.
     */
    protected Email() {
        super();
    }

    /**
     * Constructor com endereço de email.
     *
     * @param emailAddress Email
     */
    public Email(@NotNull final String emailAddress) {
        super();
        Contract.requireArgNotEmpty("emailAddress", emailAddress);

        validator.assertValid("emailAddress", emailAddress);

        this.emailStr = EmailConverter.toInternetAddress(emailAddress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String asBaseType() {
        return emailStr.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return asBaseType();
    }


}
