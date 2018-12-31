package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.helpers.EmailsParentEntity;
import org.fuin.units4j.AbstractPersistenceTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fuin.utils4j.Utils4J.deserialize;
import static org.fuin.utils4j.Utils4J.serialize;

public class EmailTest extends AbstractPersistenceTest {

    private static final String VALID_EMAIL = "e@m.c";

    @Test
    public void shouldSerialize() {
        final var original = new Email(VALID_EMAIL);
        final var copy = deserialize(serialize(original));
        assertThat(original).isEqualTo(copy);
    }

    @Test
    public void shouldNotInstantiateDefaultVOClasses() {
        new Email();
    }

    @Test
    public void shouldConstructValid() {
        final var email = new Email(VALID_EMAIL);
        assertThat(email.asBaseType()).isEqualTo(VALID_EMAIL);
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructEmpty() {
        new Email("");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructNull() {
        new Email(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructIllegal() {
        new Email("abc@");
    }

    @Test
    public void shouldAceptValidEmails() {
        new Email(VALID_EMAIL);
    }

    @Test
    public final void shouldLowerCaseBeEqualsUpperCase() {
        assertThat(new Email("Abc@DeF.Com")).isEqualTo(new Email("aBc@deF.cOM"));
    }

    @Test
    public void testJPA() {

        // PREPARE
        beginTransaction();
        getEm().persist(new EmailsParentEntity(1));
        commitTransaction();

        // TEST UPDATE
        beginTransaction();
        final EmailsParentEntity entity = getEm().find(EmailsParentEntity.class, 1L);
        entity.setEmail(new Email(VALID_EMAIL));
        commitTransaction();

        // VERIFY
        beginTransaction();
        final EmailsParentEntity copy = getEm().find(EmailsParentEntity.class, 1L);
        assertThat(copy).isNotNull();
        assertThat(copy.getId()).isEqualTo(1);
        assertThat(copy.getEmail()).isNotNull();
        assertThat(copy.getEmail().toString()).isEqualTo(VALID_EMAIL);
        commitTransaction();

    }
}
