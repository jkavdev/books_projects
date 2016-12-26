package br.com.jkavdev.jpaeficaz.modelo.heranca.tabperlcass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "aviao")
public class Aviao extends Veiculo {

	private String uso;

	private String tipo;

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
