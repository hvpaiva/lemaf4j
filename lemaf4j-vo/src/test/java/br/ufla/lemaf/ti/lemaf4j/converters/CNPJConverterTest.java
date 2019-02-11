package br.ufla.lemaf.ti.lemaf4j.converters;

import br.ufla.lemaf.ti.lemaf4j.helpers.Data;
import br.ufla.lemaf.ti.lemaf4j.vo.CNPJ;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.fuin.utils4j.JaxbUtils.XML_PREFIX;
import static org.fuin.utils4j.JaxbUtils.marshal;
import static org.fuin.utils4j.JaxbUtils.unmarshal;
import static org.junit.Assert.*;

public class CNPJConverterTest {

	private static final String XML = XML_PREFIX + "<data cnpj=\"91920787000165\"/>";

	private static final String VALID_FORMATTED_CNPJ = "91.920.787/0001-65";
	private static final String VALID_UNFORMATTED_CNPJ = "91920787000165";
	private static final String INVALID_FORMATTED_CNPJ = "91.920.787/0001-66";
	private static final String INVALID_UNFORMATTED_CNPJ = "91920787000166";


	private CNPJConverter converterToTest;

	@Before
	public void setUp() {
		converterToTest = new CNPJConverter();
	}

	@After
	public void cleanUp() {
		converterToTest = null;
	}

	@Test
	public final void shouldTestFactortInjectable() {
		assertNotNull(converterToTest);
	}

	@Test
	public final void shouldTestToVO() {
		assertEquals(converterToTest.toVO(VALID_FORMATTED_CNPJ), new CNPJ(VALID_FORMATTED_CNPJ));
		assertEquals(converterToTest.toVO(VALID_UNFORMATTED_CNPJ), new CNPJ(VALID_FORMATTED_CNPJ));
		assertEquals(converterToTest.toVO(VALID_FORMATTED_CNPJ), new CNPJ(VALID_UNFORMATTED_CNPJ));
		assertEquals(converterToTest.toVO(VALID_UNFORMATTED_CNPJ), new CNPJ(VALID_UNFORMATTED_CNPJ));
		assertNull(converterToTest.toVO(null));
	}

	@Test
	public final void shouldTestfromVO() {
		assertEquals(converterToTest.fromVO(new CNPJ(VALID_FORMATTED_CNPJ)), VALID_UNFORMATTED_CNPJ);
		assertEquals(converterToTest.fromVO(new CNPJ(VALID_UNFORMATTED_CNPJ)), VALID_UNFORMATTED_CNPJ);
	}

	@Test
	public final void shouldTestConvertToDatabaseColumn() {
		assertEquals(converterToTest.convertToDatabaseColumn(new CNPJ(VALID_UNFORMATTED_CNPJ)), VALID_UNFORMATTED_CNPJ);
		assertEquals(converterToTest.convertToDatabaseColumn(new CNPJ(VALID_UNFORMATTED_CNPJ)), VALID_UNFORMATTED_CNPJ);
	}

	@Test
	public final void shouldTestConvertToEntityAttribute() {
		assertEquals(converterToTest.convertToEntityAttribute(VALID_FORMATTED_CNPJ), new CNPJ(VALID_FORMATTED_CNPJ));
		assertEquals(converterToTest.convertToEntityAttribute(VALID_UNFORMATTED_CNPJ), new CNPJ(VALID_FORMATTED_CNPJ));
		assertEquals(converterToTest.convertToEntityAttribute(VALID_FORMATTED_CNPJ), new CNPJ(VALID_UNFORMATTED_CNPJ));
		assertEquals(converterToTest.convertToEntityAttribute(VALID_UNFORMATTED_CNPJ), new CNPJ(VALID_UNFORMATTED_CNPJ));
	}

	@Test
	public final void shouldTestcanBeConverted() {
		assertTrue(converterToTest.canBeConverted(VALID_FORMATTED_CNPJ));
		assertTrue(converterToTest.canBeConverted(VALID_UNFORMATTED_CNPJ));
		assertFalse(converterToTest.canBeConverted(INVALID_FORMATTED_CNPJ));
		assertFalse(converterToTest.canBeConverted(INVALID_UNFORMATTED_CNPJ));
		assertFalse(converterToTest.canBeConverted(null));
		assertFalse(converterToTest.canBeConverted(""));
	}

	@Test
	public final void shouldTestGetBaseTypeClass() {
		assertSame(converterToTest.getBaseTypeClass(), String.class);
	}

	@Test
	public final void shouldTestGetValueObjectClass() {
		assertSame(converterToTest.getValueObjectClass(), CNPJ.class);
	}

	@Test
	public final void shouldMarshal() {
		final Data data = new Data();
		data.cnpj = new CNPJ(VALID_FORMATTED_CNPJ);

		assertEquals(marshal(data, Data.class), XML);
	}

	@Test
	public final void shouldMarshalUnmarshal() {
		final Data data = unmarshal(XML, Data.class);

		assertEquals(data.cnpj, new CNPJ(VALID_UNFORMATTED_CNPJ));
	}

	@Test
	public final void shouldUnmarshalError() {
		final String invalidCNPJXmlData = XML_PREFIX + "<data cnpj=\"0000000000000000\"/>";

		try {
			unmarshal(invalidCNPJXmlData, Data.class);
			fail("Expected an exception");
		} catch (final RuntimeException ex) {
			assertNotNull(ex.getCause());
			assertNotNull(ex.getCause().getCause());
			assertEquals(ex.getCause().getCause().getMessage(), "CNPJError: CNPJ INVALIDO '0000000000000000'");
		}
	}
}
