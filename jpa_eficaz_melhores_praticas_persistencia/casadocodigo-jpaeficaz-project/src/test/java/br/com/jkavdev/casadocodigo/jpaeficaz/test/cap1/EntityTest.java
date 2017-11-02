package br.com.jkavdev.casadocodigo.jpaeficaz.test.cap1;

import org.junit.Test;

import br.com.jkavdev.casadocodigo.jpaeficaz.model.Country;

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

}
