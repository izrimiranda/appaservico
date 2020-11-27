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
@Table(name = "tbl_oferta")
@NamedQueries({ @NamedQuery(name = "Oferta.findAll", query = "SELECT o FROM Oferta o WHERE o.excluido = :excluido"),
	@NamedQuery(name = "Oferta.findById", query = "SELECT o FROM Oferta o WHERE o.id = :id AND o.excluido = :excluido"),
	@NamedQuery(name = "Oferta.findByIntervaloValor", query = "SELECT o FROM Oferta o WHERE o.valor BETWEEN :valorUm AND :valorDois AND o.excluido = :excluido"),
	@NamedQuery(name = "Oferta.findByValor", query = "SELECT o FROM Oferta o WHERE o.valor = :valor AND o.excluido = :excluido"),
	@NamedQuery(name = "Oferta.findByIntervaloDataEntradaOferta", query = "SELECT o FROM Oferta o WHERE o.dataEntradaOferta BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND o.excluido = :excluido"),
	@NamedQuery(name = "Oferta.findByUsuarioLogin", query = "SELECT o FROM Oferta o WHERE o.usuario.login LIKE :login AND o.excluido = :excluido"),
	@NamedQuery(name = "Oferta.exclusaoLogicaPorId", query = "UPDATE Oferta o SET o.excluido = 1 WHERE o.id = :idExclusao") })
/**
 *
 * @author Izrí
 *
 */
public class Oferta implements Serializable {

	private static final long serialVersionUID = -2396362839647505748L;

	public static final String FIND_ALL = "Oferta.findAll";
	public static final String FIND_BY_ID = "Oferta.findById";
	public static final String FIND_BY_INTERVALO_VALOR = "Oferta.findByIntervaloValor";
	public static final String FIND_BY_VALOR = "Oferta.findByValor";
	public static final String FIND_BY_INTERVALO_DATA_ENTRADA_OFERTA = "Oferta.findByIntervaloDataEntradaOferta";
	public static final String FIND_BY_USUARIO_LOGIN = "Oferta.findByUsuarioLogin";
	public static final String EXCUSAO_LOGICA_POR_ID = "Oferta.exclusaoLogicaPorId";

	private int id;

	private Double valor;
	private Calendar dataEntradaOferta;
	private Usuario usuario;
	private String observacao;
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

	@Column(name = "VALOR", nullable = false)
	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Column(name = "DATA_ENTRADA_OFERTA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataEntradaOferta() {
		return this.dataEntradaOferta;
	}

	@Transient
	public String getDataEntradaOfertaStr() {
		String retorno = "";
		if (this.dataEntradaOferta != null) {
			retorno = DateUtils.format(this.dataEntradaOferta, DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}
		return retorno;
	}

	public void setDataEntradaOferta(Calendar dataEntradaOferta) {
		this.dataEntradaOferta = dataEntradaOferta;
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

	@Column(name = "EXClUIDO", nullable = false)
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

}
