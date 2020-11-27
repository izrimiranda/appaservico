package br.com.izri.aservico.model.entity.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.izri.aservico.auxiliar.PopupDialog;
import br.com.izri.aservico.model.entity.Dizimo;
import br.com.izri.aservico.model.entity.entitymanager.EntityManagerUtils;
import br.com.izri.aservico.utils.DateUtils;
import br.com.izri.aservico.utils.StringUtils;

public class DizimoDAO extends EntityManagerUtils<Dizimo> {

	public void salvarDizimo(Dizimo dizimo) {

		this.getEntityTransaction().begin();

		this.getEntityManager().merge(dizimo.getMembro());
		this.getEntityManager().merge(dizimo.getUsuario());
		this.getEntityManager().persist(dizimo);

		this.getEntityTransaction().commit();
	}

	public List<Dizimo> pesquisarTodosDizimos() {
		TypedQuery<Dizimo> find = null;
		List<Dizimo> listaRetornoBanco = null;

		try {

			find = this.getEntityManager().createNamedQuery(Dizimo.FIND_ALL, Dizimo.class);
			find.setParameter("excluido", false);

			listaRetornoBanco = find.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRetornoBanco;
	}

	public List<Dizimo> pesquisarDizimoPorData(String dataUm, String dataDois) {
		TypedQuery<Dizimo> find = null;
		List<Dizimo> listaRetornoBanco = null;

		try {
			listaRetornoBanco = new ArrayList<>();

			find = this.getEntityManager().createNamedQuery(Dizimo.FIND_BY_INTERVALO_DATA_ENTRADA, Dizimo.class);
			find.setParameter("dataIntervaloUm", dataUm);
			find.setParameter("dataIntervaloDois", dataDois);
			find.setParameter("excluido", false);

			listaRetornoBanco = find.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRetornoBanco;
	}

	public List<Dizimo> pesquisarDizimoPorNome(String nomeMembro) {
		TypedQuery<Dizimo> find = null;
		List<Dizimo> listaRetornoBanco = null;

		try {
			listaRetornoBanco = new ArrayList<>();

			find = this.getEntityManager().createNamedQuery(Dizimo.FIND_BY_NOME_MEMBRO, Dizimo.class);
			find.setParameter("nomeMembro", nomeMembro);
			find.setParameter("excluido", false);

			listaRetornoBanco = find.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRetornoBanco;
	}

	public List<Dizimo> pesquisarPorIntervaloDataPorNomeMembro(String dataUm, String dataDois, String nomeMembro) {
		TypedQuery<Dizimo> find = null;
		List<Dizimo> listaRetorno = null;
		System.out.println("@@@ Pesquisando");
		try {
			listaRetorno = new ArrayList<>();

			System.out.println("Data1: " + dataUm);
			System.out.println("Data2: " + dataDois);
			System.out.println("Nome: " + nomeMembro);

			if (dataUm.isEmpty() && dataDois.isEmpty() && (nomeMembro == null)) {
				find = this.getEntityManager().createNamedQuery(Dizimo.FIND_ALL, Dizimo.class);
				find.setParameter("excluido", false);
				listaRetorno = find.getResultList();
			} else if (StringUtils.isNotEmpty(dataUm) && StringUtils.isNotEmpty(dataDois) && ((nomeMembro == null) || (nomeMembro == ""))) {
				if (DateUtils.isDatasValidas(dataUm, dataDois)) {
					find = this.getEntityManager().createNamedQuery(Dizimo.FIND_BY_INTERVALO_DATA_ENTRADA, Dizimo.class);
					find.setParameter("dataIntervaloUm", DateUtils.parseToCalendar(dataUm, false));
					find.setParameter("dataIntervaloDois", DateUtils.parseToCalendar(dataDois, false));
					find.setParameter("excluido", false);
					listaRetorno = find.getResultList();
				} else {
					PopupDialog.getInstance().exibirPopupErro("As datas estão inválidas. Por favor, verifique.", "Datas inválidas");
				}

			} else if (StringUtils.isEmpty(dataUm) && StringUtils.isEmpty(dataDois) && StringUtils.isNotEmpty(nomeMembro)) {
				find = this.getEntityManager().createNamedQuery(Dizimo.FIND_BY_NOME_MEMBRO, Dizimo.class);
				find.setParameter("nomeCompleto", nomeMembro);
				find.setParameter("excluido", false);
				listaRetorno = find.getResultList();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRetorno;
	}

	public void exclusaoLogicaPorId(int id) {
		TypedQuery<Dizimo> find = null;

		try {
			find = this.getEntityManager().createNamedQuery(Dizimo.EXCLUSAO_LOGICA_POR_ID, Dizimo.class);
			find.setParameter("idDizimo", id);

			this.begin();
			int executar = find.executeUpdate();
			this.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
