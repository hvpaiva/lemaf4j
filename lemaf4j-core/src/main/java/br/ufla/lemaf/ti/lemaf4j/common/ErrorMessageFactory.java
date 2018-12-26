package br.ufla.lemaf.ti.lemaf4j.common;

/**
 * Classe utilitária para construir
 * mensagens de erros com argumentos.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class ErrorMessageFactory {

    private ErrorMessageFactory() {
        throw new UnsupportedOperationException(
                "Não se deve criar uma instância de uma classe utilitária!"
        );
    }

    /**
     * Cria a mensagem de Erro.
     * Formatando o texto da mensagem com seus argumentos.
     *
     * @param error O tipo do erro
     * @param args Os argumentos do erro
     * @return Uma String formatada com a mensagem de erros
     */
    public static String of(Error error, Object ...args) {
        return String.format(error.message(), args);
    }

}
