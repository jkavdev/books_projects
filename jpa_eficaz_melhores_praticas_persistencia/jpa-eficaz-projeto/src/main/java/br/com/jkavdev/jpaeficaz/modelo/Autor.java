package br.com.jkavdev.jpaeficaz.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.jkavdev.jpaeficaz.modelo.id.AutorId;

@Entity
@Table(name = "autor")
public class Autor {

	@EmbeddedId
	private AutorId codigo;

	public AutorId getCodigo() {
		return codigo;
	}

	public void setCodigo(AutorId codigo) {
		this.codigo = codigo;
	}

}
