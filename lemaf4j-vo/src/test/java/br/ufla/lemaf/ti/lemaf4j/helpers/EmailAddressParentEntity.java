package br.ufla.lemaf.ti.lemaf4j.helpers;

import br.ufla.lemaf.ti.lemaf4j.converters.EmailAddressConverter;
import br.ufla.lemaf.ti.lemaf4j.vo.EmailAddress;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "EMAIL_ADDRESS_PARENT")
public class EmailAddressParentEntity {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "EMAIL", nullable = true)
    @Convert(converter = EmailAddressConverter.class)
    private EmailAddress emailAddress;

    public EmailAddressParentEntity() {
        super();
    }

    public EmailAddressParentEntity(long id) {
        super();
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

}
