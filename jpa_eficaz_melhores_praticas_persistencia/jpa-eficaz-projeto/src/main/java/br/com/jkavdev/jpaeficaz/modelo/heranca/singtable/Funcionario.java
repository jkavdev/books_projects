package br.com.jkavdev.jpaeficaz.modelo.heranca.singtable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Funcionario")
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(length = 25)
	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
