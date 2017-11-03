package br.com.jkavdev.casadocodigo.jpaeficaz.test.cap1;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.junit.Test;

import br.com.jkavdev.casadocodigo.jpaeficaz.model.sakiladb.Country;

public class TransactionHandleTest extends JpaTestUtils {

	@Test
	public void transactionTest() {
		EntityTransaction transaction = getManager().getTransaction();

		transaction.begin();

		List<Country> countries = getManager().createQuery("from Pais", Country.class).getResultList();
		countries.forEach(System.out::println);

		transaction.commit();
	}

	@Test
	public void transactionTryTest() throws Exception {
		// Ao realizar o controle de transacao manualmente
		// Temos tambem que ter controle caso ocorra algum erro
		// Para deixar alguma recurso ou conexao aberto

		try {
			EntityTransaction transaction = getManager().getTransaction();

			transaction.begin();

			List<Country> countries = getManager().createQuery("from Country", Country.class).getResultList();
			countries.forEach(System.out::println);

			transaction.commit();

			throw new Exception("Testando close do Junit");

		} catch (Exception e) {
			if (getManager().isOpen() && getManager().getTransaction().isActive()) {
				// rever isso
				// nao realiza o rollback em algo que ja foi commitado
				getManager().getTransaction().rollback();
			}
		} finally {
			if (getManager().isOpen()) {
				getManager().close();
			}
		}

	}

}
