/**
 * Pacote com as VO mais comuns.
 * Contém Value Objects que são objetos imutáveis cuja sua
 * igualdade não são baseadas na igualdade de seus valores.
 * Isto significa que as instâncias deste tipo são iguais quando seus valores
 * são o mesmo, não necessariamente sendo os mesmos objetos.
 */
@Vetoed
@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(value = UUIDConverter.class, type = UUID.class),
        @XmlJavaTypeAdapter(value = LocaleConverter.class, type = Locale.class)
})
package br.ufla.lemaf.ti.lemaf4j.vo;

import br.ufla.lemaf.ti.lemaf4j.converters.LocaleConverter;
import br.ufla.lemaf.ti.lemaf4j.converters.UUIDConverter;

import javax.enterprise.inject.Vetoed;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.util.Locale;
import java.util.UUID;
