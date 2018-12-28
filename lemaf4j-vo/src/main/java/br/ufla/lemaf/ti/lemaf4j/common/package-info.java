/**
 * Classes base e utilitárias comuns à biblioteca.
 */
@Vetoed
@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(value = UUIDConverter.class, type = UUID.class),
        @XmlJavaTypeAdapter(value = LocaleConverter.class, type = Locale.class)
})
package br.ufla.lemaf.ti.lemaf4j.common;

import br.ufla.lemaf.ti.lemaf4j.converters.LocaleConverter;
import br.ufla.lemaf.ti.lemaf4j.converters.UUIDConverter;

import javax.enterprise.inject.Vetoed;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.util.Locale;
import java.util.UUID;
