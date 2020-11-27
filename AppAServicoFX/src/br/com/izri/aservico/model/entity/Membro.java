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
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_membro")
@NamedQueries({
	@NamedQuery(name = "Membro.findAll", query = "SELECT m FROM Membro m WHERE m.excluido = :excluido"),
	@NamedQuery(name = "Membro.findById", query = "SELECT m FROM Membro m WHERE m.id = :id AND m.excluido = :excluido"),
	@NamedQuery(name = "Membro.findByNome", query = "SELECT m FROM Membro m WHERE m.nomeCompleto LIKE :nomeCompleto AND m.excluido = :excluido"),
	@NamedQuery(name = "Membro.findByDataBatismo", query = "SELECT m FROM Membro m WHERE m.dataBatismo = :dataBatismo AND m.excluido = :excluido"),
	@NamedQuery(name = "Membro.findByIntervaloDataBatismo", query = "SELECT m FROM Membro m WHERE m.dataBatismo BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND m.excluido = :excluido"),
	@NamedQuery(name = "Membro.findByDataRegistroMembro", query = "SELECT m FROM Membro m WHERE m.dataRegistroMembro BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND m.excluido = :excluido"),
	@NamedQuery(name = "Membro.findByIntervaloDataRegistroMembro", query = "SELECT m FROM Membro m WHERE m.dataRegistroMembro BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND m.excluido = :excluido"),
	@NamedQuery(name = "Membro.findByIntervaloDataAniversario", query = "SELECT m FROM Membro m WHERE m.dataAniversario BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND m.excluido = :excluido"),
	@NamedQuery(name = "Membro.findByDataAniversario", query = "SELECT m FROM Membro m WHERE m.dataAniversario = :dataAniversario AND m.excluido = :excluido"),
	@NamedQuery(name = "Membro.findByCargoEclesiastico", query = "SELECT m FROM Membro m WHERE m.cargoEclesiastico LIKE :cargoEclesiastico AND m.excluido = :excluido"),
	@NamedQuery(name = "Membro.findByEmail", query = "SELECT m FROM Membro m WHERE m.email LIKE :email"),
	@NamedQuery(name = "Membro.endereco.findByBairro", query = "SELECT m FROM Membro m WHERE m.endereco.bairro LIKE :bairro AND m.endereco.excluido = :excluido"),
	@NamedQuery(name = "Membro.celula.nomeCelula", query = "SELECT m FROM Membro m WHERE m.celula.nomeCelula LIKE :nomeCelula"),
	@NamedQuery(name = "Membro.exclusaoLogicaPorId", query = "UPDATE Membro m SET m.excluido = 1, m.nomeUsuarioDelecao = :nomeUsuarioDelecao, m.dataDelecao = :dataDelecao WHERE m.id = :idMembro") })
public class Membro implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6906841982420540628L;

	public static final String FIND_ALL = "Membro.findAll";
	public static final String FIND_BY_ID = "Membro.findById";
	public static final String FIND_BY_NOME = "Membro.findByNome";
	public static final String FIND_BY_DATA_BATISMO = "Membro.findByDataBatismo";
	public static final String FIND_BY_INTERVALO_DATA_BATISMO = "Membro.findByIntervaloDataBatismo";
	public static final String FIND_BY_DATA_REGISTRO_MEMBRO = "Membro.findByDataRegistroMembro";
	public static final String FIND_BY_INTERVALO_DATA_REGISTRO_MEMBRO = "Membro.findByIntervaloDataRegistro";
	public static final String FIND_BY_INTERVALO_DATA_ANIVERSARIO = "Membro.findByIntervaloDataAniversario";
	public static final String FIND_BY_DATA_ANIVERSARIO = "Membro.findByDataAniversario";
	public static final String FIND_BY_CARGO_ECLESIASTICO = "Membro.findByCargoEclesiastico";
	public static final String FIND_BY_EMAIL = "Membro.findByEmail";
	public static final String FIND_BY_ENDERECO_BAIRRO = "Membro.endereco.findByBairro";
	public static final String FIND_BY_CELULA_NOME_CELULA = "Membro.celula.findByNomeCelula";
	public static final String EXCLUSAO_LOGICA_POR_ID = "Membro.exclusaoLogicaPorId";

	private int id;

	private String nomeCompleto;
	private Calendar dataBatismo;
	private Calendar dataRegistroMembro;
	private Calendar dataAniversario;
	private String cargoEclesiastico;
	private Endereco endereco;
	private String email;
	private String numeroCelularUm;
	private String numeroCelularDois;
	private int celularWhatsApp;
	private String numeroFixoResidencial;
	private String numeroFixoTrabalho;
	private TurmaEscolaBiblica turmaEscolaBiblica;
	private Celula celula;
	private boolean excluido;
	private String nomeUsuarioDelecao;
	private Calendar dataDelecao;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NOME_COMPLETO", nullable = false)
	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	@Column(name = "DATA_BATISMO")
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataBatismo() {
		return this.dataBatismo;
	}

	@Transient
	public String getDataBatismoStr() {
		String retorno = "";

		if (this.getDataBatismo() != null) {
			retorno = DateUtils.format(this.getDataBatismo(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return retorno;
	}

	public void setDataBatismo(Calendar dataBatismo) {
		this.dataBatismo = dataBatismo;
	}

	@Column(name = "DATA_REGISTRO_MEMBRO")
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataRegistroMembro() {
		return this.dataRegistroMembro;
	}

	@Transient
	public String getDataRegistroMembroStr() {
		String retorno = "";

		if (this.dataRegistroMembro != null) {
			retorno = DateUtils.format(this.dataRegistroMembro, DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}
		return retorno;
	}

	public void setDataRegistroMembro(Calendar dataRegistroMembro) {
		this.dataRegistroMembro = dataRegistroMembro;
	}

	@Column(name = "DATA_ANIVERSARIO")
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataAniversario() {
		return this.dataAniversario;
	}

	@Transient
	public String getDataAniversarioStr() {
		String retorno = "";

		System.out.println(this.getDataAniversario());

		if (this.dataAniversario != null) {
			retorno = DateUtils.format(this.dataAniversario, DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}
		return retorno;
	}

	public void setDataAniversario(Calendar dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	/**
	 * Caso não tenha sido batizado, selecionar a opção "NÃO É MEMBRO".
	 *
	 * Valores possíveis: Membro, Não membro, Presidente, Vice-Presidente, 1º
	 * Tesoureiro(a), 2º Tesoureiro, 1º Secretário(a), 2º Secretário(a),
	 * Coordenador de Célula, Auxiliar de Célula.
	 *
	 * @return
	 */
	@Column(name = "CARGO_ECLESIASTICO", nullable = false)
	public String getCargoEclesiastico() {
		return this.cargoEclesiastico;
	}

	public void setCargoEclesiastico(String cargoEclesiastico) {
		this.cargoEclesiastico = cargoEclesiastico;
	}

	@OneToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "ENDERECO_ID")
	public Endereco getEndereco() {
		if (this.endereco == null) {
			this.endereco = new Endereco();
		}
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "NUMERO_CELULAR_UM")
	public String getNumeroCelularUm() {
		return this.numeroCelularUm;
	}

	public void setNumeroCelularUm(String numeroCelularUm) {
		this.numeroCelularUm = numeroCelularUm;
	}

	@Column(name = "NUMERO_CELULAR_DOIS")
	public String getNumeroCelularDois() {
		return this.numeroCelularDois;
	}

	public void setNumeroCelularDois(String numeroCelularDois) {
		this.numeroCelularDois = numeroCelularDois;
	}

	/**
	 * 1 - Se o "Celular um" for o whatsapp // 2 - Se o "Celular dois" for o
	 * whatsapp
	 *
	 * @return
	 */
	@Column(name = "CELULAR_WHATSAPP")
	public int getCelularWhatsApp() {
		return this.celularWhatsApp;
	}

	public void setCelularWhatsApp(int celularWhatsApp) {
		this.celularWhatsApp = celularWhatsApp;
	}

	@Column(name = "NUMERO_FIXO_RESIDENCIAL")
	public String getNumeroFixoResidencial() {
		return this.numeroFixoResidencial;
	}

	public void setNumeroFixoResidencial(String numeroFixoResidencial) {
		this.numeroFixoResidencial = numeroFixoResidencial;
	}

	@Column(name = "NUMERO_FIXO_TRABALHO")
	public String getNumeroFixoTrabalho() {
		return this.numeroFixoTrabalho;
	}

	public void setNumeroFixoTrabalho(String numeroFixoTrabalho) {
		this.numeroFixoTrabalho = numeroFixoTrabalho;
	}

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "TURMA_ESCOLA_BIBLICA_ID")
	public TurmaEscolaBiblica getTurmaEscolaBiblica() {
		if (this.turmaEscolaBiblica == null) {
			this.turmaEscolaBiblica = new TurmaEscolaBiblica();
		}
		return this.turmaEscolaBiblica;
	}

	public void setTurmaEscolaBiblica(TurmaEscolaBiblica turmaEscolaBiblica) {
		this.turmaEscolaBiblica = turmaEscolaBiblica;
	}

	@Column(name = "EXCLUIDO", nullable = false)
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "CELULA_ID")
	public Celula getCelula() {
		if (this.celula == null) {
			this.celula = new Celula();
		}
		return this.celula;
	}

	public void setCelula(Celula celula) {
		this.celula = celula;
	}

	@Column(name = "NOME_USUARIO_DELECAO")
	public String getNomeUsuarioDelecao() {
		return this.nomeUsuarioDelecao;
	}

	public void setNomeUsuarioDelecao(String nomeUsuarioDelecao) {
		this.nomeUsuarioDelecao = nomeUsuarioDelecao;
	}

	@Column(name = "DATA_DELECAO")
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataDelecao() {
		return this.dataDelecao;
	}

	public void setDataDelecao(Calendar dataDelecao) {
		this.dataDelecao = dataDelecao;
	}

	@Transient
	public String getDataDelecaoStr() {
		String retorno = "";

		if (this.getDataDelecao() != null) {
			retorno = DateUtils.format(this.getDataDelecao(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return retorno;
	}

}
