package br.com.jkavdev.jpaeficaz.modelo.heranca.singtable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.com.jkavdev.jpaeficaz.modelo.heranca.mapsuclass.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pertence_a_classe")
public abstract class Pessoa extends BaseEntity{

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
