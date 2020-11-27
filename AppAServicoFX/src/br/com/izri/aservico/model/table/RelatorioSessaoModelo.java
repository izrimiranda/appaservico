package br.com.izri.aservico.model.table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RelatorioSessaoModelo {

	public RelatorioSessaoModelo(int id, String dataSessao, String dataRegistro, String observacao, String nomeArquivo) {
		this.colunaRelatorioId = new SimpleIntegerProperty(id);
		this.colunaRelatorioDataSessao = new SimpleStringProperty(dataSessao);
		this.colunaRelatorioDataCadastro = new SimpleStringProperty(dataRegistro);
		this.colunaRelatorioObservacao = new SimpleStringProperty(observacao);
		this.colunaRelatorioNomeArquivo = new SimpleStringProperty(nomeArquivo);
	}

	private SimpleIntegerProperty colunaRelatorioId;
	private SimpleStringProperty colunaRelatorioDataSessao;
	private SimpleStringProperty colunaRelatorioDataCadastro;
	private SimpleStringProperty colunaRelatorioObservacao;
	private SimpleStringProperty colunaRelatorioNomeArquivo;

	public SimpleIntegerProperty colunaRelatorioIdProperty() {
		return this.colunaRelatorioId;
	}

	public void setColunaRelatorioId(int colunaRelatorioId) {
		this.colunaRelatorioId.set(colunaRelatorioId);
	}

	public int getColunaRelatorioId() {
		return this.colunaRelatorioId.get();
	}

	public SimpleStringProperty colunaRelatorioDataSessaoProperty() {
		return this.colunaRelatorioDataSessao;
	}

	public void setColunaRelatorioDataSessao(String colunaRelatorioDataSessao) {
		this.colunaRelatorioDataSessao.set(colunaRelatorioDataSessao);
	}

	public String getColunaRelatorioDataSessao() {
		return this.colunaRelatorioDataSessao.get();
	}

	public SimpleStringProperty colunaRelatorioDataCadastroProperty() {
		return this.colunaRelatorioDataCadastro;
	}

	public void setColunaRelatorioDataCadastro(String colunaRelatorioDataCadastro) {
		this.colunaRelatorioDataCadastro.set(colunaRelatorioDataCadastro);
	}

	public String getColunaRelatorioDataCadastro() {
		return this.colunaRelatorioDataCadastro.get();
	}

	public SimpleStringProperty colunaRelatorioObservacaoProperty() {
		return this.colunaRelatorioObservacao;
	}

	public void setColunaRelatorioObservacao(String colunaRelatorioObservacao) {
		this.colunaRelatorioObservacao.set(colunaRelatorioObservacao);
	}

	public String getColunaRelatorioObservacao() {
		return this.colunaRelatorioObservacao.get();
	}

	public SimpleStringProperty colunaRelatorioNomeArquivoProperty() {
		return this.colunaRelatorioNomeArquivo;
	}

	public void setColunaRelatorioNomeArquivo(String colunaRelatorioNomeArquivo) {
		this.colunaRelatorioNomeArquivo.set(colunaRelatorioNomeArquivo);
	}

	public String getColunaRelatorioNomeArquivo() {
		return this.colunaRelatorioNomeArquivo.get();
	}

}
