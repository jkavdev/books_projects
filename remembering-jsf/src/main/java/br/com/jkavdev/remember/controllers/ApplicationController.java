package br.com.jkavdev.remember.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jkavdev.remember.modelo.Empresa;
import br.com.jkavdev.remember.modelo.Funcionario;
import br.com.jkavdev.remember.repository.ApplicationRepository;

@Named
@ViewScoped
public class ApplicationController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ApplicationRepository repository;
	
	private Funcionario funcionario;
	
	private List<Empresa> empresas;
	
	@PostConstruct
	public void aoIniciar() {
		empresas = repository.empresas();
		funcionario = new Funcionario();
	}

	public void incluirFuncionario() {
		if(funcionario == null) {
			br.com.jkavdev.remember.util.jsf.FacesUtil.addSuccessMessage("Favor preencher dados de funcionario");
		}else {
			repository.incluir(funcionario);
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}

}
