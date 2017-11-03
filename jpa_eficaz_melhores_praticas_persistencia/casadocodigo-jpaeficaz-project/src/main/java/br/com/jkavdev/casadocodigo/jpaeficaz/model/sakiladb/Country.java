package br.com.jkavdev.casadocodigo.jpaeficaz.model.sakiladb;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Todas entidade ter 3 definicoes
//Ser anotacao com @Entity
//Definir um atributo com @Id
//E ter um construtor sem argumentos

//Definindo que eh uma entidade
//Name defini como sera esta entidade sera reconhecida pela jpa
//Podemos ver esta alteracao quando realizamos uma consulta 'from Pais'
@Entity(name = "Pais")
// Definindo o nome da tabela no banco de dados
@Table(name = "country")
public class Country {

	// Definindo o id da tabela
	@Id
	// No caso sera um atributo inteiro, onde sua estrategia de unicidade
	// ficara por conta do banco de dados
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Definindo o nome e tipo da chave primaria no banco de dados
	@Column(name = "country_id", columnDefinition = "smallint")
	private Long id;

	@Column(name = "country")
	private String name;

	// Com basic, estamos definindo dados basicos das entidades
	// Com optional estamos indicando que este campo eh opcional, nao obrigatorio

	// Com fetch estamos indicando que o jpa nao precisa retornar
	// este dado numa pesquisa
	// Podemos utilizalos caso este caso seje muito grande,
	// ou nao consultamos este dado frequentemente
	@Basic(optional = true, fetch = FetchType.LAZY)
	@Column(name = "last_update")
	private Date lastUpdate;
	
	public Country(String name) {
		this.name = name;
	}
	
	protected Country() { }

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	}

}
