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

	// Definicao da chave primaria da entidade
	@Id
	// Neste caso a chave sera gerenciada pelo banco de dados
	// A estrategia usada Identity eh a utilizada pelo Mysql
	
	// 1 - jpa persiste o registro
	// 2 - o banco persiste os dados e gera um id
	// 3 - jpa recupera este id gerado pelo banco
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
