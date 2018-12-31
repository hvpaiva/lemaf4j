package br.ufla.lemaf.ti.lemaf4j.helpers;

import br.ufla.lemaf.ti.lemaf4j.vo.CPF;
import br.ufla.lemaf.ti.lemaf4j.vo.EmailAddress;
import br.ufla.lemaf.ti.lemaf4j.vo.UserName;

import java.util.UUID;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data {

    @Valid
    @XmlAttribute
    public EmailAddress email;

    @Valid
    @XmlAttribute
    public UserName userName;

    @Valid
    @XmlAttribute
    public UUID uuid;

    @Valid
    @XmlAttribute
    public CPF cpf;

}
