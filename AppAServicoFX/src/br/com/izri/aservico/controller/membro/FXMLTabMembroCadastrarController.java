package br.com.izri.aservico.controller.membro;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.izri.aservico.auxiliar.PopupDialog;
import br.com.izri.aservico.controller.base.ControllerBase;
import br.com.izri.aservico.model.entity.Celula;
import br.com.izri.aservico.model.entity.Endereco;
import br.com.izri.aservico.model.entity.Membro;
import br.com.izri.aservico.model.entity.TurmaEscolaBiblica;
import br.com.izri.aservico.model.entity.dao.CelulaDAO;
import br.com.izri.aservico.model.entity.dao.TurmaEscolaBiblicaDAO;
import br.com.izri.aservico.model.table.MembroModelo;
import br.com.izri.aservico.utils.DateUtils;
import br.com.izri.aservico.utils.MascarasFX;
import br.com.izri.aservico.utils.StringUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLTabMembroCadastrarController extends ControllerBase implements Initializable {

	@FXML
	private TextField campoNomeCompleto;
	@FXML
	private TextField campoRua;
	@FXML
	private TextField campoComplemento;
	@FXML
	private TextField campoNumero;
	@FXML
	private TextField campoBairro;
	@FXML
	private TextField campoCidade;
	@FXML
	private ComboBox<String> comboBoxEstado;
	@FXML
	private TextField campoEmail;
	@FXML
	private TextField campoCelularUm;
	@FXML
	private TextField campoCelularDois;
	@FXML
	private TextField campoTelResidencial;
	@FXML
	private TextField campoTelTrabalho;
	@FXML
	private DatePicker campoDataAniversario;
	@FXML
	private DatePicker campoDataBatismo;
	@FXML
	private ComboBox<String> comboBoxCargoEclesiastico;
	@FXML
	private ComboBox<String> comboBoxCelula;
	@FXML
	private ComboBox<String> comboBoxTurmaEscolaBiblica;
	@FXML
	private Button botaoSalvarMembro;
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
	private TextField campoCEP;
	@FXML
	private ImageView idBotaoSalvarMembro;
	@FXML
	private Label botaoMembroCadastrar;

	private Membro membro;
	private Endereco endereco;
	private Celula celula;

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

	private Membro membroAlterar;

	@FXML
	public void salvarMembroAcao() {
		this.salvar();
	}

	public void salvar() {

		if (this.isCamposValidos()) {

			if (this.getMembroAlterar().getId() == 0) {
				this.salvarOuMesclarMembro();
			} else {
				this.getMembro().setId(this.getMembroAlterar().getId());
				this.salvarOuMesclarMembro();
			}

			PopupDialog.getInstance().exibirPopupInformacao("O membro foi salvo com sucesso.", "Salvo");

		} else {
			// NEM TODOS OS CAMPOS ESTÃO VÁLIDOS

			if (PopupDialog.getInstance().isConfirmacaoOk("Existem campos obrigatórios em branco. Deseja continuar?", "Campos Obrigatórios")) {

				if ((this.isCampoNomeValido() == false) || (this.isCampoCelularValido() == false)) {
					PopupDialog.getInstance().exibirPopupErro("O campo NOME não pode ficar em branco. Por favor, verifique.", "Campo \"nome\" em branco.");
				} else {

					this.salvarOuMesclarMembro();

					if (this.getMembroAlterar().getId() == 0) {
						PopupDialog.getInstance().exibirPopupInformacao("O membro foi salvo com sucesso.", "Salvo");
					} else {
						PopupDialog.getInstance().exibirPopupInformacao("O membro foi atualizado com sucesso.", "Membro Atualizado");
					}
				}

			} else {
				this.formatarCamposInvalidos();
			}
		}
	}

	private void salvarOuMesclarMembro() {
		this.montarMembro();
		this.getMembroDAO().salvarOrMergeMembro(this.getMembro());
		this.resetarCampos();
		this.setMembroAlterar(null);
	}

	public boolean isCampoCelularValido() {
		boolean retorno = true;

		if (StringUtils.isEmpty(this.getCampoCelularUm().getText())) {
			this.getCampoCelularUm().setStyle("-fx-text-box-border: red;");
			retorno = false;
		}

		return retorno;
	}

	public void resetarCampos() {
		this.getCampoNomeCompleto().setText("");
		this.getCampoRua().setText("");
		this.getCampoNumero().setText("");
		this.getCampoComplemento().setText("");
		this.getCampoBairro().setText("");
		this.getCampoCidade().setText("");
		this.getCampoCEP().setText("");
		this.getComboBoxEstado().setValue("");
		this.getCampoCelularUm().setText("");
		this.getCampoCelularDois().setText("");
		this.getCampoTelTrabalho().setText("");
		this.getCampoTelResidencial().setText("");
		this.getCampoDataAniversario().getEditor().setText("");
		this.getCampoDataBatismo().getEditor().setText("");
		this.getCampoEmail().setText("");
		this.getComboBoxCargoEclesiastico().setValue("");
		this.getComboBoxCelula().setValue("");
		this.getComboBoxTurmaEscolaBiblica().setValue("");

	}

	@FXML
	public void alterarMembroAcao() {
		Membro membro = new Membro();
		Membro membroRetornoBanco = new Membro();

		MembroModelo membroModeloAlterar = this.getTabelaMembros().getSelectionModel().getSelectedItem();

		membro.setNomeCompleto(membroModeloAlterar.getColunaNome());

		membroRetornoBanco = this.getMembroDAO().pesquisarMembro("Nome Completo", membro).get(0);

		this.montarAlterarMembro(membroRetornoBanco);

	}

	public void montarAlterarMembro(Membro m) {

		this.getComboBoxCargoEclesiastico().setValue(m.getCargoEclesiastico());
		this.getComboBoxCelula().setValue(m.getCelula().getNomeCelula());
		this.getComboBoxEstado().setValue(m.getEndereco().getUf());
		this.getComboBoxTurmaEscolaBiblica().setValue(m.getTurmaEscolaBiblica().getNomeTurma());

		this.getCampoBairro().setText(m.getEndereco().getBairro());
		this.getCampoCelularDois().setText(m.getNumeroCelularDois());
		this.getCampoCelularUm().setText(m.getNumeroCelularUm());
		this.getCampoCEP().setText(m.getEndereco().getCEP());
		this.getCampoCidade().setText(m.getEndereco().getCidade());
		this.getCampoComplemento().setText(m.getEndereco().getComplemento());
		this.getCampoDataAniversario().getEditor().setText(m.getDataAniversarioStr());
		this.getCampoDataBatismo().getEditor().setText(m.getDataBatismoStr());
		this.getCampoEmail().setText(m.getEmail());
		this.getCampoNomeCompleto().setText(m.getNomeCompleto());
		this.getCampoNumero().setText(String.valueOf(m.getEndereco().getNumero()));
		this.getCampoRua().setText(m.getEndereco().getLogradouro());
		this.getCampoTelResidencial().setText(m.getNumeroFixoResidencial());
		this.getCampoTelResidencial().setText(m.getNumeroFixoTrabalho());

		this.getMembroAlterar().setId(m.getId());
	}

	public void montarMembro() {

		this.getEndereco().setLogradouro(this.getCampoRua().getText());
		this.getEndereco().setNumero(Integer.parseInt(this.getCampoNumero().getText()));
		this.getEndereco().setComplemento(this.getCampoComplemento().getText());
		this.getEndereco().setBairro(this.getCampoBairro().getText());
		this.getEndereco().setCidade(this.getCampoCidade().getText());

		if (this.getComboBoxEstado().getValue() != null) {
			this.getEndereco().setUf(this.getComboBoxEstado().getValue());
		}

		this.getEndereco().setCEP(this.getCampoCEP().getText());

		this.getMembro().setEndereco(this.getEndereco());

		this.getMembro().setNomeCompleto(this.getCampoNomeCompleto().getText());
		this.getMembro().setEmail(this.getCampoEmail().getText());
		this.getMembro().setNumeroCelularUm(this.getCampoCelularUm().getText());
		this.getMembro().setNumeroCelularDois(this.getCampoCelularDois().getText());
		this.getMembro().setNumeroFixoTrabalho(this.getCampoTelResidencial().getText());
		this.getMembro().setNumeroFixoTrabalho(this.getCampoTelTrabalho().getText());

		if (this.getCampoDataAniversario().getEditor().getText() != "") {
			try {
				this.getMembro().setDataAniversario(DateUtils.parseToCalendar(this.getCampoDataAniversario().getEditor().getText(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN, false, false));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (this.getCampoDataBatismo().getEditor().getText() != "") {
			try {
				this.getMembro().setDataBatismo(DateUtils.parseToCalendar(this.getCampoDataBatismo().getEditor().getText(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN, false, false));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (this.getComboBoxCargoEclesiastico().getValue() != null) {
			this.getMembro().setCargoEclesiastico(this.getComboBoxCargoEclesiastico().getValue());
		}

		if (this.getComboBoxCelula().getValue() != null) {
			this.getMembro().setCelula(this.buscarCelulaPorNome());
		}

		if (this.getComboBoxTurmaEscolaBiblica().getValue() != null) {
			this.getMembro().setTurmaEscolaBiblica(this.buscarTurmaEscolaBiblicaPorNome());
		}
	}

	public Celula buscarCelulaPorNome() {
		Celula celulaRetornoBanco = this.getCelulaDAO().buscarCelulaPorNome(this.getComboBoxCelula().getValue());

		return celulaRetornoBanco;
	}

	public TurmaEscolaBiblica buscarTurmaEscolaBiblicaPorNome() {

		TurmaEscolaBiblica turmaRetorno = this.getTurmaEscolaBiblicaDAO().buscarTurmaEscolaBiblicaPorNome(this.getComboBoxTurmaEscolaBiblica().getValue());

		return turmaRetorno;
	}

	public boolean isCampoNomeValido() {

		boolean retorno = false;

		if (this.getCampoNomeCompleto().getText().isEmpty()) {
			this.getCampoNomeCompleto().setStyle("-fx-text-box-border: red;");
		} else {
			retorno = true;
		}

		return retorno;
	}

	public boolean isCamposValidos() {
		boolean retorno = true;

		if (this.getCampoNomeCompleto().getText().isEmpty()) {
			retorno = false;
		}

		if (this.getCampoRua().getText().isEmpty()) {
			retorno = false;
		}

		if (this.getCampoNumero().getText().isEmpty()) {
			retorno = false;
		}

		if (this.getCampoDataAniversario().getEditor().getText() == null) {
			retorno = false;
		}

		if (this.getComboBoxCargoEclesiastico().getValue() == null) {
			retorno = false;
		}

		if (this.getComboBoxEstado().getValue() == null) {
			retorno = false;
		}

		return retorno;
	}

	private void formatarCamposInvalidos() {

		if (this.getCampoNomeCompleto().getText().isEmpty()) {
			this.getCampoNomeCompleto().setStyle("-fx-text-box-border: red;");
		}

		if (this.getCampoRua().getText().isEmpty()) {
			this.getCampoRua().setStyle("-fx-text-box-border: red;");
		}

		if (this.getCampoNumero().getText().isEmpty()) {
			this.getCampoNumero().setStyle("-fx-text-box-border: red;");
		}

		if (this.getCampoDataAniversario().getValue() == null) {
			this.getCampoDataAniversario().setStyle("-fx-background-color: red;");
		}

		if (this.getComboBoxCargoEclesiastico().getValue() == null) {
			this.getComboBoxCargoEclesiastico().setStyle("-fx-background-color: red;");
		}

		if (this.getComboBoxEstado().getValue() == null) {
			this.getComboBoxEstado().setStyle("-fx-background-color: red;");
		}

	}

	public void retonarFormatacao() {
		this.getCampoNomeCompleto().setStyle("-fx-text-box-border: transparent;");
		this.getCampoRua().setStyle("-fx-text-box-border: transparent;");
		this.getCampoNumero().setStyle("-fx-text-box-border: transparent;");
		this.getCampoDataAniversario().setStyle("-fx-text-box-border: transparent;");
		this.getComboBoxEstado().setStyle("-fx-background-color: transparent;");
	}

	@Override
	public void fecharTela() {

	}

	@Override
	public void start(Stage stage) throws Exception {

		Pane telaPrincipal = FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/FXMLTabMembroCadastrar.fxml"));

		Scene scene = new Scene(telaPrincipal, 1280, 700);

		stage.setResizable(false);

		stage.getIcons().add(new Image("/imagens/icone.png"));

		this.carregarListas();

		stage.setScene(scene);

		stage.show();

	}

	public List<Membro> pesquisarListaMembro() {

		List<Membro> listaMembroRetornoBanco = new ArrayList<>();

		Membro membro = new Membro();

		if (this.getComboBoxPesquisarPor().getValue() != null) {
			listaMembroRetornoBanco = this.getMembroDAO().pesquisarMembro(this.getComboBoxPesquisarPor().getValue().toString(), membro);
		} else {
			listaMembroRetornoBanco = this.getMembroDAO().pesquisarMembro("", membro);
		}

		return listaMembroRetornoBanco;
	}

	private void carregarListas() {
		// Lista de Siglas de Estados
		ObservableList<String> listaEstados = FXCollections.observableArrayList("MG", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "PA", "PB", "PR", "PE", "PI", "RR", "RO", "RJ", "RN", "RS", "SC", "SP", "SE", "TO");

		if (listaEstados != null) {
			this.getComboBoxEstado().setItems(listaEstados);
		}

		// Lista de Cargo Eclesástico
		ObservableList<String> listaCargoEclesiastico = FXCollections.observableArrayList("", "Presidente", "Vice-Presidente", "Membro", "Não Membro", "1º Tesoureiro", "2º Tesoureiro", "1º Secretário",
			"2º Secretário", "Coordenador de Célula", "Auxiliar de Célula");

		if (listaCargoEclesiastico != null) {
			this.getComboBoxCargoEclesiastico().setItems(listaCargoEclesiastico);
		}

		// LISTA CELULA
		ObservableList<String> listaCelula = FXCollections.observableArrayList(this.buscarListaCelula());

		if (listaCelula != null) {
			this.getComboBoxCelula().setItems(listaCelula);
		}

		// LISTA TURMA ESCOLA BIBLICA
		ObservableList<String> listaTurmaEscolaBiblica = FXCollections.observableArrayList(this.buscarListaTurmaEscolaBiblica());
		if (listaTurmaEscolaBiblica != null) {
			this.getComboBoxTurmaEscolaBiblica().setItems(listaTurmaEscolaBiblica);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		this.carregarListas();

		this.getCampoCEP().textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				MascarasFX.mascaraCEP(FXMLTabMembroCadastrarController.this.getCampoCEP());
			}

		});

		this.getCampoCelularUm().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				MascarasFX.mascaraCelular(FXMLTabMembroCadastrarController.this.getCampoCelularUm());
			}
		});

		this.getCampoCelularDois().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				MascarasFX.mascaraCelular(FXMLTabMembroCadastrarController.this.getCampoCelularDois());
			}
		});

		this.getCampoTelTrabalho().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				MascarasFX.mascaraTelefoneFixo(FXMLTabMembroCadastrarController.this.getCampoTelTrabalho());
			}
		});

		this.getCampoTelResidencial().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				MascarasFX.mascaraTelefoneFixo(FXMLTabMembroCadastrarController.this.getCampoTelResidencial());
			}
		});
	}

	public List<String> buscarListaCelula() {
		List<Celula> listaCelulaBanco = new ArrayList<>();
		List<String> listaCelulaString = new ArrayList<>();

		listaCelulaBanco = new CelulaDAO().pesquisarTodasCelulas();

		listaCelulaString.add("");

		for (Celula c : listaCelulaBanco) {
			listaCelulaString.add(c.getNomeCelula());
		}

		return listaCelulaString;
	}

	public List<String> buscarListaTurmaEscolaBiblica() {
		List<TurmaEscolaBiblica> listaTurma = new ArrayList<>();
		List<String> listaTurmaString = new ArrayList<>();

		listaTurma = new TurmaEscolaBiblicaDAO().pesquisarTodasTurmas();

		listaTurmaString.add("");

		for (TurmaEscolaBiblica turma : listaTurma) {
			listaTurmaString.add(turma.getNomeTurma());
		}

		return listaTurmaString;
	}

	public TextField getCampoNomeCompleto() {
		if (this.campoNomeCompleto == null) {
			this.campoNomeCompleto = new TextField();
		}
		return this.campoNomeCompleto;
	}

	public void setCampoNomeCompleto(TextField campoNomeCompleto) {
		this.campoNomeCompleto = campoNomeCompleto;
	}

	public TextField getCampoRua() {
		if (this.campoRua == null) {
			this.campoRua = new TextField();
		}
		return this.campoRua;
	}

	public void setCampoRua(TextField campoRua) {
		this.campoRua = campoRua;
	}

	public TextField getCampoComplemento() {
		if (this.campoComplemento == null) {
			this.campoComplemento = new TextField();
		}
		return this.campoComplemento;
	}

	public void setCampoComplemento(TextField campoComplemento) {
		this.campoComplemento = campoComplemento;
	}

	public TextField getCampoNumero() {
		if (this.campoNumero == null) {
			this.campoNumero = new TextField();
		}
		return this.campoNumero;
	}

	public void setCampoNumero(TextField campoNumero) {
		this.campoNumero = campoNumero;
	}

	public TextField getCampoBairro() {
		if (this.campoBairro == null) {
			this.campoBairro = new TextField();
		}
		return this.campoBairro;
	}

	public void setCampoBairro(TextField campoBairro) {
		this.campoBairro = campoBairro;
	}

	public TextField getCampoCidade() {
		if (this.campoCidade == null) {
			this.campoCidade = new TextField();
		}
		return this.campoCidade;
	}

	public void setCampoCidade(TextField campoCidade) {
		this.campoCidade = campoCidade;
	}

	public TextField getCampoEmail() {
		if (this.campoEmail == null) {
			this.campoEmail = new TextField();
		}
		return this.campoEmail;
	}

	public void setCampoEmail(TextField campoEmail) {
		this.campoEmail = campoEmail;
	}

	public TextField getCampoCelularUm() {
		if (this.campoCelularUm == null) {
			this.campoCelularUm = new TextField();
		}
		return this.campoCelularUm;
	}

	public void setCampoCelularUm(TextField campoCelularUm) {
		this.campoCelularUm = campoCelularUm;
	}

	public TextField getCampoCelularDois() {
		if (this.campoCelularDois == null) {
			this.campoCelularDois = new TextField();
		}
		return this.campoCelularDois;
	}

	public void setCampoCelularDois(TextField campoCelularDois) {
		this.campoCelularDois = campoCelularDois;
	}

	public TextField getCampoTelResidencial() {
		if (this.campoTelResidencial == null) {
			this.campoTelResidencial = new TextField();
		}
		return this.campoTelResidencial;
	}

	public void setCampoTelResidencial(TextField campoTelResidencial) {
		this.campoTelResidencial = campoTelResidencial;
	}

	public TextField getCampoTelTrabalho() {
		if (this.campoTelTrabalho == null) {
			this.campoTelTrabalho = new TextField();
		}
		return this.campoTelTrabalho;
	}

	public void setCampoTelTrabalho(TextField campoTelTrabalho) {
		this.campoTelTrabalho = campoTelTrabalho;
	}

	public DatePicker getCampoDataAniversario() {
		if (this.campoDataAniversario == null) {
			this.campoDataAniversario = new DatePicker();
		}
		return this.campoDataAniversario;
	}

	public void setCampoDataAniversario(DatePicker campoDataAniversario) {
		this.campoDataAniversario = campoDataAniversario;
	}

	public DatePicker getCampoDataBatismo() {
		if (this.campoDataBatismo == null) {
			this.campoDataBatismo = new DatePicker();
		}
		return this.campoDataBatismo;
	}

	public void setCampoDataBatismo(DatePicker campoDataBatismo) {
		this.campoDataBatismo = campoDataBatismo;
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

	public TextField getCampoCEP() {
		if (this.campoCEP == null) {
			this.campoCEP = new TextField();
		}
		return this.campoCEP;
	}

	public void setCampoCEP(TextField campoCEP) {
		this.campoCEP = campoCEP;
	}

	public Label getBotaoMembroCadastrar() {
		if (this.botaoMembroCadastrar == null) {
			this.botaoMembroCadastrar = new Label();
		}
		return this.botaoMembroCadastrar;
	}

	public void setBotaoMembroCadastrar(Label botaoMembroCadastrar) {
		this.botaoMembroCadastrar = botaoMembroCadastrar;
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

	public ComboBox<String> getComboBoxCargoEclesiastico() {
		if (this.comboBoxCargoEclesiastico == null) {
			this.comboBoxCargoEclesiastico = new ComboBox<>();
		}
		return this.comboBoxCargoEclesiastico;
	}

	public void setComboBoxCargoEclesiastico(ComboBox<String> comboBoxCargoEclesiastico) {
		this.comboBoxCargoEclesiastico = comboBoxCargoEclesiastico;
	}

	public ComboBox<String> getComboBoxCelula() {
		if (this.comboBoxCelula == null) {
			this.comboBoxCelula = new ComboBox<>();
		}
		return this.comboBoxCelula;
	}

	public void setComboBoxCelula(ComboBox<String> comboBoxCelula) {
		this.comboBoxCelula = comboBoxCelula;
	}

	public ComboBox<String> getComboBoxTurmaEscolaBiblica() {
		if (this.comboBoxTurmaEscolaBiblica == null) {
			this.comboBoxTurmaEscolaBiblica = new ComboBox<>();
		}
		return this.comboBoxTurmaEscolaBiblica;
	}

	public void setComboBoxTurmaEscolaBiblica(ComboBox<String> comboBoxTurmaEscolaBiblica) {
		this.comboBoxTurmaEscolaBiblica = comboBoxTurmaEscolaBiblica;
	}

	public ComboBox<String> getComboBoxEstado() {
		if (this.comboBoxEstado == null) {
			this.comboBoxEstado = new ComboBox<>();
		}
		return this.comboBoxEstado;
	}

	public void setComboBoxEstado(ComboBox<String> comboBoxEstado) {
		this.comboBoxEstado = comboBoxEstado;
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

	public Membro getMembroAlterar() {
		if (this.membroAlterar == null) {
			this.membroAlterar = new Membro();
		}

		return this.membroAlterar;
	}

	public void setMembroAlterar(Membro membro) {
		this.membroAlterar = membro;
	}

	public Celula getCelula() {
		if (this.celula == null) {
			this.celula = new Celula();
		}

		return this.celula;
	}

	public void setCelula(Celula celula) {
		this.celula = celula;
	}

	public Endereco getEndereco() {
		if (this.endereco == null) {
			this.endereco = new Endereco();
		}
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
