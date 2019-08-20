package br.com.jkavdev.sgt.flexibilizacao;

import java.util.List;

public class Gerenciador {
	
	private Flexibilizacao flexibilizacao;
	private List<HistoricoFlexibilizacao> historico;

	public Gerenciador(Flexibilizacao flexibilizacao) {
		this.flexibilizacao = flexibilizacao;
		buscarHistorico(flexibilizacao);
	}

	private void buscarHistorico(Flexibilizacao flexibilizacao2) {
		
	}

	public void avaliar() {
		
	}

	public void aprovar() {
		
		//atualizar que status?
		
		System.out.println(flexibilizacao);
		
		preencherParecer();
		
		flexibilizacao.aprovar();
		
		//aprovar ou reprovar, apenas do parecer ou da flexibilizacao

	}

	private void preencherParecer() {
		Parecer parecer = new Parecer();
		parecer.setObsNegocial("Cliente muito confiavel");
		this.flexibilizacao.parecer(parecer);
		
		System.out.println("parecer preenchido");
	}

	public void rejeitar() {
		
		System.out.println(flexibilizacao);
		
		flexibilizacao.rejeitar();

	}
	
	public void exibirHistorico() {
		
		//votacoes por alcada??
		
		//uma flexibilizacao pode ser votada por varias alcadas
		
		//como eu ligo uma flexibilizacao a uma alcada??
		
		//varias alcadas podem estar vinculadas a mesma flexibilizacao??
		
		//eh uma relacionamento muitos pra muitos??
		
		//qual o criterio de aprovacao de uma flexibilizacao??
		
		//aonde eh realizada a votacao da flexibilizacao
		
		//todos os parametros de visualizacao se diferem para cada tipo de flexibilizacao
		
	}

}
