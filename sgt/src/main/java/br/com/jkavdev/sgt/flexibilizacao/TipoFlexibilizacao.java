package br.com.jkavdev.sgt.flexibilizacao;

public enum TipoFlexibilizacao {
	
	TAXA_CREDITO("Taxa de Crédito"),
	TAXA_CAPTACAO("Taxa de Captação"),
	TAXA_CAMBIO("Taxa de Câmbio"),
	TARIFA("Tarifa");
	
	private String descricao;

	TipoFlexibilizacao(String descricao) {
		this.descricao = descricao;
	}
	

}
