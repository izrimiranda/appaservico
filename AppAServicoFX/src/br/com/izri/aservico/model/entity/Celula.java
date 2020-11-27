package br.com.izri.aservico.model.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_celula")
@NamedQueries({ @NamedQuery(name = "Celula.findAll", query = "SELECT c FROM Celula c WHERE c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findById", query = "SELECT c FROM Celula c WHERE c.id = :id AND c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByNomeLider", query = "SELECT c FROM Celula c WHERE c.lider.nomeCompleto LIKE :nomeLider AND c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByLoginLider", query = "SELECT c FROM Celula c WHERE c.lider.login LIKE :loginLider AND c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByNomeAuxiliarUm", query = "SELECT c FROM Celula c WHERE c.nomeAuxiliarUm LIKE :nomeAuxiliarUm AND c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByNomeAuxiliarDois", query = "SELECT c FROM Celula c WHERE c.nomeAuxiliarDois LIKE :nomeAuxiliarDois AND c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByNomeCoordenador", query = "SELECT c FROM Celula c WHERE c.coordenador.nomeCompleto LIKE :nomeCoordenador AND c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByLoginCoordenador", query = "SELECT c FROM Celula c WHERE c.coordenador.login LIKE :loginCoordenador AND c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByExcluido", query = "SELECT c FROM Celula c WHERE c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByNomeCelula", query = "SELECT c FROM Celula c WHERE c.nomeCelula LIKE :nomeCelula AND c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByDiaDaSemanaReuniao", query = "SELECT c FROM Celula c WHERE c.diaDaSemanaReuniao = :diaDaSemanaReuniao AND c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByHorarioReuniaoApartirDe", query = "SELECT c FROM Celula c WHERE c.horarioReuniao >= :horarioReuniao AND c.excluido = :excluido"),
	@NamedQuery(name = "Celula.findByIntervaloDataNascimento", query = "SELECT c FROM Celula c WHERE c.dataNascimento BETWEEN :intervaloDataUm AND :intervaloDataDois AND c.excluido = :excluido") })
public class Celula implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Celula.findAll";
	public static final String FIND_BY_ID = "Celula.findById";
	public static final String FIND_BY_NOME_LIDER = "Celula.findByNomeLider";
	public static final String FIND_BY_LOGIN_LIDER = "Celula.findByLoginLider";
	public static final String FIND_BY_NOME_AUXILIAR_UM = "Celula.findByNomeAuxiliarUm";
	public static final String FIND_BY_NOME_AUXILIAR_DOIS = "Celula.findByNomeAuxilarDois";
	public static final String FIND_BY_NOME_COORDENADOR = "Celula.findByNomeCoordenador";
	public static final String FIND_BY_LOGIN_COORDENADOR = "Celula.findByLoginCoordenador";
	public static final String FIND_BY_EXCLUIDO = "Celula.findByExcluido";
	public static final String FIND_BY_NOME_CELULA = "Celula.findByNomeCelula";
	public static final String FIND_BY_DIA_DA_SEMANA_REUNIAO = "Celula.FindByDiaDaSemanaReuniao";
	public static final String FIND_BY_HORARIO_REUNIAO_APARTIR_DE = "Celula.findByHorarioReuniaoApartirDe";
	public static final String FIND_BY_INTERVALO_DATA_NASCIMENTO = "Celula.IntervaloDataNascimento";

	private int id;

	private List<Membro> listaMembro;
	private Usuario lider;
	private String nomeAuxiliarUm;
	private String nomeAuxiliarDois;
	private Usuario coordenador;
	private boolean excluido;
	private String nomeCelula;
	private String diaDaSemanaReuniao;
	private String horarioReuniao;
	private Calendar dataNascimento;
	private Calendar dataMorte;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(targetEntity = Membro.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "celula")
	public List<Membro> getListaMembro() {
		return this.listaMembro;
	}

	public void setListaMembro(List<Membro> listaMembro) {
		this.listaMembro = listaMembro;
	}

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "USUARIO_LIDER_ID")
	public Usuario getLider() {
		return this.lider;
	}

	public void setLider(Usuario lider) {
		this.lider = lider;
	}

	@Column(name = "NOME_AUXILIAR_UM")
	public String getNomeAuxiliarUm() {
		return this.nomeAuxiliarUm;
	}

	public void setNomeAuxiliarUm(String nomeAuxiliarUm) {
		this.nomeAuxiliarUm = nomeAuxiliarUm;
	}

	@Column(name = "NOME_AUXILIAR_DOIS")
	public String getNomeAuxiliarDois() {
		return this.nomeAuxiliarDois;
	}

	public void setNomeAuxiliarDois(String nomeAuxiliarDois) {
		this.nomeAuxiliarDois = nomeAuxiliarDois;
	}

	@ManyToOne
	@JoinColumn(name = "USUARIO_COORDENADOR_ID")
	public Usuario getCoordenador() {
		return this.coordenador;
	}

	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
	}

	@Column(name = "EXCLUIDO")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	@Column(name = "NOME_CELULA")
	public String getNomeCelula() {
		return this.nomeCelula;
	}

	public void setNomeCelula(String nomeCelula) {
		this.nomeCelula = nomeCelula;
	}

	@Column(name = "DIA_DA_SEMANA_REUNIAO")
	public String getDiaDaSemanaReuniao() {
		return this.diaDaSemanaReuniao;
	}

	public void setDiaDaSemanaReuniao(String diaDaSemanaReuniao) {
		this.diaDaSemanaReuniao = diaDaSemanaReuniao;
	}

	@Column(name = "HORARIO_REUNIAO")
	public String getHorarioReuniao() {
		return this.horarioReuniao;
	}

	public void setHorarioReuniao(String horarioReuniao) {
		this.horarioReuniao = horarioReuniao;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATA_NASCIMENTO")
	public Calendar getDataNascimento() {
		return this.dataNascimento;
	}

	/**
	 * Retorna a data (String) no formato 'dd/MM/aaaa'.
	 *
	 * @return
	 */
	@Transient
	public String getDataNascimentoStr() {
		return DateUtils.format(this.getDataNascimento(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATA_MORTE")
	public Calendar getDataMorte() {
		return this.dataMorte;
	}

	/**
	 * Retorna a data (String) no formato 'dd/MM/aaaa'.
	 *
	 * @return
	 */
	@Transient
	public String getDataMorteStr() {
		return DateUtils.format(this.getDataMorte(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
	}

	public void setDataMorte(Calendar dataMorte) {
		this.dataMorte = dataMorte;
	}
}
