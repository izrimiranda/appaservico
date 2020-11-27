package br.com.izri.aservico.controller.secretaria;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.IOUtils;

import br.com.izri.aservico.auxiliar.PopupDialog;
import br.com.izri.aservico.controller.base.ControllerBase;
import br.com.izri.aservico.controller.session.UsuarioSessao;
import br.com.izri.aservico.model.entity.RelatorioSessao;
import br.com.izri.aservico.model.table.RelatorioSessaoModelo;
import br.com.izri.aservico.utils.DateUtils;
import br.com.izri.aservico.utils.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FXMLTabSecretariaConsultarController extends ControllerBase implements Initializable {

	@FXML
	private Button botaoSelecionarArquivo;
	@FXML
	private TextField campoArquivo;
	@FXML
	private DatePicker campoDataSessao;
	@FXML
	private DatePicker campoDataUm;
	@FXML
	private DatePicker campoDataDois;
	@FXML
	private TextArea campoObservacao;
	@FXML
	private TextField campoObservacaoPesquisa;
	@FXML
	private Label labelNovoNomeArquivo;
	@FXML
	private Button botaoBaixarRelatorio;
	@FXML
	private Button botaoExcluirRelatorio;
	@FXML
	private Button botaoPesquisar;

	private File arquivoSelecionado;

	private List<RelatorioSessao> listaRelatorioSessaoRetornoBanco;

	// TABELA
	@FXML
	private TableView<RelatorioSessaoModelo> tabelaRelatorios;
	@FXML
	private TableColumn<RelatorioSessaoModelo, String> colunaRelatorioDataSessao;
	@FXML
	private TableColumn<RelatorioSessaoModelo, String> colunaRelatorioDataCadastro;
	@FXML
	private TableColumn<RelatorioSessaoModelo, String> colunaRelatorioObservacao;
	@FXML
	private TableColumn<RelatorioSessaoModelo, String> colunaRelatorioNomeArquivo;

	/**
	 * Atualiza a tabela de relatórios
	 *
	 * @param listaRelatorioSessao
	 */
	private void atualizarTabelaRelatorios(List<RelatorioSessao> listaRelatorioSessao) {
		this.carregarTabelaRelatorios(this.converterParaRelatorioModelo(listaRelatorioSessao));
	}

	/**
	 * Exclui o relatório selecionado da tabela de relatórios.
	 *
	 * @param event
	 */
	@FXML
	public void botaoExcluirRelatorioAcao(ActionEvent event) {

		this.excluirRelatorio();

		this.atualizarTabelaRelatorios(this.getListaRelatorioSessaoRetornoBanco());

		PopupDialog.getInstance().exibirPopupSucesso("O relatório foi excluído com sucesso.", "Relatório excluído");

	}

	private void excluirRelatorio() {
		if (PopupDialog.getInstance().isConfirmacaoOk("Deseja realmente excluir o relatório?", "Excluir Relatório")) {
			int id = this.getIdRelatorioSelecionado();
			int index = 0;

			this.getRelatorioSessaoDAO().exclusaoLogicaPorId(id);

			for (RelatorioSessao rs : this.getListaRelatorioSessaoRetornoBanco()) {
				id = rs.getId();
				index = index++;
				this.getListaRelatorioSessaoRetornoBanco().remove(index);
				break;
			}
		}
	}

	/**
	 * Executa o download do relatório selecionado da tabela. Antes de fazer o
	 * download e exibido um popup para selecionar o diretório para salvar o
	 * arquivo.
	 *
	 * @param event
	 */
	@FXML
	public void botaoBaixarRelatorioAcao(ActionEvent event) {
		this.setArquivoRelatorioSelecionado();
		PopupDialog.getInstance().exibirPopupInformacao("O relatório foi baixado com sucesso! Pasta: " + this.getArquivoSelecionado().getPath(), "Relatório baixado");
	}

	private String getPastaSalvarRelatorio() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		return directoryChooser.showDialog(null).getAbsolutePath() + "\\";
	}

	private String getNomeRelatorioSelecionado() {
		String extensao = "";

		for (RelatorioSessao rs : this.getListaRelatorioSessaoRetornoBanco()) {
			if (this.getIdRelatorioSelecionado() == rs.getId()) {
				extensao = rs.getExtensaoArquivo();
			}
		}

		return this.getTabelaRelatorios().getSelectionModel().getSelectedItem().getColunaRelatorioNomeArquivo() + extensao;
	}

	private void setArquivoRelatorioSelecionado() {
		FileOutputStream output = null;
		File file = null;
		byte[] arquivo = null;

		try {
			arquivo = this.getRelatorioParaDownload();

			file = new File(this.getPastaSalvarRelatorio() + this.getNomeRelatorioSelecionado());

			if (!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}

			output = new FileOutputStream(file.getAbsolutePath());

			output.write(arquivo);

			output.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] getRelatorioParaDownload() {
		byte[] arquivo = null;
		for (RelatorioSessao rs : this.getListaRelatorioSessaoRetornoBanco()) {
			System.out.println("ID: " + this.getIdRelatorioSelecionado());
			if (this.getIdRelatorioSelecionado() == rs.getId()) {
				arquivo = rs.getRelatorio();
			}
		}

		return arquivo;
	}

	private int getIdRelatorioSelecionado() {
		return this.getTabelaRelatorios().getSelectionModel().getSelectedItem().getColunaRelatorioId();
	}

	@FXML
	public void selecionarLinhaTabelaOnMouseClicked(MouseEvent event) {
		this.getBotaoBaixarRelatorio().setDisable(false);
		this.getBotaoExcluirRelatorio().setDisable(false);
	}

	private void verificarTipoPesquisa() {
		String dataInicio = this.getCampoDataUm().getEditor().getText();
		String dataFinal = this.getCampoDataDois().getEditor().getText();
		String observacao = this.getCampoObservacaoPesquisa().getText();

		if (dataInicio.isEmpty() && dataFinal.isEmpty()) {
			if (StringUtils.isEmpty(observacao)) {
				this.setListaRelatorioSessaoRetornoBanco(this.getRelatorioSessaoDAO().buscarTodos());
				this.carregarTabelaRelatorios(this.converterParaRelatorioModelo(this.getListaRelatorioSessaoRetornoBanco()));
			}

			if (StringUtils.isNotEmpty(observacao)) {
				this.setListaRelatorioSessaoRetornoBanco(this.getRelatorioSessaoDAO().buscarPorObservacao(observacao));
				this.carregarTabelaRelatorios(this.converterParaRelatorioModelo(this.getListaRelatorioSessaoRetornoBanco()));
			}
		} else {
			if (DateUtils.isDatasValidas(dataInicio, dataFinal)) {

				if (StringUtils.isEmpty(observacao)) {
					if (DateUtils.isDatasValidas(dataInicio, dataFinal)) {
						this.setListaRelatorioSessaoRetornoBanco(this.getRelatorioSessaoDAO().buscarPorDataSessao(dataInicio, dataFinal));
						this.carregarTabelaRelatorios(this.converterParaRelatorioModelo(this.getListaRelatorioSessaoRetornoBanco()));
					}
				} else if ((StringUtils.isEmpty(dataInicio)) && (StringUtils.isEmpty(dataFinal)) && (StringUtils.isNotEmpty(observacao))) {
					this.setListaRelatorioSessaoRetornoBanco(this.getRelatorioSessaoDAO().buscarPorObservacao(observacao));
					this.carregarTabelaRelatorios(this.converterParaRelatorioModelo(this.getListaRelatorioSessaoRetornoBanco()));
				} else {
					this.setListaRelatorioSessaoRetornoBanco(this.getRelatorioSessaoDAO().buscarPorDataSessaoObservacao(dataInicio, dataFinal, observacao));
					this.carregarTabelaRelatorios(this.converterParaRelatorioModelo(this.getListaRelatorioSessaoRetornoBanco()));
				}

			} else {
				PopupDialog.getInstance().exibirPopupErro("Os campos de data apresentam erro. Por favor verifique.", "Datas inválidas");
				if (StringUtils.isEmpty(dataInicio)) {
					this.getCampoDataUm().setStyle("-fx-background-color: red;");
				}

				if (StringUtils.isEmpty(dataFinal)) {
					this.getCampoDataDois().setStyle("-fx-background-color: red;");
				}
			}
		}
	}

	@FXML
	public void botaoPesquisarAcao(ActionEvent event) {
		this.verificarTipoPesquisa();
	}

	private void carregarTabelaRelatorios(List<RelatorioSessaoModelo> listaRelatorioModelo) {
		ObservableList<RelatorioSessaoModelo> observableList = FXCollections.observableArrayList(listaRelatorioModelo);

		this.colunaRelatorioDataSessao.setCellValueFactory(new PropertyValueFactory<>("colunaRelatorioDataSessao"));
		this.colunaRelatorioDataCadastro.setCellValueFactory(new PropertyValueFactory<>("colunaRelatorioDataCadastro"));
		this.colunaRelatorioObservacao.setCellValueFactory(new PropertyValueFactory<>("colunaRelatorioObservacao"));
		this.colunaRelatorioNomeArquivo.setCellValueFactory(new PropertyValueFactory<>("colunaRelatorioNomeArquivo"));

		this.getTabelaRelatorios().setItems(observableList);

	}

	private List<RelatorioSessaoModelo> converterParaRelatorioModelo(List<RelatorioSessao> listaRelatorio) {
		List<RelatorioSessaoModelo> listaRelatorioModelo = new ArrayList<>();
		RelatorioSessaoModelo relatorioModelo = null;

		for (RelatorioSessao rs : listaRelatorio) {
			relatorioModelo = new RelatorioSessaoModelo(rs.getId(), rs.getDataSessaoStr(), rs.getDataSessaoStr(), rs.getObservacao(), rs.getNomeArquivo() + rs.getExtensaoArquivo());

			listaRelatorioModelo.add(relatorioModelo);
		}

		return listaRelatorioModelo;
	}

	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if ((fileName.lastIndexOf(".") != -1) && (fileName.lastIndexOf(".") != 0)) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}

	private boolean isCamposCadastroValidos() {
		boolean retorno = true;

		if (this.getCampoObservacao().getText().trim().isEmpty()) {
			retorno = false;
			this.getCampoObservacao().setStyle("-fx-text-box-border: red;");
		}

		if (this.getCampoArquivo().getText().trim().isEmpty()) {
			retorno = false;
			this.getCampoObservacao().setStyle("-fx-text-box-border: red;");
		}

		if (this.getCampoDataSessao().getValue() == null) {
			retorno = false;
			this.getCampoDataSessao().setStyle("-fx-background-color: red;");
		}

		return retorno;
	}

	@FXML
	public void botaoSalvarAcao() {
		this.montarRelatorioSessao();
		this.salvar();
		this.limparCamposCadastro();
		PopupDialog.getInstance().exibirPopupSucesso("Relatório salvo com sucesso!", "Relatório salvo");
	}

	private void limparCamposCadastro() {
		this.getCampoDataSessao().getEditor().setText(DateUtils.format(Calendar.getInstance(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN));
		this.getCampoArquivo().setText("");
		this.getLabelNovoNomeArquivo().setText("");
		this.getCampoObservacao().setText("");
		this.setArquivoSelecionado(null);
	}

	private void salvar() {
		RelatorioSessao relatorio = null;

		if (this.isCamposCadastroValidos()) {
			try {
				relatorio = this.montarRelatorioSessao();
				this.getRelatorioSessaoDAO().salvarRelatorioSessao(this.montarRelatorioSessao());

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			PopupDialog.getInstance().exibirPopupErro("Existem campos obrigatórios não preenchidos. Por favor, verifique!", "Campos em branco");
		}

	}

	private byte[] montarRelatorioArquivo() {

		FileInputStream input = null;
		byte[] arquivoByte = null;

		try {
			File file = new File(this.getArquivoSelecionado().getPath());
			input = new FileInputStream(file);
			arquivoByte = IOUtils.toByteArray(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arquivoByte;
	}

	/*
	 * Return: Objeto Relatório Sessão
	 */
	private RelatorioSessao montarRelatorioSessao() {
		RelatorioSessao relatorio = new RelatorioSessao();

		relatorio.setDataRegistro(Calendar.getInstance());
		relatorio.setDataSessao(DateUtils.parseToCalendar(this.getCampoDataSessao().getEditor().getText(), false));
		relatorio.setUsuario(UsuarioSessao.getInstance().getUsuario());
		relatorio.setObservacao(this.getCampoObservacao().getText());
		relatorio.setRelatorio(this.montarRelatorioArquivo());
		relatorio.setNomeArquivo(this.getLabelNovoNomeArquivo().getText());
		relatorio.setExtensaoArquivo("." + FXMLTabSecretariaConsultarController.getFileExtension(this.getArquivoSelecionado()));
		relatorio.setExcluido(false);

		return relatorio;
	}

	@FXML
	public void campoPesquisaDataUmAcao() {
		this.getCampoDataDois().getEditor().setText(this.getCampoDataUm().getEditor().getText());
	}

	@FXML
	public void selecionarArquivoAcao(ActionEvent event) {
		this.executarFileChooser();
	}

	@Override
	public void initialize(URL url, ResourceBundle args) {
		this.getCampoDataSessao().getEditor().setText(DateUtils.format(Calendar.getInstance(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN));
	}

	private void executarFileChooser() {
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Word (.doc, .docx)", "*.doc", "*.docx"));

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		this.getCampoArquivo().setText(file.getName());
		this.setArquivoSelecionado(file);
		this.getLabelNovoNomeArquivo().setText("RELATORIO_SESSAO_DATA_" + this.getCampoDataSessao().getEditor().getText().replaceAll("/", "_"));

		System.out.println("file.getPath()" + file.getPath());
	}

	@Override
	public void fecharTela() {

	}

	@Override
	public void start(Stage arg0) throws Exception {

	}

	public File getArquivoSelecionado() {
		if (this.arquivoSelecionado == null) {
			this.arquivoSelecionado = new File("");
		}

		return this.arquivoSelecionado;
	}

	public void setArquivoSelecionado(File arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public List<RelatorioSessao> getListaRelatorioSessaoRetornoBanco() {
		if (this.listaRelatorioSessaoRetornoBanco == null) {
			this.listaRelatorioSessaoRetornoBanco = new ArrayList<>();
		}

		return this.listaRelatorioSessaoRetornoBanco;
	}

	public void setListaRelatorioSessaoRetornoBanco(List<RelatorioSessao> listaRelatorioSessaoRetornoBanco) {
		this.listaRelatorioSessaoRetornoBanco = listaRelatorioSessaoRetornoBanco;
	}

	public TableView<RelatorioSessaoModelo> getTabelaRelatorios() {
		if (this.tabelaRelatorios == null) {
			this.tabelaRelatorios = new TableView<>();
		}
		return this.tabelaRelatorios;
	}

	public void setTabelaRelatorios(TableView<RelatorioSessaoModelo> tabelaRelatorios) {
		this.tabelaRelatorios = tabelaRelatorios;
	}

	public TextField getCampoArquivo() {
		if (this.campoArquivo == null) {
			this.campoArquivo = new TextField();
		}
		return this.campoArquivo;
	}

	public void setCampoArquivo(TextField campoArquivo) {
		this.campoArquivo = campoArquivo;
	}

	public DatePicker getCampoDataSessao() {
		if (this.campoDataSessao == null) {
			this.campoDataSessao = new DatePicker();
		}
		return this.campoDataSessao;
	}

	public void setCampoDataSessao(DatePicker campoDataSessao) {
		this.campoDataSessao = campoDataSessao;
	}

	public DatePicker getCampoDataUm() {
		if (this.campoDataUm == null) {
			this.campoDataUm = new DatePicker();
		}
		return this.campoDataUm;
	}

	public void setCampoDataUm(DatePicker campoDataUm) {
		this.campoDataUm = campoDataUm;
	}

	public DatePicker getCampoDataDois() {
		if (this.campoDataDois == null) {
			this.campoDataDois = new DatePicker();
		}
		return this.campoDataDois;
	}

	public void setCampoDataDois(DatePicker campoDataDois) {
		this.campoDataDois = campoDataDois;
	}

	public TextArea getCampoObservacao() {
		if (this.campoObservacao == null) {
			this.campoObservacao = new TextArea();
		}
		return this.campoObservacao;
	}

	public void setCampoObservacao(TextArea campoObservacao) {
		this.campoObservacao = campoObservacao;
	}

	public TextField getCampoObservacaoPesquisa() {
		if (this.campoObservacaoPesquisa == null) {
			this.campoObservacaoPesquisa = new TextField();
		}
		return this.campoObservacaoPesquisa;
	}

	public void setCampoObservacaoPesquisa(TextField campoObservacaoPesquisa) {
		this.campoObservacaoPesquisa = campoObservacaoPesquisa;
	}

	public Label getLabelNovoNomeArquivo() {
		if (this.labelNovoNomeArquivo == null) {
			this.labelNovoNomeArquivo = new Label();
		}
		return this.labelNovoNomeArquivo;
	}

	public void setLabelNovoNomeArquivo(Label labelNovoNomeArquivo) {
		this.labelNovoNomeArquivo = labelNovoNomeArquivo;
	}

	public Button getBotaoBaixarRelatorio() {
		if (this.botaoBaixarRelatorio == null) {
			this.botaoBaixarRelatorio = new Button();
		}
		return this.botaoBaixarRelatorio;
	}

	public void setBotaoBaixarRelatorio(Button botaoBaixarRelatorio) {
		this.botaoBaixarRelatorio = botaoBaixarRelatorio;
	}

	public Button getBotaoExcluirRelatorio() {
		if (this.botaoExcluirRelatorio == null) {
			this.botaoExcluirRelatorio = new Button();
		}
		return this.botaoExcluirRelatorio;
	}

	public void setBotaoExcluirRelatorio(Button botaoExcluirRelatorio) {
		this.botaoExcluirRelatorio = botaoExcluirRelatorio;
	}
}
