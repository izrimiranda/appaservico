package br.com.izri.aservico.controller.membro;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.izri.aservico.auxiliar.Constantes;
import br.com.izri.aservico.auxiliar.PopupDialog;
import br.com.izri.aservico.controller.FXMLTabAlterarMembroController;
import br.com.izri.aservico.controller.base.ControllerBase;
import br.com.izri.aservico.model.entity.Celula;
import br.com.izri.aservico.model.entity.Endereco;
import br.com.izri.aservico.model.entity.Membro;
import br.com.izri.aservico.model.entity.TurmaEscolaBiblica;
import br.com.izri.aservico.model.entity.dao.CelulaDAO;
import br.com.izri.aservico.model.entity.dao.TurmaEscolaBiblicaDAO;
import br.com.izri.aservico.model.table.MembroModelo;
import br.com.izri.aservico.utils.DateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLTabMembroConsultarController extends ControllerBase implements Initializable {

	@FXML
	private Button botaoAlterarMembro;
	@FXML
	private ComboBox<String> comboBoxPesquisarPor;
	@FXML
	private Button botaoPesquisar;
	@FXML
	private Button botaoExcluirMembro;
	@FXML
	private TextField campoPesquisarPor;
	@FXML
	private DatePicker campoDataUm;
	@FXML
	private DatePicker campoDataDois;
	@FXML
	private ComboBox<String> comboBoxPesquisaValor;

	private Membro membro;

	private Membro membroRetornoBanco;

	// TABELA
	@FXML
	private TableView<MembroModelo> tabelaMembros;
	@FXML
	private TableColumn<MembroModelo, String> colunaNome;
	@FXML
	private TableColumn<MembroModelo, String> colunaCargoEclesiastico;
	@FXML
	private TableColumn<MembroModelo, String> colunaCelula;
	@FXML
	private TableColumn<MembroModelo, String> colunaDataAniversario;
	@FXML
	private TableColumn<MembroModelo, String> colunaDataBatismo;
	@FXML
	private TableColumn<MembroModelo, String> colunaCelularUm;
	@FXML
	private TableColumn<MembroModelo, String> colunaCelularDois;
	@FXML
	private TableColumn<MembroModelo, String> colunaRua;
	@FXML
	private TableColumn<MembroModelo, Integer> colunaNumero;
	@FXML
	private TableColumn<MembroModelo, String> colunaBairro;
	@FXML
	private TableColumn<MembroModelo, String> colunaTurma;

	private FXMLTabMembroCadastrarController tabMembroCadastrarController;

	public static Stage stage;

	public static ActionEvent event;

	@FXML
	public void comboBoxPesquisar(ActionEvent event) {

	}

	@FXML
	public void alterarMembroAcao(ActionEvent event) {

		Membro membro = new Membro();

		MembroModelo membroModeloAlterar = this.getTabelaMembros().getSelectionModel().getSelectedItem();

		membro.setNomeCompleto(membroModeloAlterar.getColunaNome());

		this.setMembroRetornoBanco(this.getMembroDAO().pesquisarMembro("Nome Completo", membro).get(0));

		this.carregarTabMembrosCadastrarAlterar();
	}

	public void carregarTabMembrosCadastrarAlterar() {
		FXMLLoader loaderAlterar = new FXMLLoader();

		loaderAlterar.setLocation(this.getClass().getResource("/br/com/izri/aservico/telas/tab/FXMLTabAlterarMembro.fxml"));

		try {
			loaderAlterar.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FXMLTabAlterarMembroController controller = loaderAlterar.getController();

		try {
			this.montarAlterarMembro(this.getMembroRetornoBanco(), controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Parent root = loaderAlterar.getRoot();

		Stage stage = new Stage();

		stage.getIcons().add(new Image("/imagens/icone.png"));

		stage.setTitle("Alterando Membro: " + this.getMembroRetornoBanco().getNomeCompleto());
		stage.setScene(new Scene(root));

		stage.showAndWait();
	}

	public void montarAlterarMembro(Membro m, FXMLTabAlterarMembroController controller) throws IOException {

		controller.getComboBoxCargoEclesiastico().setValue(m.getCargoEclesiastico());
		controller.getComboBoxCelula().setValue(m.getCelula().getNomeCelula());
		controller.getComboBoxEstado().setValue(m.getEndereco().getUf());
		controller.getComboBoxTurmaEscolaBiblica().setValue(m.getTurmaEscolaBiblica().getNomeTurma());

		controller.getCampoBairro().setText(m.getEndereco().getBairro());
		controller.getCampoCelularDois().setText(m.getNumeroCelularDois());
		controller.getCampoCelularUm().setText(m.getNumeroCelularUm());
		controller.getCampoCEP().setText(m.getEndereco().getCEP());
		controller.getCampoCidade().setText(m.getEndereco().getCidade());
		controller.getCampoComplemento().setText(m.getEndereco().getComplemento());
		controller.getCampoDataAniversario().getEditor().setText(m.getDataAniversarioStr());
		controller.getCampoDataBatismo().getEditor().setText(m.getDataBatismoStr());
		controller.getCampoEmail().setText(m.getEmail());
		controller.getCampoNomeCompleto().setText(m.getNomeCompleto());
		controller.getCampoNumero().setText(String.valueOf(m.getEndereco().getNumero()));
		controller.getCampoRua().setText(m.getEndereco().getLogradouro());
		controller.getCampoTelResidencial().setText(m.getNumeroFixoResidencial());
		controller.getCampoTelResidencial().setText(m.getNumeroFixoTrabalho());

		controller.getMembroAlterar().setId(m.getId());
	}

	@Override
	public void fecharTela() {

	}

	@Override
	public void start(Stage stage) throws Exception {

		FXMLTabMembroConsultarController.stage = stage;

		Pane telaPrincipal = FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/FXMLTabMembroConsultar.fxml"));

		Scene scene = new Scene(telaPrincipal, 1280, 700);

		stage.setResizable(false);

		stage.getIcons().add(new Image("/imagens/icone.png"));

		this.carregarListas();

		stage.setScene(scene);

		stage.show();

	}

	@FXML
	public void campoDataUmAcao(ActionEvent event) {
		LocalDate data = this.getCampoDataUm().getValue();
		this.getCampoDataDois().setValue(data);
	}

	private void habilitarCamposDataPesquisa(boolean valor) {
		this.getCampoDataUm().setDisable(!valor);
		this.getCampoDataDois().setDisable(!valor);
	}

	@FXML
	public void comboBoxPesquisarPorAcao(ActionEvent event) {

		String pesquisarPor = this.getComboBoxPesquisarPor().getValue();

		if (pesquisarPor.equals(Constantes.PESQUISAR_TODOS)) {
			this.getComboBoxPesquisaValor().setValue("");
			this.getComboBoxPesquisaValor().setEditable(false);
			this.getComboBoxPesquisaValor().setDisable(true);

		} else if (pesquisarPor.equals(Constantes.BAIRRO)) {
			this.getComboBoxPesquisaValor().setVisible(true);
			this.getComboBoxPesquisaValor().setDisable(false);

			this.carregarListaBairro();

			this.habilitarCamposDataPesquisa(false);

			this.getComboBoxPesquisaValor().setEditable(true);
		} else if (pesquisarPor.equals(Constantes.CARGO_ECLESIASTICO)) {
			this.getComboBoxPesquisaValor().setVisible(true);
			this.getComboBoxPesquisaValor().setDisable(false);
			this.getComboBoxPesquisaValor().setEditable(false);

			this.carregarListaCargoEclesiastico();
			this.habilitarCamposDataPesquisa(false);
		} else if (pesquisarPor.equals(Constantes.CELULA)) {
			this.getComboBoxPesquisaValor().setVisible(true);
			this.carregarListaCelula();

			this.habilitarCamposDataPesquisa(false);

			this.getComboBoxPesquisaValor().setEditable(false);
		} else if (pesquisarPor.equals(Constantes.TURMA_ESCOLA_BIBLICA)) {
			this.getComboBoxPesquisaValor().setVisible(true);
			this.getComboBoxPesquisaValor().setEditable(false);

			this.carregarListaTurmaEscolaBiblica();

			this.habilitarCamposDataPesquisa(false);

		} else if (pesquisarPor.equals(Constantes.DATA_DE_ANIVERSARIO)) {
			this.getComboBoxPesquisaValor().getEditor().setText("");
			this.getComboBoxPesquisaValor().setDisable(true);

			this.habilitarCamposDataPesquisa(true);
		} else if (pesquisarPor.equals(Constantes.DATA_DE_BATISMO)) {
			this.getComboBoxPesquisaValor().setDisable(true);
			this.getComboBoxPesquisaValor().getEditor().setText("");

			this.habilitarCamposDataPesquisa(true);
		} else if (pesquisarPor.equals(Constantes.DATA_DE_REGISTRO)) {
			this.getComboBoxPesquisaValor().setDisable(true);

			this.getComboBoxPesquisaValor().getEditor().setText("");

			this.habilitarCamposDataPesquisa(true);
		} else if (pesquisarPor.equals(Constantes.NOME_COMPLETO)) {
			this.getComboBoxPesquisaValor().setVisible(true);
			this.getComboBoxPesquisaValor().setDisable(false);

			this.carregarListaNomeCompleto();

			this.habilitarCamposDataPesquisa(false);

			this.getComboBoxPesquisaValor().setEditable(true);
		}
	}

	@FXML
	public void botaoPesquisarPorAcao(ActionEvent actionEvent) {
		List<MembroModelo> listaMembroModelo = new ArrayList<>();

		this.limparTabela();

		if (this.isCampoPesquisarPorValido()) {
			listaMembroModelo = this.converterMembroParaMembroModelo(this.pesquisarListaMembro());

			this.carregarTabela(listaMembroModelo);
		} else {
			PopupDialog.getInstance().exibirPopupErro("O campo de pesquisa está em branco. Verifique!", "Campo em branco");
			this.formatarCampoPesquisarPor();
		}
	}

	public void formatarCampoPesquisarPor() {
		this.getComboBoxPesquisaValor().setStyle("-fx-text-box-border: red;");
	}

	public void limparTabela() {
		System.out.println("LIMPANDO TABELAS...");
		this.getTabelaMembros().setItems(null);
	}

	public List<Membro> pesquisarListaMembro() {

		List<Membro> listaMembroRetornoBanco = new ArrayList<>();

		Membro membro = new Membro();

		membro = this.montarMembroPesquisaPor();

		if (this.getComboBoxPesquisarPor().getValue() != null) {
			listaMembroRetornoBanco = this.getMembroDAO().pesquisarMembro(this.getComboBoxPesquisarPor().getValue().toString(), membro);
		} else {
			listaMembroRetornoBanco = this.getMembroDAO().pesquisarMembro("", membro);
		}

		return listaMembroRetornoBanco;
	}

	private void carregarListas() {

		// Lista "Pesquisa Por"
		ObservableList<String> listaPesquisarPor = FXCollections.observableArrayList(Constantes.PESQUISAR_TODOS, Constantes.BAIRRO, Constantes.CARGO_ECLESIASTICO, Constantes.CELULA, Constantes.TURMA_ESCOLA_BIBLICA, Constantes.DATA_DE_ANIVERSARIO, Constantes.DATA_DE_BATISMO, Constantes.DATA_DE_REGISTRO, Constantes.NOME_COMPLETO);
		this.getComboBoxPesquisarPor().setItems(listaPesquisarPor);

	}

	private void carregarListaCargoEclesiastico() {

		ObservableList<String> listaCargoEclesiastico = FXCollections.observableArrayList(Constantes.PRESIDENTE, Constantes.VICE_PRESIDENTE, Constantes.MEMBRO, Constantes.NAO_MEMBRO, Constantes.PRIMEIRO_SECRETARIO, Constantes.SEGUNDO_SECRETARIO, Constantes.PRIMEIRO_TESOUREIRO,
			Constantes.SEGUNDO_TESOUREIRO, Constantes.COORDENADOR_CELULA, Constantes.AUXILIAR_CELULA);

		this.getComboBoxPesquisaValor().setItems(listaCargoEclesiastico);
	}

	private void carregarListaCelula() {
		ObservableList<String> listaCelula = FXCollections.observableArrayList(this.buscarListaCelula());

		this.getComboBoxPesquisaValor().setItems(listaCelula);
	}

	private void carregarListaTurmaEscolaBiblica() {
		ObservableList<String> listaTurma = FXCollections.observableArrayList(this.buscarListaTurmaEscolaBiblica());

		this.getComboBoxPesquisaValor().setItems(listaTurma);
	}

	private void carregarListaBairro() {
		List<String> listaBairroStr = new ArrayList<>();
		List<Endereco> listaEndereco = this.getEnderecoDAO().buscarTodos();

		listaBairroStr.add("Todos os bairros");

		for (Endereco e : listaEndereco) {
			listaBairroStr.add(e.getBairro());
		}

		ObservableList<String> lista = FXCollections.observableArrayList(listaBairroStr);

		this.getComboBoxPesquisaValor().setItems(lista);
	}

	private void carregarListaNomeCompleto() {
		List<String> listaNomeStr = new ArrayList<>();
		List<Membro> listaNomeCompleto = this.getMembroDAO().pesquisarTodos();

		listaNomeStr.add("Todos");

		for (Membro membro : listaNomeCompleto) {
			listaNomeStr.add(membro.getNomeCompleto());
		}

		ObservableList<String> lista = FXCollections.observableArrayList(listaNomeStr);

		this.getComboBoxPesquisaValor().setItems(lista);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		this.carregarListas();

	}

	public boolean isCampoPesquisarPorValido() {
		boolean verificador = true;

		String valorComboBoxPesquisa = "";
		String valorPesquisaValor = "";

		if (this.getComboBoxPesquisarPor().getValue() == null) {
			verificador = false;
		} else {
			valorComboBoxPesquisa = this.getComboBoxPesquisarPor().getValue();
		}

		if (this.getComboBoxPesquisaValor().getValue() == null) {
			verificador = false;
		} else {
			valorPesquisaValor = this.getComboBoxPesquisaValor().getValue();
		}

		// System.out.println("valorComboBoxPesquisa" + valorComboBoxPesquisa);
		// System.out.println("valorPesquisaValor" + valorPesquisaValor);

		if (valorComboBoxPesquisa.equals(Constantes.PESQUISAR_TODOS)) {
			verificador = true;
		} else if (valorComboBoxPesquisa.equals(Constantes.BAIRRO) && (valorPesquisaValor == null)) {
			verificador = false;
		} else if (valorComboBoxPesquisa.equals(Constantes.CARGO_ECLESIASTICO) && (valorPesquisaValor == null)) {
			verificador = false;
		} else if (valorComboBoxPesquisa.equals(Constantes.CELULA) && (valorPesquisaValor == null)) {
			verificador = false;
		} else if (valorComboBoxPesquisa.equals(Constantes.TURMA_ESCOLA_BIBLICA) && (valorPesquisaValor == null)) {
			verificador = false;
		} else if (valorComboBoxPesquisa.equals(Constantes.DATA_DE_ANIVERSARIO) && this.isDatasPesquiarPorValidas()) {
			verificador = true;
		} else if (valorComboBoxPesquisa.equals(Constantes.DATA_DE_BATISMO) && this.isDatasPesquiarPorValidas()) {
			verificador = true;
		} else if (valorComboBoxPesquisa.equals(Constantes.DATA_DE_REGISTRO) && this.isDatasPesquiarPorValidas()) {
			verificador = true;
		} else if (valorComboBoxPesquisa.equals(Constantes.NOME_COMPLETO) && (valorPesquisaValor == null)) {
			verificador = false;
		}

		return verificador;

	}

	public boolean isDatasPesquiarPorValidas() {
		boolean retorno = false;

		if (DateUtils.isDatasValidas(this.getCampoDataUm().getValue().toString(), this.getCampoDataDois().getValue().toString())) {
			retorno = true;
		}

		return retorno;
	}

	public List<String> buscarListaCelula() {
		List<Celula> listaCelulaBanco = new ArrayList<>();
		List<String> listaCelulaString = new ArrayList<>();

		listaCelulaBanco = new CelulaDAO().pesquisarTodasCelulas();
		listaCelulaString.add("Todas as células");

		for (Celula c : listaCelulaBanco) {
			listaCelulaString.add(c.getNomeCelula());
		}

		return listaCelulaString;
	}

	public List<String> buscarListaTurmaEscolaBiblica() {
		List<TurmaEscolaBiblica> listaTurma = new ArrayList<>();
		List<String> listaTurmaString = new ArrayList<>();

		listaTurma = new TurmaEscolaBiblicaDAO().pesquisarTodasTurmas();
		listaTurmaString.add("Todas as turmas");

		for (TurmaEscolaBiblica turma : listaTurma) {
			listaTurmaString.add(turma.getNomeTurma());
		}

		return listaTurmaString;
	}

	public void carregarTabela(List<MembroModelo> listaMembros) {

		ObservableList<MembroModelo> observableList = FXCollections.observableArrayList(listaMembros);

		this.getColunaNome().setCellValueFactory(new PropertyValueFactory<>("colunaNome"));
		this.getColunaCargoEclesiastico().setCellValueFactory(new PropertyValueFactory<>("colunaCargoEclesiastico"));
		this.getColunaCelula().setCellValueFactory(new PropertyValueFactory<>("colunaCelula"));
		this.getColunaTurma().setCellValueFactory(new PropertyValueFactory<>("colunaTurma"));
		this.getColunaDataAniversario().setCellValueFactory(new PropertyValueFactory<>("colunaDataAniversario"));
		this.getColunaDataBatismo().setCellValueFactory(new PropertyValueFactory<>("colunaDataBatismo"));
		this.getColunaCelularUm().setCellValueFactory(new PropertyValueFactory<>("colunaCelularUm"));
		this.getColunaCelularDois().setCellValueFactory(new PropertyValueFactory<>("colunaCelularDois"));
		this.getColunaRua().setCellValueFactory(new PropertyValueFactory<>("colunaRua"));
		this.getColunaNumero().setCellValueFactory(new PropertyValueFactory<>("colunaNumero"));
		this.getColunaBairro().setCellValueFactory(new PropertyValueFactory<>("colunaBairro"));

		this.getTabelaMembros().setItems(observableList);
	}

	@FXML
	public void botaoExcluirMembroAcao(ActionEvent event) {
		int idExcluir = this.getTabelaMembros().getSelectionModel().getSelectedItem().getColunaId();
		System.out.println("ID: " + idExcluir);
		if (PopupDialog.getInstance().isConfirmacaoOk("Deseja mesmo excluir o membro?", "Exclusão de membro")) {
			this.getMembroDAO().exclusaoLogicaMembroPorId(idExcluir);
			PopupDialog.getInstance().exibirPopupInformacao("Membro excluído com sucesso!", "Excluído com sucesso");
		}
	}

	@FXML
	public void selecionarLinhaTabelaAcao(MouseEvent event) {
		this.getBotaoAlterarMembro().setDisable(false);
		this.getBotaoExcluirMembro().setDisable(false);
	}

	public Membro montarMembroPesquisaPor() {
		Membro membro = null;

		if (this.getComboBoxPesquisarPor().getValue() != null) {

			if (this.getComboBoxPesquisarPor().getValue().toString().equals(Constantes.BAIRRO)) {
				membro = new Membro();
				membro.getEndereco().setBairro(this.getCampoPesquisarPor().getText());
			}

			if (this.getComboBoxPesquisarPor().getValue().toString().equals(Constantes.CARGO_ECLESIASTICO)) {
				membro = new Membro();
				membro.setCargoEclesiastico(this.getCampoPesquisarPor().getText());
			}

			if (this.getComboBoxPesquisarPor().getValue().toString().equals(Constantes.CELULA)) {
				membro = new Membro();
				membro.getCelula().setNomeCelula(this.getCampoPesquisarPor().getText());
			}

			if (this.getComboBoxPesquisarPor().getValue().toString().equals(Constantes.TURMA_ESCOLA_BIBLICA)) {
				membro = new Membro();
				membro.getTurmaEscolaBiblica().setNomeTurma(this.getCampoPesquisarPor().getText());
			}

			if (this.getComboBoxPesquisarPor().getValue().toString().equals(Constantes.DATA_DE_ANIVERSARIO)) {
				membro = new Membro();
				try {
					membro.setDataAniversario(DateUtils.parseToCalendar(DateUtils.converterDatePickerToString(this.getCampoDataUm()), false));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (this.getComboBoxPesquisarPor().getValue().toString().equals(Constantes.DATA_DE_BATISMO)) {
				membro = new Membro();
				try {
					membro.setDataBatismo(DateUtils.parseToCalendar(DateUtils.converterDatePickerToString(this.getCampoDataUm()), false));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (this.getComboBoxPesquisarPor().getValue().toString().equals(Constantes.DATA_DE_REGISTRO)) {
				membro = new Membro();
				try {
					membro.setDataRegistroMembro(DateUtils.parseToCalendar(DateUtils.converterDatePickerToString(this.getCampoDataUm()), false));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (this.getComboBoxPesquisarPor().getValue().toString().equals(Constantes.NOME_COMPLETO)) {
				membro = new Membro();
				membro.setNomeCompleto(this.getCampoPesquisarPor().getText());
			}
		} else {
			membro = new Membro();
		}

		return membro;
	}

	private List<MembroModelo> converterMembroParaMembroModelo(List<Membro> listaMembro) {

		List<MembroModelo> listaMembroModelo = new ArrayList<>();
		MembroModelo membroModelo = null;

		for (Membro m : listaMembro) {
			membroModelo = new MembroModelo(m.getId(), m.getNomeCompleto(), m.getCargoEclesiastico(), m.getCelula().getNomeCelula(), m.getTurmaEscolaBiblica().getNomeTurma(), m.getDataAniversarioStr(), m.getDataBatismoStr(), m.getNumeroCelularUm(), m.getNumeroCelularDois(), m.getEndereco().getLogradouro(), m.getEndereco().getNumero(),
				m.getEndereco().getBairro());

			listaMembroModelo.add(membroModelo);
		}
		return listaMembroModelo;
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

	public FXMLTabMembroCadastrarController getTabMembroCadastrarController() {
		if (this.tabMembroCadastrarController == null) {
			this.tabMembroCadastrarController = new FXMLTabMembroCadastrarController();
		}
		return this.tabMembroCadastrarController;
	}

	public void setTabMembroCadastrarController(FXMLTabMembroCadastrarController tabMembroCadastrarController) {
		this.tabMembroCadastrarController = tabMembroCadastrarController;
	}

	public ComboBox<String> getComboBoxPesquisaValor() {
		if (this.comboBoxPesquisaValor == null) {
			this.comboBoxPesquisaValor = new ComboBox<>();
		}
		return this.comboBoxPesquisaValor;
	}

	public void setComboBoxPesquisaValor(ComboBox<String> comboBoxPesquisaValor) {
		this.comboBoxPesquisaValor = comboBoxPesquisaValor;
	}

	public Button getBotaoAlterarMembro() {
		if (this.botaoAlterarMembro == null) {
			this.botaoAlterarMembro = new Button();
		}
		return this.botaoAlterarMembro;
	}

	public void setBotaoAlterarMembro(Button botaoAlterarMembro) {
		this.botaoAlterarMembro = botaoAlterarMembro;
	}

	public ComboBox<String> getComboBoxPesquisarPor() {
		if (this.comboBoxPesquisarPor == null) {
			this.comboBoxPesquisarPor = new ComboBox<>();
		}
		return this.comboBoxPesquisarPor;
	}

	public void setComboBoxPesquisarPor(ComboBox<String> comboBoxPesquisarPor) {
		this.comboBoxPesquisarPor = comboBoxPesquisarPor;
	}

	public Button getBotaoPesquisar() {
		if (this.botaoPesquisar == null) {
			this.botaoPesquisar = new Button();
		}
		return this.botaoPesquisar;
	}

	public void setBotaoPesquisar(Button botaoPesquisar) {
		this.botaoPesquisar = botaoPesquisar;
	}

	public Button getBotaoExcluirMembro() {
		if (this.botaoExcluirMembro == null) {
			this.botaoExcluirMembro = new Button();
		}
		return this.botaoExcluirMembro;
	}

	public void setBotaoExcluirMembro(Button botaoExcluirMembro) {
		this.botaoExcluirMembro = botaoExcluirMembro;
	}

	public TextField getCampoPesquisarPor() {
		if (this.campoPesquisarPor == null) {
			this.campoPesquisarPor = new TextField();
		}
		return this.campoPesquisarPor;
	}

	public void setCampoPesquisarPor(TextField campoPesquisarPor) {
		this.campoPesquisarPor = campoPesquisarPor;
	}

	public TableView<MembroModelo> getTabelaMembros() {
		if (this.tabelaMembros == null) {
			this.tabelaMembros = new TableView<>();
		}
		return this.tabelaMembros;
	}

	public void setTabelaMembros(TableView<MembroModelo> tabelaMembros) {
		this.tabelaMembros = tabelaMembros;
	}

	public TableColumn<MembroModelo, String> getColunaNome() {
		if (this.colunaNome == null) {
			this.colunaNome = new TableColumn<>();
		}
		return this.colunaNome;
	}

	public void setColunaNome(TableColumn<MembroModelo, String> colunaNome) {
		this.colunaNome = colunaNome;
	}

	public TableColumn<MembroModelo, String> getColunaCargoEclesiastico() {
		if (this.colunaCargoEclesiastico == null) {
			this.colunaCargoEclesiastico = new TableColumn<>();
		}
		return this.colunaCargoEclesiastico;
	}

	public void setColunaCargoEclesiastico(TableColumn<MembroModelo, String> colunaCargoEclesiastico) {
		this.colunaCargoEclesiastico = colunaCargoEclesiastico;
	}

	public TableColumn<MembroModelo, String> getColunaCelula() {
		if (this.colunaCelula == null) {
			this.colunaCelula = new TableColumn<>();
		}
		return this.colunaCelula;
	}

	public void setColunaCelula(TableColumn<MembroModelo, String> colunaCelula) {
		this.colunaCelula = colunaCelula;
	}

	public TableColumn<MembroModelo, String> getColunaDataAniversario() {
		if (this.colunaDataAniversario == null) {
			this.colunaDataAniversario = new TableColumn<>();
		}
		return this.colunaDataAniversario;
	}

	public void setColunaDataAniversario(TableColumn<MembroModelo, String> colunaDataAniversario) {
		this.colunaDataAniversario = colunaDataAniversario;
	}

	public TableColumn<MembroModelo, String> getColunaDataBatismo() {
		if (this.colunaDataBatismo == null) {
			this.colunaDataBatismo = new TableColumn<>();
		}
		return this.colunaDataBatismo;
	}

	public void setColunaDataBatismo(TableColumn<MembroModelo, String> colunaDataBatismo) {
		this.colunaDataBatismo = colunaDataBatismo;
	}

	public TableColumn<MembroModelo, String> getColunaCelularUm() {
		if (this.colunaCelularUm == null) {
			this.colunaCelularUm = new TableColumn<>();
		}
		return this.colunaCelularUm;
	}

	public void setColunaCelularUm(TableColumn<MembroModelo, String> colunaCelularUm) {
		this.colunaCelularUm = colunaCelularUm;
	}

	public TableColumn<MembroModelo, String> getColunaCelularDois() {
		if (this.colunaCelularDois == null) {
			this.colunaCelularDois = new TableColumn<>();
		}
		return this.colunaCelularDois;
	}

	public void setColunaCelularDois(TableColumn<MembroModelo, String> colunaCelularDois) {
		this.colunaCelularDois = colunaCelularDois;
	}

	public TableColumn<MembroModelo, String> getColunaRua() {
		if (this.colunaRua == null) {
			this.colunaRua = new TableColumn<>();
		}
		return this.colunaRua;
	}

	public void setColunaRua(TableColumn<MembroModelo, String> colunaRua) {
		this.colunaRua = colunaRua;
	}

	public TableColumn<MembroModelo, Integer> getColunaNumero() {
		if (this.colunaNumero == null) {
			this.colunaNumero = new TableColumn<>();
		}
		return this.colunaNumero;
	}

	public void setColunaNumero(TableColumn<MembroModelo, Integer> colunaNumero) {
		this.colunaNumero = colunaNumero;
	}

	public TableColumn<MembroModelo, String> getColunaBairro() {
		if (this.colunaBairro == null) {
			this.colunaBairro = new TableColumn<>();
		}
		return this.colunaBairro;
	}

	public void setColunaBairro(TableColumn<MembroModelo, String> colunaBairro) {
		this.colunaBairro = colunaBairro;
	}

	public Membro getMembroRetornoBanco() {
		if (this.membroRetornoBanco == null) {
			this.membroRetornoBanco = new Membro();
		}
		return this.membroRetornoBanco;
	}

	public void setMembroRetornoBanco(Membro membroRetornoBanco) {
		this.membroRetornoBanco = membroRetornoBanco;
	}

	public TableColumn<MembroModelo, String> getColunaTurma() {
		if (this.colunaTurma == null) {
			this.colunaTurma = new TableColumn<>();
		}
		return this.colunaTurma;
	}

	public void setColunaTurma(TableColumn<MembroModelo, String> colunaTurma) {
		this.colunaTurma = colunaTurma;
	}

	public Membro getMembro() {
		if (this.membro == null) {
			this.membro = new Membro();
		}
		return this.membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}
}
