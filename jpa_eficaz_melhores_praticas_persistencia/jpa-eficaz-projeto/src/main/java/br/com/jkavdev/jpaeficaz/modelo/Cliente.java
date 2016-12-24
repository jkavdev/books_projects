package br.com.jkavdev.jpaeficaz.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import br.com.jkavdev.jpaeficaz.modelo.id.ClienteId;

@Entity
@Table(name = "cliente")
@IdClass(ClienteId.class)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long codigo;

	@Id
	private String nome;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
