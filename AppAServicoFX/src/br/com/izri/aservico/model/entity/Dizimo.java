package br.com.izri.aservico.model.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

import br.com.izri.aservico.utils.DateUtils;

@Entity
@Table(name = "tbl_dizimo")
@NamedQueries({ @NamedQuery(name = "Dizimo.findAll", query = "SELECT d FROM Dizimo d WHERE d.excluido = :excluido"),
	@NamedQuery(name = "Dizimo.findById", query = "SELECT d FROM Dizimo d WHERE d.id = :id AND d.excluido = :excluido"),
	@NamedQuery(name = "Dizimo.findByIntervaloValor", query = "SELECT d FROM Dizimo d WHERE d.valor BETWEEN :intervaloUm AND :intervaloDois AND d.excluido = :excluido"),
	@NamedQuery(name = "Dizimo.findByIntervaloDataEntrada", query = "SELECT d FROM Dizimo d WHERE d.dataEntradaDizimo BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND d.excluido = :excluido"),
	@NamedQuery(name = "Dizimo.findByDataEntrada", query = "SELECT d FROM Dizimo d WHERE d.dataEntradaDizimo = :dataEntradaDizimo AND d.excluido = :excluido"),
	@NamedQuery(name = "Dizimo.findByUsuarioLogin", query = "SELECT d FROM Dizimo d WHERE d.usuario.login LIKE :login AND d.excluido = :excluido"),
	@NamedQuery(name = "Dizimo.findByUsuarioNome", query = "SELECT d FROM Dizimo d WHERE d.usuario.nomeCompleto LIKE :nomeCompleto AND d.excluido = :excluido"),
	@NamedQuery(name = "Dizimo.findByNomeMembro", query = "SELECT d FROM Dizimo d WHERE d.membro.nomeCompleto LIKE :nomeCompleto AND d.excluido = :excluido"),
	@NamedQuery(name = "Dizimo.findByIntervaloDataNomeMembro", query = "SELECT d FROM Dizimo d WHERE d.dataEntradaDizimo BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND d.membro.nomeCompleto LIKE :nomeCompleto and d.excluido = :excluido"),
	@NamedQuery(name = "Dizimo.exclusaoLogicaPorId", query = "UPDATE Dizimo d SET d.excluido = 1 WHERE d.id = :idDizimo") })
public class Dizimo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6101541189821285603L;

	public static final String FIND_ALL = "Dizimo.findAll";
	public static final String FIND_BY_ID = "Dizimo.findById";
	public static final String FIND_BY_INTERVALO_VALOR = "Dizimo.findByIntervaloValor";
	public static final String FIND_BY_INTERVALO_DATA_ENTRADA = "Dizimo.findByIntervaloDataEntrada";
	public static final String FIND_BY_DATA_ENTRADA = "Dizimo.findByDataEntrada";
	public static final String FIND_BY_USUARIO_LOGIN = "Dizimo.findByUsuarioLogin";
	public static final String FIND_BY_USUARIO_NOME = "Dizimo.findByUsuarioNome";
	public static final String FIND_BY_NOME_MEMBRO = "Dizimo.findByNomeMembro";
	public static final String FIND_BY_INTERVALO_DATA_NOME_MEMBRO = "Dizimo.findByIntervaloDataNomeMembro";
	public static final String EXCLUSAO_LOGICA_POR_ID = "Dizimo.exclusaoLogicaPorId";

	private int id;

	private Double valor;
	private Calendar dataEntradaDizimo;
	private Usuario usuario;
	private String observacao;
	private boolean excluido;
	private Membro membro;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "VALOR", nullable = false)
	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Column(name = "DATA_ENTRADA_DIZIMO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataEntradaDizimo() {
		return this.dataEntradaDizimo;
	}

	@Transient
	public String getDataEntradaDizimoStr() {
		String retorno = "";
		if (this.dataEntradaDizimo != null) {
			retorno = DateUtils.format(this.dataEntradaDizimo, DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}
		return retorno;
	}

	public void setDataEntradaDizimo(Calendar dataEntradaDizimo) {
		this.dataEntradaDizimo = dataEntradaDizimo;
	}

	@OneToOne
	@JoinColumn(name = "USUARIO_ID")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "OBSERVACAO")
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "EXCLUIDO", nullable = false)
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "MEMBRO_ID")
	public Membro getMembro() {
		return this.membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

}
