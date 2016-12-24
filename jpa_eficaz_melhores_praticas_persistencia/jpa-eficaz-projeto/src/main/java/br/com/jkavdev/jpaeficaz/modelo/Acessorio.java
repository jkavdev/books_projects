package br.com.jkavdev.jpaeficaz.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "acessorio")
@SequenceGenerator(
		name = Acessorio.SEQUENCE_NAME, 
		sequenceName = Acessorio.SEQUENCE_NAME, 
		initialValue = 10, 
		allocationSize = 53)
public class Acessorio {
	
	public static final String SEQUENCE_NAME = "seq_acessorio";
	
	@Id
	@GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
	private Long codigo;
	
	@Column(length = 100, nullable = false)
	private String descricao;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
