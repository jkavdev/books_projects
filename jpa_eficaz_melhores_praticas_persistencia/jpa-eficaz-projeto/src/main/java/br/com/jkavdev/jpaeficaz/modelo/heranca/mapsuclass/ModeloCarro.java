package br.com.jkavdev.jpaeficaz.modelo.heranca.mapsuclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "modelo_carro")
public class ModeloCarro extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Column(length = 100, nullable = false)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
