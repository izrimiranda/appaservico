package br.com.izri.aservico.model.entity.dao;

import java.util.Calendar;

import br.com.izri.aservico.controller.session.UsuarioSessao;
import br.com.izri.aservico.model.entity.HistoricoEventosSistema;
import br.com.izri.aservico.model.entity.Usuario;
import br.com.izri.aservico.model.entity.entitymanager.EntityManagerUtils;

public class HistoricoEventosSistemaDAO extends EntityManagerUtils<HistoricoEventosSistema> {

	public void salvarEventoSistema(Usuario usuario, String tipoEvento) {

		HistoricoEventosSistema evento = new HistoricoEventosSistema();

		evento.setDataEvento(Calendar.getInstance());
		evento.setObservacao(tipoEvento);
		evento.setUsuario(UsuarioSessao.getInstance().getUsuario());
		evento.setExcluido(Boolean.FALSE);

		if (this.getEntityTransaction().isActive()) {
			this.getEntityManager().merge(evento.getUsuario());
			this.getEntityManager().persist(evento);
			this.getEntityTransaction().commit();
		} else {
			this.getEntityTransaction().begin();
			this.getEntityManager().merge(evento.getUsuario());
			this.getEntityManager().persist(evento);
			this.getEntityTransaction().commit();
		}

		System.out.println("EVENTO SALVO COM SUCESSO");
	}
}
