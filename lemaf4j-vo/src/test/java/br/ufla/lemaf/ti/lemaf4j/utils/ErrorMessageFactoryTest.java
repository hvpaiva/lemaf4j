package br.ufla.lemaf.ti.lemaf4j.utils;

import org.junit.Test;

public class ErrorMessageFactoryTest {

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotInstantiateUtilityClasses() {
        new ErrorMessageFactory();
    }
}
