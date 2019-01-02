package br.ufla.lemaf.ti.lemaf4j.common.messaging;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;

public class ResourceBundleMessageProducerTest {

    private ResourceBundleMessageProducer producer;

    @Before
    public void setUp() {
        var resource = ResourceBundle.getBundle("messages");
        producer = new ResourceBundleMessageProducer(resource);
    }

    @After
    public void cleanUp() {
        producer = null;
    }

    @Test
    public void shouldReturnMessageInTheBundle() {
        var message = producer.messageOf(Erros.ERRO_GENERICO).toString();

        assertEquals("Erro genérico!", message);
    }

    @Test
    public void shouldReturnDefaultCallbackMessage() {
        var message = producer.messageOf(Erros.ERRO_QUALQUER).message();

        assertEquals("Erros: ERRO QUALQUER", message);
    }

    @Test
    public void shouldReturnMessageInTheBundleWithArgs() {
        var message = producer.messageOf(Erros.ERRO_NUMERO, 50).message();

        assertEquals("Um erro no número 50!", message);
    }

    @Test
    public void testResourceBundleWithSpecificLocale() {
        var resource = ResourceBundle.getBundle("messages", Locale.ENGLISH);
        var producerWithLocale = new ResourceBundleMessageProducer(resource);

        var message = producerWithLocale.messageOf(Erros.ERRO_NUMERO, 50).message();

        assertEquals("There is a error in number 50!", message);
    }

    @Test
    public void testResourceBundleWithNoLocaleInexistent() {
        var resource = ResourceBundle.getBundle("messages", Locale.ENGLISH);
        var producerWithLocale = new ResourceBundleMessageProducer(resource);

        var message = producerWithLocale.messageOf(Erros.ERRO_NUMERO, 50).message();

        assertEquals("There is a error in number 50!", message);
    }

    private enum Erros implements ErrorType {
        ERRO_GENERICO, ERRO_NUMERO, ERRO_QUALQUER
    }
}
