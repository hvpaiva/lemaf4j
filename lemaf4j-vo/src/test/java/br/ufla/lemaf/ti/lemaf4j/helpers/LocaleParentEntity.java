package br.ufla.lemaf.ti.lemaf4j.helpers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Locale;

@Entity(name = "LOCALE_PARENT")
public class LocaleParentEntity {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "LOCALE", nullable = true)
    private Locale locale;

    public LocaleParentEntity() {
        super();
    }

    public LocaleParentEntity(long id) {
        super();
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
