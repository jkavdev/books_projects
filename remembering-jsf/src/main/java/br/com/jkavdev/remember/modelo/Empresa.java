package br.com.jkavdev.remember.modelo;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Empresa {
	
	@Id
	private String sigla;
	
	private String nome;
	
	@OneToMany(mappedBy = "empresa")
	private Set<Funcionario> funcionarios;

	protected Empresa() { }
	
	public Empresa(String sigla, String nome) {
		this.sigla = sigla;
		this.nome = nome;
	}
	
	public Set<Funcionario> getFuncionarios() {
		return Collections.unmodifiableSet(funcionarios);
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void adicionarFuncionario(Funcionario funcionario) {
		this.funcionarios.add(funcionario);
	}

}
