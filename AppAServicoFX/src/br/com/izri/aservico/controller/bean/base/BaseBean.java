package br.com.izri.aservico.controller.bean.base;

import br.com.izri.aservico.model.entity.dao.UsuarioDAO;

public abstract class BaseBean {

	private UsuarioDAO dao;

	public UsuarioDAO getUsuarioDAO() {
		if (this.dao == null) {
			this.dao = new UsuarioDAO();
		}
		return this.dao;
	}

	public void setUsuarioDAO(UsuarioDAO dao) {
		this.dao = dao;
	}

}
