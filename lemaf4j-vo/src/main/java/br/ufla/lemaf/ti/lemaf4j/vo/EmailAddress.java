package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.common.AbstractStringValueObject;
import br.ufla.lemaf.ti.lemaf4j.common.Contract;
import br.ufla.lemaf.ti.lemaf4j.converters.EmailAddressConverter;
import br.ufla.lemaf.ti.lemaf4j.types.EmailAddressStr;
import br.ufla.lemaf.ti.lemaf4j.validators.EmailAddressStrValidator;

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

    private static final long serialVersionUID = 811127657088134517L;

    @NotNull
    private InternetAddress email;

    /**
     * Construtor padrão protected para deserialização.
     */
    protected EmailAddress() {
        super();
    }

    /**
     * Constructor com endereço de email.
     *
     * @param emailAddress Email
     */
    public EmailAddress(@NotNull @EmailAddressStr final String emailAddress) {
        super();
        Contract.requireArgNotEmpty("emailAddress", emailAddress);
        EmailAddressStrValidator.requiredArgValid("emailAddress", emailAddress);
        this.email = EmailAddressConverter.toInternetAddress(emailAddress);
    }

    @Override
    public final String asBaseType() {
        return email.toString();
    }

    @Override
    public final String toString() {
        return asBaseType();
    }


}
