package br.ufla.lemaf.ti.lemaf4j.common.messaging;

import java.util.Locale;

/**
 * Interface base para produtores
 * de mensagens de validação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
interface BaseMessageProducer extends MessageProducer {

    /**
     * A chave padrao é gerada com base no nome da classe do erro e do nome do
     * erro, sempre minúscula.
     * <p>
     * Ex.:
     * classe do erro: CPFError
     * nome do erro: INVALID_DIGITS
     * <p>
     * Chave gerada: cpferror.invalid_digits
     *
     * @param locale    O locale a ser usado na conversão
     *                  para minúsculo
     * @param error     O tipo do erro
     * @param lowerCase Se a chave será ou não tudo minuscula
     *                  <code>true</code> se a chave deve ser minuscula
     * @return chave que representa o erro a ser usada para recuperar sua
     * mensagem associada
     */
    default String messageKeyFor(Locale locale, ErrorType error, boolean lowerCase) {
        String simpleName = error.getClass().getSimpleName();
        String errorName = error.name();
        String key = simpleName + "." + errorName;

        return lowerCase ? key.toLowerCase(locale) : key;
    }

    /**
     * A chave padrao é gerada com base no nome da classe do erro e do nome do
     * erro, sempre minúscula.
     * <p>
     * Ex.:
     * classe do erro: CPFError
     * nome do erro: INVALID_DIGITS
     * <p>
     * Chave gerada: cpferror.invalid_digits
     *
     * @param error O tipo do erro
     * @return chave que representa o erro a ser usada para recuperar sua
     * mensagem associada
     */
    default String messageKeyFor(ErrorType error) {
        return messageKeyFor(Locale.ENGLISH, error, false);
    }


    /**
     * Dado a chave de referência do erro,
     * constroi a mensagem.
     *
     * @param messageKey Chave representativa da mensagem
     * @return A mensagem de erro
     */
    default String gerarMensagemSimples(String messageKey) {
        return messageKey
                .replaceFirst("[.]", ": ")
                .replaceAll("_", " ");
    }

}
