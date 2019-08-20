package br.com.jkavdev.sgt.flexibilizacao;

public enum TipoCliente {
	
	PF("Pessoa F�sica"),
	PJ("Pessoa Jur�dica");
	
	private String descricao;

	TipoCliente(String descricao) {
		this.descricao = descricao;
	}

}
