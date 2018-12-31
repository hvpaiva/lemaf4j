package br.ufla.lemaf.ti.lemaf4j.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ContractTest {

    @Test
    public final void testRequireArgNotNull() {

        // TEST
        Contract.requireArgNotNull("name", "Whatever");

        // TEST
        try {
            Contract.requireArgNotNull("name", null);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O argumento 'name' não pode ser nulo!");
        }
    }

    @Test
    public final void testRequireArgNotEmpty() {

        // TEST
        Contract.requireArgNotNull("name", "Whatever");

        // TEST
        try {
            Contract.requireArgNotNull("name", null);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O argumento 'name' não pode ser nulo!");
        }

        // TEST
        try {
            Contract.requireArgNotNull("name", "");
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O argumento 'name' não pode ser vazio!");
        }

    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotInstantiateUtilityClasses() {
        new Contract();
    }

    @Test
    public void testRequireArgMaxLength() {

        // TEST
        Contract.requireArgMaxLength("name", "123", 3);

        // TEST
        Contract.requireArgMaxLength("name", "12", 3);

        // TEST
        try {
            Contract.requireArgMaxLength("name", "123", 2);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O tamanho máximo para o argumento 'name' é 2, mas foi: 3");
        }

    }

    @Test
    public void testRequireArgMinLength() {

        // TEST
        Contract.requireArgMinLength("name", "1234", 4);

        // TEST
        try {
            Contract.requireArgMinLength("name", "123", 4);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O tamanho mínimo para o argumento 'name' é 4, mas foi: 3");
        }

    }

    @Test
    public void testRequireArgMax() {

        // TEST
        Contract.requireArgMax("name", 4, 5);

        // TEST
        Contract.requireArgMax("name", 5, 5);

        // TEST
        try {
            Contract.requireArgMax("name", 6, 5);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O valor máximo para o argumento 'name' é 5, mas foi: 6");
        }

    }

    @Test
    public void testRequireArgMin() {

        // TEST
        Contract.requireArgMin("name1", 5, 4);
        Contract.requireArgMin("name3", 4, 4);
        Contract.requireArgMin("name3", Integer.valueOf(5), Integer.valueOf(4));
        Contract.requireArgMin("name4", 5L, 4L);
        Contract.requireArgMin("name5", Long.valueOf(5), Long.valueOf(4));
        try {
            Contract.requireArgMin("name6", 3, 4);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O valor mínimo para o argumento 'name6' é 4, mas foi: 3");
        }

    }

}
