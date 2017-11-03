package br.com.jkavdev.casadocodigo.jpaeficaz.model.jpaeficazdb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(
		name = Usuario.IDS_TABLE_NAME,
		table = Usuario.IDS_TABLE_NAME,
		pkColumnValue = "tabela",
		pkColumnName = "usuario_id",
		valueColumnName = "id_atual",
		initialValue = 10)
public class Usuario {
	
	public static final String IDS_TABLE_NAME = "TABELA_DE_IDS";

	@Id
	@GeneratedValue(generator = IDS_TABLE_NAME, strategy = GenerationType.TABLE)
	private Long id;

}
