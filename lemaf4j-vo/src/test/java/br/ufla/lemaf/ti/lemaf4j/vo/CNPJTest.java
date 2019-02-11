package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.helpers.CNPJParentEntity;
import org.fuin.units4j.AbstractPersistenceTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fuin.utils4j.Utils4J.deserialize;
import static org.fuin.utils4j.Utils4J.serialize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CNPJTest extends AbstractPersistenceTest {

	private static final String VALID_FORMATTED_CNPJ = "91.920.787/0001-65";
	private static final String VALID_UNFORMATTED_CNPJ = "91920787000165";
	private static final String INVALID_FORMATTED_CNPJ = "91.920.787/0001-66";
	private static final String INVALID_UNFORMATTED_CNPJ = "91920787000166";

	@Test
	public final void shouldSerialize() {
		final String cnpj = VALID_FORMATTED_CNPJ;
		final CNPJ original = new CNPJ(cnpj);
		final CNPJ copy = deserialize(serialize(original));

		assertEquals(original, copy);
	}

	@Test
	public void shouldAcceptValidFormattedCNPJ() {
		CNPJ cnpj = new CNPJ(VALID_FORMATTED_CNPJ);

		assertEquals(VALID_UNFORMATTED_CNPJ, cnpj.unformatted());
		assertEquals(VALID_FORMATTED_CNPJ, cnpj.formatted());
	}

	@Test
	public void shouldAcceptValidUnformattedCNPJ() {
		CNPJ cnpj = new CNPJ(VALID_UNFORMATTED_CNPJ);

		assertEquals(VALID_UNFORMATTED_CNPJ, cnpj.unformatted());
		assertEquals(VALID_FORMATTED_CNPJ, cnpj.formatted());
	}

	@Test(expected = ConstraintViolationException.class)
	public void shouldNotAcceptInvalidCNPJ() {
		new CNPJ(INVALID_FORMATTED_CNPJ);
		new CNPJ(INVALID_UNFORMATTED_CNPJ);
	}

	@Test(expected = ConstraintViolationException.class)
	public void shouldNotAcceptRepeatedCNPJDigits() {
		new CNPJ("11111111111111");
	}

	@Test(expected = ConstraintViolationException.class)
	public void shouldNotAcceptCreateEmpty() {
		new CNPJ("");
	}

	@Test(expected = ConstraintViolationException.class)
	public void shouldNotAcceptCreateNull() {
		new CNPJ(null);
	}

	@Test
	public void shouldHaveVOEquality() {
		CNPJ a = new CNPJ(VALID_FORMATTED_CNPJ);
		CNPJ b = new CNPJ(VALID_FORMATTED_CNPJ);

		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
	}

	@Test
	public void shouldReturnTheRightBaseType() {
		CNPJ a = new CNPJ(VALID_FORMATTED_CNPJ);
		CNPJ b = new CNPJ(VALID_UNFORMATTED_CNPJ);

		assertEquals(VALID_UNFORMATTED_CNPJ, a.asBaseType());
		assertEquals(VALID_UNFORMATTED_CNPJ, b.asBaseType());
	}

	@Test
	public void testJPA() {

		// PREPARE
		beginTransaction();
		getEm().persist(new CNPJParentEntity(1));
		commitTransaction();

		// TEST UPDATE
		beginTransaction();
		final CNPJParentEntity entity = getEm().find(CNPJParentEntity.class, 1L);
		entity.setCnpj(new CNPJ(VALID_UNFORMATTED_CNPJ));
		commitTransaction();

		// VERIFY
		beginTransaction();
		final CNPJParentEntity copy = getEm().find(CNPJParentEntity.class, 1L);
		assertThat(copy).isNotNull();
		assertThat(copy.getId()).isEqualTo(1);
		assertThat(copy.getCnpj()).isNotNull();
		assertThat(copy.getCnpj().toString()).isEqualTo(VALID_UNFORMATTED_CNPJ);
		commitTransaction();

	}
}
