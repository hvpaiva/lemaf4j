package br.ufla.lemaf.ti.lemaf4j.formatters;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class CNPJFormatterTest {

	private CNPJFormatter formatterToTest;

	private static final String VALID_FORMATTED_CNPJ = "91.920.787/0001-65";
	private static final String VALID_UNFORMATTED_CNPJ = "91920787000165";
	private static final String INVALID_FORMATTED_CNPJ = "91.920.787/0001-66";
	private static final String INVALID_UNFORMATTED_CNPJ = "91920787000166";

	@Before
	public final void setUp() {
		formatterToTest = new CNPJFormatter();
	}

	@After
	public final void cleanUp() {
		formatterToTest = null;
	}

	@Test
	public final void shouldFormatAValidCNPJ() {
		assertEquals(VALID_FORMATTED_CNPJ, formatterToTest.format(VALID_UNFORMATTED_CNPJ));
	}

	@Test
	public final void shouldUnformatAValidCNPJ() {
		assertEquals(VALID_UNFORMATTED_CNPJ, formatterToTest.unformat(VALID_FORMATTED_CNPJ));
	}

	@Test
	public final void shouldReturnaAnUnformattedCNPJOfAnAlreadyUnformattedCNPJ() {
		assertEquals(VALID_UNFORMATTED_CNPJ, formatterToTest.unformat(VALID_UNFORMATTED_CNPJ));
	}

	@Test
	public final void shouldReturnaAnFormattedCNPJOfAnAlreadyFormattedCNPJ() {
		assertEquals(VALID_FORMATTED_CNPJ, formatterToTest.format(VALID_FORMATTED_CNPJ));
	}

	@Test
	public final void shouldNotFormatObviousInvalidCNPJ() {
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.format(""));
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.format(null));
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.format("111122111111111212"));
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.format("11111"));
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.format("aaaaaa"));
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.format("adasasasdadasdassdffas"));
	}

	@Test
	public final void shouldFormatNonObviousInvalidCNPJ() {
		assertEquals(INVALID_FORMATTED_CNPJ, formatterToTest.format(INVALID_FORMATTED_CNPJ));
		assertEquals(INVALID_FORMATTED_CNPJ, formatterToTest.format(INVALID_UNFORMATTED_CNPJ));

	}

	@Test
	public final void shouldNotUnformatObviousInvalidCNPJ() {
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.unformat(""));
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.unformat(null));
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.unformat("111122111111111212"));
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.unformat("11111"));
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.unformat("aaaaaa"));
		assertThrows(IllegalArgumentException.class, () -> formatterToTest.unformat("adasasasdadasdassdffas"));
	}

	@Test
	public final void shouldUnformatNonObviousInvalidCNPJ() {
		assertEquals(INVALID_UNFORMATTED_CNPJ, formatterToTest.unformat(INVALID_FORMATTED_CNPJ));
		assertEquals(INVALID_UNFORMATTED_CNPJ, formatterToTest.unformat(INVALID_UNFORMATTED_CNPJ));

	}

	@Test
	public final void shouldThrowCorrectNullMessageError() {
		assertThrows(
				"Argument can't be null!",
				IllegalArgumentException.class,
				() -> formatterToTest.format(null)
		);
	}

	@Test
	public final void shouldThrowCorrectInvalidMessageError() {
		assertThrows(
				"Invalid format. Impossible to format!",
				IllegalArgumentException.class,
				() -> formatterToTest.format("aa.aaa.aaa/aaaa-aa")
		);
	}

	@Test
	public final void shouldDetectIfAValueIsFormattedOrNot() {
		assertTrue(formatterToTest.isFormatted(VALID_FORMATTED_CNPJ));
		assertTrue(formatterToTest.isFormatted(INVALID_FORMATTED_CNPJ));
		assertFalse(formatterToTest.isFormatted(INVALID_UNFORMATTED_CNPJ));
		assertFalse(formatterToTest.isFormatted(VALID_UNFORMATTED_CNPJ));
		assertFalse(formatterToTest.isFormatted("1.1.1.1.1.1"));
	}

}
