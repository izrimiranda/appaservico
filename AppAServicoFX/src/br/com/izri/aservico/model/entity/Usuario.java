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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.izri.aservico.auxiliar.Verificadores;
import br.com.izri.aservico.utils.DateUtils;

/**
 *
 * @author Izrí Miranda
 *
 */
@Entity
@Table(name = "tbl_usuario")
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id AND u.excluido = :excluido"),
	@NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nomeCompleto LIKE :nomeCompleto AND u.excluido = :excluido"),
	@NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login LIKE :login AND u.excluido = :excluido"),
	@NamedQuery(name = "Usuario.findByDataCadastro", query = "SELECT u FROM Usuario u WHERE u.dataCadastro = :dataCadastro AND u.excluido = :excluido"),
	@NamedQuery(name = "Usuario.findByIntervaloDataCadastro", query = "SELECT u FROM Usuario u WHERE u.dataCadastro BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND u.excluido = :excluido"),
	@NamedQuery(name = "Usuario.findByLoginSenha", query = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha"),
	@NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email LIKE :email AND u.excluido = :excluido") })
public class Usuario implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5456442554433815661L;

	public static final String FIND_ALL = "Usuario.findAll";
	public static final String FIND_BY_ID = "Usuario.findById";
	public static final String FIND_BY_NOME = "Usuario.findByNome";
	public static final String FIND_BY_LOGIN = "Usuario.findByLogin";
	public static final String FIND_BY_DATA_CADASTRO = "Usuario.findByDataCadastro";
	public static final String FIND_BY_INTERVALO_DATA_CADASTRO = "Usuario.findByIntervaloDataCadastro";
	public static final String FIND_BY_LOGIN_SENHA = "Usuario.findByLoginSenha";
	public static final String FIND_BY_EMAIL = "Usuario.findByEmail";

	private int id;

	private String nomeCompleto;
	private String login;
	private String senha;
	private int tipoUsuario;
	private Calendar dataCadastro;
	private boolean excluido;
	private Celula celulaLiderada;
	private List<Celula> listaCelula;
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false, unique = true)
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

	@Column(name = "LOGIN", nullable = false, unique = true)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "SENHA", nullable = false)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "TIPO_USUARIO", nullable = false)
	public int getTipoUsuario() {
		return this.tipoUsuario;
	}

	/**
	 * Retorna a string referente ao "tipoUsuario".
	 *
	 * @return
	 */
	@Transient
	public String getTipoUsuarioStr() {
		String retorno = "";

		if (this.getTipoUsuario() == 1) {
			retorno = "Administrador Full";
		} else if (this.getTipoUsuario() == 2) {
			retorno = "Líder de Célula";
		} else if (this.getTipoUsuario() == 3) {
			retorno = "Coordenador de Célula";
		} else if (this.getTipoUsuario() == 4) {
			retorno = "Tesoureiro";
		} else if (this.getTipoUsuario() == 5) {
			retorno = "Secretário";
		} else if (this.getTipoUsuario() == 6) {
			retorno = "Administrador Básico";
		}

		return retorno;
	}

	/**
	 * 1 - Administrador Full / 2 - Líder de Célula / 3 - Coodenador de Célula /
	 * 4 - Tesoureiro / 5 - Secretário / 6 - Administrador Básico
	 *
	 * @param tipoUsuario
	 */
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Column(name = "DATA_CADASTRO")
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataCadastro() {
		return this.dataCadastro;
	}

	/**
	 * Retorna a data de cadastro (Variável 'dataCadastro') no formato string
	 * (dd/MM/aaaa).
	 *
	 * @return
	 */
	@Transient
	public String getDataCadastroStr() {
		String retorno = "";

		if (Verificadores.isDataNotNull(this.getDataCadastro())) {
			retorno = DateUtils.format(this.getDataCadastro(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return retorno;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Column(name = "EXCLUIDO", nullable = false)
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	@OneToOne(mappedBy = "lider", cascade = { CascadeType.MERGE })
	@JoinColumn(name = "CELULA_LIDER_ID")
	public Celula getCelulaLiderada() {
		return this.celulaLiderada;
	}

	public void setCelulaLiderada(Celula celulaLiderada) {
		this.celulaLiderada = celulaLiderada;
	}

	/**
	 * A lista só será preenchida caso do "tipoUsuario" for "Coodernador de
	 * Célula" (tipoUsuario = 3).
	 *
	 * @return
	 */
	@OneToMany(targetEntity = Celula.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "coordenador")
	public List<Celula> getListaCelula() {
		return this.listaCelula;
	}

	public void setListaCelula(List<Celula> listaCelula) {
		this.listaCelula = listaCelula;
	}

	@Column(name = "EMAIL", unique = true)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
