package br.ufla.lemaf.ti.lemaf4j.formatters;

import br.ufla.lemaf.ti.lemaf4j.BaseStringFormatter;
import br.ufla.lemaf.ti.lemaf4j.Formatter;
import br.ufla.lemaf.ti.lemaf4j.validators.CPFValidator;

import java.util.regex.Pattern;

/**
 * Formatador de CPF.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class CPFFormatter implements Formatter<String> {

    private BaseStringFormatter base;

    private static final Pattern FORMATTED = Pattern.compile("(\\d{3})[.](\\d{3})[.](\\d{3})-(\\d{2})");
    private static final Pattern UNFORMATTED = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");

    /**
     * Construtor padrão do CPFFormatter.
     * Injeta a dependência de BaseStringFormatter.
     */
    public CPFFormatter() {
        this.base = new BaseStringFormatter(
                FORMATTED,
                "$1.$2.$3-$4",
                UNFORMATTED,
                "$1$2$3$4"
        );
    }

    @Override
    public String format(String value) {
        return base.format(value);
    }

    @Override
    public String unformat(String value) {
        return base.unformat(value);
    }

    @Override
    public boolean isFormatted(String value) {
        return base.isFormatted(value);
    }

    @Override
    public boolean isNotFormatted(String value) {
        return base.isNotFormatted(value);
    }
}
