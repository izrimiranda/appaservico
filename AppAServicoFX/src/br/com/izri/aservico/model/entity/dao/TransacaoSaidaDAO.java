package br.com.izri.aservico.model.entity.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.izri.aservico.model.entity.TransacaoSaida;
import br.com.izri.aservico.model.entity.entitymanager.EntityManagerUtils;
import br.com.izri.aservico.utils.DateUtils;

public class TransacaoSaidaDAO extends EntityManagerUtils<TransacaoSaida> {

	public List<TransacaoSaida> buscarTodos() {
		TypedQuery<TransacaoSaida> find = null;
		List<TransacaoSaida> listaRetornoBanco = null;

		try {
			listaRetornoBanco = new ArrayList<>();

			find = this.getEntityManager().createNamedQuery(TransacaoSaida.FIND_ALL, TransacaoSaida.class);
			find.setParameter("excluido", false);

			listaRetornoBanco = find.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRetornoBanco;
	}

	public List<TransacaoSaida> buscarPorData(String dataUm, String dataDois) {
		TypedQuery<TransacaoSaida> find = null;
		List<TransacaoSaida> listaRetornoBanco = null;

		try {
			listaRetornoBanco = new ArrayList<>();

			find = this.getEntityManager().createNamedQuery(TransacaoSaida.FIND_BY_INTERVALO_DATA_SAIDA, TransacaoSaida.class);
			find.setParameter("dataIntervaloUm", DateUtils.parseToCalendar(dataUm, false));
			find.setParameter("dataIntervaloDois", DateUtils.parseToCalendar(dataDois, false));
			find.setParameter("excluido", false);

			listaRetornoBanco = find.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRetornoBanco;
	}

	public void salvarTransacaoSaida(TransacaoSaida saida) {

		this.begin();
		this.getEntityManager().merge(saida.getUsuario());
		this.getEntityManager().persist(saida);
		this.getEntityTransaction().commit();

	}

	public void exclusaoLogica(int id) {
		System.out.println("EXCLUSAO LÓGICA: " + id);
		TypedQuery<TransacaoSaida> find = null;

		try {
			find = this.getEntityManager().createNamedQuery(TransacaoSaida.UPDATE_EXCLUSAO_LOGICA_POR_ID, TransacaoSaida.class);
			find.setParameter("idTransacaoSaida", id);

			this.begin();
			int executar = find.executeUpdate();
			this.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
