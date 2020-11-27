package br.com.izri.aservico.model.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Izrí Miranda
 *
 */
@Entity
@Table(name = "tbl_relatorio_celula")
@NamedQueries({ @NamedQuery(name = "RelatorioCelula.findAll", query = "SELECT rc FROM RelatorioCelula rc"),
	@NamedQuery(name = "RelatorioCelula.findById", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.id = :id AND rc.excluido = :excluido"),
	@NamedQuery(name = "RelatorioCelula.findByIntervaloDataNascimento", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.dataNascimento BETWEEN :intervaloDataUm AND :intervaloDataDois AND rc.excluido = :excluido"),
	@NamedQuery(name = "RelatorioCelula.findByUsuarioId", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.usuario.id = :idUsuario AND rc.excluido = :excluido"),
	@NamedQuery(name = "RelatorioCelula.findByUsuarioNome", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.usuario.nomeCompleto LIKE :nomeCompleto AND rc.excluido = :excluido"),
	@NamedQuery(name = "RelatorioCelula.findByCelulaId", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.celula.id = :id"),
	@NamedQuery(name = "RelatorioCelula.findByCelulaNome", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.celula.nomeCelula LIKE :nomeCelula AND rc.excluido = :excluido"),
	@NamedQuery(name = "RelatorioCelula.findByIntervaloDataReuniao", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.dataReuniao BETWEEN :intervaloDataUm AND :intervaloDataDois AND rc.excluido = :excluido"),
	@NamedQuery(name = "RelatorioCelula.findByAnalisado", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.analisado = :analisado"),
	@NamedQuery(name = "RelatorioCelula.findByExcluido", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.excluido = :excluido"),
	@NamedQuery(name = "RelatorioCelula.findByIsVisitantes", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.visitantes = :isVisitantes AND rc.excluido = :excluido"),
	@NamedQuery(name = "RelatorioCelula.findByIsVisitantesCadastrados", query = "SELECT rc FROM RelatorioCelula rc WHERE rc.visitantesCadastrados = :isVisitantesCadastrados AND rc.excluido = :excluido") })
public class RelatorioCelula implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "RelatorioCelula.findAll";
	public static final String FIND_BY_ID = "RelatorioCelula.findById";
	public static final String FIND_BY_INTERVALO_DATA_NASCIMENTO = "RelatorioCelula.findByIntervaloDataNascimento";
	public static final String FIND_BY_CELULA_ID = "RelatorioCelula.findByCelulaId";
	public static final String FIND_BY_USUARIO_ID = "RelatorioCelula.findByUsuarioId";
	public static final String FIND_BY_USUARIO_NOME = "RelatorioCelula.findByUsuarioNome";
	public static final String FIND_BY_CELULA_NOME = "RelatorioCelula.findByCelulaNome";
	public static final String FIND_BY_INTERVALO_DATA_REUNIAO = "RelatorioCelula.findByIntervaloDataReuniao";
	public static final String FIND_BY_ANALISADO = "RelatorioCelula.findByAnalisado";
	public static final String FIND_BY_EXCLUIDO = "RelatorioCelula.findByExcluido";
	public static final String FIND_BY_IS_VISITANTES = "RelatorioCelula.findByIsVisitantes";
	public static final String FIND_BY_IS_VISITANTES_CADASTRADOS = "RelatorioCelula.IsVisitantesCadastrados";

	private int id;
	private Calendar dataNascimento;
	private Usuario usuario;
	private Celula celula;
	private Calendar dataReuniao;
	private boolean analisado;
	private boolean excluido;
	private String observacao;
	private boolean visitantes;
	private boolean visitantesCadastrados;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	public Calendar getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@OneToOne
	@JoinColumn(name = "USUARIO_ID", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToOne
	@JoinColumn(name = "CELULA_ID", nullable = false)
	public Celula getCelula() {
		return this.celula;
	}

	public void setCelula(Celula celula) {
		this.celula = celula;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_REUNIAO", nullable = false)
	public Calendar getDataReuniao() {
		return this.dataReuniao;
	}

	public void setDataReuniao(Calendar dataReuniao) {
		this.dataReuniao = dataReuniao;
	}

	@Column(name = "ANALISADO", nullable = false)
	public boolean isAnalisado() {
		return this.analisado;
	}

	public void setAnalisado(boolean analisado) {
		this.analisado = analisado;
	}

	@Column(name = "EXCLUIDO")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	@Column(name = "OBSERVACAO")
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "VISITANTES")
	public boolean isVisitantes() {
		return this.visitantes;
	}

	public void setVisitantes(boolean visitantes) {
		this.visitantes = visitantes;
	}

	@Column(name = "VISITANTES_CADASTRADOS")
	public boolean isVisitantesCadastrados() {
		return this.visitantesCadastrados;
	}

	public void setVisitantesCadastrados(boolean visitantesCadastrados) {
		this.visitantesCadastrados = visitantesCadastrados;
	}

}
