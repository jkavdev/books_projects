package br.com.jkavdev.casadocodigo.jpaeficaz.model.jpaeficazdb;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Autor {

	// Neste caso estamos indicando uma chave primaria 
	// Que sera gerenciada pela aplicacao
	// Gerando um trabalho adicional
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
