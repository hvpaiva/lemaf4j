package br.ufla.lemaf.ti.lemaf4j.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fuin.utils4j.JaxbUtils.*;
import static org.junit.Assert.fail;

import java.util.Locale;

import br.ufla.lemaf.ti.lemaf4j.helpers.Data;
import br.ufla.lemaf.ti.lemaf4j.helpers.LocaleParentEntity;
import org.fuin.units4j.AbstractPersistenceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LocaleConverterTest extends AbstractPersistenceTest {

    private static final String VALID_LOCALE = "en";

    private LocaleConverter converterToTest;

    @Before
    public void setUp() {
        converterToTest = new LocaleConverter();
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
    public final void shouldTestConvertToDatabaseColumn() {
        assertThat(converterToTest.convertToDatabaseColumn(new Locale(VALID_LOCALE))).isEqualTo(VALID_LOCALE);
    }

    @Test
    public final void shouldTestConvertToEntityAttribute() {
        assertThat(converterToTest.convertToEntityAttribute(VALID_LOCALE)).isEqualTo(new Locale(VALID_LOCALE));
    }

    @Test
    public final void testJPA() {

        // PREPARE
        beginTransaction();
        getEm().persist(new LocaleParentEntity(1));
        commitTransaction();

        // TEST UPDATE
        final Locale locale = Locale.GERMAN;
        beginTransaction();
        final LocaleParentEntity entity = getEm().find(LocaleParentEntity.class, 1L);
        entity.setLocale(locale);
        commitTransaction();

        // VERIFY
        beginTransaction();
        final LocaleParentEntity copy = getEm().find(LocaleParentEntity.class, 1L);
        assertThat(copy).isNotNull();
        assertThat(copy.getId()).isEqualTo(1);
        assertThat(copy.getLocale()).isNotNull();
        assertThat(copy.getLocale()).isEqualTo(locale);
        commitTransaction();

    }

}
