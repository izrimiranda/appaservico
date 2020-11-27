package br.com.izri.aservico.controller.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import br.com.izri.Seguranca;
import br.com.izri.aservico.auxiliar.FacesMsg;
import br.com.izri.aservico.controller.bean.base.BaseBean;
import br.com.izri.aservico.model.entity.Usuario;
import br.com.izri.aservico.utils.StringUtils;

/**
 *
 * @author Izrí Miranda
 *
 */
@ManagedBean
@ViewScoped
public class RedefinirSenhaBean extends BaseBean {

	public RedefinirSenhaBean() {

	}

	private String login;
	private String email;
	private String senha;
	private String senhaConfirmada;
	private Usuario usuario;
	private boolean verificado;

	public String getLoginFromURL() {

		String login = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("login");

		this.setLogin(login);

		return login;
	}

	public String getEmailFromURL() {

		String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");

		this.setEmail(email);

		return email;
	}

	public String setLoginURL() {

		return "";
	}

	public String buscarUsuarioBanco() {
		System.out.println("########### buscando usuários no banco");

		if (StringUtils.isEmpty(this.getLogin())) {
			System.out.println("########### dentro do se");

			this.getLoginFromURL();

			if (StringUtils.isEmpty(this.getLogin())) {
				FacesMsg.setMsgLoginErro(this.email);
			} else {
				System.out.println("########## buscando usuario no banco");

				Usuario usuarioBanco = this.getUsuarioDAO().getUsuarioPorLogin(this.getLoginFromURL());

				this.setUsuario(usuarioBanco);
			}
		}

		return "";
	}

	/**
	 *
	 * @return
	 * @throws EmailException
	 */
	public String enviarDados() {

		if (this.isSenhasConferem() && this.isNotCamposBrancos()) {

			this.getUsuario().setSenha(Seguranca.convertStringToMd5(this.getSenhaConfirmada()));

			this.salvarUsuario();

			try {
				new RecuperarUsuarioSenhaBean().enviarEmailSenhaResetada(this.getSenha());

			} catch (Exception ex) {

			}

			FacesMsg.setMsgSenhaAlteradaSucesso();
		}

		return "";
	}

	public boolean isSenhasConferem() {

		boolean retorno = true;

		if (!this.getSenha().equals(this.getSenhaConfirmada())) {

			FacesMsg.setMsgSenhasNaoConferem();

			retorno = false;
		}

		return retorno;
	}

	public boolean isNotCamposBrancos() {

		boolean retorno = true;

		if (this.getSenha().isEmpty() || this.getSenhaConfirmada().isEmpty()) {

			FacesMsg.setMsgCamposBrancos();

			retorno = false;
		}

		return retorno;
	}

	public void salvarUsuario() {
		System.out.println("########### Usuario ID: " + this.getUsuario().getId());
		this.getUsuarioDAO().persistOrUpdate(this.getUsuario());
	}

	public Usuario getUsuario() {

		if (this.usuario == null) {
			this.usuario = new Usuario();
		}

		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaConfirmada() {
		return this.senhaConfirmada;
	}

	public void setSenhaConfirmada(String senhaConfirmada) {
		this.senhaConfirmada = senhaConfirmada;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getVerificado() {
		return this.verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}
}
