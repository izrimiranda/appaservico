package br.com.izri.aservico.model.table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DizimoModelo {

	public DizimoModelo(int id, String data, String valor, String observacao, String cadastradoPor, String nomeMembro) {
		this.colunaDizimoId = new SimpleIntegerProperty(id);
		this.colunaDizimoData = new SimpleStringProperty(data);
		this.colunaDizimoValor = new SimpleStringProperty(valor);
		this.colunaDizimoObservacao = new SimpleStringProperty(observacao);
		this.colunaDizimoCadastradoPor = new SimpleStringProperty(cadastradoPor);
		this.colunaDizimoNomeMembro = new SimpleStringProperty(nomeMembro);
	}

	private SimpleIntegerProperty colunaDizimoId;
	private SimpleStringProperty colunaDizimoData;
	private SimpleStringProperty colunaDizimoValor;
	private SimpleStringProperty colunaDizimoObservacao;
	private SimpleStringProperty colunaDizimoCadastradoPor;
	private SimpleStringProperty colunaDizimoNomeMembro;

	public SimpleIntegerProperty colunaDizimoIdProperty() {
		return this.colunaDizimoId;
	}

	public void setColunaDizimoId(int colunaDizimoId) {
		this.colunaDizimoId.set(colunaDizimoId);
	}

	public SimpleStringProperty colunaDizimoDataProperty() {
		return this.colunaDizimoData;
	}

	public void setColunaDizimoData(String colunaDizimoData) {
		this.colunaDizimoData.set(colunaDizimoData);
	}

	public SimpleStringProperty colunaDizimoValorProperty() {
		return this.colunaDizimoValor;
	}

	public void setColunaDizimoValor(String colunaDizimoValor) {
		this.colunaDizimoValor.set(colunaDizimoValor);
	}

	public SimpleStringProperty colunaDizimoObservacaoProperty() {
		return this.colunaDizimoObservacao;
	}

	public void setColunaDizimoObservacao(String colunaDizimoObservacao) {
		this.colunaDizimoObservacao.set(colunaDizimoObservacao);
	}

	public SimpleStringProperty colunaDizimoCadastradoPorProperty() {
		return this.colunaDizimoCadastradoPor;
	}

	public void setColunaDizimoCadastradoPor(String colunaDizimoCadastradoPor) {
		this.colunaDizimoCadastradoPor.set(colunaDizimoCadastradoPor);
	}

	public SimpleStringProperty colunaDizimoNomeMembroProperty() {
		return this.colunaDizimoNomeMembro;
	}

	public void setColunaDizimoNomeMembro(String colunaDizimoNomeMembro) {
		this.colunaDizimoNomeMembro.set(colunaDizimoNomeMembro);
	}

	public int getColunaDizimoId() {
		return this.colunaDizimoId.get();
	}

	public String getColunaDizimoData() {
		return this.colunaDizimoData.get();
	}

	public String getColunaDizimoValor() {
		return this.colunaDizimoValor.get();
	}

	public String getColunaDizimoObservacao() {
		return this.colunaDizimoObservacao.get();
	}

	public String getColunaDizimoCadastradoPor() {
		return this.colunaDizimoCadastradoPor.get();
	}

	public String getColunaDizimoNomeMembro() {
		return this.colunaDizimoNomeMembro.get();
	}
}
