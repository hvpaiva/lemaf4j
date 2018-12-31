package br.ufla.lemaf.ti.lemaf4j.helpers;

import br.ufla.lemaf.ti.lemaf4j.converters.EmailConverter;
import br.ufla.lemaf.ti.lemaf4j.vo.Email;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "EMAIL_ADDRESS_PARENT")
public class EmailsParentEntity {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "EMAIL")
    @Convert(converter = EmailConverter.class)
    private Email email;

    public EmailsParentEntity() {
        super();
    }

    public EmailsParentEntity(long id) {
        super();
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

}
