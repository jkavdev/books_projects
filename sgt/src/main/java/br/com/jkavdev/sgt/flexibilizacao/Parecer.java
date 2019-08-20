package br.com.jkavdev.sgt.flexibilizacao;

import java.time.LocalDateTime;

public class Parecer {

	private String obsNegocial;

	private String obsFinal;
	
	private final LocalDateTime criacao;
	
	private LocalDateTime ultimaAlteracao;

	public Parecer(String obsNegocial, String obsFinal) {
		this();
		this.obsNegocial = obsNegocial;
		this.obsFinal = obsFinal;
	}
	
	public Parecer() {
		this.criacao = LocalDateTime.now();
		this.ultimaAlteracao = LocalDateTime.now();
	}

	public String getObsNegocial() {
		return obsNegocial;
	}

	public void setObsNegocial(String obsNegocial) {
		this.ultimaAlteracao = LocalDateTime.now();
		this.obsNegocial = obsNegocial;
	}

	public String getObsFinal() {
		return obsFinal;
	}

	public void setObsFinal(String obsFinal) {
		this.ultimaAlteracao = LocalDateTime.now();
		this.obsFinal = obsFinal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parecer [obsNegocial=");
		builder.append(obsNegocial);
		builder.append(", obsFinal=");
		builder.append(obsFinal);
		builder.append("]");
		return builder.toString();
	}

}
