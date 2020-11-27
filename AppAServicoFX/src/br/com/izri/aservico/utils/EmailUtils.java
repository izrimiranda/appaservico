package br.com.izri.aservico.utils;

import org.apache.commons.mail.HtmlEmail;

import br.com.izri.aservico.controller.bean.RedefinirSenhaBean;
import br.com.izri.aservico.model.entity.Email;

/**
 *
 * @author Izrí Miranda
 *
 */
public class EmailUtils {

	private final static String userAuthentication = "aservicoadm@gmail.com";
	private final static String passwordUserAuthentication = "ibm11235813@23i";
	private final static String sender = "aservicoadm@gmail.com";
	private final static String smtp = "smtp.gmail.com";
	private final static int portaSmtp = 587;
	private final static boolean authentication = true;

	public RedefinirSenhaBean redefinirSenhaBean;

	public RedefinirSenhaBean getRedefinirSenhaBean() {
		if (this.redefinirSenhaBean == null) {
			this.redefinirSenhaBean = new RedefinirSenhaBean();
		}

		return this.redefinirSenhaBean;
	}

	public String enviarEmail(Email email) throws Exception {

		HtmlEmail htmlEmail = new HtmlEmail();

		htmlEmail.setHostName(EmailUtils.smtp);
		htmlEmail.setAuthentication(EmailUtils.userAuthentication, EmailUtils.passwordUserAuthentication);
		htmlEmail.setSSL(EmailUtils.authentication);
		htmlEmail.setTLS(EmailUtils.authentication);
		htmlEmail.addTo(this.getRedefinirSenhaBean().getEmailFromURL());
		htmlEmail.setFrom(EmailUtils.sender);
		htmlEmail.setSmtpPort(EmailUtils.portaSmtp);
		htmlEmail.setSubject(email.getAssunto());
		htmlEmail.setHtmlMsg(email.getMensagemBuilder().toString());

		htmlEmail.send();

		return "@emailEnviado";
	}

}
