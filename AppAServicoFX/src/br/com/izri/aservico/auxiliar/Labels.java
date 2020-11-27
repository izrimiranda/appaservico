package br.com.izri.aservico.auxiliar;

public class Labels {

	private static Labels instance;

	private final PropertiesReader reader;

	public static String labelSistemaAServico = "Sistema de Gestão Eclesiástica: A Serviço - ";

	private Labels() {
		this.reader = new PropertiesReader("properties/labels");
	}

	public static Labels getInstance() {
		if (Labels.instance == null) {
			Labels.instance = new Labels();
		}

		return Labels.instance;
	}

	protected String getProperty(String key, Object... args) {
		return this.reader.getProperty(key, args);
	}

	public String getLabelUsuarios() {
		return this.getProperty("label.usuarios");
	}

	public String getTituloTelaLogin() {
		return Labels.labelSistemaAServico + this.getProperty("label.tela.login");
	}

	public String getCadastrarEntradaSaida() {
		return this.getProperty("label.cadastrar.entrada.saida");
	}

	public String getConsultarEntradaSaida() {
		return this.getProperty("label.consultar.entrada.saida");
	}

	public String getGerarRelatorio() {
		return this.getProperty("label.gerar.relatorios");
	}

	public String getRelatorios() {
		return this.getProperty("label.relatorios");
	}

	public String getSair() {
		return this.getProperty("label.sair");
	}

	public String getSistema() {
		return this.getProperty("label.sistema");
	}

	public String getSobre() {
		return this.getProperty("label.sobre");
	}

	public String getTituloTelaPrincipal(String nome) {
		return this.getProperty("label.titulo.tela.principal", nome);
	}

	public String getLabelAServico() {
		return this.getProperty("label.a.servico");
	}

}
