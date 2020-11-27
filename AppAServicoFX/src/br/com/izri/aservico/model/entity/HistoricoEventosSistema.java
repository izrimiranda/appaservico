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

/**
 *
 * @author Izrí Miranda
 *
 */

@Entity
@Table(name = "tbl_historico_eventos_sistema")
@NamedQueries({ @NamedQuery(name = "HistoricoEventosSistema.findAll", query = "SELECT hes FROM HistoricoEventosSistema hes WHERE hes.excluido = :excluido"),
	@NamedQuery(name = "HistoricoEventosSistema.findById", query = "SELECT hes FROM HistoricoEventosSistema hes WHERE hes.id = :id AND hes.excluido = :excluido"),
	@NamedQuery(name = "HistoricoEventosSistema.findByIntervaloDataEvento", query = "SELECT hes FROM HistoricoEventosSistema hes WHERE hes.dataEvento BETWEEN :dataIntervaloum AND :dataIntervaloDois  AND hes.excluido = :excluido"),
	@NamedQuery(name = "HistoricoEventosSistema.findByDataEvento", query = "SELECT hes FROM HistoricoEventosSistema hes WHERE hes.dataEvento = :dataEvento  AND hes.excluido = :excluido"),
	@NamedQuery(name = "HistoricoEventosSistema.findByNomeUsuario", query = "SELECT hes FROM HistoricoEventosSistema hes WHERE hes.usuario.nomeCompleto LIKE :nome  AND hes.excluido = :excluido"),
	@NamedQuery(name = "HistoricoEventosSistema.findByLoginUsuario", query = "SELECT hes FROM HistoricoEventosSistema hes WHERE hes.usuario.login LIKE :login  AND hes.excluido = :excluido") })
public class HistoricoEventosSistema implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 766052440034075893L;

	public static final String FIND_ALL = "HistoricoEventosSistema.findAll";
	public static final String FIND_BY_ID = "HistoricoEventosSistema.findById";
	public static final String FIND_BY_INTERVALO_DATA_EVENTO = "HistoricoEventosSistema.findByIntevaloDataEvento";
	public static final String FIND_BY_DATA_EVENTO = "HistoricoEventosSistema.findByDataEvento";
	public static final String FIND_BY_NOME_USUARIO = "HistoricoEventosSistema.findByNomeUsuario";
	public static final String FIND_BY_LOGIN_USUARIO = "HistoricoEventosSistema.findByLoginUsuario";

	private int id;
	private Calendar dataEvento;
	private Usuario usuario;
	private String observacao;
	private boolean excluido;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_EVENTO")
	public Calendar getDataEvento() {
		return this.dataEvento;
	}

	@Transient
	public String getDataEventoStr() {
		String retorno = "";
		if (this.dataEvento != null) {
			retorno = DateUtils.format(this.dataEvento, DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return retorno;
	}

	public void setDataEvento(Calendar dataEvento) {
		this.dataEvento = dataEvento;
	}

	@OneToOne(cascade = { CascadeType.MERGE })
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

	@Transient
	public static long getSerialversionuid() {
		return HistoricoEventosSistema.serialVersionUID;
	}

	@Column(name = "EXCLUIDO", nullable = false)
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

}
