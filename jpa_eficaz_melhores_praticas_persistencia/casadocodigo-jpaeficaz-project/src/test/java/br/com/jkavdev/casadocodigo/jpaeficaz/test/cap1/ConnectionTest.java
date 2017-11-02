package br.com.jkavdev.casadocodigo.jpaeficaz.test.cap1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class ConnectionTest {
	
	@Test
	public void connectionTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaeficazPU");
		EntityManager manager = factory.createEntityManager();
		
		manager.close();
		factory.close();
	}

}
