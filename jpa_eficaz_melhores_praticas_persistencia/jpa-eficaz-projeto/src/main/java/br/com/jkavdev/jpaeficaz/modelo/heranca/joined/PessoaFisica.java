package br.com.jkavdev.jpaeficaz.modelo.heranca.joined;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(length = 11)
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
