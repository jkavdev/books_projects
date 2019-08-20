package br.com.jkavdev.sgt.flexibilizacao;

public enum TipoCliente {
	
	PF("Pessoa Física"),
	PJ("Pessoa Jurídica");
	
	private String descricao;

	TipoCliente(String descricao) {
		this.descricao = descricao;
	}

}
