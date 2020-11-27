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
import javax.persistence.Transient;

import br.com.izri.aservico.utils.DateUtils;

/**
 *
 * @author Izrí Miranda
 *
 */
@Entity
@Table(name = "tbl_historico_auxilio")
@NamedQueries({ @NamedQuery(name = "HistoricoAuxilio.findAll", query = "SELECT ha FROM HistoricoAuxilio ha WHERE ha.excluido = :excluido"),
	@NamedQuery(name = "HistoricoAuxilio.findById", query = "SELECT ha FROM HistoricoAuxilio ha WHERE ha.id = :id AND ha.excluido = :excluido"),
	@NamedQuery(name = "HistoricoAuxilio.findByDataAuxilio", query = "SELECT ha FROM HistoricoAuxilio ha WHERE ha.dataAuxilio = :dataAuxilio AND ha.excluido = :excluido"),
	@NamedQuery(name = "HistoricoAuxilio.findByIntervaloDataAuxilio", query = "SELECT ha FROM HistoricoAuxilio ha WHERE ha.dataAuxilio BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND ha.excluido = :excluido"),
	@NamedQuery(name = "HistoricoAuxilio.findByLoginUsuario", query = "SELECT ha FROM HistoricoAuxilio ha WHERE ha.usuario.login = :loginUsuario AND ha.excluido = :excluido"),
	@NamedQuery(name = "HistoricoAuxilio.findByNomeAuxiliado", query = "SELECT ha FROM HistoricoAuxilio ha WHERE ha.auxiliado.nomeCompleto LIKE :nomeAuxiliado AND ha.excluido = :excluido"),
	@NamedQuery(name = "HistoricoAuxilio.findByDocumentoAuxiliado", query = "SELECT ha FROM HistoricoAuxilio ha WHERE ha.auxiliado.numeroDocumento LIKE :numeroDocumento AND ha.excluido = :excluido") })
public class HistoricoAuxilio implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -529021445764159824L;

	public static final String FIND_ALL = "HistoricoAuxilio.findAll";
	public static final String FIND_BY_ID = "HistoricoAuxilio.findById";
	public static final String FIND_BY_DATA_AUXILIO = "HistoricoAuxilio.findByDataAuxilio";
	public static final String FIND_BY_INTERVALO_DATA_AUXILIO = "HistoricoAuxilio.findByIntervaloDataAuxilio";
	public static final String FIND_BY_LOGIN_USUARIO = "HistoricoAuxilio.findByLoginUsuario";
	public static final String FIND_BY_NOME_AUXILIADO = "HistoricoAuxilio.findByNomeAuxiliado";
	public static final String FIND_BY_DOCUMENTO_AUXILIADO = "HistoricoAuxilio.findByDocumentoAuxiliado";

	private int id;

	private Calendar dataAuxilio;
	private String observacao;
	private Usuario usuario;
	private Auxiliado auxiliado;
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

	@Column(name = "DATA_AUXILIO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataAuxilio() {
		return this.dataAuxilio;
	}

	@Transient
	public String getDataAuxilioStr() {
		String retorno = "";
		if (this.dataAuxilio != null) {
			retorno = DateUtils.format(this.dataAuxilio, DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return retorno;
	}

	public void setDataAuxilio(Calendar dataAuxilio) {
		this.dataAuxilio = dataAuxilio;
	}

	@Column(name = "OBSERVACAO")
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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

	@OneToOne
	@JoinColumn(name = "AUXILIADO_ID")
	public Auxiliado getAuxiliado() {
		if (this.auxiliado == null) {
			this.auxiliado = new Auxiliado();
		}
		return this.auxiliado;
	}

	public void setAuxiliado(Auxiliado auxiliado) {
		this.auxiliado = auxiliado;
	}

	@Column(name = "EXCLUIDO", nullable = false)
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

}