package br.com.izri.aservico.model.table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MembroModelo {

	public MembroModelo(int id, String nome, String cargoEclesiastico, String celula, String turma, String dataAniversario, String dataBatismo, String celularUm, String celularDois, String rua, Integer numero, String bairro) {
		this.colunaId = new SimpleIntegerProperty(id);
		this.colunaNome = new SimpleStringProperty(nome);
		this.colunaCargoEclesiastico = new SimpleStringProperty(cargoEclesiastico);
		this.colunaCelula = new SimpleStringProperty(celula);
		this.colunaTurma = new SimpleStringProperty(turma);
		this.colunaDataAniversario = new SimpleStringProperty(dataAniversario);
		this.colunaDataBatismo = new SimpleStringProperty(dataBatismo);
		this.colunaCelularUm = new SimpleStringProperty(celularUm);
		this.colunaCelularDois = new SimpleStringProperty(celularDois);
		this.colunaRua = new SimpleStringProperty(rua);
		this.colunaNumero = new SimpleIntegerProperty(numero);
		this.colunaBairro = new SimpleStringProperty(bairro);
	}

	private SimpleIntegerProperty colunaId;
	private SimpleStringProperty colunaNome;
	private SimpleStringProperty colunaCargoEclesiastico;
	private SimpleStringProperty colunaCelula;
	private SimpleStringProperty colunaTurma;
	private SimpleStringProperty colunaDataAniversario;
	private SimpleStringProperty colunaDataBatismo;
	private SimpleStringProperty colunaCelularUm;
	private SimpleStringProperty colunaCelularDois;
	private SimpleStringProperty colunaRua;
	private SimpleIntegerProperty colunaNumero;
	private SimpleStringProperty colunaBairro;

	public SimpleIntegerProperty colunaIdProperty() {
		return this.colunaId;
	}

	public SimpleStringProperty colunaNomeProperty() {
		return this.colunaNome;
	}

	public SimpleStringProperty colunaCargoEclesiasticoProperty() {
		return this.colunaCargoEclesiastico;
	}

	public SimpleStringProperty colunaCelulaProperty() {
		return this.colunaCelula;
	}

	public SimpleStringProperty colunaTurmaProperty() {
		return this.colunaTurma;
	}

	public SimpleStringProperty colunaDataAniversarioProperty() {
		return this.colunaDataAniversario;
	}

	public SimpleStringProperty colunaDataBatismoProperty() {
		return this.colunaDataBatismo;
	}

	public SimpleStringProperty colunaCelularUmProperty() {
		return this.colunaCelularUm;
	}

	public SimpleStringProperty colunaCelularDoisProperty() {
		return this.colunaCelularDois;
	}

	public SimpleStringProperty colunaRuaProperty() {
		return this.colunaRua;
	}

	public SimpleIntegerProperty colunaNumeroProperty() {
		return this.colunaNumero;
	}

	public SimpleStringProperty colunaBairroProperty() {
		return this.colunaBairro;
	}

	public int getColunaId() {
		return this.colunaId.get();
	}

	public String getColunaNome() {
		return this.colunaNome.get();
	}

	public String getColunaCargoEclesiastico() {
		return this.colunaCargoEclesiastico.get();
	}

	public String getColunaCelula() {
		return this.colunaCelula.get();
	}

	public String getColunaTurma() {
		return this.colunaTurma.get();
	}

	public String getColunaDataAniversario() {
		return this.colunaDataAniversario.get();
	}

	public String getColunaDataBatismo() {
		return this.colunaDataBatismo.get();
	}

	public String getColunaCelularUm() {
		return this.colunaCelularUm.get();
	}

	public String getColunaCelularDois() {
		return this.colunaCelularDois.get();
	}

	public String getColunaRua() {
		return this.colunaRua.get();
	}

	public Integer getColunaNumero() {
		return this.colunaNumero.get();
	}

	public String getColunaBairro() {
		return this.colunaBairro.get();
	}

	public void setColunaId(int id) {
		this.colunaId.set(id);
	}

	public void setColunaNome(String colunaNome) {
		this.colunaNome.set(colunaNome);
	}

	public void setColunaCargoEclesiastico(String colunaCargoEclesiastico) {
		this.colunaCargoEclesiastico.set(colunaCargoEclesiastico);
	}

	public void setColunaCelula(String colunaCelula) {
		this.colunaCelula.set(colunaCelula);
	}

	public void setColunaTurma(String colunaTurma) {
		this.colunaTurma.set(colunaTurma);
	}

	public void setColunaDataAniversario(String colunaDataAniversario) {
		this.colunaDataAniversario.set(colunaDataAniversario);
	}

	public void setColunaDataBatismo(String colunaDataBatismo) {
		this.colunaDataBatismo.set(colunaDataBatismo);
	}

	public void setColunaCelularUm(String colunaCelularUm) {
		this.colunaCelularUm.set(colunaCelularUm);
	}

	public void setColunaCelularDois(String colunaCelularDois) {
		this.colunaCelularDois.set(colunaCelularDois);
	}

	public void setColunaRua(String colunaRua) {
		this.colunaRua.set(colunaRua);
	}

	public void setColunaNumero(Integer colunaNumero) {
		this.colunaNumero.set(colunaNumero);
	}

	public void setColunaBairro(String colunaBairro) {
		this.colunaBairro.set(colunaBairro);
	}

}
