package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.AbstractStringValueObject;
import br.ufla.lemaf.ti.lemaf4j.common.Contract;
import br.ufla.lemaf.ti.lemaf4j.converters.EmailAddressConverter;
import br.ufla.lemaf.ti.lemaf4j.validators.EmailAddressValidator;

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
@XmlJavaTypeAdapter(EmailAddressConverter.class)
public final class EmailAddress extends AbstractStringValueObject {

    private static final long serialVersionUID = 1L;

    private static EmailAddressValidator validator = new EmailAddressValidator();

    private InternetAddress email;

    /**
     * Construtor protected para deserialização.
     */
    protected EmailAddress() {
        super();
    }

    /**
     * Constructor com endereço de email.
     *
     * @param emailAddress Email
     */
    public EmailAddress(@NotNull final String emailAddress) {
        super();
        Contract.requireArgNotEmpty("emailAddress", emailAddress);

        validator.assertValid("emailAddress", emailAddress);

        this.email = EmailAddressConverter.toInternetAddress(emailAddress);
    }

    @Override
    public String asBaseType() {
        return email.toString();
    }

    @Override
    public String toString() {
        return asBaseType();
    }


}
