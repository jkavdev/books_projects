package br.com.jkavdev.jpaeficaz.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.jkavdev.jpaeficaz.modelo.id.CodigoUnico;

@Entity
@Table(name = "livro")
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name = "codigo_id")
	private CodigoUnico codigoUnico;

	@Id
	@ManyToOne
	@JoinColumn(name = "escritor_id")
	private Escritor escritor;

	public CodigoUnico getCodigoUnico() {
		return codigoUnico;
	}

	public void setCodigoUnico(CodigoUnico codigoUnico) {
		this.codigoUnico = codigoUnico;
	}

	public Escritor getEscritor() {
		return escritor;
	}

	public void setEscritor(Escritor escritor) {
		this.escritor = escritor;
	}

}
