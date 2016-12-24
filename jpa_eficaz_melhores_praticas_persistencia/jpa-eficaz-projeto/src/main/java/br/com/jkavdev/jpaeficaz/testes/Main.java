package br.com.jkavdev.jpaeficaz.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.jkavdev.jpaeficaz.modelo.Musica;

public class Main {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaEficazPU");
		EntityManager manager = factory.createEntityManager();
		
		manager.find(Musica.class, "1");

		manager.close();
		factory.close();

	}

}
