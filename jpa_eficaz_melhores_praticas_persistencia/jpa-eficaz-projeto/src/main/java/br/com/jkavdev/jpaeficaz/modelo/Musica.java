package br.com.jkavdev.jpaeficaz.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Musica {

	@Id
	private String id;

	private String titulo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
