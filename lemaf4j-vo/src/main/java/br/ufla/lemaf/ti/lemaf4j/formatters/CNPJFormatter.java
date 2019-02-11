package br.ufla.lemaf.ti.lemaf4j.formatters;

import br.ufla.lemaf.ti.lemaf4j.BaseStringFormatter;
import br.ufla.lemaf.ti.lemaf4j.Formatter;

import java.util.regex.Pattern;

/**
 * Formatador de CNPJ.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class CNPJFormatter implements Formatter<String> {

    private BaseStringFormatter base;

    private static final Pattern FORMATTED = Pattern.compile("(\\d{2})[.](\\d{3})[.](\\d{3})/(\\d{4})-(\\d{2})");
    private static final Pattern UNFORMATTED = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");

    /**
     * Construtor padrão do CNPJFormatter.
     * Injeta a dependência de BaseStringFormatter.
     */
    public CNPJFormatter() {
        this.base = new BaseStringFormatter(
                FORMATTED,
                "$1.$2.$3/$4-$5",
                UNFORMATTED,
                "$1$2$3$4$5"
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String format(final String value) {
        return base.format(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String unformat(final String value) {
        return base.unformat(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFormatted(final String value) {
        return base.isFormatted(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNotFormatted(final String value) {
        return base.isNotFormatted(value);
    }
}
