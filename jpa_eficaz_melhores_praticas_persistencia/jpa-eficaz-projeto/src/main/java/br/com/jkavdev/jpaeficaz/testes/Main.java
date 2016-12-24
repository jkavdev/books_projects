package br.com.jkavdev.jpaeficaz.testes;

import javax.persistence.EntityManager;

import br.com.jkavdev.jpaeficaz.modelo.Musica;
import br.com.jkavdev.jpaeficaz.util.JpaUtil;

public class Main {

	public static void main(String[] args) {

		EntityManager manager = JpaUtil.geEntityManager();

		manager.find(Musica.class, "1");

		JpaUtil.close();

	}

}
