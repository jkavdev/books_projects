package br.com.jkavdev.remember.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jkavdev.remember.modelo.Empresa;
import br.com.jkavdev.remember.modelo.Funcionario;
import br.com.jkavdev.remember.util.jpa.Transactional;

public class ApplicationRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public void incluir(Funcionario funcionario) {
		manager.persist(funcionario);
	}

	public List<Empresa> empresas() {
		return manager.createQuery("from Empresa", Empresa.class).getResultList();
	}

}
