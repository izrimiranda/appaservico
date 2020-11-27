package br.com.izri.aservico.model.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.izri.aservico.utils.DateUtils;

@Entity
@Table(name = "tbl_relatorio_sessao")
@NamedQueries({ @NamedQuery(name = "RelatorioSessao.findAll", query = "SELECT rs FROM RelatorioSessao rs WHERE rs.excluido = :excluido"),
	@NamedQuery(name = "RelatorioSessao.findByIntervaloDataSessao", query = "SELECT rs FROM RelatorioSessao rs WHERE rs.dataSessao BETWEEN :dataInicio AND :dataFinal AND rs.excluido = :excluido"),
	@NamedQuery(name = "RelatorioSessao.findByIntervaloDataRegistro", query = "SELECT rs FROM RelatorioSessao rs WHERE rs.dataRegistro BETWEEN :dataInicio AND :dataFinal AND rs.excluido = :excluido"),
	@NamedQuery(name = "RelatorioRegistro.findByUsuario", query = "SELECT rs FROM RelatorioSessao rs WHERE rs.usuario = :usuario AND rs.excluido = :excluido"),
	@NamedQuery(name = "RelatorioSessao.findByObservacao", query = "SELECT rs FROM RelatorioSessao rs WHERE rs.observacao LIKE :observacao AND rs.excluido = :excluido"),
	@NamedQuery(name = "RelatorioSessao.findByDataSessaoObservacao", query = "SELECT rs FROM RelatorioSessao rs WHERE rs.dataSessao BETWEEN :dataInicio AND :dataFinal AND rs.observacao LIKE :observacao AND rs.excluido = :excluido"),
	@NamedQuery(name = "RelatorioSessao.exclusaoLogicaPorId", query = "UPDATE RelatorioSessao rs SET rs.excluido = 1, rs.dataExclusao = :dataExclusao WHERE rs.id = :id ") })
public class RelatorioSessao {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "RelatorioSessao.findAll";
	public static final String FIND_BY_INTERVALO_DATA_SESSAO = "RelatorioSessao.findByIntervaloDataSessao";
	public static final String FIND_BY_INTERVALO_DATA_REGISTRO = "RelatorioSessao.findByIntervaloDataRegistro";
	public static final String FIND_BY_USUARIO = "RelatorioSessao.findByUsuario";
	public static final String FIND_BY_OBSERVACAO = "RelatorioSessao.findByObservacao";
	public static final String FIND_BY_DATA_SESSAO_OBSERVACAO = "RelatorioSessao.findByDataSessaoObservacao";
	public static final String EXCLUSAO_LOGICA_POR_ID = "RelatorioSessao.exclusaoLogicaPorId";

	private int id;
	private Calendar dataSessao;
	private Calendar dataRegistro;
	private Usuario usuario;
	private byte[] relatorio;
	private boolean excluido;
	private String observacao;
	private String nomeArquivo;
	private String extensaoArquivo;
	private Calendar dataExclusao;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATA_SESSAO")
	public Calendar getDataSessao() {
		return this.dataSessao;
	}

	@Transient
	public String getDataSessaoStr() {
		return DateUtils.format(this.getDataSessao(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
	}

	public void setDataSessao(Calendar dataSessao) {
		this.dataSessao = dataSessao;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATA_REGISTRO")
	public Calendar getDataRegistro() {
		return this.dataRegistro;
	}

	@Transient
	public String getDataRegistroStr() {
		return DateUtils.format(this.getDataRegistro(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
	}

	public void setDataRegistro(Calendar dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	@OneToOne
	@JoinColumn(name = "USUARIO_ID")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Lob
	@Column(name = "RELATORIO")
	public byte[] getRelatorio() {
		return this.relatorio;
	}

	public void setRelatorio(byte[] relatorio) {
		this.relatorio = relatorio;
	}

	@Column(name = "EXCLUIDO", nullable = false)
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

	@Column(name = "NOME_ARQUIVO")
	public String getNomeArquivo() {
		return this.nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	@Column(name = "EXTENSAO_ARQUIVO")
	public String getExtensaoArquivo() {
		return this.extensaoArquivo;
	}

	public void setExtensaoArquivo(String extensaoArquivo) {
		this.extensaoArquivo = extensaoArquivo;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATA_EXCLUSAO")
	public Calendar getDataExclusao() {
		return this.dataExclusao;
	}

	@Transient
	public String getDataExclusaoStr() {
		return DateUtils.format(this.getDataExclusao(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
	}

	public void setDataExclusao(Calendar dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

}
