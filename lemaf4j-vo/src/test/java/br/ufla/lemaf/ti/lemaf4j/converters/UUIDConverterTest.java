package br.ufla.lemaf.ti.lemaf4j.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fuin.utils4j.JaxbUtils.*;
import static org.fuin.utils4j.JaxbUtils.unmarshal;
import static org.junit.Assert.fail;

import java.util.UUID;

import br.ufla.lemaf.ti.lemaf4j.helpers.Data;
import br.ufla.lemaf.ti.lemaf4j.helpers.UUIDParentEntity;
import org.fuin.units4j.AbstractPersistenceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UUIDConverterTest extends AbstractPersistenceTest {

    private static final String VALID_UUID_STRING = "ec5468bd-9cd9-4a0f-a502-96190f6a0995";
    private static final String XML = XML_PREFIX + "<data uuid=\"ec5468bd-9cd9-4a0f-a502-96190f6a0995\"/>";
    private static final UUID VALID_UUID = UUID.fromString(VALID_UUID_STRING);

    private UUIDConverter converterToTest;

    @Before
    public void setUp() {
        converterToTest = new UUIDConverter();
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
    public void testMarshal() throws Exception {

        // TEST & VERIFY
        assertThat(converterToTest.marshal(VALID_UUID)).isEqualTo(VALID_UUID_STRING);
        assertThat(converterToTest.marshal(null)).isNull();

    }

    @Test
    public void testUnmarshal() throws Exception {

        // TEST & VERIFY
        assertThat(converterToTest.unmarshal(VALID_UUID_STRING)).isEqualTo(VALID_UUID);
        assertThat(converterToTest.unmarshal(null)).isNull();

    }

    @Test
    public final void shouldMarshal() {

        final Data data = new Data();
        data.uuid = VALID_UUID;
        assertThat(marshal(data, Data.class)).isEqualTo(XML);

    }

    @Test
    public final void shouldMarshalUnmarshal() {

        final Data data = unmarshal(XML, Data.class);
        assertThat(data.uuid).isEqualTo(VALID_UUID);

    }

    @Test
    public final void testJPA() {

        // PREPARE
        beginTransaction();
        getEm().persist(new UUIDParentEntity(1));
        commitTransaction();

        // TEST UPDATE
        final UUID uuid = UUID.randomUUID();
        beginTransaction();
        final UUIDParentEntity entity = getEm().find(UUIDParentEntity.class, 1L);
        entity.setUUID(uuid);
        commitTransaction();

        // VERIFY
        beginTransaction();
        final UUIDParentEntity copy = getEm().find(UUIDParentEntity.class, 1L);
        assertThat(copy).isNotNull();
        assertThat(copy.getId()).isEqualTo(1);
        assertThat(copy.getUUID()).isNotNull();
        assertThat(copy.getUUID()).isEqualTo(uuid);
        commitTransaction();

    }

}
