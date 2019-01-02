package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class UserNameValidatorTest {

    private static final String VALID_USERNAME = "hvPaiva1994";

    private UserNameValidator validatorToTest;

    @Before
    public final void setUp() {
        validatorToTest = new UserNameValidator();
    }

    @After
    public final void cleanUp() {
        validatorToTest = null;
    }

    @Test
    public final void shouldValidEmailBeTrue() {
        assertThat(validatorToTest.isValid(VALID_USERNAME)).isTrue();
        assertThat(validatorToTest.isValid("michael8901234567890")).isTrue();
        assertThat(validatorToTest.isValid("michael8901234567890")).isTrue();
        assertThat(validatorToTest.isValid("michael-1_a")).isTrue();
        assertThat(validatorToTest.isValid("MICHAEL-1_a")).isTrue();
    }

    @Test
    public final void shouldInvalidEmailBeFalse() {
        assertThat(validatorToTest.isValid("")).isFalse();
        assertThat(validatorToTest.isValid(null)).isFalse();
        assertThat(validatorToTest.isValid("e@")).isFalse();
        assertThat(validatorToTest.isValid("eb")).isFalse();
        assertThat(validatorToTest.isValid("a12345678901234567890")).isFalse();
        assertThat(validatorToTest.isValid("1ab")).isFalse();
        assertThat(validatorToTest.isValid("_abc")).isFalse();
        assertThat(validatorToTest.isValid("-abc")).isFalse();
        assertThat(validatorToTest.isValid("abc#1")).isFalse();
        assertThat(validatorToTest.isValid("abc@def.com")).isFalse();
    }

    @Test
    public void shouldValidateValidEmail() {
        validatorToTest.assertValid(VALID_USERNAME);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldNotValidateInvalidCPF() {
        validatorToTest.assertValid("");
        validatorToTest.assertValid(null);
        validatorToTest.assertValid("e@");
        validatorToTest.assertValid("eb");
        validatorToTest.assertValid("a12345678901234567890");
        validatorToTest.assertValid("1ab");
        validatorToTest.assertValid("_abc");
        validatorToTest.assertValid("-abc");
        validatorToTest.assertValid("abc#1");
        validatorToTest.assertValid("abc@def.com");
    }

    @Test
    public final void shouldThrowRightErrorMessage() {

        try {
            validatorToTest.assertValid("");
            fail();
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("UserNameError: USERNAME INVALIDO ''");
        }

        try {
            validatorToTest.assertValid("abc@");
            fail();
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("UserNameError: USERNAME INVALIDO 'abc@'");
        }

    }

}
