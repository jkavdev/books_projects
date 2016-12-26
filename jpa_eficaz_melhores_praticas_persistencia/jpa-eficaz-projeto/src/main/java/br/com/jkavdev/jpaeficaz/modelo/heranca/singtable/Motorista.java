package br.com.jkavdev.jpaeficaz.modelo.heranca.singtable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Motorista")
public class Motorista extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(length = 25)
	private String cnh;

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

}
