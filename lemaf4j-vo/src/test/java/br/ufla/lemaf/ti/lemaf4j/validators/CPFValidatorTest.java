package br.ufla.lemaf.ti.lemaf4j.validators;

import static org.assertj.core.api.Assertions.assertThat;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CPFValidatorTest {

    private CPFValidator validatorToTest;

    private static final String VALID_CPF_FORMATTED = "248.438.034-80";
    private static final String VALID_CPF_UNFORMATTED = "24843803480";
    private static final String INVALID_CPF_FORMATTED = "020.020.020-20";
    private static final String INVALID_CPF_UNFORMATTED = "02002002020";

    @Before
    public final void setUp() {
        validatorToTest = new CPFValidator();
    }

    @After
    public final void cleanUp() {
        validatorToTest = null;
    }

    @Test
    public final void shouldValidCPFBeTrueIsValid() {
        assertThat(validatorToTest.isValid(VALID_CPF_UNFORMATTED)).isTrue();
        assertThat(validatorToTest.isValid(VALID_CPF_FORMATTED)).isTrue();
    }

    @Test
    public final void shouldInvalidCPFBeFalseIsValid() {
        assertThat(validatorToTest.isValid(INVALID_CPF_UNFORMATTED)).isFalse();
        assertThat(validatorToTest.isValid(INVALID_CPF_FORMATTED)).isFalse();
        assertThat(validatorToTest.isValid("24843803470")).isFalse();
        assertThat(validatorToTest.isValid("09907586561")).isFalse();
    }

    @Test
    public final void shouldNotValidateCPFWithFistDigitWrong() {
        // CPF válido: 248.438.034-80
        assertThat(validatorToTest.isValid("248.438.034-70")).isFalse();
    }

    @Test
    public final void shouldNotValidateCPFWithSecondDigitWrong() {
        // CPF válido: 248.438.034-80
        assertThat(validatorToTest.isValid("248.438.034-81")).isFalse();
    }

    @Test
    public final void shouldBeFalseNullArg() {
        assertThat(validatorToTest.isValid(null)).isFalse();
    }

    @Test
    public final void shouldBeFalseNullEmpty() {
        assertThat(validatorToTest.isValid("")).isFalse();
    }

    @Test
    public final void shouldBeFalseCPFWithLessDigitsThanAllowed() {
        assertThat(validatorToTest.isValid("111111111")).isFalse();
    }

    @Test
    public final void shouldBeFalseCPFWithMoreDigitsThanAllowed() {
        assertThat(validatorToTest.isValid("111111111111111")).isFalse();
    }

    @Test
    public void shouldValidateValidCPF() {
        validatorToTest.assertValid("11144477735");
        validatorToTest.assertValid("88641577947");
        validatorToTest.assertValid("34608514300");
        validatorToTest.assertValid("47393545608");
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldNotValidateInvalidCPF() {
        validatorToTest.assertValid("11144477730");
        validatorToTest.assertValid("88641577940");
        validatorToTest.assertValid("34608514301");
        validatorToTest.assertValid("47393545601");
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldNotValidateNullCPF() {
        validatorToTest.assertValid(null);
    }

    @Test
    public void shouldValidateCPFWithLeadingZeros() {
        validatorToTest.assertValid("01169538452");
    }

    //TODO - testar mensagens de erro (Pulado pois acho que vou trocar como as mensagens funcionam)
}
