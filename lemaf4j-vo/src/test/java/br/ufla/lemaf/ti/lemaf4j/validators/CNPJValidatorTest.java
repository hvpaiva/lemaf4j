package br.ufla.lemaf.ti.lemaf4j.validators;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.common.errors.CNPJError;
import br.ufla.lemaf.ti.lemaf4j.common.messaging.SimpleMessageProducer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class CNPJValidatorTest {

	private CNPJValidator validatorToTest;

	private static final String VALID_FORMATTED_CNPJ = "91.920.787/0001-65";
	private static final String VALID_UNFORMATTED_CNPJ = "91920787000165";
	private static final String INVALID_FORMATTED_CNPJ = "91.920.787/0001-66";
	private static final String INVALID_UNFORMATTED_CNPJ = "91920787000166";

	@Before
	public final void setUp() {
		validatorToTest = new CNPJValidator();
	}

	@After
	public final void cleanUp() {
		validatorToTest = null;
	}

	@Test
	public final void shouldValidCNPJBeTrueInIsValid() {
		assertTrue(validatorToTest.isValid(VALID_FORMATTED_CNPJ));
		assertTrue(validatorToTest.isValid(VALID_UNFORMATTED_CNPJ));
	}

	@Test
	public final void shouldInvalidCNPJBeFalseInIsValid() {
		assertFalse(validatorToTest.isValid(INVALID_FORMATTED_CNPJ));
		assertFalse(validatorToTest.isValid(INVALID_UNFORMATTED_CNPJ));
		assertFalse(validatorToTest.isValid("10101010101010"));
		assertFalse(validatorToTest.isValid("10.101.010/1010-10"));
		assertFalse(validatorToTest.isValid(null));
		assertFalse(validatorToTest.isValid(""));
		assertFalse(validatorToTest.isValid("11111111111111"));
		assertFalse(validatorToTest.isValid("11.111.111/1111-11"));
	}

	@Test
	public final void shouldAssertValidCNPJ() {
		validatorToTest.assertValid(VALID_FORMATTED_CNPJ);
		validatorToTest.assertValid(VALID_UNFORMATTED_CNPJ);
	}

	@Test
	public final void shouldAssertInvalidCNPJ() {
		assertThrows(ConstraintViolationException.class, () -> validatorToTest.assertValid(INVALID_FORMATTED_CNPJ));
		assertThrows(ConstraintViolationException.class, () -> validatorToTest.assertValid(INVALID_UNFORMATTED_CNPJ));
		assertThrows(ConstraintViolationException.class, () -> validatorToTest.assertValid(null));
		assertThrows(ConstraintViolationException.class, () -> validatorToTest.assertValid(""));
	}

	@Test
	public final void shouldThrowCorrectSimpleEmptyValidationError() {
		assertThrows(
				"CNPJError: CNPJ_INVALIDO ''",
				ConstraintViolationException.class,
				() -> validatorToTest.assertValid(null)
		);
	}

	@Test
	public final void shouldThrowCorrectSimpleInvalidValidationError() {
		assertThrows(
				"CNPJError: CNPJ_INVALIDO '123'",
				ConstraintViolationException.class,
				() -> validatorToTest.assertValid("123")
		);
	}

	@Test
	public final void testValidationMessage() {
		var messageProducer = new SimpleMessageProducer();

		var digitoInvalido = messageProducer.messageOf(CNPJError.DIGITOS_INVALIDOS).message();
		var formatoInvalido = messageProducer.messageOf(CNPJError.FORMATO_INVALIDO).message();
		var digitosRepetidos = messageProducer.messageOf(CNPJError.DIGITOS_REPETIDOS).message()
				+ "; \n"
				+ messageProducer.messageOf(CNPJError.DIGITOS_VERIFICADORES_INVALIDOS);
		var cnpjInvalido = messageProducer.messageOf(CNPJError.DIGITOS_VERIFICADORES_INVALIDOS).message();

		assertEquals(digitoInvalido, validatorToTest.invalidMessagesFor(""));
		assertEquals(formatoInvalido, validatorToTest.invalidMessagesFor("qrasfq"));
		assertEquals(digitosRepetidos, validatorToTest.invalidMessagesFor("11111111111111"));
		assertEquals(cnpjInvalido, validatorToTest.invalidMessagesFor(INVALID_FORMATTED_CNPJ));
	}

}
