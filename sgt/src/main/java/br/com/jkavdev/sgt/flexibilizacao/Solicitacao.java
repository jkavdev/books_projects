package br.com.jkavdev.sgt.flexibilizacao;

import java.time.LocalDateTime;

public class Solicitacao {
	
	private Integer codigo;
	
	private LocalDateTime horaEnvio;
	
	private Double valor;
	
	private Solicitante solicitante;
	
	private SituacaoSolicitacao situacao;
	
	public void aprovar() {
		this.situacao = SituacaoSolicitacao.APROVADA;
	}
	
	public void reprovar() {
		this.situacao = SituacaoSolicitacao.REJEITADA;
	}
	
	public void emAndamento() {
		this.situacao = SituacaoSolicitacao.EM_APROVACAO;
	}
	

}
