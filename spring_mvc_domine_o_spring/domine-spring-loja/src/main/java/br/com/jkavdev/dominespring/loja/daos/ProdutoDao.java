package br.com.jkavdev.dominespring.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jkavdev.dominespring.loja.model.Produto;

@Repository
public class ProdutoDao {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void salva(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> lista() {
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto busca(Integer id) {
		TypedQuery<Produto> query = manager
				.createQuery("select distinct(p) from Produto p join fetch p.prices where p.id=:id", Produto.class)
				.setParameter("id", id);
		
		return query.getSingleResult();
	}
}
