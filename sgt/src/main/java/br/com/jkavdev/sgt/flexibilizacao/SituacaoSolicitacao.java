package br.com.jkavdev.sgt.flexibilizacao;

public enum SituacaoSolicitacao {

	AGUARDANDO_APROVACAO("Em Aprova��o", 
			"A solicita��o � criada e est� aguardando a aprova��o ou reprova��o pelo workflow de vota��o"),
	EM_APROVACAO("Em Aprova��o",
			"A solicita��o ap�s criada inicia a valida��o pelas al�adas de vota��o"),
	APROVADA("Aprovada",
			"Todas as al�adas aprovaram a solicita��o ou foi aprovada automaticamente pelo workflow"),
	REJEITADA("Rejeitada",
			"Ao menos uma al�ada reprovou a solicita��o ou foi reprovada automaticamente pelo workflow"),
	CANCELADA("Cancelada",
			"A solicita��o � exclu�da pelo solicitante ou pelo gestor do sistema"),
	VENCIDA("Vencida",
			"A solicita��o ultrapassou o per�odo definido nos par�metros de validade.");

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
