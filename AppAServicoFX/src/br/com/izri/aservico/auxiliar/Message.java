package br.com.izri.aservico.auxiliar;

public class Message {

	private final PropertiesReader reader;

	private static Message instance;

	private Message() {
		this.reader = new PropertiesReader("properties/message");
	}

	public static Message getInstance() {
		if (Message.instance == null) {
			Message.instance = new Message();
		}

		return Message.instance;
	}

	protected String getProperty(String key, Object... args) {
		return this.reader.getProperty(key, args);
	}

	public String getUsuarioErro() {
		return this.getProperty("msg.usuario.erro");
	}

	public String getSenhaErro() {
		return this.getProperty("msg.senha.erro");
	}

	public String getSenhaUsuarioErro() {
		return this.getProperty("msg.senha.usuario.erro");
	}

	public String getUsuarioPreencher() {
		return this.getProperty("msg.usuario.preencher");
	}

	public String getSenhaPreencher() {
		return this.getProperty("msg.senha.preencher");
	}

	public String getUsuarioSenhaPreencher() {
		return this.getProperty("msg.senha.usuario.preencher");
	}

	public String getEmailPreencher() {
		return this.getProperty("msg.email.preencher");
	}

	public String getEmailFormatoErro(String email) {
		return this.getProperty("msg.email.formato.erro", email);
	}

	public String getEmailNaoExiste(String email) {
		return this.getProperty("msg.email.nao.existe", email);
	}

	public String getEmailEnviadoSucesso(String email) {
		return this.getProperty("msg.email.enviar.sucesso", email);
	}

	public String getEmailInexistente(String email) {
		return this.getProperty("msg.email.inexistente", email);
	}

	public String getLinkNovaSenha(String login, String email) {
		return this.getProperty("msg.link.nova.senha", login, email);
	}

	public String getErroObterLogin(String email) {
		return this.getProperty("msg.login.erro", email);
	}

	public String getCamposBrancos() {
		return this.getProperty("msg.campos.brancos");
	}

	public String getSenhaNaoConferem() {
		return this.getProperty("msg.senha.nao.confere");
	}

	public String getSenhaAlteradaSucesso() {
		return this.getProperty("msg.senha.alterada.sucesso");
	}

}
