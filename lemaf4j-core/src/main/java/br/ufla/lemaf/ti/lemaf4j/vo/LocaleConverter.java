package br.ufla.lemaf.ti.lemaf4j.vo;

import javax.annotation.concurrent.ThreadSafe;
import javax.persistence.AttributeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Converte {@link Locale} para String e vice-versa.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@ThreadSafe
public final class LocaleConverter
        extends XmlAdapter<String, Locale>
        implements AttributeConverter<Locale, String> {

    @Override
    public final String marshal(final Locale value) throws Exception {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @Override
    public final Locale unmarshal(final String value) throws Exception {
        return asLocale(value);
    }

    @Override
    public final String convertToDatabaseColumn(final Locale value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @Override
    public final Locale convertToEntityAttribute(final String value) {
        return asLocale(value);
    }

    /**
     * Retorna dada String como Locale.
     * O locale NÃO é verificado na lista Java de
     * códigos de idioma disponíveis.
     *
     * @param value Valor a se converter em locale
     * @return Locale
     */
    public static Locale asLocale(final String value) {
        final Locale locale;
        final int p = value.indexOf("__");
        if (p > -1) {
            locale = new Locale(
                    value.substring(0, p),
                    null,
                    value.substring(p + 2)
            );
        } else {
            final var tok = new StringTokenizer(value, "_");
            if (tok.countTokens() == 1) {
                locale = new Locale(value);
            } else if (tok.countTokens() == 2) {
                locale = new Locale(tok.nextToken(), tok.nextToken());
            } else if (tok.countTokens() == 3) {
                locale = new Locale(tok.nextToken(), tok.nextToken(), tok.nextToken());
            } else {
                throw new IllegalArgumentException("Impossível converter: '" + value + "'");
            }
        }
        return locale;
    }

    /**
     * Verifica se dado locale está na lista do Java de locales
     * conhecidos.
     *
     * @param locale O locale a se verificar
     * @return <code>true</code> se o locale é conhecido,
     *         e <code>false</code> senão for
     */
    public static boolean validLocale(final Locale locale) {

        return Arrays.asList(Locale.getAvailableLocales())
                .contains(locale);
    }
}
