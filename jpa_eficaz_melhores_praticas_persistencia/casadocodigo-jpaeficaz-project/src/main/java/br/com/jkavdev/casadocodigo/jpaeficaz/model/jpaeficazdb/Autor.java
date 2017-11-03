package br.com.jkavdev.casadocodigo.jpaeficaz.model.jpaeficazdb;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Autor {

	@Id
	private Integer id;

	private String nome;

	public Autor(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	protected Autor() { }
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + "]";
	}

}
