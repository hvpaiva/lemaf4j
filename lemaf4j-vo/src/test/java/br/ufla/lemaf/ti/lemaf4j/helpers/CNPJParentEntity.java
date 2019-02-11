package br.ufla.lemaf.ti.lemaf4j.helpers;

import br.ufla.lemaf.ti.lemaf4j.converters.CNPJConverter;
import br.ufla.lemaf.ti.lemaf4j.vo.CNPJ;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "CNPJ_PARENT")
public class CNPJParentEntity {

	@Id
	@Column(name = "ID")
	private long id;

	@Column(name = "CNPJ")
	@Convert(converter = CNPJConverter.class)
	private CNPJ cnpj;

	public CNPJParentEntity() {
		super();
	}

	public CNPJParentEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public CNPJ getCnpj() {
		return cnpj;
	}

	public void setCnpj(final CNPJ cnpj) {
		this.cnpj = cnpj;
	}
}
