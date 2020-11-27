package br.com.izri.aservico.controller.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import br.com.izri.aservico.auxiliar.FacesMsg;
import br.com.izri.aservico.auxiliar.Message;
import br.com.izri.aservico.controller.bean.base.BaseBean;
import br.com.izri.aservico.model.entity.Email;
import br.com.izri.aservico.model.entity.Usuario;
import br.com.izri.aservico.utils.EmailUtils;
import br.com.izri.aservico.utils.StringUtils;

/**
 *
 * @author Izrí Miranda
 *
 */
@ManagedBean
public class RecuperarUsuarioSenhaBean extends BaseBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String email;
	private String login;

	private Email emailEntity;
	private EmailUtils emailUtils;
	private Usuario usuario;

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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Email getEmailEntity() {
		if (this.emailEntity == null) {
			this.emailEntity = new Email();
		}
		return this.emailEntity;
	}

	public void setEmailEntity(Email emailEntity) {
		this.emailEntity = emailEntity;
	}

	public EmailUtils getEmailUtils() {
		if (this.emailUtils == null) {
			this.emailUtils = new EmailUtils();
		}
		return this.emailUtils;
	}

	public void setEmailUtils(EmailUtils emailUtils) {
		this.emailUtils = emailUtils;
	}

	public boolean isEmailValido() {

		boolean valido = true;

		if (StringUtils.isEmpty(this.getEmail()) || (this.getEmail() == null)) {
			valido = false;
			FacesMsg.setMsgPreencherEmail();
		} else if (!this.getEmail().contains("@")) {
			valido = false;
			FacesMsg.setMsgEmailFormatoErro(this.getEmail());
		} else if (!this.getEmail().contains(".")) {
			valido = false;
			FacesMsg.setMsgEmailFormatoErro(this.getEmail());
		} else if (this.getEmail().contains(" ")) {
			valido = false;
			FacesMsg.setMsgEmailFormatoErro(this.getEmail());
		}

		return valido;
	}

	public boolean isEmailExistente() {
		boolean retorno = true;
		Usuario usuario = null;

		usuario = this.getUsuarioDAO().isEmailValido(this.getEmail());
		this.setUsuario(usuario);
		System.out.println();

		if ((usuario == null) || usuario.equals(null)) {
			FacesMsg.setMsgEmailInexistente(this.getEmail());
			retorno = false;
			this.setEmail("");
		}

		return retorno;
	}

	public String enviarEmailResetarSenha() throws Exception {

		if (this.isEmailValido() && this.isEmailExistente()) {

			this.getEmailEntity().setDestinatario(this.getEmail());
			this.getEmailEntity().setAssunto("A Serviço - Redefinição de senha de acesso. Usuário: " + this.getUsuario().getLogin());
			this.getEmailEntity().getMensagemBuilder().append("<b>Olá, " + this.getUsuario().getNomeCompleto() + ".</b>");
			this.getEmailEntity().getMensagemBuilder().append("<p>Foi solicitado em nosso sistema a redefinição da senha do usuario <b>" + this.getUsuario().getLogin() + "</b>.</p>");
			this.getEmailEntity().getMensagemBuilder().append("<p>Caso não tenha solicitado, entre em contato urgentemente com o administrador do sistema.</p>");
			this.getEmailEntity().getMensagemBuilder().append("<p>Clique no link abaixo para redefinir a senha.</p>");
			this.getEmailEntity().getMensagemBuilder()
			        .append("<a href=" + Message.getInstance().getLinkNovaSenha(this.getUsuario().getLogin(), this.getUsuario().getEmail()).toString() + ">Resetar senha" + "</a>");
			this.getEmailEntity().getMensagemBuilder().append("<br></br>");
			this.getEmailEntity().getMensagemBuilder().append("<br></br>");
			this.getEmailEntity().getMensagemBuilder().append("<br></br>");
			this.getEmailEntity().getMensagemBuilder().append("<img src=\"http://s2.postimg.org/y79sokuux/logo200_Preta.png\"></img>");
			this.getEmailEntity().getMensagemBuilder().append("<br></br>");
			this.getEmailEntity().getMensagemBuilder().append("Izrí Miranda - Analista de Sistemas");

			this.getEmailUtils().enviarEmail(this.getEmailEntity());

			FacesMsg.setMsgEmailEnviadoSucesso(this.getEmail());

			this.setEmail("");
		}

		return "@EmailEnviado";
	}

	public String enviarEmailSenhaResetada(String novaSenha) throws Exception {

		this.getEmailEntity().setDestinatario(this.getEmail());
		this.getEmailEntity().setAssunto("A Serviço - Senha resetada. Usuário: " + this.getUsuario().getLogin());
		this.getEmailEntity().getMensagemBuilder().append("<b>Olá, " + this.getUsuario().getNomeCompleto() + ".</b>");
		this.getEmailEntity().getMensagemBuilder().append("<p>O reset da senha foi feito com sucesso. </b>.</p>");
		this.getEmailEntity().getMensagemBuilder().append("<br></br>");
		this.getEmailEntity().getMensagemBuilder().append("Nova senha: <b>" + novaSenha + "</b>");
		this.getEmailEntity().getMensagemBuilder().append("<br></br>");
		this.getEmailEntity().getMensagemBuilder().append("<img src=\"http://s2.postimg.org/y79sokuux/logo200_Preta.png\"></img>");
		this.getEmailEntity().getMensagemBuilder().append("<br></br>");
		this.getEmailEntity().getMensagemBuilder().append("Izrí Miranda - Analista de Sistemas");

		this.getEmailUtils().enviarEmail(this.getEmailEntity());

		return "@EmailEnviado";
	}

}
