package br.ufla.lemaf.ti.lemaf4j;

import br.ufla.lemaf.ti.lemaf4j.utils.Error;
import br.ufla.lemaf.ti.lemaf4j.utils.ErrorMessageFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementação base para os formatadores.
 * Oferece implementação para os métodos de
 * formatação/desformatação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class BaseStringFormatter implements Formatter<String> {

    private final Pattern formatted;

    private final String formattedReplacement;

    private final Pattern unformatted;

    private final String unformattedReplacement;

    /**
     * Construtor padrão para o BaseStringFormatter.
     *
     * @param formatted              O padrão formatado
     * @param formattedReplacement   String contendo a base formatada para
     *                               substituir o valor a se formatar
     * @param unformatted            O padrão desformatado
     * @param unformattedReplacement String contendo a base desformatada para
     *                               substituir o valor a se desformatar
     */
    public BaseStringFormatter(final Pattern formatted,
                               final String formattedReplacement,
                               final Pattern unformatted,
                               final String unformattedReplacement) {
        super();
        this.formatted = formatted;
        this.formattedReplacement = formattedReplacement;
        this.unformatted = unformatted;
        this.unformattedReplacement = unformattedReplacement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String format(String value) {
        String result;
        assetNotNull(value);

        if (isFormatted(value)) return value;

        Matcher matcher = unformatted.matcher(value);
        result = matchAndReplace(matcher, formattedReplacement);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String unformat(String value) {
        String result;
        assetNotNull(value);

        if (isNotFormatted(value)) return value;

        Matcher unformattedMatcher = unformatted.matcher(value);
        if (unformattedMatcher.matches()) return value;

        Matcher matcher = formatted.matcher(value);
        result = matchAndReplace(matcher, unformattedReplacement);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFormatted(String value) {
        return formatted.matcher(value).matches();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNotFormatted(String value) {
        return unformatted.matcher(value).matches();
    }

    /**
     * Formatador.
     *
     * @param matcher     O Matcher a se formatar
     * @param replacement Padrão para substituir com a
     *                    nova formatação/desformatação
     * @return O valor formatado
     */
    private String matchAndReplace(Matcher matcher, String replacement) {
        String result;

        if (matcher.matches()) {
            result = matcher.replaceAll(replacement);

        } else {
            throw new IllegalArgumentException(
                    Error.NAO_FORMATADO.message()
            );
        }

        return result;
    }

    /**
     * Confere se o argumento passado não é nulo.
     *
     * @param value O valor a se checar
     * @throws IllegalArgumentException O argumento é nulo
     */
    private void assetNotNull(String value) {
        if (value == null)
            throw new IllegalArgumentException(
                    ErrorMessageFactory.of(Error.ARGUMENTO_NULO)
            );
    }
}
