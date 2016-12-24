package br.com.jkavdev.jpaeficaz.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class JpaUtil {

	private static final Logger logger = Logger.getLogger(JpaUtil.class);
	private static final String PERSISTENCE_UNIT = "jpaEficazPU";
	private static EntityManagerFactory factory;

	private static ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<>();

	private JpaUtil() {
	}

	public static EntityManager geEntityManager() {
		if (factory == null) {
			logger.info("Criando Factory....................");

			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		}

		EntityManager entityManager = threadEntityManager.get();

		if (entityManager == null || !entityManager.isOpen()) {
			logger.info("Criando EntityManager....................");
			entityManager = factory.createEntityManager();
			JpaUtil.threadEntityManager.set(entityManager);
		}

		return entityManager;
	}

	public static void close() {
		EntityManager entityManager = threadEntityManager.get();

		if (entityManager != null) {
			EntityTransaction transaction = entityManager.getTransaction();

			if (transaction.isActive()) {
				transaction.commit();
			}

			logger.info("Fechando EntityManager....................");

			entityManager.close();

			threadEntityManager.set(null);
		}
	}

}
