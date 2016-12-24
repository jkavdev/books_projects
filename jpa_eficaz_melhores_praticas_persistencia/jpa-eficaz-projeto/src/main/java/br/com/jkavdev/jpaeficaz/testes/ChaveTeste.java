package br.com.jkavdev.jpaeficaz.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.jkavdev.jpaeficaz.modelo.Acessorio;
import br.com.jkavdev.jpaeficaz.modelo.Aluguel;
import br.com.jkavdev.jpaeficaz.modelo.ModeloCarro;
import br.com.jkavdev.jpaeficaz.util.JpaUtil;

public class ChaveTeste {

	public static void main(String[] args) {

		EntityManager manager = JpaUtil.geEntityManager();

		Acessorio acessorio = new Acessorio();
		acessorio.setDescricao("Ar condicionado");
		
		Aluguel aluguel = new Aluguel();
		aluguel.setValorTotal(new BigDecimal(800));
		
		ModeloCarro modeloCarro = new ModeloCarro();
		modeloCarro.setDescricao("Uno Fire");

		manager.getTransaction().begin();
		manager.persist(acessorio);
		manager.persist(aluguel);
		manager.persist(modeloCarro);
		manager.getTransaction().commit();

		JpaUtil.close();

	}

}
