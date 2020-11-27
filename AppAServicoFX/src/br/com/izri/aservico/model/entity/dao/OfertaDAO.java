package br.com.izri.aservico.model.entity.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.izri.aservico.auxiliar.PopupDialog;
import br.com.izri.aservico.model.entity.Oferta;
import br.com.izri.aservico.model.entity.entitymanager.EntityManagerUtils;
import br.com.izri.aservico.utils.DateUtils;

public class OfertaDAO extends EntityManagerUtils<Oferta> {

	public void salvarOferta(Oferta oferta) {

		this.getEntityTransaction().begin();

		this.getEntityManager().merge(oferta.getUsuario());
		this.getEntityManager().persist(oferta);

		this.getEntityTransaction().commit();
	}

	public List<Oferta> pesquisarListaTodosIntervaloData(String dataUm, String dataDois) {
		TypedQuery<Oferta> find = null;
		List<Oferta> listaRetorno = null;

		try {
			listaRetorno = new ArrayList<>();

			if (dataUm.isEmpty() && dataDois.isEmpty()) {
				find = this.getEntityManager().createNamedQuery(Oferta.FIND_ALL, Oferta.class);
				find.setParameter("excluido", false);
				listaRetorno = find.getResultList();
			} else {
				if (DateUtils.isDatasValidas(dataUm, dataDois)) {
					find = this.getEntityManager().createNamedQuery(Oferta.FIND_BY_INTERVALO_DATA_ENTRADA_OFERTA, Oferta.class);
					find.setParameter("dataIntervaloUm", DateUtils.parseToCalendar(dataUm, false));
					find.setParameter("dataIntervaloDois", DateUtils.parseToCalendar(dataDois, false));
					find.setParameter("excluido", false);

					listaRetorno = find.getResultList();
				} else {
					PopupDialog.getInstance().exibirPopupErro("As datas estão inválidas. Por favor, verifique.", "Datas inválidas");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRetorno;
	}

	public void exclusaoLogicaPorId(int id) {
		TypedQuery<Oferta> find = null;

		try {
			find = this.getEntityManager().createNamedQuery(Oferta.EXCUSAO_LOGICA_POR_ID, Oferta.class);
			find.setParameter("idExclusao", id);

			this.begin();
			int executar = find.executeUpdate();
			this.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Oferta> buscarTodasOfertas() {
		List<Oferta> listaRetorno = null;
		TypedQuery<Oferta> find = null;

		try {
			find = this.getEntityManager().createNamedQuery(Oferta.FIND_ALL, Oferta.class);
			find.setParameter("excluido", false);

			listaRetorno = find.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRetorno;
	}

}
