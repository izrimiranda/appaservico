package br.com.izri.aservico.controller.session;

import br.com.izri.aservico.model.entity.Usuario;

public class UsuarioSessao {

	private static UsuarioSessao instance;
	private Usuario usuario;

	private UsuarioSessao() {

	}

	public static UsuarioSessao getInstance() {
		if (UsuarioSessao.instance == null) {
			UsuarioSessao.instance = new UsuarioSessao();
		}
		return UsuarioSessao.instance;
	}

	public void setInstance(UsuarioSessao instance) {
		UsuarioSessao.instance = instance;
	}

	public Usuario getUsuario() {
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
