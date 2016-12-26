package br.com.jkavdev.jpaeficaz.modelo.heranca.joined;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.com.jkavdev.jpaeficaz.modelo.heranca.mapsuclass.BaseEntity;

@Entity(name = "pessoas")
@Table(name = "pessoas")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 60)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
