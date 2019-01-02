package br.ufla.lemaf.ti.lemaf4j.common.messaging;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleMessageProducerTest {

    @Test
    public void tesMessageGenerator() {
        var messageProducer = new SimpleMessageProducer();
        var errorMessage = messageProducer.messageOf(ErroGenerico.INVALID_ERROR);

        assertEquals("ErroGenerico: INVALID ERROR", errorMessage.message());
    }

    private enum ErroGenerico implements ErrorType {
        INVALID_ERROR
    }
}
