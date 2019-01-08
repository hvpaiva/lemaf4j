package br.ufla.lemaf.ti.lemaf4j;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractIntegerValueObjectTest {

    @Test
    public void testEqualsHashCode() {
        EqualsVerifier.forClass(TestIntegerVO.class).withRedefinedSuperclass()
                .withPrefabValues(AbstractIntegerValueObject.class, new TestIntegerVO(1), new TestIntegerVO(2))
                .suppress(Warning.NULL_FIELDS).verify();
    }

    @Test
    public void testBaseType() {
        var testeVO = new TestIntegerVO(1);

        assertThat(testeVO.getBaseType()).isEqualTo(Integer.class);
    }

    /** Implementation for tests. */
    public static final class TestIntegerVO extends AbstractIntegerValueObject {

        private static final long serialVersionUID = 1L;

        private final Integer value;

        public TestIntegerVO(final Integer value) {
            super();
            this.value = value;
        }

        @Override
        public Integer asBaseType() {
            return value;
        }

    }

    /** Implementation for tests. */
    public static final class TestIntegerVO2 extends AbstractIntegerValueObject {

        private static final long serialVersionUID = 1L;

        private final Integer value;

        public TestIntegerVO2(final Integer value) {
            super();
            this.value = value;
        }

        @Override
        public Integer asBaseType() {
            return value;
        }

    }

}