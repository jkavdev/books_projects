package br.com.jkavdev.jpaeficaz.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "alugueis")
@TableGenerator(
		name = Aluguel.TABELA_ID,
		table = "tabela_de_ids",
		pkColumnName = "tabela",
		pkColumnValue = "aluguel",
		initialValue = 1,
		allocationSize = 100)
public class Aluguel implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABELA_ID = "tabela_ids";

	@Id
	@GeneratedValue(generator = TABELA_ID, strategy = GenerationType.TABLE)
	private Long codigo;
	private BigDecimal valorTotal;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "valor_diaria")
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
