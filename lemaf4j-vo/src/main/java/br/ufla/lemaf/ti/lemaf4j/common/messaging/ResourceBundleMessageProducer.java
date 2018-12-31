package br.ufla.lemaf.ti.lemaf4j.common.messaging;

import br.ufla.lemaf.ti.lemaf4j.common.Contract;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * ResourceBlundeMessageProducer é responsável pela geração de mensagens de
 * erro. Estas mensagens são procuradas em um ResourceBundle, através de uma
 * chave associada a cada erro.
 * <p>
 * A chave de um erro é composta do seguinte modo:
 * <p>
 * String key = simpleName + "." + errorName;
 * <p>
 * Onde simpleName é o atributo simpleName do Enum e o errorName é o nome da
 * constante enum. Além disso, a chave é convertida em letras minúsculas.
 * <p>
 * Veja o exemplo:
 * A chave do erro representado por CPFError.INVALID_DIGITS é
 * cpferror.invalid_digits
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class ResourceBundleMessageProducer implements BaseMessageProducer {

    private final ResourceBundle bundle;

    /**
     * Construtou do producer.
     * Injetando a Dependência do Resource Bundle.
     *
     * @param bundle ResourceBlunde que contém o
     *               mapa que associa erros as mensagens.
     */
    public ResourceBundleMessageProducer(final ResourceBundle bundle) {
        Contract.requireArgNotNull("bundle", bundle);
        this.bundle = bundle;
    }

    /**
     * Busca o locale do bundle.
     *
     * @param bundle O bundle
     * @return O locale do bundle
     */
    private static Locale buscarLocale(ResourceBundle bundle) {
        return bundle.getLocale();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationMessage messageOf(ErrorType errorType) {
        Locale locale = buscarLocale(bundle);

        String message;
        try {

            message = bundle.getString(messageKeyFor(locale, errorType, true));

        } catch (MissingResourceException ex) {

            message = gerarMensagemSimples(messageKeyFor(errorType));

        }

        return new ErrorMessage(message);
    }

    /**
     * Constroi a mensagem com argumentos.
     *
     * @param errorType O tipo do erro
     * @param args      Os argumentos para a mensagem
     * @return A mensagem
     */
    public ValidationMessage messageOf(ErrorType errorType, Object... args) {
        var message = messageOf(errorType).message();

        return new ErrorMessage(String.format(message, args));
    }
}
