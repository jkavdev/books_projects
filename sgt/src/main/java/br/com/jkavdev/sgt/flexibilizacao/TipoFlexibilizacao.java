package br.com.jkavdev.sgt.flexibilizacao;

public enum TipoFlexibilizacao {
	
	TAXA_CREDITO("Taxa de Cr�dito"),
	TAXA_CAPTACAO("Taxa de Capta��o"),
	TAXA_CAMBIO("Taxa de C�mbio"),
	TARIFA("Tarifa");
	
	private String descricao;

	TipoFlexibilizacao(String descricao) {
		this.descricao = descricao;
	}
	

}
