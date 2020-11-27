package br.com.izri.aservico.controller.session;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.izri.aservico.model.entity.Usuario;

public class SessionContext {

	private static SessionContext instance;

	public static SessionContext getInstance() {
		if (SessionContext.instance == null) {
			SessionContext.instance = new SessionContext();
		}
		return SessionContext.instance;
	}

	private SessionContext() {

	}

	private ExternalContext currentExternalContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
		} else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}

	public Usuario getUsuarioLogado() {
		return (Usuario) this.getAttribute("usuarioLogado");
	}

	public void setUsuarioLogado(Usuario usuario) {
		this.setAttribute("usuarioLogado", usuario);

	}

	public void encerrarSessao() {
		this.currentExternalContext().invalidateSession();
	}

	public Object getAttribute(String nome) {
		return this.currentExternalContext().getSessionMap().get(nome);
	}

	public void setAttribute(String nome, Object valor) {
		this.currentExternalContext().getSessionMap().put(nome, valor);
	}
}
