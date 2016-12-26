package br.com.jkavdev.jpaeficaz.modelo.heranca.tabperlcass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "carro")
public class Carro extends Veiculo {

	private Integer ano;

	private Integer portas;

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getPortas() {
		return portas;
	}

	public void setPortas(Integer portas) {
		this.portas = portas;
	}

}
