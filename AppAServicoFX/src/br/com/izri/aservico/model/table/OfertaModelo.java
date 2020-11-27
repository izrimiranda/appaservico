package br.com.izri.aservico.model.table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OfertaModelo {

	public OfertaModelo(int id, String valor, String data, String observacao, String cadastradoPor) {
		this.colunaOfertaId = new SimpleIntegerProperty(id);
		this.colunaOfertaValor = new SimpleStringProperty(valor);
		this.colunaOfertaData = new SimpleStringProperty(data);
		this.colunaOfertaObservacao = new SimpleStringProperty(observacao);
		this.colunaOfertaCadastradoPor = new SimpleStringProperty(cadastradoPor);
	}

	private SimpleIntegerProperty colunaOfertaId;
	private SimpleStringProperty colunaOfertaValor;
	private SimpleStringProperty colunaOfertaData;
	private SimpleStringProperty colunaOfertaObservacao;
	private SimpleStringProperty colunaOfertaCadastradoPor;

	public SimpleIntegerProperty colunaOfertaIdProperty() {
		return this.colunaOfertaId;
	}

	public void setcolunaOfertaId(SimpleIntegerProperty colunaOfertaId) {
		this.colunaOfertaId = colunaOfertaId;
	}

	public SimpleStringProperty colunaOfertaDataProperty() {
		return this.colunaOfertaData;
	}

	public void setcolunaOfertaData(SimpleStringProperty colunaOfertaData) {
		this.colunaOfertaData = colunaOfertaData;
	}

	public SimpleStringProperty colunaOfertaObservacaoProperty() {
		return this.colunaOfertaObservacao;
	}

	public void setcolunaOfertaObservacao(SimpleStringProperty colunaOfertaObservacao) {
		this.colunaOfertaObservacao = colunaOfertaObservacao;
	}

	public SimpleStringProperty colunaOfertaCadastradoPorProperty() {
		return this.colunaOfertaCadastradoPor;
	}

	public void setcolunaOfertaCadastradoPor(SimpleStringProperty colunaOfertaCadastradoPor) {
		this.colunaOfertaCadastradoPor = colunaOfertaCadastradoPor;
	}

	public SimpleStringProperty colunaOfertaValorProperty() {
		return this.colunaOfertaValor;
	}

	public void setcolunaOfertaValor(SimpleStringProperty colunaOfertaValor) {
		this.colunaOfertaValor = colunaOfertaValor;
	}

	public int getColunaOfertaId() {
		return this.colunaOfertaId.get();
	}

	public String getcolunaOfertaValor() {
		return this.colunaOfertaValor.get();
	}

	public String getcolunaOfertaData() {
		return this.colunaOfertaData.get();
	}

	public String getcolunaOfertaObservacao() {
		return this.colunaOfertaObservacao.get();
	}

	public String getcolunaOfertaCadastradoPor() {
		return this.colunaOfertaCadastradoPor.get();
	}
}
