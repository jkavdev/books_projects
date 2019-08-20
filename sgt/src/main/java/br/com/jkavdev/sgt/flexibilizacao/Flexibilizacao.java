package br.com.jkavdev.sgt.flexibilizacao;

public class Flexibilizacao {

	private Solicitacao solicitacao;

	private Proposta proposta;

	private Cliente cliente;

	private TipoFlexibilizacao tipo;

	private Produto produto;

	private Dependencia dependencia;

	private Alcada alcada;

	private Parecer parecer;

	public void parecer(Parecer parecer2) {
		this.parecer = parecer2;
	}

	public void aprovar() {
		this.solicitacao.aprovar();
	}

	public void rejeitar() {
		this.solicitacao.reprovar();
	}

}
