package br.com.jkavdev.casadocodigo.jpaeficaz.test.cap1;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

import br.com.jkavdev.casadocodigo.jpaeficaz.utils.JpaUtils;
import br.com.jkavdev.casadocodigo.jpaeficaz.utils.PersistenceUnitName;

public class JpaTestUtils {

	private EntityManager manager;
	private EntityManager managerOutro;
	private EntityManager managerPostgres;

	@Before
	public void setup() {
		manager = JpaUtils.getEntityManager();
		managerOutro = JpaUtils.getEntityManager(PersistenceUnitName.JPA_EFICAZ_OUTRO_PU);
		managerPostgres = JpaUtils.getEntityManager(PersistenceUnitName.JPA_EFICAZ_POSTGRES_PU);
	}

	@After
	public void close() {
		JpaUtils.closeEntityManagerFactory();
	}

	public void beginTransaction() {
		manager.getTransaction().begin();
	}

	public void beginTransactionOutro() {
		managerOutro.getTransaction().begin();
	}

	public void beginTransactionPostgres() {
		managerPostgres.getTransaction().begin();
	}

	public void commit() {
		manager.getTransaction().commit();
	}

	public void commitOutro() {
		managerOutro.getTransaction().commit();
	}

	public void commitPostgres() {
		managerPostgres.getTransaction().commit();
	}

	public EntityManager getManager() {
		return manager;
	}

	public EntityManager getManagerOutro() {
		return managerOutro;
	}

	public EntityManager getManagerPostgres() {
		return managerPostgres;
	}

}