package br.ufla.lemaf.ti.lemaf4j;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractStringValueObjectTest {

    @Test
    public void testEqualsHashCode() {
        EqualsVerifier.forClass(TestStringVO.class).withRedefinedSuperclass()
                .withPrefabValues(AbstractStringValueObject.class, new TestStringVO("a"), new TestStringVO("b"))
                .suppress(Warning.NULL_FIELDS).verify();
    }

    @Test
    public void testBaseType() {
        var testeVO = new TestStringVO("a");

        assertThat(testeVO.getBaseType()).isEqualTo(String.class);
    }

    /** Implementation for tests. */
    public static final class TestStringVO extends AbstractStringValueObject {

        private static final long serialVersionUID = 1L;

        private final String value;

        public TestStringVO(final String value) {
            super();
            this.value = value;
        }

        @Override
        public String asBaseType() {
            return value;
        }

    }

    /** Implementation for tests. */
    public static final class TestStringVO2 extends AbstractStringValueObject {

        private static final long serialVersionUID = 1L;

        private final String value;

        public TestStringVO2(final String value) {
            super();
            this.value = value;
        }

        @Override
        public String asBaseType() {
            return value;
        }

    }

}
