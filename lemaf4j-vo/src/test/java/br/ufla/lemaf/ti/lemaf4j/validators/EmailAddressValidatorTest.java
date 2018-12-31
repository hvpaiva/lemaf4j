package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailAddressValidatorTest {

    private static final String VALID_EMAIL = "e@m.c";

    private EmailAddressValidator validatorToTest;

    @Before
    public final void setUp() {
        validatorToTest = new EmailAddressValidator();
    }

    @After
    public final void cleanUp() {
        validatorToTest = null;
    }

    @Test
    public final void shouldValidEmailBeTrue() {
        assertThat(validatorToTest.isValid(VALID_EMAIL)).isTrue();
        assertThat(validatorToTest.isValid("E@M.C")).isTrue();
    }

    @Test
    public final void shouldInvalidEmailBeFalse() {
        assertThat(validatorToTest.isValid("")).isFalse();
        assertThat(validatorToTest.isValid(null)).isFalse();
        assertThat(validatorToTest.isValid("e@")).isFalse();
        assertThat(validatorToTest.isValid("e @ c . d")).isFalse();
    }

    @Test
    public void shouldValidateValidEmail() {
        validatorToTest.assertValid(VALID_EMAIL);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldNotValidateInvalidCPF() {
        validatorToTest.assertValid("");
        validatorToTest.assertValid(null);
        validatorToTest.assertValid("e@");
        validatorToTest.assertValid("e @ c .d");
    }

}
