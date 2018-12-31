package br.ufla.lemaf.ti.lemaf4j.common.messaging;

/**
 * SimpleMessageProducer é responsável pela
 * geração de mensagens de erro.
 * Estas mensagens são geradas atraves dos
 * nomes das anotoções que representam os erros.
 * <p>
 * A mesagem do erro representado por CPFError.INVALID_DIGITS é :
 * <p>
 * CPFError: INVALID DIGITS
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class SimpleMessageProducer implements BaseMessageProducer {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationMessage messageOf(ErrorType errorType) {
        var key = messageKeyFor(errorType);

        return new ErrorMessage(gerarMensagemSimples(key));
    }
}
