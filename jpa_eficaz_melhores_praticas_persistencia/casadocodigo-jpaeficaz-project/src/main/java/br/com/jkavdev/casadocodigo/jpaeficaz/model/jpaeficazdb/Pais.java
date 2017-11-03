package br.com.jkavdev.casadocodigo.jpaeficaz.model.jpaeficazdb;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Pais")
@Table(name = "pais")
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pais_id", columnDefinition = "smallint")
	private Long id;

	@Column(name = "pais")
	private String nome;

	@Basic(optional = true, fetch = FetchType.LAZY)
	@Column(name = "utlima_alteracao")
	private Date lastUpdate;

	public Pais(String nome) {
		this.nome = nome;
	}

	protected Pais() {
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nome=" + nome + "]";
	}

}
