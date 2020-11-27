package br.com.izri.aservico.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.izri.aservico.utils.DateUtils;

/**
 *
 * @author Izrí Miranda
 *
 */
@Entity
@Table(name = "tbl_turma_escola_biblica")
@NamedQueries({ @NamedQuery(name = "TurmaEscolaBiblica.findAll", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findById", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.id = :id AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findByAno", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.ano = :ano AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findBySemestre", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.semestre = :semestre AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findByTipoTurma", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.tipoTurma = :tipoTurma AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findBySala", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.sala = :sala AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findByIntervaloHorario", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.horario BETWEEN :horarioIntervaloUm AND :horarioIntervaloDois AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findByDiaDaSemana", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.diaDaSemana = :diaDaSemana AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findByProfessor", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.professor LIKE :professor AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findByUsuarioLogin", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.usuario.login LIKE :login AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findByUsuarioNome", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.usuario.nomeCompleto LIKE :nome AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findByIntervaloDataCadastro", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.dataCadastro BETWEEN :dataCadastroIntervaloUm AND :dataCadastroIntervaloDois AND teb.excluido = :excluido"),
	@NamedQuery(name = "TurmaEscolaBiblica.findByNomeTurma", query = "SELECT teb FROM TurmaEscolaBiblica teb WHERE teb.nomeTurma LIKE :nomeTurma AND teb.excluido = :excluido")

})
public class TurmaEscolaBiblica implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8918188784920105365L;

	public static final String FIND_ALL = "TurmaEscolaBiblica.findAll";
	public static final String FIND_BY_ID = "TurmaEscolaBiblica.findById";
	public static final String FIND_BY_ANO = "TurmaEscolaBiblica.findByAno";
	public static final String FIND_BY_SEMESTRE = "TurmaEscolaBiblica.findBySemestre";
	public static final String FIND_BY_TIPO_TURMA = "TurmaEscolaBiblica.findByTipoTurma";
	public static final String FIND_BY_SALA = "TurmaEscolaBiblica.findBySala";
	public static final String FIND_BY_INTERVALO_HORARIO = "TurmaEscolaBiblica.findByIntervaloHorario";
	public static final String FIND_BY_DIA_DA_SEMANA = "TurmaEscolaBiblica.findByDiaDaSemana";
	public static final String FIND_BY_PROFESSOR = "TurmaEscolaBiblica.findByProfessor";
	public static final String FIND_BY_USUARIO_LOGIN = "TurmaEscolaBiblica.findByUsuarioLogin";
	public static final String FIND_BY_USUARIO_NOME = "TurmaEscolaBiblica.findByUsuarioNome";
	public static final String FIND_BY_INTERVALO_DATA_CADASTRO = "TurmaEscolaBiblica.findByIntervaloDataCadastro";
	public static final String FIND_BY_NOME_TURMA = "TurmaEscolaBiblica.findByNomeTurma";

	private int id;

	private int ano;
	private int semestre;
	private List<Membro> listaMembro;
	private String tipoTurma;
	private String sala;
	private String horario;
	private String diaDaSemana;
	private String professor;
	private String observacao;
	private String nomeTurma;
	private Usuario usuario;
	private Calendar dataCadastro;
	private boolean excluido;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ANO", nullable = false)
	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Column(name = "SEMESTRE", nullable = false)
	public int getSemestre() {
		return this.semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	@OneToMany(targetEntity = Membro.class, cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "turmaEscolaBiblica")
	public List<Membro> getListaMembro() {
		if (this.listaMembro == null) {
			this.listaMembro = new ArrayList<>();
		}
		return this.listaMembro;
	}

	public void setListaMembro(List<Membro> listaMembro) {
		this.listaMembro = listaMembro;
	}

	@Column(name = "TIPO_TURMA", nullable = false)
	public String getTipoTurma() {
		return this.tipoTurma;
	}

	/**
	 * Adultos, Jovens, Adolescentes, Crianças
	 *
	 * @return
	 */
	public void setTipoTurma(String tipoTurma) {
		this.tipoTurma = tipoTurma;
	}

	@Column(name = "SALA")
	public String getSala() {
		return this.sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	@Column(name = "HORARIO")
	public String getHorario() {
		return this.horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Column(name = "DIA_DA_SEMANA", nullable = false)
	public String getDiaDaSemana() {
		return this.diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	@Column(name = "PROFESSOR", nullable = false)
	public String getProfessor() {
		return this.professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	@Column(name = "OBSERVACAO")
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "NOME_TURMA")
	public String getNomeTurma() {
		return this.nomeTurma;
	}

	/**
	 * Nome da matéria que será ministrada.
	 *
	 * @param nomeTurma
	 */
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "USUARIO_ID")
	public Usuario getUsuario() {
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
		return this.usuario;
	}

	@Transient
	public String getDataCadastroStr() {
		String retorno = "";

		if (this.dataCadastro != null) {
			retorno = DateUtils.format(this.dataCadastro, DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return retorno;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO", nullable = false)
	public Calendar getDataCadastro() {
		return this.dataCadastro;
	}

	@Column(name = "EXCLUIDO", nullable = false)
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

}
