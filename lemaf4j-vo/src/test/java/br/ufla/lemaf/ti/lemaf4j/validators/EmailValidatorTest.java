package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class EmailValidatorTest {

    private static final String VALID_EMAIL = "e@m.c";

    private EmailValidator validatorToTest;

    @Before
    public final void setUp() {
        validatorToTest = new EmailValidator();
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
        assertThat(validatorToTest.isValid("abc@.def")).isFalse();
        assertThat(validatorToTest.isValid("abc@.")).isFalse();
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

    @Test
    public final void shouldThrowRightErrorMessage() {

        try {
            validatorToTest.assertValid("a", "");
            fail();
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O argumento 'a' não é válido: ''");
        }

        try {
            validatorToTest.assertValid("abc@");
            fail();
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O argumento 'Email' não é válido: 'abc@'");
        }

    }

}
