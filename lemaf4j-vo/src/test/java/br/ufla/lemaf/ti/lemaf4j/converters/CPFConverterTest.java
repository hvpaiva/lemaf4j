package br.ufla.lemaf.ti.lemaf4j.converters;

import static org.assertj.core.api.Assertions.assertThat;

import br.ufla.lemaf.ti.lemaf4j.vo.CPF;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CPFConverterTest {

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

    // TODO - testar conversor marshall e unmarshall

}
