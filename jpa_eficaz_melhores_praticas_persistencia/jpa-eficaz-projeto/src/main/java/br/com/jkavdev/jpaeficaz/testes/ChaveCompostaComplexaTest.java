package br.com.jkavdev.jpaeficaz.testes;

import java.util.Date;

import javax.persistence.EntityManager;

import br.com.jkavdev.jpaeficaz.modelo.Escritor;
import br.com.jkavdev.jpaeficaz.modelo.Livro;
import br.com.jkavdev.jpaeficaz.modelo.id.CodigoUnico;
import br.com.jkavdev.jpaeficaz.util.JpaUtil;

public class ChaveCompostaComplexaTest {

	public static void main(String[] args) {

		EntityManager manager = JpaUtil.geEntityManager();

		CodigoUnico codigoUnico = new CodigoUnico(5, new Date());
		Escritor escritor = new Escritor();
		escritor.setNome("Lucas Robis");

		manager.getTransaction().begin();

		manager.persist(escritor);
		manager.persist(codigoUnico);

		CodigoUnico codigo = manager.createQuery("from CodigoUnico", CodigoUnico.class).getSingleResult();
		Escritor escritor1 = manager.createQuery("from Escritor", Escritor.class).getSingleResult();

		Livro livro = new Livro();
		livro.setCodigoUnico(codigo);
		livro.setEscritor(escritor1);

		manager.persist(livro);
		manager.getTransaction().commit();

		JpaUtil.close();
	}

}
