package br.com.jkavdev.casadocodigo.jpaeficaz.test.cap1;

import org.junit.Assert;
import org.junit.Test;

import br.com.jkavdev.casadocodigo.jpaeficaz.model.jpaeficazdb.Autor;
import br.com.jkavdev.casadocodigo.jpaeficaz.model.jpaeficazdb.Pais;
import br.com.jkavdev.casadocodigo.jpaeficaz.model.sakiladb.Country;

public class EntityTest extends JpaTestUtils {

	@Test
	public void fetchCountryTest() {
		getManager().createQuery("from Pais", Country.class).getResultList().forEach(System.out::println);
	}

	@Test
	public void insertCountry() {
		beginTransaction();

		// Nao precisamos definir a data de atualizacao
		// Devido a @Basic true
		Country country = new Country("Novo Pais");

		getManager().persist(country);
	}
	
	@Test
	public void insertCountryInOutro() {
		beginTransactionOutro();
		
		Pais pais = new Pais("Novo Pais");
		
		getManagerOutro().persist(pais);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void insertAutorTest() {
		Autor jhonatan = new Autor(1, "Jhonatan");
		beginTransactionOutro();
		getManagerOutro().persist(jhonatan);
		Autor jhonatanBanco = getManager().find(Autor.class, jhonatan.getId());
		Assert.assertEquals(jhonatanBanco.getId(), jhonatan.getId());
		
		//Erro, inserindo autores com mesmo id
		Autor lucas = new Autor(1, "Lucas");
		beginTransactionOutro();
		getManagerOutro().persist(lucas);
	}

}
