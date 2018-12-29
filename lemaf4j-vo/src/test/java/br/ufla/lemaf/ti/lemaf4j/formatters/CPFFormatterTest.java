package br.ufla.lemaf.ti.lemaf4j.formatters;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CPFFormatterTest {

    private CPFFormatter formatterToTest;

    private static final String VALID_CPF_FORMATTED = "248.438.034-80";
    private static final String VALID_CPF_UNFORMATTED = "24843803480";

    @Before
    public final void setUp() {
        formatterToTest = new CPFFormatter();
    }

    @After
    public final void cleanUp() {
        formatterToTest = null;
    }

    @Test
    public void shouldFormatAValidCPF() {
        assertEquals(VALID_CPF_FORMATTED, formatterToTest.format(VALID_CPF_UNFORMATTED));
    }

    @Test
    public void shouldUnformatAValidCPF() {
        assertEquals(VALID_CPF_UNFORMATTED, formatterToTest.unformat(VALID_CPF_FORMATTED));
    }

    @Test
    public void shouldReturnAnUnformattedCPFOfAAlreadyUnformattedCPF() {
        assertEquals(VALID_CPF_UNFORMATTED, formatterToTest.unformat(VALID_CPF_UNFORMATTED));
    }

    @Test
    public void shouldReturnAnFormattedCPFOfAAlreadyFormattedCPF() {
        assertEquals(VALID_CPF_FORMATTED, formatterToTest.format(VALID_CPF_FORMATTED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFormatObviousInvalidsCPF() {
        formatterToTest.format("");
        formatterToTest.format(null);
        formatterToTest.format("123456789012345");
        formatterToTest.format("123456789");
        formatterToTest.unformat("aaa.aaa.aaa-aa");
        formatterToTest.unformat("aaaaaaaaaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUnormatObviousInvalidsCPF() {
        formatterToTest.unformat("");
        formatterToTest.unformat(null);
        formatterToTest.unformat("123456789012345");
        formatterToTest.unformat("123456789");
        formatterToTest.unformat("aaa.aaa.aaa-aa");
        formatterToTest.unformat("aaaaaaaaaaa");

    }

    @Test
    public void shouldFormatNonObviousInvalidsCPF() {
        formatterToTest.format("00000000000");
        formatterToTest.format("000.000.000-00");
        formatterToTest.format("12345678912");
        formatterToTest.format("123.456.789-12");
    }

    @Test
    public void shouldUnformatNonObviousInvalidsCPF() {
        formatterToTest.unformat("00000000000");
        formatterToTest.unformat("000.000.000-00");
        formatterToTest.unformat("12345678912");
        formatterToTest.unformat("123.456.789-12");
    }

    @Test
    public void shouldDetectIfAValueIsFormattedOrNot() {
        assertTrue(formatterToTest.isFormatted("111.222.333-44"));
        assertFalse(formatterToTest.isFormatted("aaa.aaa.aaa-aa"));
        assertFalse(formatterToTest.isFormatted("11122233344"));
        assertFalse(formatterToTest.isFormatted("1.1a1.1-2"));
    }

    @Test
    public void shouldDetectIfAValueCanBeFormattedOrNot() {
        assertTrue(formatterToTest.canBeFormatted("11122233344"));
        assertFalse(formatterToTest.canBeFormatted("aaaaaaaaaaa"));
        assertFalse(formatterToTest.canBeFormatted("111.222.333-44"));
        assertFalse(formatterToTest.canBeFormatted("1.1a1.1-2"));
    }
}
