package br.ufla.lemaf.ti.lemaf4j.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fuin.utils4j.JaxbUtils.XML_PREFIX;
import static org.fuin.utils4j.JaxbUtils.marshal;
import static org.fuin.utils4j.JaxbUtils.unmarshal;
import static org.junit.Assert.fail;

import br.ufla.lemaf.ti.lemaf4j.helpers.Data;
import br.ufla.lemaf.ti.lemaf4j.vo.CPF;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CPFConverterTest {

    private static final String XML = XML_PREFIX + "<data cpf=\"11458201660\"/>";

    private CPFConverter converterToTest;

    @Before
    public void setUp() {
        converterToTest = new CPFConverter();
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
        assertThat(converterToTest.toVO("114.582.016-60")).isEqualTo(new CPF("114.582.016-60"));
        assertThat(converterToTest.toVO("11458201660")).isEqualTo(new CPF("114.582.016-60"));
        assertThat(converterToTest.toVO("11458201660")).isEqualTo(new CPF("11458201660"));
        assertThat(converterToTest.toVO("114.582.016-60")).isEqualTo(new CPF("11458201660"));
        assertThat(converterToTest.toVO(null)).isEqualTo(null);
    }

    @Test
    public final void shouldTestFromVO() {
        assertThat(converterToTest.fromVO(new CPF("114.582.016-60"))).isEqualTo("11458201660");
        assertThat(converterToTest.fromVO(new CPF("11458201660"))).isEqualTo("11458201660");
    }

    @Test
    public final void shouldTestConvertToDatabaseColumn() {
        assertThat(converterToTest.convertToDatabaseColumn(new CPF("114.582.016-60"))).isEqualTo("11458201660");
        assertThat(converterToTest.convertToDatabaseColumn(new CPF("11458201660"))).isEqualTo("11458201660");
    }

    @Test
    public final void shouldTestConvertToEntityAttribute() {
        assertThat(converterToTest.convertToEntityAttribute("114.582.016-60")).isEqualTo(new CPF("114.582.016-60"));
        assertThat(converterToTest.convertToEntityAttribute("11458201660")).isEqualTo(new CPF("114.582.016-60"));
        assertThat(converterToTest.convertToEntityAttribute("11458201660")).isEqualTo(new CPF("11458201660"));
        assertThat(converterToTest.convertToEntityAttribute("114.582.016-60")).isEqualTo(new CPF("11458201660"));
    }

    @Test
    public final void shouldTestIsValid() {
        assertThat(converterToTest.canBeConverted("114.582.016-60")).isTrue();
        assertThat(converterToTest.canBeConverted("11458201660")).isTrue();
        assertThat(converterToTest.canBeConverted(null)).isFalse();
        assertThat(converterToTest.canBeConverted("")).isFalse();
    }

    @Test
    public final void shouldTestGetSimpleValueObjectClass() {
        assertThat(converterToTest.getValueObjectClass()).isSameAs(CPF.class);
    }

    @Test
    public final void shouldTestGetSimpleBaseTypeClass() {
        assertThat(converterToTest.getBaseTypeClass()).isSameAs(String.class);
    }

    @Test
    public final void shouldMarshal() {

        final Data data = new Data();
        data.cpf = new CPF("11458201660");
        assertThat(marshal(data, Data.class)).isEqualTo(XML);

    }

    @Test
    public final void shouldMarshalUnmarshal() {

        final Data data = unmarshal(XML, Data.class);
        assertThat(data.cpf).isEqualTo(new CPF("11458201660"));

    }

    @Test
    public final void shouldUnmarshalError() {

        final String invalidCPFInXmlData = XML_PREFIX + "<data cpf=\"0000000\"/>";
        try {
            unmarshal(invalidCPFInXmlData, Data.class);
            fail("Expected an exception");
        } catch (final RuntimeException ex) {
            assertThat(ex.getCause()).isNotNull();
            assertThat(ex.getCause().getCause()).isNotNull();
            assertThat(ex.getCause().getCause().getMessage())
                    .isEqualTo("O argumento 'CPF' não é válido: '0000000'");
        }

    }

}
