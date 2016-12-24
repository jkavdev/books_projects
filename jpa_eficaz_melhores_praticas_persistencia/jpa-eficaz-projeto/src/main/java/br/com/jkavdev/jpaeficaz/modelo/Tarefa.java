package br.com.jkavdev.jpaeficaz.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.jkavdev.jpaeficaz.modelo.id.CodigoUnico;

@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name = "codigo_id")
	private CodigoUnico codigoUnico;

	@Column(length = 150, nullable = false)
	private String descricao;

	public CodigoUnico getCodigoUnico() {
		return codigoUnico;
	}

	public void setCodigoUnico(CodigoUnico codigoUnico) {
		this.codigoUnico = codigoUnico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
