package br.com.jkavdev.casadocodigo.jpaeficaz.test.cap1;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

import br.com.jkavdev.casadocodigo.jpaeficaz.utils.JpaUtils;

public class JpaTestUtils {

	private EntityManager manager;

	@Before
	public void setup() {
		manager = JpaUtils.getEntityManager();
	}

	@After
	public void close() {
		JpaUtils.closeEntityManager();
		JpaUtils.closeEntityManagerFactory();
	}

	public EntityManager getManager() {
		return manager;
	}

}