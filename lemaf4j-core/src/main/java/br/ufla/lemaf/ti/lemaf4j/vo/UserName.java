package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.types.UserNameStr;
import br.ufla.lemaf.ti.lemaf4j.common.Contract;
import br.ufla.lemaf.ti.lemaf4j.validators.UserNameStrValidator;
import br.ufla.lemaf.ti.lemaf4j.common.AbstractStringValueObject;

import javax.validation.constraints.NotNull;

/**
 * Value Object de UserName:
 * UserName precisa ter:
 * - Entre 3-20 caractere
 * - Letras em caixa-baixa (a-z)
 * - Números (0-9)
 * - Hífens (-)
 * - Underscore (_)
 * - Não começar com hífem, underscore ou número
 */
public final class UserName extends AbstractStringValueObject {

    private static final long serialVersionUID = 9055520843135472634L;

    @NotNull
    @UserNameStr
    private String str;

    /**
     * Construtor padrão protected para deserialização.
     */
    protected UserName() {
        super();
    }

    /**
     * Construtor com o nome de usuário.
     *
     * @param userName User name
     */
    public UserName(@NotNull @UserNameStr final String userName) {
        super();
        Contract.requireArgNotNull("userName", userName);
        UserNameStrValidator.parseArg("userName", userName);
        this.str = userName.trim().toLowerCase();
    }

    @Override
    public final String asBaseType() {
        return str;
    }

    @Override
    public final String toString() {
        return asBaseType();
    }

}
