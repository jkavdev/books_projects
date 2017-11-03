package br.com.jkavdev.casadocodigo.jpaeficaz.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUtils {

	private final static PersistenceUnitName PERSISTENCE_UNIT = PersistenceUnitName.JPA_EFICAZ_PU;

	// Neste comando Persistence.createEntityManagerFactory("jpaeficazPU")
	// A JPA fara a leitura do persistence.xml
	// Realizara a validacao de conexao, verificao das entidades da aplicacao
	// Validacao de schema do banco
	// Por isso este processo requer um tempo adicional para tais varreduras
	// Nao sendo indicado ser realizado varias vezes, devido ao tempo tomado

	// Eh seguro ter o factory como estatico, devido ele ser thread-safe
	private static EntityManagerFactory factory;

	// Utilizando o threadLocal que definira um entityManager diferente para cada
	// thread diferente
	private static ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<>();

	public static EntityManager getEntityManager(PersistenceUnitName PERSISTENCE_UNIT) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT.getPersistenceUnitName());
		return getEntityManager();
	}

	public static EntityManager getEntityManager() {
		
		EntityManager manager = null;
		
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT.getPersistenceUnitName());
			manager = threadEntityManager.get();
		}

		if (manager == null || !manager.isOpen()) {
			manager = factory.createEntityManager();
			threadEntityManager.set(manager);
		}

		return manager;
	}

	public static void closeEntityManager() {
		EntityManager manager = threadEntityManager.get();

		if (manager != null) {
			EntityTransaction transaction = manager.getTransaction();

			if (transaction.isActive()) {
				transaction.commit();
			}

			manager.close();

			threadEntityManager.set(null);
		}
	}

	public static void closeEntityManagerFactory() {
		closeEntityManager();
		factory.close();
	}

}
