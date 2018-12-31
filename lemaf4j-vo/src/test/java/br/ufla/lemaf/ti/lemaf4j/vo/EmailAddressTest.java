package br.ufla.lemaf.ti.lemaf4j.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fuin.utils4j.Utils4J.deserialize;
import static org.fuin.utils4j.Utils4J.serialize;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.helpers.EmailAddressParentEntity;
import org.fuin.units4j.AbstractPersistenceTest;
import org.junit.Test;

public class EmailAddressTest extends AbstractPersistenceTest {

    private static final String VALID_EMAIL = "e@m.c";

    @Test
    public void shouldSerialize() {
        final var original = new EmailAddress(VALID_EMAIL);
        final var copy = deserialize(serialize(original));
        assertThat(original).isEqualTo(copy);
    }

    @Test
    public void shouldNotInstantiateDefaultVOClasses() {
        new EmailAddress();
    }

    @Test
    public void shouldConstructValid() {
        final var email = new EmailAddress(VALID_EMAIL);
        assertThat(email.asBaseType()).isEqualTo(VALID_EMAIL);
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructEmpty() {
        new EmailAddress("");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructNull() {
        new EmailAddress(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructIllegal() {
        new EmailAddress("abc@");
    }

    @Test
    public void shouldAceptValidEmails() {
        new EmailAddress(VALID_EMAIL);
    }

    @Test
    public final void shouldLowerCaseBeEqualsUpperCase() {
        assertThat(new EmailAddress("Abc@DeF.Com")).isEqualTo(new EmailAddress("aBc@deF.cOM"));
    }

    @Test
    public void testJPA() {

        // PREPARE
        beginTransaction();
        getEm().persist(new EmailAddressParentEntity(1));
        commitTransaction();

        // TEST UPDATE
        beginTransaction();
        final EmailAddressParentEntity entity = getEm().find(EmailAddressParentEntity.class, 1L);
        entity.setEmailAddress(new EmailAddress(VALID_EMAIL));
        commitTransaction();

        // VERIFY
        beginTransaction();
        final EmailAddressParentEntity copy = getEm().find(EmailAddressParentEntity.class, 1L);
        assertThat(copy).isNotNull();
        assertThat(copy.getId()).isEqualTo(1);
        assertThat(copy.getEmailAddress()).isNotNull();
        assertThat(copy.getEmailAddress().toString()).isEqualTo(VALID_EMAIL);
        commitTransaction();

    }
}
