package br.ufla.lemaf.ti.lemaf4j.helpers;

import br.ufla.lemaf.ti.lemaf4j.converters.CPFConverter;
import br.ufla.lemaf.ti.lemaf4j.vo.CPF;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "CPF_PARENT")
public class CPFParentEntity {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "CPF")
    @Convert(converter = CPFConverter.class)
    private CPF cpf;

    public CPFParentEntity() {
        super();
    }

    public CPFParentEntity(long id) {
        super();
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CPF getCPF() {
        return cpf;
    }

    public void setCPF(CPF cpf) {
        this.cpf = cpf;
    }

}
