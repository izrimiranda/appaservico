package br.com.izri.aservico.model.entity;

import java.io.Serializable;

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
import javax.persistence.Transient;

@Entity
@Table(name = "TBL_AUXILIADO")
@NamedQueries({ @NamedQuery(name = "Auxiliado.findAll", query = "SELECT a FROM Auxiliado a WHERE a.excluido = :excluido"),
	@NamedQuery(name = "Auxiliado.findById", query = "SELECT a FROM Auxiliado a WHERE a.id = :id AND a.excluido = :excluido"),
	@NamedQuery(name = "Auxiliado.findByNome", query = "SELECT a FROM Auxiliado a WHERE a.nomeCompleto = :nomeCompleto AND a.excluido = :excluido"),
	@NamedQuery(name = "Auxiliado.findByNumeroDocumento", query = "SELECT a FROM Auxiliado a WHERE a.numeroDocumento = :numeroDocumeto AND a.excluido = :excluido") })
public class Auxiliado implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5946976741622076108L;

	public static final String FIND_ALL = "Auxiliado.findAll";
	public static final String FIND_BY_ID = "Auxiliado.findById";
	public static final String FIND_BY_NOME = "Auxiliado.FindByNome";
	public static final String FIND_BY_NUMERO_DOCUMENTO = "Auxiliado.findByNumeroDocumento";

	private int id;

	private String nomeCompleto;
	private String numeroDocumento;
	private int tipoDocumento;
	private Endereco endereco;
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

	@Column(name = "NOME_COMPLETO", nullable = false)
	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	@Column(name = "NUMERO_DOCUMENTO", nullable = false)
	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	@Column(name = "TIPO_DOCUMENTO", nullable = false)
	public int getTipoDocumento() {
		return this.tipoDocumento;
	}

	@Transient
	public String getTipoDocumentoStr() {
		String retorno = "";

		if (this.getTipoDocumento() == 1) {
			retorno = "CPF";
		} else if (this.getTipoDocumento() == 2) {
			retorno = "RG";
		} else if (this.getTipoDocumento() == 3) {
			retorno = "CTPS";
		} else if (this.getTipoDocumento() == 4) {
			retorno = "CNH";
		}

		return retorno;
	}

	/**
	 * 1 - CPF / 2 - RG / 3 - CTPS (Carteira de Trabalho e Previdência Social) /
	 * 4 - CNH (Carteira Nacional de Habilitação)
	 *
	 * @param tipoDocumento
	 */
	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@OneToOne
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

	@Column(name = "EXCLUIDO", nullable = false)
	public boolean isExcluido() {
		return this.excluido;
	}

	/**
	 *
	 * @param excluido
	 */
	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

}
