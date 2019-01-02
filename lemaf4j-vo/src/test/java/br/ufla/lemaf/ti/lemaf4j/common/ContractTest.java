package br.ufla.lemaf.ti.lemaf4j.common;

import br.ufla.lemaf.ti.lemaf4j.common.messaging.ResourceBundleMessageProducer;
import org.junit.After;
import org.junit.Test;

import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;

public class ContractTest {

    @After
    public void cleanUp() {
        Contract.removeMessageProducer();
    }

    @Test
    public final void testRequireArgNotNull() {

        // TEST
        try {
            Contract.requireArgNotNull("name", null);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("Error: ARGUMENTO NULO");
        }

        // TEST
        var resource = ResourceBundle.getBundle("messages");
        Contract.setMessageProducer(new ResourceBundleMessageProducer(resource));
        try {
            Contract.requireArgNotNull("name", null);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O argumento 'name' não pode ser nulo!");
        }
    }

    @Test
    public final void testRequireArgNotEmpty() {

        // TEST
        try {
            Contract.requireArgNotEmpty("name", "");
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("Error: ARGUMENTO VAZIO");
        }

        // TEST
        var resource = ResourceBundle.getBundle("messages");
        Contract.setMessageProducer(new ResourceBundleMessageProducer(resource));
        try {
            Contract.requireArgNotEmpty("name", "");
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
            assertThat(ex.getMessage()).isEqualTo("Error: TAMANHO MAXIMO ATINGIDO");
        }

        // TEST
        var resource = ResourceBundle.getBundle("messages");
        Contract.setMessageProducer(new ResourceBundleMessageProducer(resource));
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
            assertThat(ex.getMessage()).isEqualTo("Error: TAMANHO MINIMO NAO ALCANCADO");
        }

        // TEST
        var resource = ResourceBundle.getBundle("messages");
        Contract.setMessageProducer(new ResourceBundleMessageProducer(resource));
        try {
            Contract.requireArgMinLength("name", "123", 5);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O tamanho mínimo para o argumento 'name' é 5, mas foi: 3");
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

        // TEST
        try {
            Contract.requireArgMin("name6", 3, 4);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("Error: VALOR MINIMO NAO ALCANCADO");
        }

        // TEST
        var resource = ResourceBundle.getBundle("messages");
        Contract.setMessageProducer(new ResourceBundleMessageProducer(resource));
        try {
            Contract.requireArgMin("name6", 3, 4);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O valor mínimo para o argumento 'name6' é 4, mas foi: 3");
        }
    }

    @Test
    public void testRequireArgMax() {

        // TEST
        Contract.requireArgMax("name1", 5, 6);
        Contract.requireArgMax("name3", 4, 5);
        Contract.requireArgMax("name3", Integer.valueOf(5), Integer.valueOf(9));
        Contract.requireArgMax("name4", 5L, 6L);
        Contract.requireArgMax("name5", Long.valueOf(5), Long.valueOf(8));

        // TEST
        try {
            Contract.requireArgMax("name6", 3, 1);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("Error: VALOR MAXIMO ATINGIDO");
        }

        // TEST
        var resource = ResourceBundle.getBundle("messages");
        Contract.setMessageProducer(new ResourceBundleMessageProducer(resource));
        try {
            Contract.requireArgMax("name6", 3, 1);
        } catch (final ConstraintViolationException ex) {
            assertThat(ex.getMessage()).isEqualTo("O valor máximo para o argumento 'name6' é 1, mas foi: 3");
        }
    }

}
