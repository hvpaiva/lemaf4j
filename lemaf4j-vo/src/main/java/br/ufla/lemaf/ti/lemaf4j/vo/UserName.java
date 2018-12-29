package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.converters.UserNameConverter;
import br.ufla.lemaf.ti.lemaf4j.common.Contract;
import br.ufla.lemaf.ti.lemaf4j.validators.UserNameValidator;
import br.ufla.lemaf.ti.lemaf4j.AbstractStringValueObject;

import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Value Object de UserName.
 * UserName precisa ter:
 * - Entre 3-20 caractere
 * - Letras em caixa-baixa (a-z)
 * - Números (0-9)
 * - Hífens (-)
 * - Underscore (_)
 * - Não começar com hífem, underscore ou número
 */
@Immutable
@XmlJavaTypeAdapter(UserNameConverter.class)
public final class UserName extends AbstractStringValueObject {

    private static final long serialVersionUID = 1L;

    private static UserNameValidator validator = new UserNameValidator();

    private String str;

    /**
     * Construtor protected para deserialização.
     */
    protected UserName() {
        super();
    }

    /**
     * Construtor padrão com o nome de usuário.
     *
     * @param userName User name
     */
    public UserName(@NotNull final String userName) {
        super();
        Contract.requireArgNotNull("userName", userName);

        validator.assertValid("userName", userName);

        this.str = userName.trim().toLowerCase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String asBaseType() {
        return str;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return asBaseType();
    }

}
