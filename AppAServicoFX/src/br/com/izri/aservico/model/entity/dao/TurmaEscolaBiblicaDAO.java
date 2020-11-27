package br.com.izri.aservico.model.entity.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.izri.aservico.model.entity.TurmaEscolaBiblica;
import br.com.izri.aservico.model.entity.entitymanager.EntityManagerUtils;

public class TurmaEscolaBiblicaDAO extends EntityManagerUtils<TurmaEscolaBiblica> {

	public TurmaEscolaBiblica buscarTurmaEscolaBiblicaPorNome(String nomeTurma) {

		TurmaEscolaBiblica turmaRetorno = null;
		TypedQuery<TurmaEscolaBiblica> find = null;

		try {
			turmaRetorno = new TurmaEscolaBiblica();
			find = this.getEntityManager().createNamedQuery(TurmaEscolaBiblica.FIND_BY_NOME_TURMA, TurmaEscolaBiblica.class);
			find.setParameter("nomeTurma", nomeTurma);
			find.setParameter("excluido", false);
			turmaRetorno = find.getSingleResult();

		} catch (Exception e) {
			return null;
		}

		return turmaRetorno;
	}

	public void salvarTurmaTeste(String nomeTurma) {
		TurmaEscolaBiblica turma = new TurmaEscolaBiblica();

		turma.setDataCadastro(Calendar.getInstance());
		turma.setAno(2018);
		turma.setDiaDaSemana("Segunda");
		turma.setHorario("19:00");
		turma.setNomeTurma("Turma " + nomeTurma);
		turma.setProfessor("Izri Miranda");
		turma.setSala("01");
		turma.setSemestre(1);
		turma.setTipoTurma("Adultos");
		turma.setUsuario(new UsuarioDAO().getUsuarioPorLogin("izrimiranda"));

		this.getEntityTransaction().begin();
		this.getEntityManager().persist(turma);
		this.getEntityTransaction().commit();
		System.out.println("SALVAR TURMA");
	}

	public List<TurmaEscolaBiblica> pesquisarTodasTurmas() {
		TypedQuery<TurmaEscolaBiblica> find = null;

		find = this.getEntityManager().createNamedQuery(TurmaEscolaBiblica.FIND_ALL, TurmaEscolaBiblica.class);
		find.setParameter("excluido", false);

		return find.getResultList();
	}
}
