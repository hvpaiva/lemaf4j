package br.ufla.lemaf.ti.lemaf4j.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fuin.utils4j.Utils4J.deserialize;
import static org.fuin.utils4j.Utils4J.serialize;

import br.ufla.lemaf.ti.lemaf4j.helpers.CPFParentEntity;
import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import org.fuin.units4j.AbstractPersistenceTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CPFTest extends AbstractPersistenceTest {

    @Test
    public final void shouldSerialize() {
        final String cpf = "114.582.016-60";
        final CPF original = new CPF(cpf);
        final CPF copy = deserialize(serialize(original));
        assertThat(original).isEqualTo(copy);
    }

    @Test
    public void shouldAcceptValidFormattedCPF() {
        CPF cpf = new CPF("114.582.016-60");
        assertEquals("11458201660", cpf.unformatted());
        assertEquals("114.582.016-60", cpf.formatted());
    }

    @Test
    public void shouldAcceptValidUnformattedCPF() {
        CPF cpf = new CPF("11458201660");
        assertEquals("11458201660", cpf.unformatted());
        assertEquals("114.582.016-60", cpf.formatted());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldNotAcceptInvalidCPF() {
        new CPF("843.843.131-84");
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldNotAcceptRepeatedCPFDigits() {
        new CPF("11111111111");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotAcceptCreateEmpty() {
        new CPF("");
    }

    @Test
    public void shouldHaveVOEquality() {
        CPF a = new CPF("11458201660");
        CPF b = new CPF("11458201660");
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    public void shouldReturnTheRightBaseType() {
        CPF cpf = new CPF("114.582.016-60");
        CPF cpf2 = new CPF("11458201660");

        assertEquals("11458201660", cpf.asBaseType());
        assertEquals("11458201660", cpf2.asBaseType());
    }

    @Test
    public void testJPA() {

        // PREPARE
        beginTransaction();
        getEm().persist(new CPFParentEntity(1));
        commitTransaction();

        // TEST UPDATE
        beginTransaction();
        final CPFParentEntity entity = getEm().find(CPFParentEntity.class, 1L);
        entity.setCPF(new CPF("11458201660"));
        commitTransaction();

        // VERIFY
        beginTransaction();
        final CPFParentEntity copy = getEm().find(CPFParentEntity.class, 1L);
        assertThat(copy).isNotNull();
        assertThat(copy.getId()).isEqualTo(1);
        assertThat(copy.getCPF()).isNotNull();
        assertThat(copy.getCPF().toString()).isEqualTo("11458201660");
        commitTransaction();

    }

}
