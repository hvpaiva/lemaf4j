package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.common.ConstraintViolationException;
import br.ufla.lemaf.ti.lemaf4j.helpers.UserNameParentEntity;
import org.fuin.units4j.AbstractPersistenceTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fuin.utils4j.Utils4J.deserialize;
import static org.fuin.utils4j.Utils4J.serialize;

public class UserNameTest extends AbstractPersistenceTest {

    private static final String VALID_USERNAME = "hvpaiva";

    @Test
    public void shouldSerialize() {
        final var original = new UserName(VALID_USERNAME);
        final var copy = deserialize(serialize(original));
        assertThat(original).isEqualTo(copy);
    }

    @Test
    public void shouldNotInstantiateDefaultVOClasses() {
        new UserName();
    }

    @Test
    public void shouldConstructValid() {
        final var email = new UserName(VALID_USERNAME);
        assertThat(email.asBaseType()).isEqualTo(VALID_USERNAME);
    }

    @Test
    public final void testCreateValidLowerCase() {
        final String userName = "michael-1_a";
        assertThat(new UserName(userName).toString()).isEqualTo(userName);
        assertThat(new UserName(userName).length()).isEqualTo(userName.length());
    }

    @Test
    public final void testCreateValidUpperCase() {
        final String userName = "MICHAEL-1_a";
        assertThat(new UserName(userName).toString()).isEqualTo(userName.toLowerCase());
        assertThat(new UserName(userName).length()).isEqualTo(userName.length());
    }

    @Test
    public final void testEqualsLowerUpperCase() {
        assertThat(new UserName("abc-1_B")).isEqualTo(new UserName("AbC-1_b"));
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructEmpty() {
        new UserName("");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructNull() {
        new UserName(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructTooShortUserName() {
        new UserName("ab");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructTooLongUserName() {
        new UserName("a12345678901234567890");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructOnlyNumbersUserName() {
        new UserName("123124");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void shouldNotConstructStartWithNumbersUserName() {
        new UserName("1hvpaiva");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void testCreateStartsWithUnderscore() {
        new UserName("_abc");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void testCreateStartsWithHyphen() {
        new UserName("-abc");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void testCreateContainsIllegalDoubleCross() {
        new UserName("abc#1");
    }

    @Test(expected = ConstraintViolationException.class)
    public final void testCreateContainsIllegalAtAndDot() {
        new UserName("abc@def.com");
    }

    @Test
    public void testJPA() {

        // PREPARE
        beginTransaction();
        getEm().persist(new UserNameParentEntity(1));
        commitTransaction();

        // TEST UPDATE
        beginTransaction();
        final UserNameParentEntity entity = getEm().find(UserNameParentEntity.class, 1L);
        entity.setUserName(new UserName("michael-1_a"));
        commitTransaction();

        // VERIFY
        beginTransaction();
        final UserNameParentEntity copy = getEm().find(UserNameParentEntity.class, 1L);
        assertThat(copy).isNotNull();
        assertThat(copy.getId()).isEqualTo(1);
        assertThat(copy.getUserName()).isNotNull();
        assertThat(copy.getUserName()).isEqualTo(new UserName("michael-1_a"));
        commitTransaction();

    }
}
