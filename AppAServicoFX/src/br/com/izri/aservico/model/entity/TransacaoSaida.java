package br.com.izri.aservico.model.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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

/**
 *
 * @author Izrí Miranda
 *
 */
@Entity
@Table(name = "tbl_transacao_saida")
@NamedQueries({ @NamedQuery(name = "TransacaoSaida.findAll", query = "SELECT ts FROM TransacaoSaida ts WHERE ts.excluido = :excluido"),
	@NamedQuery(name = "TransacaoSaida.findById", query = "SELECT ts FROM TransacaoSaida ts WHERE ts.id = :id AND ts.excluido = :excluido"),
	@NamedQuery(name = "TransacaoSaida.findByDataSaida", query = "SELECT ts FROM TransacaoSaida ts WHERE ts.dataSaida = :dataSaida AND ts.excluido = :excluido"),
	@NamedQuery(name = "TransacaoSaida.findByIntervaloDataSaida", query = "SELECT ts FROM TransacaoSaida ts WHERE ts.dataSaida BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND ts.excluido = :excluido"),
	@NamedQuery(name = "TransacaoSaida.findByUsuarioLogin", query = "SELECT ts FROM TransacaoSaida ts WHERE ts.usuario.login LIKE :usuario AND ts.excluido = :excluido"),
	@NamedQuery(name = "TransacaoSaida.findByIntervaloValor", query = "SELECT ts FROM TransacaoSaida ts WHERE ts.valor BETWEEN :valorIntervaloUm AND :valorIntervalorDois AND ts.excluido = :excluido"),
	@NamedQuery(name = "TransacaoSaida.exclusaoLogicaPorId", query = "UPDATE TransacaoSaida ts SET ts.excluido = 1 WHERE ts.id = :idTransacaoSaida") })
public class TransacaoSaida implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 456761678356234347L;

	public static final String FIND_ALL = "TransacaoSaida.findAll";
	public static final String FIND_BY_ID = "TransacaoSaida.findById";
	public static final String FIND_BY_DATA_SAIDA = "TransacaoSaida.findByDataSaida";
	public static final String FIND_BY_INTERVALO_DATA_SAIDA = "TransacaoSaida.findByIntervaloDataSaida";
	public static final String FIND_BY_USUARIO_LOGIN = "TransacaoSaida.findByUsuarioLogin";
	public static final String FIND_BY_INTERVALO_VALOR = "TransacaoSaida.findByIntevaloValor";
	public static final String UPDATE_EXCLUSAO_LOGICA_POR_ID = "TransacaoSaida.exclusaoLogicaPorId";

	private int id;

	private Double valor;
	private Calendar dataSaida;
	private Usuario usuario;
	private String observacao;
	private boolean excluido;

	@Id
	@GeneratedValue
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

	@Column(name = "DATA_SAIDA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataSaida() {
		return this.dataSaida;
	}

	@Transient
	public String getDataSaidaStr() {
		String retorno = "";
		if (this.dataSaida != null) {
			retorno = DateUtils.format(this.dataSaida, DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}
		return retorno;
	}

	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}

	@OneToOne
	@JoinColumn(name = "USUARIO_ID")
	public Usuario getUsuario() {
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
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

}
