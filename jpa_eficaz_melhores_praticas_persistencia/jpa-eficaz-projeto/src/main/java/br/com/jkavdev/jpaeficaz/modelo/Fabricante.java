package br.com.jkavdev.jpaeficaz.modelo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "fabricante")
@Table(
		name = "fabricante", 
		uniqueConstraints = { 
				@UniqueConstraint(columnNames = { "codigo", "fabricante_nome" }, name = "un_fabricante"),
				@UniqueConstraint(columnNames = { "fabricante_nome" }, name = "un_nome")
		})
public class Fabricante {
	
	@Id
	@GeneratedValue
	private Long codigo;
	
	@Basic(optional = false, fetch = FetchType.EAGER)
	@Column(
		name = "fabricante_nome",
		length = 100,
		nullable = false,
		columnDefinition = "VARCHAR(100)")
	private String nome;
	
	@Basic(optional = true, fetch = FetchType.LAZY)
	private byte[] foto;

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
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}
