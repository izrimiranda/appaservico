package br.com.izri.aservico.model.entity.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.izri.aservico.model.entity.Celula;
import br.com.izri.aservico.model.entity.entitymanager.EntityManagerUtils;

public class CelulaDAO extends EntityManagerUtils<Celula> {

	private HistoricoEventosSistemaDAO historicoEventosDAO;

	public HistoricoEventosSistemaDAO getHistoricoEventosDAO() {
		if (this.historicoEventosDAO == null) {
			this.historicoEventosDAO = new HistoricoEventosSistemaDAO();
		}

		return this.historicoEventosDAO;
	}

	public Celula buscarCelulaPorNome(String nomeCelula) {
		Celula celulaRetorno = null;
		TypedQuery<Celula> find = null;

		try {

			celulaRetorno = new Celula();

			find = this.getEntityManager().createNamedQuery(Celula.FIND_BY_NOME_CELULA, Celula.class);
			find.setParameter("nomeCelula", nomeCelula);
			find.setParameter("excluido", false);

			celulaRetorno = find.getSingleResult();
			// this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(),
			// "Pesquisa feita pelo nome da célula: Nome da Célula: " +
			// nomeCelula);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return celulaRetorno;
	}

	public void salvarCelulaTeste(String nomeCelula) {
		Celula celula = new Celula();

		celula.setCoordenador(new UsuarioDAO().getUsuarioPorLogin("izrimiranda"));
		celula.setDataNascimento(Calendar.getInstance());
		celula.setDiaDaSemanaReuniao("Segunda");
		celula.setHorarioReuniao("19:30");
		celula.setLider(new UsuarioDAO().getUsuarioPorLogin("izrimiranda"));
		celula.setNomeCelula("Celula " + nomeCelula);

		this.getEntityTransaction().begin();
		this.getEntityManager().merge(celula.getLider());
		this.getEntityManager().persist(celula);
		this.getEntityTransaction().commit();
		System.out.println("CELULA SALVA COM SUCESSO");
	}

	public List<Celula> pesquisarTodasCelulas() {

		List<Celula> listaCelula = new ArrayList<>();
		TypedQuery<Celula> find = null;

		find = this.getEntityManager().createNamedQuery(Celula.FIND_ALL, Celula.class);
		find.setParameter("excluido", false);

		listaCelula = find.getResultList();

		// this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(),
		// "Pesquisa feita de todas as células.");

		return listaCelula;
	}
}
