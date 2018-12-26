/**
 * Contém objetos imutáveis que representam Value Objects, ou seja,
 * objetos cuja sua igualdade não são baseadas em suas igualdades.
 * Isto significa que as instâncias deste tipo são iguais quando seus valores
 * são o mesmo, não necessariamente sendo os mesmos objetos.
 */
@Vetoed
@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(value = UUIDConverter.class, type = UUID.class),
        @XmlJavaTypeAdapter(value = LocaleConverter.class, type = Locale.class)
})
package br.ufla.lemaf.ti.lemaf4j.vo;

import javax.enterprise.inject.Vetoed;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.util.Locale;
import java.util.UUID;