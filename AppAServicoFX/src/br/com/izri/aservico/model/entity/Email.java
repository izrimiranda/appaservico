package br.com.izri.aservico.model.entity;

public class Email {

	private String assunto;
	private StringBuilder mensagemBuilder;
	private String destinatario;

	public String getAssunto() {
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public StringBuilder getMensagemBuilder() {
		if (this.mensagemBuilder == null) {
			this.mensagemBuilder = new StringBuilder();
		}
		return this.mensagemBuilder;
	}

	public void setMensagemBuilder(StringBuilder mensagemBuilder) {
		this.mensagemBuilder = mensagemBuilder;
	}

	public String getDestinatario() {
		return this.destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

}
