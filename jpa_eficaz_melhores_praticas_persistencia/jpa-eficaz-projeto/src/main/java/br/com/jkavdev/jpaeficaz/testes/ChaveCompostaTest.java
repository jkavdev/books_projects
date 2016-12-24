package br.com.jkavdev.jpaeficaz.testes;

import java.util.Date;

import javax.persistence.EntityManager;

import br.com.jkavdev.jpaeficaz.modelo.Autor;
import br.com.jkavdev.jpaeficaz.modelo.Cliente;
import br.com.jkavdev.jpaeficaz.modelo.Tarefa;
import br.com.jkavdev.jpaeficaz.modelo.id.AutorId;
import br.com.jkavdev.jpaeficaz.modelo.id.CodigoUnico;
import br.com.jkavdev.jpaeficaz.util.JpaUtil;

public class ChaveCompostaTest {

	public static void main(String[] args) {

		EntityManager manager = JpaUtil.geEntityManager();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo(1L);
		cliente.setNome("Jhonatan");
		
		Autor autor = new Autor();
		autor.setCodigo(new AutorId(1L, "Lucas"));
		
		Tarefa tarefa = new Tarefa();
		tarefa.setDescricao("Acordar cedo todo dia");
		CodigoUnico codigoUnico = new CodigoUnico(5, new Date());

		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.persist(autor);
		manager.persist(codigoUnico);
		
		CodigoUnico codigo = manager.createQuery("from CodigoUnico", CodigoUnico.class).getSingleResult();		
		tarefa.setCodigoUnico(codigo);
		
		manager.persist(tarefa);
		manager.getTransaction().commit();

		JpaUtil.close();
	}

}
