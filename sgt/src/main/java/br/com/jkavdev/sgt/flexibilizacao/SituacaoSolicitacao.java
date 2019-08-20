package br.com.jkavdev.sgt.flexibilizacao;

public enum SituacaoSolicitacao {

	AGUARDANDO_APROVACAO("Em Aprovação", 
			"A solicitação é criada e está aguardando a aprovação ou reprovação pelo workflow de votação"),
	EM_APROVACAO("Em Aprovação",
			"A solicitação após criada inicia a validação pelas alçadas de votação"),
	APROVADA("Aprovada",
			"Todas as alçadas aprovaram a solicitação ou foi aprovada automaticamente pelo workflow"),
	REJEITADA("Rejeitada",
			"Ao menos uma alçada reprovou a solicitação ou foi reprovada automaticamente pelo workflow"),
	CANCELADA("Cancelada",
			"A solicitação é excluída pelo solicitante ou pelo gestor do sistema"),
	VENCIDA("Vencida",
			"A solicitação ultrapassou o período definido nos parâmetros de validade.");

	private String descricao;
	private String detalhada;

	SituacaoSolicitacao(String descricao, String detalhada) {
		this.descricao = descricao;
		this.detalhada = detalhada;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getDetalhada() {
		return detalhada;
	}

}
