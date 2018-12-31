package br.ufla.lemaf.ti.lemaf4j.converters;

import br.ufla.lemaf.ti.lemaf4j.helpers.Data;
import br.ufla.lemaf.ti.lemaf4j.vo.UserName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fuin.utils4j.JaxbUtils.*;
import static org.junit.Assert.fail;

public class UserNameConverterTest {

    private static final String XML = XML_PREFIX + "<data userName=\"hvpaiva\"/>";
    private static final String VALID_USERNAME = "hvpaiva";
    private static final String INVALID_USERNAME = "1hvpaiva";

    private UserNameConverter converterToTest;

    @Before
    public void setUp() {
        converterToTest = new UserNameConverter();
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
        assertThat(converterToTest.toVO(VALID_USERNAME)).isEqualTo(new UserName(VALID_USERNAME));
    }

    @Test
    public final void shouldTestFromVO() {
        assertThat(converterToTest.fromVO(new UserName(VALID_USERNAME))).isEqualTo(VALID_USERNAME);
    }

    @Test
    public final void shouldTestConvertToDatabaseColumn() {
        assertThat(converterToTest.convertToDatabaseColumn(new UserName(VALID_USERNAME))).isEqualTo(VALID_USERNAME);
    }

    @Test
    public final void shouldTestConvertToEntityAttribute() {
        assertThat(converterToTest.convertToEntityAttribute(VALID_USERNAME)).isEqualTo(new UserName(VALID_USERNAME));
    }

    @Test
    public final void shouldTestIsValid() {
        assertThat(converterToTest.canBeConverted(VALID_USERNAME)).isTrue();
        assertThat(converterToTest.canBeConverted(null)).isFalse();
        assertThat(converterToTest.canBeConverted("")).isFalse();
        assertThat(converterToTest.canBeConverted(INVALID_USERNAME)).isFalse();
    }

    @Test
    public final void shouldTestGetSimpleValueObjectClass() {
        assertThat(converterToTest.getValueObjectClass()).isSameAs(UserName.class);
    }

    @Test
    public final void shouldTestGetSimpleBaseTypeClass() {
        assertThat(converterToTest.getBaseTypeClass()).isSameAs(String.class);
    }

    @Test
    public final void shouldMarshal() {

        final Data data = new Data();
        data.userName = new UserName(VALID_USERNAME);
        assertThat(marshal(data, Data.class)).isEqualTo(XML);

    }

    @Test
    public final void shouldMarshalUnmarshal() {

        final Data data = unmarshal(XML, Data.class);
        assertThat(data.userName).isEqualTo(new UserName(VALID_USERNAME));

    }

    @Test
    public final void shouldUnmarshalError() {

        final String invalidUserNameInXmlData = XML_PREFIX + "<data userName=\"e@\"/>";
        try {
            unmarshal(invalidUserNameInXmlData, Data.class);
            fail("Expected an exception");
        } catch (final RuntimeException ex) {
            assertThat(ex.getCause()).isNotNull();
            assertThat(ex.getCause().getCause()).isNotNull();
            assertThat(ex.getCause().getCause().getMessage())
                    .isEqualTo("O argumento 'userName' não é válido: 'e@'");
        }

    }
}
