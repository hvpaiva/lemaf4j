package br.ufla.lemaf.ti.lemaf4j.converters;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.helpers.Data;
import br.ufla.lemaf.ti.lemaf4j.vo.Email;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fuin.utils4j.JaxbUtils.*;
import static org.junit.Assert.fail;

public class EmailConverterTest {

    private static final String XML = XML_PREFIX + "<data email=\"e@m.c\"/>";
    private static final String VALID_EMAIL = "e@m.c";
    private static final String INVALID_EMAIL = "e@";

    private EmailConverter converterToTest;

    @Before
    public void setUp() {
        converterToTest = new EmailConverter();
    }

    @After
    public void cleanUp() {
        converterToTest = null;
    }

    @Test
    public final void shouldTestFactoryInjectable() {
        assertThat(converterToTest).isNotNull();
    }

    @Test
    public final void shouldTestToVO() {
        assertThat(converterToTest.toVO(VALID_EMAIL)).isEqualTo(new Email(VALID_EMAIL));
        assertThat(converterToTest.toVO(null)).isEqualTo(null);
    }

    @Test
    public final void shouldTestFromVO() {
        assertThat(converterToTest.fromVO(new Email(VALID_EMAIL))).isEqualTo(VALID_EMAIL);
    }

    @Test
    public final void shouldTestConvertToDatabaseColumn() {
        assertThat(converterToTest.convertToDatabaseColumn(new Email(VALID_EMAIL))).isEqualTo(VALID_EMAIL);
    }

    @Test
    public final void shouldTestConvertToEntityAttribute() {
        assertThat(converterToTest.convertToEntityAttribute(VALID_EMAIL)).isEqualTo(new Email(VALID_EMAIL));
    }

    @Test
    public final void shouldTestIsValid() {
        assertThat(converterToTest.canBeConverted(VALID_EMAIL)).isTrue();
        assertThat(converterToTest.canBeConverted(null)).isFalse();
        assertThat(converterToTest.canBeConverted("")).isFalse();
        assertThat(converterToTest.canBeConverted(INVALID_EMAIL)).isFalse();
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConvertInvalidEmail() {
        EmailConverter.toInternetAddress(INVALID_EMAIL);
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConvertMultipleEmails() {
        EmailConverter.toInternetAddress("hi@c.c alt@g.c");
    }

    @Test
    public final void shouldTestGetSimpleValueObjectClass() {
        assertThat(converterToTest.getValueObjectClass()).isSameAs(Email.class);
    }

    @Test
    public final void shouldTestGetSimpleBaseTypeClass() {
        assertThat(converterToTest.getBaseTypeClass()).isSameAs(String.class);
    }

    @Test
    public final void shouldMarshal() {

        final Data data = new Data();
        data.email = new Email(VALID_EMAIL);
        assertThat(marshal(data, Data.class)).isEqualTo(XML);

    }

    @Test
    public final void shouldMarshalUnmarshal() {

        final Data data = unmarshal(XML, Data.class);
        assertThat(data.email).isEqualTo(new Email(VALID_EMAIL));

    }

    @Test
    public final void shouldUnmarshalError() {

        final String invalidEmailAddressInXmlData = XML_PREFIX + "<data email=\"e@\"/>";
        try {
            unmarshal(invalidEmailAddressInXmlData, Data.class);
            fail("Expected an exception");
        } catch (final RuntimeException ex) {
            assertThat(ex.getCause()).isNotNull();
            assertThat(ex.getCause().getCause()).isNotNull();
            assertThat(ex.getCause().getCause().getMessage())
                    .isEqualTo("EmailError: EMAIL INVALIDO 'e@'");
        }

    }
}
