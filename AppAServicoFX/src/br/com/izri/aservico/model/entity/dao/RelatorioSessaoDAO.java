package br.com.izri.aservico.model.entity.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.izri.aservico.model.entity.RelatorioSessao;
import br.com.izri.aservico.model.entity.dao.base.BaseDAO;
import br.com.izri.aservico.model.entity.entitymanager.EntityManagerUtils;
import br.com.izri.aservico.utils.DateUtils;

/**
 *
 * @author Izrí
 *
 */
public class RelatorioSessaoDAO extends EntityManagerUtils<RelatorioSessao> {

	/**
	 * Salva objeto RelatorioSessao no banco.
	 *
	 * @param relatorio
	 *            RelatorioSessao
	 */
	public void salvarRelatorioSessao(RelatorioSessao relatorio) {
		this.begin();
		this.getEntityManager().merge(relatorio.getUsuario());
		this.getEntityManager().persist(relatorio);
		this.getEntityTransaction().commit();

	}

	/**
	 * Buscar por data sessão e por observação.
	 *
	 * @param dataInicio
	 *            String
	 * @param dataFinal
	 *            String
	 * @param observacao
	 *            String
	 * @return List<RelatorioSessao>
	 */
	public List<RelatorioSessao> buscarPorDataSessaoObservacao(String dataInicio, String dataFinal, String observacao) {
		List<RelatorioSessao> listaRelatorioRetorno = null;
		TypedQuery<RelatorioSessao> find = null;

		try {
			find = this.getEntityManager().createNamedQuery(RelatorioSessao.FIND_BY_DATA_SESSAO_OBSERVACAO, RelatorioSessao.class);

			find.setParameter("dataInicio", DateUtils.parseToCalendar(dataInicio, false));
			find.setParameter("dataFinal", DateUtils.parseToCalendar(dataFinal, false));
			find.setParameter("observacao", String.format(BaseDAO.LIKE_CLAUSE_PATTERN, observacao));
			find.setParameter("excluido", false);

			listaRelatorioRetorno = find.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRelatorioRetorno;
	}

	/**
	 * Busca por "observação".
	 *
	 * @param observação
	 *            String
	 * @return List<RelatorioSessao>
	 */
	public List<RelatorioSessao> buscarPorObservacao(String observacao) {
		List<RelatorioSessao> listaRelatorioSessaRetorno = null;
		TypedQuery<RelatorioSessao> find = null;

		try {

			find = this.getEntityManager().createNamedQuery(RelatorioSessao.FIND_BY_OBSERVACAO, RelatorioSessao.class);
			find.setParameter("observacao", String.format(BaseDAO.LIKE_CLAUSE_PATTERN, observacao));
			find.setParameter("excluido", false);

			listaRelatorioSessaRetorno = find.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRelatorioSessaRetorno;

	}

	/**
	 * Busca por data da sessão.
	 *
	 * @param dataInicio
	 * @param dataFinal
	 * @return List<RelatorioSessao>
	 */
	public List<RelatorioSessao> buscarPorDataSessao(String dataInicio, String dataFinal) {
		List<RelatorioSessao> listaRelatorioSessaoRetorno = null;
		TypedQuery<RelatorioSessao> find = null;

		try {
			find = this.getEntityManager().createNamedQuery(RelatorioSessao.FIND_BY_INTERVALO_DATA_SESSAO, RelatorioSessao.class);

			find.setParameter("dataInicio", DateUtils.parseToCalendar(dataInicio, false));
			find.setParameter("dataFinal", DateUtils.parseToCalendar(dataFinal, false));
			find.setParameter("excluido", false);

			listaRelatorioSessaoRetorno = find.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRelatorioSessaoRetorno;
	}

	/**
	 * Busca todos os relatórios registrados
	 *
	 * @return List<RelatorioSessao>
	 */
	public List<RelatorioSessao> buscarTodos() {
		List<RelatorioSessao> listaRelatorioSessaoRetorno = null;
		TypedQuery<RelatorioSessao> find = null;

		find = this.getEntityManager().createNamedQuery(RelatorioSessao.FIND_ALL, RelatorioSessao.class);
		find.setParameter("excluido", false);

		listaRelatorioSessaoRetorno = find.getResultList();

		return listaRelatorioSessaoRetorno;
	}

	public void exclusaoLogicaPorId(int id) {
		TypedQuery<RelatorioSessao> find = null;

		find = this.getEntityManager().createNamedQuery(RelatorioSessao.EXCLUSAO_LOGICA_POR_ID, RelatorioSessao.class);
		find.setParameter("dataExclusao", Calendar.getInstance());
		find.setParameter("id", id);

		this.begin();
		int executar = find.executeUpdate();
		this.commit();
	}
}
