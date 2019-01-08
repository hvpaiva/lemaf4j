package br.ufla.lemaf.ti.lemaf4j;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractLongValueObjectTest {

    @Test
    public void testEqualsHashCode() {
        EqualsVerifier.forClass(TestLongVO.class).withRedefinedSuperclass()
                .withPrefabValues(AbstractLongValueObject.class, new TestLongVO(1L), new TestLongVO(2L))
                .suppress(Warning.NULL_FIELDS).verify();
    }

    @Test
    public void testBaseType() {
        var testeVO = new TestLongVO(1L);

        assertThat(testeVO.getBaseType()).isEqualTo(Long.class);
    }

        /** Implementation for tests. */
    public static final class TestLongVO extends AbstractLongValueObject {

        private static final long serialVersionUID = 1L;

        private final Long value;

        public TestLongVO(final Long value) {
            super();
            this.value = value;
        }

        @Override
        public Long asBaseType() {
            return value;
        }

    }

    /** Implementation for tests. */
    public static final class TestLongVO2 extends AbstractLongValueObject {

        private static final long serialVersionUID = 1L;

        private final Long value;

        public TestLongVO2(final Long value) {
            super();
            this.value = value;
        }

        @Override
        public Long asBaseType() {
            return value;
        }

    }

}
