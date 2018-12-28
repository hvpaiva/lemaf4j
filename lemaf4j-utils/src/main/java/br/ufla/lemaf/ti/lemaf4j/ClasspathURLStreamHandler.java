package br.ufla.lemaf.ti.lemaf4j;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * Stream handler que lê do class path.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public final class ClasspathURLStreamHandler extends URLStreamHandler {

    private final ClassLoader cl;

    /**
     * Construtor padrão.
     */
    public ClasspathURLStreamHandler() {
        this.cl = getClass().getClassLoader();
    }

    /**
     * Construtor com class loader.
     *
     * @param cl Class loader
     */
    public ClasspathURLStreamHandler(final ClassLoader cl) {
        this.cl = cl;
    }

    @Override
    protected URLConnection openConnection(final URL url) throws IOException {
        final String path = url.getPath();
        final URL resourceUrl = cl.getResource(path);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Recurso não encontrado: " + path);
        }
        return resourceUrl.openConnection();
    }

}
