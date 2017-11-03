package br.com.jkavdev.casadocodigo.jpaeficaz.model.jpaeficazdb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
// Podemos definir seu valor inicial, 
// e tambem podemos indicar um lote de ids em memoria
// sem que o jpa precise consultar qual sera o proximo id
@SequenceGenerator(
		name = Livro.SEQUENCE_NAME,
		sequenceName = Livro.SEQUENCE_NAME,
		initialValue = 1,
		allocationSize = 50)
public class Livro {
	
	public static final String SEQUENCE_NAME = "SEQ_LIVRO";

	@Id
	// Utilizando uma sequence
	// No mysql nao existe sequence, ao utilizar uma sequence no mysql
	// Ele cria uma tabela que armazera este valor, e o controle de id
	// estara nesta tabela
	
	// A sequence eh utilizada pelo Postgres e Oracle
	
	// 1 - jpa busca um lote de id e salva em memoria
	// 2 - jpa persiste o registro com um id salvo em memoria
	// 3 - jpa realizara outra busca a um lote de se os em memoria se esgotarem
	@GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
	private Long id;

}
