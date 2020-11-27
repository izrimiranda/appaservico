package br.com.izri.aservico.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_endereco")
@NamedQueries({ @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e WHERE e.excluido = :excluido"),
	@NamedQuery(name = "Endereco.findById", query = "SELECT e FROM Endereco e WHERE e.id = :id AND e.excluido = :excluido"),
	@NamedQuery(name = "Endereco.findByLogradouro", query = "SELECT e FROM Endereco e WHERE e.logradouro LIKE :logradouro AND e.excluido = :excluido"),
	@NamedQuery(name = "Endereco.findByBairro", query = "SELECT e FROM Endereco e WHERE e.bairro LIKE :bairro AND e.excluido = :excluido"),
	@NamedQuery(name = "Endereco.findByCidade", query = "SELECT e FROM Endereco e WHERE e.cidade LIKE :cidade AND e.excluido = :excluido"),
	@NamedQuery(name = "Endereco.findByCEP", query = "SELECT e FROM Endereco e WHERE e.CEP = :cep AND e.excluido = :excluido"),
	@NamedQuery(name = "Endereco.findByUF", query = "SELECT e FROM Endereco e WHERE e.uf = :uf AND e.excluido = :excluido") })
/**
 *
 * @author Izrí Miranda
 *
 */
public class Endereco implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3471335267675200453L;

	public static final String FIND_ALL = "Endereco.findAll";
	public static final String FIND_BY_ID = "Endereco.findById";
	public static final String FIND_BY_LOGRADOURO = "Endereco.findByLogradouro";
	public static final String FIND_BY_BAIRRO = "Endereco.findByBairro";
	public static final String FIND_BY_CIDADE = "Endereco.findByCidade";
	public static final String FIND_BY_UF = "Endereco.findByUF";
	public static final String FIND_BY_CEP = "Endereco.findByCEP";

	private int id;

	private String logradouro;
	private int numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private boolean excluido;
	private String cep;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "LOGRADOURO")
	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name = "NUMERO")
	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Column(name = "COMPLEMENTO")
	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Column(name = "BAIRRO")
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "CIDADE")
	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "UF")
	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Column(name = "EXCLUIDO", nullable = false)
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	@Column(name = "CEP")
	public String getCEP() {
		return this.cep;
	}

	public void setCEP(String cep) {
		this.cep = cep;
	}

}
