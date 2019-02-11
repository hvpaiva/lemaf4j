package br.ufla.lemaf.ti.lemaf4j.helpers;

import br.ufla.lemaf.ti.lemaf4j.vo.CNPJ;
import br.ufla.lemaf.ti.lemaf4j.vo.CPF;
import br.ufla.lemaf.ti.lemaf4j.vo.Email;
import br.ufla.lemaf.ti.lemaf4j.vo.UserName;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data {

    @Valid
    @XmlAttribute
    public Email email;

    @Valid
    @XmlAttribute
    public UserName userName;

    @Valid
    @XmlAttribute
    public CPF cpf;

    @Valid
    @XmlAttribute
    public CNPJ cnpj;

}
