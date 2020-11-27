package br.com.izri.aservico.model.table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TransacaoSaidaModelo {

	public TransacaoSaidaModelo(int id, String valor, String data, String cadastradoPor, String observacao) {
		this.colunaTransacaoId = new SimpleIntegerProperty(id);
		this.colunaTransacaoValor = new SimpleStringProperty(valor);
		this.colunaTransacaoData = new SimpleStringProperty(data);
		this.colunaTransacaoCadastradoPor = new SimpleStringProperty(cadastradoPor);
		this.colunaTransacaoObservacao = new SimpleStringProperty(observacao);
	}

	private SimpleIntegerProperty colunaTransacaoId;
	private SimpleStringProperty colunaTransacaoValor;
	private SimpleStringProperty colunaTransacaoData;
	private SimpleStringProperty colunaTransacaoCadastradoPor;
	private SimpleStringProperty colunaTransacaoObservacao;

	public SimpleIntegerProperty colunaTransacaoIdProperty() {
		return this.colunaTransacaoId;
	}

	public void setcolunaTransacaoId(int colunaTransacaoId) {
		this.colunaTransacaoId.set(colunaTransacaoId);
	}

	public SimpleStringProperty colunaTransacaoValorProperty() {
		return this.colunaTransacaoValor;
	}

	public void setcolunaTransacaoValor(String colunaTransacaoValor) {
		this.colunaTransacaoValor.set(colunaTransacaoValor);
	}

	public SimpleStringProperty colunaTransacaoDataProperty() {
		return this.colunaTransacaoData;
	}

	public void setcolunaTransacaoData(String colunaTransacaoData) {
		this.colunaTransacaoData.set(colunaTransacaoData);
	}

	public SimpleStringProperty colunaTransacaoCadastradoPorProperty() {
		return this.colunaTransacaoCadastradoPor;
	}

	public void setcolunaTransacaoCadastradoPor(String colunaTransacaoCadastradoPor) {
		this.colunaTransacaoCadastradoPor.set(colunaTransacaoCadastradoPor);
	}

	public SimpleStringProperty colunaTransacaoObservacaoProperty() {
		return this.colunaTransacaoObservacao;
	}

	public void setColunaTransacaoObservacao(String colunaTransacaoObservacao) {
		this.colunaTransacaoObservacao.set(colunaTransacaoObservacao);
	}

	public int getColunaTransacaoId() {
		return this.colunaTransacaoId.get();
	}

	public String getColunaTransacaoValor() {
		return this.colunaTransacaoValor.get();
	}

	public String getColunaTransacaoData() {
		return this.colunaTransacaoData.get();
	}

	public String getColunaTransacaoCadastradoPor() {
		return this.colunaTransacaoCadastradoPor.get();
	}

	public String getColunaTransacaoObservacao() {
		return this.colunaTransacaoObservacao.get();
	}

}
