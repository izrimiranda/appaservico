package br.com.izri.aservico.auxiliar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMsg {

	public static void setMsgSenhaErro() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getSenhaErro(), ""));
	}

	public static void setMsgSenhaUsuarioErro() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getSenhaUsuarioErro(), ""));
	}

	public static void setMsgUsuarioErro() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getUsuarioErro(), ""));
	}

	public static void setMsgUsuarioPreencher() {
		FacesContext.getCurrentInstance().addMessage("formLogin:idCampoUsuario", new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getSenhaPreencher(), ""));
	}

	public static void setMsgPreencherEmail() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getEmailPreencher(), ""));
	}

	public static void setMsgEmailFormatoErro(String email) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getEmailFormatoErro(email), ""));
	}

	public static void setMsgEmailNaoExiste(String email) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getEmailNaoExiste(email), ""));
	}

	public static void setMsgEmailEnviadoSucesso(String email) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Message.getInstance().getEmailEnviadoSucesso(email), ""));
	}

	public static void setMsgEmailInexistente(String email) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getEmailInexistente(email), ""));
	}

	public static void setMsgLoginErro(String email) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getErroObterLogin(email), ""));
	}

	public static void setMsgCamposBrancos() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getCamposBrancos(), ""));
	}

	public static void setMsgSenhasNaoConferem() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message.getInstance().getSenhaNaoConferem(), ""));
	}

	public static void setMsgSenhaAlteradaSucesso() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Message.getInstance().getSenhaAlteradaSucesso(), ""));
	}
}
