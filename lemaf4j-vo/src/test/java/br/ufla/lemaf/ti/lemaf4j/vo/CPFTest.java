package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CPFTest {
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

}
