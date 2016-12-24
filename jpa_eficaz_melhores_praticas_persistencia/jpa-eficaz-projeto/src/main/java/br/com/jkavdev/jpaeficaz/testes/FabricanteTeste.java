package br.com.jkavdev.jpaeficaz.testes;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.jkavdev.jpaeficaz.modelo.Fabricante;
import br.com.jkavdev.jpaeficaz.util.JpaUtil;

public class FabricanteTeste {

	public static void main(String[] args) {

		EntityManager manager = JpaUtil.geEntityManager();

		try {
			Fabricante fabricante = manager.createQuery("from fabricante", Fabricante.class).getSingleResult();

			System.out.println(fabricante.getNome());
		} catch (NoResultException e) {
			System.out.println("Nenhum resultado encontrado!");
		}

		Fabricante fabricante = new Fabricante();
		fabricante.setNome("Jhonatan");

		manager.getTransaction().begin();
		manager.persist(fabricante);
		manager.getTransaction().commit();

		JpaUtil.close();

	}

}
