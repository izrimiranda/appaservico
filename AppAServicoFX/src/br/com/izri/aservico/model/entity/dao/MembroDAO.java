package br.com.izri.aservico.model.entity.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.izri.aservico.auxiliar.Constantes;
import br.com.izri.aservico.controller.session.UsuarioSessao;
import br.com.izri.aservico.model.entity.Celula;
import br.com.izri.aservico.model.entity.Endereco;
import br.com.izri.aservico.model.entity.Membro;
import br.com.izri.aservico.model.entity.TurmaEscolaBiblica;
import br.com.izri.aservico.model.entity.dao.base.BaseDAO;
import br.com.izri.aservico.model.entity.entitymanager.EntityManagerUtils;
import br.com.izri.aservico.utils.DateUtils;

public class MembroDAO extends EntityManagerUtils<Membro> {

	private HistoricoEventosSistemaDAO historicoEventosDAO;

	public HistoricoEventosSistemaDAO getHistoricoEventosDAO() {
		if (this.historicoEventosDAO == null) {
			this.historicoEventosDAO = new HistoricoEventosSistemaDAO();
		}

		return this.historicoEventosDAO;
	}

	public List<Membro> pesquisarTodos() {
		List<Membro> listaMembro = null;
		TypedQuery<Membro> find = null;

		try {
			find = this.getEntityManager().createNamedQuery(Membro.FIND_ALL, Membro.class);

			find.setParameter("excluido", false);

			listaMembro = find.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaMembro;
	}

	public void salvarOrMergeMembro(Membro membro) {
		membro.setExcluido(false);
		membro.setDataRegistroMembro(Calendar.getInstance());

		this.getEntityTransaction().begin();

		if (membro.getId() == 0) {
			if (membro.getCelula() != null) {
				this.getEntityManager().persist(membro.getCelula());
				this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Membro salvo. Nome do membro: " + membro.getNomeCompleto());
			}

			if (membro.getTurmaEscolaBiblica() != null) {
				this.getEntityManager().persist(membro.getTurmaEscolaBiblica());
			}

			if (membro.getEndereco() != null) {
				this.getEntityManager().persist(membro.getEndereco());
			}

			this.getEntityManager().persist(membro);
		} else {
			if (membro.getCelula() != null) {
				this.getEntityManager().merge(membro.getCelula());
			}

			if (membro.getTurmaEscolaBiblica() != null) {
				this.getEntityManager().merge(membro.getTurmaEscolaBiblica());
			}

			if (membro.getEndereco() != null) {
				this.getEntityManager().merge(membro.getEndereco());
			}

			this.getEntityManager().merge(membro);
			this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Membro atualizado. Nome do membro: " + membro.getNomeCompleto());
		}

		this.getEntityTransaction().commit();
	}

	public List<Membro> pesquisarMembro(String opcao, Membro membro) {
		List<Membro> listaRetorno = null;
		TypedQuery<Membro> find = null;

		try {
			listaRetorno = new ArrayList<>();
			if (opcao.equals(Constantes.PESQUISAR_TODOS)) {

				System.out.println("PESQUISANDO TODOS");
				find = this.getEntityManager().createNamedQuery(Membro.FIND_ALL, Membro.class);

				find.setParameter("excluido", Boolean.FALSE);

				listaRetorno = find.getResultList();
				this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Pesquisa Todos os membros.");
			} else if (opcao.equals(Constantes.BAIRRO)) {
				find = this.getEntityManager().createNamedQuery(Membro.FIND_BY_ENDERECO_BAIRRO, Membro.class);

				find.setParameter("bairro", String.format(BaseDAO.LIKE_CLAUSE_PATTERN, membro.getEndereco().getBairro()));
				find.setParameter("excluido", Boolean.FALSE);

				listaRetorno = find.getResultList();
				this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Pesquisa Membro por Bairro. Bairro Membro: " + membro.getEndereco().getBairro());
			} else if (opcao.equals(Constantes.CARGO_ECLESIASTICO)) {
				find = this.getEntityManager().createNamedQuery(Membro.FIND_BY_CARGO_ECLESIASTICO, Membro.class);

				find.setParameter("cargoEclesiastico", String.format(BaseDAO.LIKE_CLAUSE_PATTERN, membro.getCargoEclesiastico()));
				find.setParameter("excluido", Boolean.FALSE);

				listaRetorno = find.getResultList();
				this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Pesquisa membro por cargo eclesiástico. Cargo Eclesiástico Membro: " + membro.getCargoEclesiastico());
			} else if (opcao.equals(Constantes.CELULA)) {
				find = this.getEntityManager().createNamedQuery(Membro.FIND_BY_CELULA_NOME_CELULA, Membro.class);

				find.setParameter("nomeCelula", String.format(BaseDAO.LIKE_CLAUSE_PATTERN, membro.getCelula().getNomeCelula()));
				find.setParameter("excluido", Boolean.FALSE);

				listaRetorno = find.getResultList();
				this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Pesquisa de membro por nome da Célula. Nome da Célula: " + membro.getCelula().getNomeCelula());
			} else if (opcao.equals(Constantes.DATA_DE_ANIVERSARIO)) {
				find = this.getEntityManager().createNamedQuery(Membro.FIND_BY_INTERVALO_DATA_ANIVERSARIO, Membro.class);

				find.setParameter("dataIntervaloUm", membro.getDataAniversario());
				find.setParameter("dataIntervaloDois", membro.getDataAniversario());
				find.setParameter("excluido", Boolean.FALSE);

				listaRetorno = find.getResultList();
				this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Pesquisa de membro por Data de Aniversário. Data Aniversário Membro: " + membro.getDataAniversarioStr());
			} else if (opcao.equals(Constantes.DATA_DE_BATISMO)) {
				find = this.getEntityManager().createNamedQuery(Membro.FIND_BY_INTERVALO_DATA_BATISMO, Membro.class);

				find.setParameter("dataIntervaloUm", membro.getDataBatismo());
				find.setParameter("dataIntervaloDois", membro.getDataBatismo());
				find.setParameter("excluido", Boolean.FALSE);

				listaRetorno = find.getResultList();
				this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Pesquisa de membro por Data de Batismo. Data de Batismo Membro: " + membro.getDataBatismoStr());

			} else if (opcao.equals(Constantes.DATA_DE_REGISTRO)) {
				find = this.getEntityManager().createNamedQuery(Membro.FIND_BY_INTERVALO_DATA_REGISTRO_MEMBRO, Membro.class);

				find.setParameter("dataIntervaloUm", membro.getDataRegistroMembro());
				find.setParameter("dataIntervaloDois", membro.getDataRegistroMembro());
				find.setParameter("excluido", Boolean.FALSE);

				listaRetorno = find.getResultList();
				this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Pesquisa de membro por Data de Registro: Data de Registro Membro: " + membro.getDataRegistroMembroStr());
			} else if (opcao.equals(Constantes.NOME_COMPLETO)) {

				if (membro.getNomeCompleto().equals(Constantes.TODOS)) {
					find = this.getEntityManager().createNamedQuery(Membro.FIND_ALL, Membro.class);
				} else {
					find = this.getEntityManager().createNamedQuery(Membro.FIND_BY_NOME, Membro.class);
					find.setParameter("nomeCompleto", String.format(BaseDAO.LIKE_CLAUSE_PATTERN, membro.getNomeCompleto()));
				}

				find.setParameter("excluido", Boolean.FALSE);

				listaRetorno = find.getResultList();
				this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Pesquisa de membro por Nome Completo: Nome do Membro: " + membro.getNomeCompleto());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRetorno;
	}

	public void testarSalvarMembro() {
		Membro membro = new Membro();
		Celula celula = new Celula();
		TurmaEscolaBiblica turma = new TurmaEscolaBiblica();

		celula.setNomeCelula("Celula Teste 1");
		turma.setNomeTurma("Turma Teste 1");

		membro.setCelula(celula);
		membro.setTurmaEscolaBiblica(turma);

		membro.setCargoEclesiastico("Membro");

	}

	public void salvarMembroTeste(String posicao) {
		Membro membro = new Membro();

		Endereco endereco = new Endereco();

		endereco.setBairro("São Geraldo");
		endereco.setCEP("35450-000");
		endereco.setCidade("Itabirito");
		endereco.setComplemento("A");
		endereco.setExcluido(false);
		endereco.setLogradouro("Rua Doutor Baeta Costa");
		endereco.setNumero(490);
		endereco.setUf("MG");

		membro.setCargoEclesiastico("Membro");
		membro.setCelula(new CelulaDAO().buscarCelulaPorNome("Celula Um"));
		membro.setDataAniversario(DateUtils.parseToCalendar("03/02/1988", false));
		membro.setDataBatismo(DateUtils.parseToCalendar("01/01/2000", false));
		membro.setDataRegistroMembro(Calendar.getInstance());
		membro.setEmail("email" + posicao + "@teste.com.br");
		membro.setEndereco(endereco);

		membro.setExcluido(false);
		membro.setNomeCompleto("Izri Miranda " + posicao);
		membro.setNumeroCelularUm("(31)98880-2212");
		membro.setTurmaEscolaBiblica(new TurmaEscolaBiblicaDAO().buscarTurmaEscolaBiblicaPorNome("Turma Um"));

		this.getEntityTransaction().begin();
		this.getEntityManager().merge(membro.getEndereco());
		this.getEntityManager().merge(membro.getTurmaEscolaBiblica());
		this.getEntityManager().merge(membro.getCelula());
		this.getEntityManager().persist(membro);
		this.getEntityTransaction().commit();

		System.out.println("MEMBRO TESTE SALVO COM SUCESSO");

	}

	public void exclusaoLogicaMembroPorId(int id) {

		TypedQuery<Membro> find = null;

		find = this.getEntityManager().createNamedQuery(Membro.EXCLUSAO_LOGICA_POR_ID, Membro.class);
		find.setParameter("nomeUsuarioDelecao", UsuarioSessao.getInstance().getUsuario().getNomeCompleto());
		find.setParameter("dataDelecao", Calendar.getInstance());
		find.setParameter("idMembro", id);

		this.getEntityTransaction().begin();
		int executar = find.executeUpdate();
		this.getEntityTransaction().commit();
		this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Membro exclusão lógica. ID membro: " + id);

		System.out.println("MEMBRO EXCLUIDO");

	}
}
