package br.com.izri.aservico.controller.tesouraria;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.itextpdf.text.DocumentException;

import br.com.izri.aservico.auxiliar.Constantes;
import br.com.izri.aservico.auxiliar.PopupDialog;
import br.com.izri.aservico.controller.base.ControllerBase;
import br.com.izri.aservico.controller.pdf.CriarPDF;
import br.com.izri.aservico.controller.session.UsuarioSessao;
import br.com.izri.aservico.model.entity.Dizimo;
import br.com.izri.aservico.model.entity.Membro;
import br.com.izri.aservico.model.entity.Oferta;
import br.com.izri.aservico.model.entity.TransacaoSaida;
import br.com.izri.aservico.model.table.DizimoModelo;
import br.com.izri.aservico.model.table.OfertaModelo;
import br.com.izri.aservico.model.table.TransacaoSaidaModelo;
import br.com.izri.aservico.utils.DateUtils;
import br.com.izri.aservico.utils.MascarasFX;
import br.com.izri.aservico.utils.StringUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLTabTesourariaConsultarController extends ControllerBase implements Initializable {

	private static FXMLTabTesourariaConsultarController instance;

	public static FXMLTabTesourariaConsultarController getInstance() {
		if (FXMLTabTesourariaConsultarController.instance == null) {
			FXMLTabTesourariaConsultarController.instance = new FXMLTabTesourariaConsultarController();
		}

		return FXMLTabTesourariaConsultarController.instance;
	}

	public FXMLTabTesourariaConsultarController() {
		System.out.println("FXMLTabTesourariaController()");
	}

	@FXML
	private ComboBox<String> comboBoxTipoMovimentacao;
	@FXML
	private ComboBox<String> comboBoxNomeMembro;
	@FXML
	private DatePicker campoDataMovimentacao;
	@FXML
	private Label labelNomeMembro;
	@FXML
	private Label labelObservacao;
	@FXML
	private TextArea campoObservacao;

	@FXML
	private TextField campoValor;
	@FXML
	private Button botaoGerarRelatorio;
	@FXML
	private ComboBox<String> comboBoxSelMovimentConsulta;
	@FXML
	private ComboBox<String> comboBoxSelTipoConsulta;
	@FXML
	private DatePicker campoPesquisaDataUm;
	@FXML
	private DatePicker campoPesquisaDataDois;
	@FXML
	private ComboBox<String> comboBoxNomeMembroConsulta;
	@FXML
	private Label idLabelNomeMembroConsulta;
	@FXML
	private Button idBotaoExcluir;
	@FXML
	private Label idLabelDizimoOferta;
	@FXML
	private Label idLabelValorEntradaPesquisada;
	@FXML
	private Label idLabelValorSaidasPesquisadas;
	@FXML
	private Label idLabelValorTotalCaixa;
	@FXML
	private Label idLabelTotalSaidaConsolidada;
	@FXML
	private Label idLabelTotalEntradaConsolidada;
	@FXML
	private Label idLabelValorOfertaConsolidada;
	@FXML
	private Label idLabelValorDizimoConsolidado;
	@FXML
	private Label idLogoInicio;

	// TABELA DÍZIMO
	@FXML
	private TableView<DizimoModelo> idTabelaDizimo;
	@FXML
	private TableColumn<DizimoModelo, String> colunaDizimoId;
	@FXML
	private TableColumn<DizimoModelo, String> colunaDizimoValor;
	@FXML
	private TableColumn<DizimoModelo, String> colunaDizimoData;
	@FXML
	private TableColumn<DizimoModelo, String> colunaDizimoObservacao;
	@FXML
	private TableColumn<DizimoModelo, String> colunaDizimoCadastradoPor;
	@FXML
	private TableColumn<DizimoModelo, String> colunaDizimoNomeMembro;

	// TABELA OFERTA
	@FXML
	private TableView<OfertaModelo> idTabelaOFerta;
	@FXML
	private TableColumn<OfertaModelo, String> colunaOfertaId;
	@FXML
	private TableColumn<OfertaModelo, String> colunaOfertaValor;
	@FXML
	private TableColumn<OfertaModelo, String> colunaOfertaData;
	@FXML
	private TableColumn<OfertaModelo, String> colunaOfertaObservacao;
	@FXML
	private TableColumn<OfertaModelo, String> colunaOfertaCadastradoPor;

	// TABELA SAÍDA
	@FXML
	private TableView<TransacaoSaidaModelo> idTabelaSaida;
	@FXML
	private TableColumn<TransacaoSaidaModelo, String> colunaTransacaoId;
	@FXML
	private TableColumn<TransacaoSaidaModelo, String> colunaTransacaoValor;
	@FXML
	private TableColumn<TransacaoSaidaModelo, String> colunaTransacaoData;
	@FXML
	private TableColumn<TransacaoSaidaModelo, String> colunaTransacaoCadastradoPor;
	@FXML
	private TableColumn<TransacaoSaidaModelo, String> colunaTransacaoObservacao;

	@FXML
	private Label idBotaoInicio;

	@FXML
	private Parent idTabInicio;

	private List<Dizimo> listaDizimo;
	private List<Oferta> listaOferta;

	private static String VARIOS = "Vários";

	public enum MES_BANCO {
		JANUARY("JANUARY"), FEBRUARY("FEBRUARY"), MARCH("MARCH"), APRIL("APRIL"), MAY("MAY"), JUNE("JUNE"), JULY("JULY"), AUGUST("AUGUST"), SEPTEMBER("SEPTEMBER"), OCTOBER("OCTOBER"), NOVEMBER("NOVEMBER"), DECEMBER("DECEMBER");

		private final String mesBanco;

		private MES_BANCO(String mesBanco) {
			this.mesBanco = mesBanco;
		}

	}

	public enum MES {
		JANEIRO("Janeiro"), FEVEREIRO("Fevereiro"), MARCO("Março"), ABRIL("Abril"), MAIO("Maio"), JUNHO("Junho"), JULHO("Julho"), AGOSTO("Agosto"), SETEMBRO("Setembro"), OUTUBRO("Outubro"), NOVEMBRO("Novembro"), DEZEMBRO("Dezembro");

		private final String mes;

		private MES(String mes) {
			this.mes = mes;
		}

		public String getValor() {
			return this.mes;
		}
	}

	public Parent getIdTabInicio() {
		if (this.idTabInicio == null) {
			this.idTabInicio = new Parent() {
			};
		}
		return this.idTabInicio;
	}

	private boolean isValoresTabelaValidos() {
		boolean retorno = true;

		if (this.getListaDizimo().isEmpty() && this.getListaOferta().isEmpty()) {
			retorno = false;
		}
		return retorno;
	}

	public String getMes() {

		String mesRetorno = "";
		String mes = this.getCampoPesquisaDataUm().getValue().getMonth().toString();

		int campoData = this.getCampoPesquisaDataUm().getValue().getMonth().compareTo(this.getCampoPesquisaDataDois().getValue().getMonth());

		if (campoData == 0) {
			if (mes == MES_BANCO.JANUARY.toString()) {
				mesRetorno = MES.JANEIRO.getValor();
			} else if (mes == MES_BANCO.FEBRUARY.toString()) {
				mesRetorno = MES.FEVEREIRO.getValor();
			} else if (mes == MES_BANCO.MARCH.toString()) {
				mesRetorno = MES.MARCO.getValor();
			} else if (mes == MES_BANCO.APRIL.toString()) {
				mesRetorno = MES.ABRIL.getValor();
			} else if (mes == MES_BANCO.MAY.toString()) {
				mesRetorno = MES.MAIO.getValor();
			} else if (mes == MES_BANCO.JUNE.toString()) {
				mesRetorno = MES.JUNHO.getValor();
			} else if (mes == MES_BANCO.JULY.toString()) {
				mesRetorno = MES.JUNHO.getValor();
			} else if (mes == MES_BANCO.AUGUST.toString()) {
				mesRetorno = MES.AGOSTO.getValor();
			} else if (mes == MES_BANCO.SEPTEMBER.toString()) {
				mesRetorno = MES.SETEMBRO.getValor();
			} else if (mes == MES_BANCO.OCTOBER.toString()) {
				mesRetorno = MES.OUTUBRO.getValor();
			} else if (mes == MES_BANCO.NOVEMBER.toString()) {
				mesRetorno = MES.NOVEMBRO.getValor();
			} else if (mes == MES_BANCO.DECEMBER.toString()) {
				mesRetorno = MES.DEZEMBRO.getValor();
			}
		} else {
			mesRetorno = FXMLTabTesourariaConsultarController.VARIOS;
		}

		return mesRetorno;
	}

	private boolean isMesmoAnoDizimo() {
		boolean retorno = false;

		String primeiroAno = DateUtils.format(this.getListaDizimo().get(0).getDataEntradaDizimo(), DateUtils.PATTERN.YEAR);
		String segundoAno = DateUtils.format(this.getListaDizimo().get(this.getListaDizimo().size() - 1).getDataEntradaDizimo(), DateUtils.PATTERN.YEAR);

		if (primeiroAno == segundoAno) {
			retorno = true;
		}

		return retorno;
	}

	private boolean isMesmoAnoOferta() {
		boolean retorno = false;

		String primeiroAno = DateUtils.format(this.getListaOferta().get(0).getDataEntradaOferta(), DateUtils.PATTERN.YEAR);
		String segundoAno = DateUtils.format(this.getListaOferta().get(this.getListaOferta().size() - 1).getDataEntradaOferta(), DateUtils.PATTERN.YEAR);

		if (primeiroAno == segundoAno) {
			retorno = true;
		}

		return retorno;
	}

	// IMPLEMENTAR
	private String getAno() {

		int ultimoItemDizimo = this.getListaDizimo().lastIndexOf(this.getListaDizimo());
		String retorno = "";

		if (this.isMesmoAnoDizimo()) {
			retorno = String.valueOf(this.getCampoPesquisaDataUm().getValue().getYear());
		} else {
			retorno = FXMLTabTesourariaConsultarController.VARIOS;
		}

		// int campoDataUm = this.getCampoPesquisaDataUm().getValue().getYear();
		// int campoDataDois =
		// this.getCampoPesquisaDataDois().getValue().getYear();
		//
		// if (campoDataUm == campoDataDois) {
		// retorno = String.valueOf(campoDataUm);
		// } else {
		// }

		return retorno;
	}

	private void gerarRelatorioPorTipo() {
		if (this.getComboBoxSelTipoConsulta().getValue().toString().equals("Dízimo")) {
			try {
				new CriarPDF().gerarRelatorioTesourariaDizimo(this.getAno(), this.getMes(), this.getListaDizimo());
			} catch (DocumentException | IOException | ParseException e) {
				e.printStackTrace();
			}
		} else if (this.getComboBoxSelTipoConsulta().getValue().toString().equals("Oferta")) {
			try {
				new CriarPDF().gerarRelatorioTesourariaOferta(this.getAno(), this.getMes(), this.getListaOferta());
			} catch (DocumentException | IOException | ParseException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void botaoGerarRelatorioAcao(ActionEvent event) {

		if (this.isValoresTabelaValidos()) {
			this.gerarRelatorioPorTipo();
			PopupDialog.getInstance().exibirPopupSucesso("Relatório gerado com sucesso!", "Relatório gerado com sucesso");
		} else {
			PopupDialog.getInstance().exibirPopupErro("Não há nenhum informação na tabela. O relatório não será gerado.", "Lista vazia");
		}
	}

	private List<TransacaoSaida> buscarTransacaoSaida() {
		List<TransacaoSaida> transacaoSaida = null;
		System.out.println("@@@ buscarTransacaoSaida");
		if (DateUtils.isDatasValidas(this.getCampoPesquisaDataUm().getEditor().getText(), this.getCampoPesquisaDataDois().getEditor().getText())) {
			System.out.println("@@@ Datas válidas");
			if (this.getCampoPesquisaDataUm().getEditor().getText().isEmpty()) {
				transacaoSaida = new ArrayList<>();
				transacaoSaida = this.getTransacaoSaidaDAO().buscarTodos();
			} else {
				transacaoSaida = new ArrayList<>();
				transacaoSaida = this.getTransacaoSaidaDAO().buscarPorData(this.getCampoPesquisaDataUm().getEditor().getText(), this.getCampoPesquisaDataDois().getEditor().getText());
			}
		} else {
			this.getCampoPesquisaDataDois().setStyle("-fx-background-color: red;");
			this.getCampoPesquisaDataUm().setStyle("-fx-background-color: red;");
			PopupDialog.getInstance().exibirPopupErro("As datas estão inválidas. Por favor, verifique.", "Data inválidas");
		}

		return transacaoSaida;
	}

	public List<TransacaoSaidaModelo> converterParaTransacaoSaidaModelo(List<TransacaoSaida> listaTransacao) {
		List<TransacaoSaidaModelo> listaTSM = new ArrayList<>();
		TransacaoSaidaModelo tsm = null;

		for (TransacaoSaida ts : listaTransacao) {
			tsm = new TransacaoSaidaModelo(ts.getId(), NumberFormat.getCurrencyInstance().format(ts.getValor()), ts.getDataSaidaStr(), ts.getUsuario().getNomeCompleto(), ts.getObservacao());
			listaTSM.add(tsm);
		}

		return listaTSM;
	}

	public void carregarTabelaSaida(List<TransacaoSaidaModelo> tsm) {
		ObservableList<TransacaoSaidaModelo> observableList = FXCollections.observableArrayList(tsm);

		this.getColunaTransacaoCadastradoPor().setCellValueFactory(new PropertyValueFactory<>("colunaTransacaoCadastradoPor"));
		this.getColunaTransacaoData().setCellValueFactory(new PropertyValueFactory<>("colunaTransacaoData"));
		this.getColunaTransacaoObservacao().setCellValueFactory(new PropertyValueFactory<>("colunaTransacaoObservacao"));
		this.getColunaTransacaoValor().setCellValueFactory(new PropertyValueFactory<>("colunaTransacaoValor"));

		this.getIdTabelaSaida().setItems(observableList);
	}

	public Oferta montarTransacaoOferta() {
		Oferta oferta = null;

		if (this.isCamposOfertaValidos()) {
			oferta = new Oferta();

			oferta.setDataEntradaOferta(DateUtils.parseToCalendar(this.getCampoDataMovimentacao().getEditor().getText(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN, false, false));
			oferta.setObservacao(this.getCampoObservacao().getText());
			oferta.setUsuario(UsuarioSessao.getInstance().getUsuario());
			oferta.setValor(Double.parseDouble(this.getCampoValor().getText().replaceAll("\\.", "").replaceAll(",", ".")));

		} else {
			PopupDialog.getInstance().exibirPopupErro("Existem campos obrigatórios não preenchidos. Por favor, verifique.", "Campos em branco");
		}

		return oferta;

	}

	public List<OfertaModelo> converterParaOfertaModelo(List<Oferta> listaOferta) {
		List<OfertaModelo> listaOfertaModelo = new ArrayList<>();
		OfertaModelo ofertaModelo = null;

		// Preechendo listaOferta para ser usada ao gerar o relatório em PDF.
		this.setListaOferta(listaOferta);

		for (Oferta o : listaOferta) {
			ofertaModelo = new OfertaModelo(o.getId(), NumberFormat.getCurrencyInstance().format(o.getValor()), o.getDataEntradaOfertaStr(), o.getObservacao(), o.getUsuario().getNomeCompleto());
			listaOfertaModelo.add(ofertaModelo);
		}

		return listaOfertaModelo;
	}

	public void carregarTabelaOferta(List<OfertaModelo> listaOfertaModelo) {
		ObservableList<OfertaModelo> observableList = FXCollections.observableArrayList(listaOfertaModelo);

		this.getColunaOfertaCadastradoPor().setCellValueFactory(new PropertyValueFactory<>("colunaOfertaCadastradoPor"));
		this.getColunaOfertaData().setCellValueFactory(new PropertyValueFactory<>("colunaOfertaData"));
		this.getColunaOfertaObservacao().setCellValueFactory(new PropertyValueFactory<>("colunaOfertaObservacao"));
		this.getColunaOfertaValor().setCellValueFactory(new PropertyValueFactory<>("colunaOfertaValor"));

		this.getIdTabelaOFerta().setItems(observableList);
	}

	public double getValorTransacaoSaidaConsolidado() {
		double saida = 0;

		List<TransacaoSaida> listaSaida = null;

		try {
			listaSaida = this.getTransacaoSaidaDAO().buscarTodos();

			for (TransacaoSaida ts : listaSaida) {
				saida = saida + ts.getValor();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return saida;
	}

	public double getValorOfertaConsolidado() {
		double oferta = 0;
		List<Oferta> listaOferta = null;

		try {
			listaOferta = this.getOfertaDAO().buscarTodasOfertas();

			for (Oferta o : listaOferta) {
				oferta = oferta + o.getValor();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oferta;
	}

	private boolean isCamposOfertaValidos() {
		boolean verificador = true;

		if (this.getCampoDataMovimentacao().getEditor().getText().isEmpty()) {
			verificador = false;
			this.getCampoDataMovimentacao().setStyle("-fx-background-color: red;");
		}

		if (this.getCampoValor().getText().isEmpty()) {
			verificador = false;
			this.getCampoValor().setStyle("-fx-text-box-border: red;");
		}

		if (this.getCampoObservacao().getText().isEmpty()) {
			verificador = false;
			this.getCampoObservacao().setStyle("-fx-text-box-border: red;");
		}

		return verificador;
	}

	public Dizimo montarTransacaoDizimo() {
		Dizimo dizimo = null;
		Membro membro = null;

		if (this.isCamposDizimoValidos()) {
			dizimo = new Dizimo();
			membro = new Membro();

			membro.setNomeCompleto(this.getComboBoxNomeMembro().getEditor().getText());

			dizimo.setDataEntradaDizimo(DateUtils.parseToCalendar(this.getCampoDataMovimentacao().getEditor().getText(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN, false, false));
			dizimo.setMembro(this.getMembroDAO().pesquisarMembro("Nome Completo", membro).get(0));
			dizimo.setObservacao(this.getCampoObservacao().getText());
			dizimo.setUsuario(UsuarioSessao.getInstance().getUsuario());
			dizimo.setValor(Double.parseDouble(this.getCampoValor().getText().replaceAll("\\.", "").replaceAll(",", ".")));
		} else {
			PopupDialog.getInstance().exibirPopupErro("Existem campos obrigatórios em branco. Por favor, verifique.", "Campos em branco");
		}

		return dizimo;
	}

	private boolean isCamposDizimoValidos() {
		boolean verificador = true;

		if (this.getComboBoxNomeMembro().getValue().isEmpty()) {
			verificador = false;
			this.getComboBoxNomeMembro().setStyle("-fx-background-color: red;");
		}

		if (this.getCampoDataMovimentacao().getEditor().getText().isEmpty()) {
			verificador = false;
			this.getCampoDataMovimentacao().setStyle("-fx-background-color: red;");
		}

		if (this.getCampoValor().getText().isEmpty()) {
			verificador = false;
			this.getCampoValor().setStyle("-fx-text-box-border: red;");
		}

		if (this.getCampoObservacao().getText().isEmpty()) {
			verificador = false;
			this.getCampoObservacao().setStyle("-fx-text-box-border: red;");
		}

		return verificador;
	}

	public List<DizimoModelo> converterParaDizimoModelo(List<Dizimo> listaDizimo) {
		List<DizimoModelo> listaDizimoModelo = new ArrayList<>();
		DizimoModelo dizimoModelo = null;

		// Preenche a listaDizimo para ser usada para montar o relatório em PDF.
		this.setListaDizimo(listaDizimo);
		for (Dizimo d : listaDizimo) {
			dizimoModelo = new DizimoModelo(d.getId(), d.getDataEntradaDizimoStr(), NumberFormat.getCurrencyInstance().format(d.getValor()), d.getObservacao(), d.getUsuario().getNomeCompleto(), d.getMembro().getNomeCompleto());
			listaDizimoModelo.add(dizimoModelo);
		}

		return listaDizimoModelo;
	}

	public void carregarTabelaDizimo(List<DizimoModelo> listaDizimoModelo) {
		ObservableList<DizimoModelo> observableList = FXCollections.observableList(listaDizimoModelo);

		this.getColunaDizimoCadastradoPor().setCellValueFactory(new PropertyValueFactory<>("colunaDizimoCadastradoPor"));
		this.getColunaDizimoData().setCellValueFactory(new PropertyValueFactory<>("colunaDizimoData"));
		this.getColunaDizimoNomeMembro().setCellValueFactory(new PropertyValueFactory<>("colunaDizimoNomeMembro"));
		this.getColunaDizimoObservacao().setCellValueFactory(new PropertyValueFactory<>("colunaDizimoObservacao"));
		this.getColunaDizimoValor().setCellValueFactory(new PropertyValueFactory<>("colunaDizimoValor"));

		this.getIdTabelaDizimo().setItems(observableList);
	}

	public double getValorDizimoConsolidado() {
		double dizimo = 0;
		List<Dizimo> listaDizimo = null;

		try {
			listaDizimo = this.getDizimoDAO().pesquisarTodosDizimos();

			for (Dizimo d : listaDizimo) {
				dizimo = dizimo + d.getValor();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dizimo;
	}

	@FXML
	public void botaoPesquisarAcao(ActionEvent event) {
		this.getIdTabInicio().setVisible(false);
		this.executarPesquisa();
		this.atualizarMovimentoFinanceiro();
	}

	private void executarPesquisa() {
		this.verificarTipoPesquisa();
	}

	private void verificarTipoPesquisa() {
		List<Dizimo> listaDizimoRetorno = null;
		List<Oferta> listaOfertaRetorno = null;
		List<TransacaoSaida> listaTS = null;

		if (this.isCampoSelMovimentacaoTipoValidos()) {
			if ((this.getComboBoxSelMovimentConsulta().getValue() == "Saída") && this.getCampoPesquisaDataUm().getEditor().getText().isEmpty()) {
				listaTS = this.getTransacaoSaidaDAO().buscarTodos();
				this.carregarTabelaSaida(this.converterParaTransacaoSaidaModelo(listaTS));

				this.getIdLabelValorSaidasPesquisadas().setText(NumberFormat.getCurrencyInstance().format(this.calcularTransacaoSaidaPesquisada(listaTS)));
			} else if ((this.getComboBoxSelMovimentConsulta().getValue() == "Saída") && StringUtils.isNotEmpty(this.getCampoPesquisaDataUm().getEditor().getText())) {
				this.carregarTabelaSaida(this.converterParaTransacaoSaidaModelo(this.getTransacaoSaidaDAO().buscarPorData(this.getCampoPesquisaDataUm().getEditor().getText(), this.getCampoPesquisaDataDois().getEditor().getText())));
			} else if ((this.getComboBoxSelMovimentConsulta().getValue() == "Entrada")) {
				if ((this.getComboBoxSelTipoConsulta().getValue() == "Dízimo")) {
					listaDizimoRetorno = this.getDizimoDAO().pesquisarPorIntervaloDataPorNomeMembro(this.getCampoPesquisaDataUm().getEditor().getText(), this.getCampoPesquisaDataDois().getEditor().getText(), this.getComboBoxNomeMembroConsulta().getValue());
					this.carregarTabelaDizimo(this.converterParaDizimoModelo(listaDizimoRetorno));
					this.getIdLabelValorEntradaPesquisada().setText(NumberFormat.getCurrencyInstance().format(this.calcularDizimoPesquisado(listaDizimoRetorno)));
				} else if (this.getComboBoxSelTipoConsulta().getValue() == "Oferta") {
					listaOfertaRetorno = this.getOfertaDAO().pesquisarListaTodosIntervaloData(this.getCampoPesquisaDataUm().getEditor().getText(), this.getCampoPesquisaDataDois().getEditor().getText());
					this.carregarTabelaOferta(this.converterParaOfertaModelo(listaOfertaRetorno));

					this.getIdLabelValorEntradaPesquisada().setText(NumberFormat.getCurrencyInstance().format(this.calcularOfertaPesquisada(listaOfertaRetorno)));
				} else {

				}
			}
		}
	}

	private double calcularTransacaoSaidaPesquisada(List<TransacaoSaida> ListaTS) {
		double saida = 0;

		for (TransacaoSaida ts : ListaTS) {
			saida = saida + ts.getValor();
		}

		return saida;
	}

	private double calcularOfertaPesquisada(List<Oferta> listaOferta) {
		double oferta = 0;

		for (Oferta o : listaOferta) {
			oferta = oferta + o.getValor();
		}
		return oferta;
	}

	private double calcularDizimoPesquisado(List<Dizimo> listaDizimo) {
		double dizimo = 0;

		for (Dizimo o : listaDizimo) {
			dizimo = dizimo + o.getValor();
		}

		return dizimo;
	}

	private boolean isCampoSelMovimentacaoTipoValidos() {
		boolean verificador = true;

		if ((this.getComboBoxSelMovimentConsulta().getValue() == null) && (this.getComboBoxSelTipoConsulta().getValue() == null)) {
			PopupDialog.getInstance().exibirPopupErro("Selecione a MOVIMENTAÇÃO e o TIPO DE MOVIMENTAÇÃO.", "Selecione a movimentação");
			this.getComboBoxSelMovimentConsulta().setStyle("-fx-background-color: red;");
			this.getComboBoxSelTipoConsulta().setStyle("-fx-background-color: red;");
			verificador = false;
		} else if ((this.getComboBoxSelMovimentConsulta().getValue() == "Entrada") && (this.getComboBoxSelTipoConsulta().getValue() == null)) {
			PopupDialog.getInstance().exibirPopupErro("Selecione o TIPO DE MOVIMENTAÇÃO.", "Selecione o tipo de movimentação");
			this.getComboBoxSelTipoConsulta().setStyle("-fx-background-color: red;");
		}

		return verificador;
	}

	public boolean isCamposDatasPesquisaValidos() {
		boolean retorno = true;

		if ((this.getCampoPesquisaDataUm().getEditor().getText() == "") && (this.getCampoPesquisaDataDois().getEditor().getText() != "")) {
			retorno = false;
		} else {
			if (DateUtils.isDatasValidas(this.getCampoPesquisaDataUm().getEditor().getText(), this.getCampoPesquisaDataDois().getEditor().getText())) {
			} else {
				retorno = false;
			}
		}

		return retorno;
	}

	@FXML
	public void selecionarLinhaTabelaOnMouseClicked(MouseEvent event) {
		this.getIdBotaoExcluir().setDisable(false);
	}

	@FXML
	public void botaoExcluirAcao(ActionEvent event) {
		this.excluirItem();
		PopupDialog.getInstance().exibirPopupInformacao("Item excluído com sucesso.", "Item Excluido");
	}

	private void excluirItem() {
		int id = 0;

		if (this.getComboBoxSelMovimentConsulta().getValue() == "Entrada") {
			if (this.getComboBoxSelTipoConsulta().getValue() == "Dízimo") {
				id = this.getIdTabelaDizimo().getSelectionModel().getSelectedItem().getColunaDizimoId();
				this.getDizimoDAO().exclusaoLogicaPorId(id);
			} else if (this.getComboBoxSelTipoConsulta().getValue() == "Oferta") {
				id = this.getIdTabelaOFerta().getSelectionModel().getSelectedItem().getColunaOfertaId();
				this.getOfertaDAO().exclusaoLogicaPorId(id);
			}
		} else if (this.getComboBoxSelMovimentConsulta().getValue() == "Saída") {
			id = this.getIdTabelaSaida().getSelectionModel().getSelectedItem().getColunaTransacaoId();
			this.getTransacaoSaidaDAO().exclusaoLogica(id);
		}
	}

	private void limparCampos() {
		this.getCampoValor().setText("");
		this.getCampoObservacao().setText("");
		this.getCampoDataMovimentacao().getEditor().setText(DateUtils.format(Calendar.getInstance(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN));
		this.getComboBoxNomeMembro().setValue("");
	}

	private TransacaoSaida montarTransacaoSaida() {
		TransacaoSaida saida = new TransacaoSaida();

		if (this.isCamposSaidaValidos()) {
			saida.setDataSaida(DateUtils.parseToCalendar(this.getCampoDataMovimentacao().getEditor().getText(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN, false, false));
			saida.setObservacao(this.getCampoObservacao().getText());
			saida.setUsuario(UsuarioSessao.getInstance().getUsuario());
			saida.setValor(Double.parseDouble(this.getCampoValor().getText().replaceAll("\\.", "").replaceAll(",", ".")));
		} else {
			PopupDialog.getInstance().exibirPopupErro("Existem campos obrigatórios em branco. Por favor, verifique!", "Existem campos em branco");
		}

		return saida;
	}

	private boolean isCamposSaidaValidos() {
		boolean retorno = true;

		if (this.getCampoDataMovimentacao().getEditor().getText().isEmpty()) {
			retorno = false;
			this.getCampoDataMovimentacao().setStyle("-fx-background-color: red;");
		}

		if (this.getCampoObservacao().getText().trim().isEmpty()) {
			retorno = false;
			this.getCampoObservacao().setStyle("-fx-text-box-border: red;");
		}

		if (this.getCampoValor().getText().trim().isEmpty()) {
			retorno = false;
			this.getCampoValor().setStyle("-fx-text-box-border: red;");
		}

		return retorno;
	}

	@FXML
	public void comboBoxSelMovimentConsultaAcao(ActionEvent event) {
		if (this.getComboBoxSelMovimentConsulta().getValue().equals("Saída")) {
			this.getIdTabelaDizimo().setVisible(false);
			this.getIdTabelaOFerta().setVisible(false);
			this.getIdTabelaSaida().setVisible(true);
			this.getIdLabelNomeMembroConsulta().setVisible(false);
			this.getComboBoxNomeMembroConsulta().setVisible(false);
			this.getComboBoxSelTipoConsulta().setDisable(true);
		} else if (this.getComboBoxSelMovimentConsulta().getValue().equals("Entrada")) {
			this.getComboBoxSelTipoConsulta().setDisable(false);

			if (this.getComboBoxSelTipoConsulta().getValue() != null) {
				if (this.getComboBoxSelTipoConsulta().getValue() == "Dízimo") {
					this.getIdTabelaDizimo().setVisible(true);
					this.getIdTabelaOFerta().setVisible(false);
					this.getIdTabelaSaida().setVisible(false);
					this.getIdLabelNomeMembroConsulta().setVisible(true);
					this.getComboBoxNomeMembroConsulta().setVisible(true);

					this.getIdLabelDizimoOferta().setText("Dízimo:");
				} else if (this.getComboBoxSelTipoConsulta().getValue() == "Oferta") {
					this.getIdTabelaOFerta().setVisible(true);
					this.getIdTabelaDizimo().setVisible(false);
					this.getIdTabelaSaida().setVisible(false);
					this.getIdLabelNomeMembroConsulta().setVisible(false);
					this.getComboBoxNomeMembroConsulta().setVisible(false);

					this.getIdLabelDizimoOferta().setText("Oferta:");
				}

			}
		}
	}

	@FXML
	public void comboBoxSelTipoConsultaAcao(ActionEvent event) {
		if (this.getComboBoxSelTipoConsulta().getValue() == "Dízimo") {
			this.getIdTabelaDizimo().setVisible(true);
			this.getIdTabelaOFerta().setVisible(false);
			this.getIdTabelaSaida().setVisible(false);
			this.getIdLabelNomeMembroConsulta().setVisible(true);
			this.getComboBoxNomeMembroConsulta().setVisible(true);

			this.getIdLabelDizimoOferta().setText("Dízimo:");
			this.buscarNomesMembros();
		} else if (this.getComboBoxSelTipoConsulta().getValue() == "Oferta") {
			this.getIdTabelaOFerta().setVisible(true);
			this.getIdTabelaDizimo().setVisible(false);
			this.getIdTabelaSaida().setVisible(false);
			this.getIdLabelNomeMembroConsulta().setVisible(false);
			this.getComboBoxNomeMembroConsulta().setVisible(false);

			this.getIdLabelDizimoOferta().setText("Oferta:");
		}
	}

	public void buscarNomesMembros() {
		List<Membro> listaMembros = new ArrayList<>();
		List<String> listaNomeMembros = new ArrayList<>();

		listaMembros = this.getMembroDAO().pesquisarMembro(Constantes.PESQUISAR_TODOS, null);

		for (Membro m : listaMembros) {
			System.out.println(m.getNomeCompleto());
			listaNomeMembros.add(m.getNomeCompleto());
		}

		ObservableList<String> listaComboBoxNomeMembros = FXCollections.observableArrayList(listaNomeMembros);
		this.getComboBoxNomeMembro().setItems(listaComboBoxNomeMembros);
		this.getComboBoxNomeMembroConsulta().setItems(listaComboBoxNomeMembros);
	}

	private void carregarListas() {
		ObservableList<String> listaTipoMovimentacao = FXCollections.observableArrayList("", "Entrada", "Saída");
		this.getComboBoxSelMovimentConsulta().setItems(listaTipoMovimentacao);

		ObservableList<String> listaSelecionarTipo = FXCollections.observableArrayList("", "Dízimo", "Oferta");
		this.getComboBoxSelTipoConsulta().setItems(listaSelecionarTipo);

		this.buscarNomesMembros();
	}

	private void inserirDataCampoDataMovimentacao() {
		this.getCampoDataMovimentacao().getEditor().setText(DateUtils.format(Calendar.getInstance(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN));
	}

	private void atualizarMovimentoFinanceiro() {
		this.getIdLabelValorDizimoConsolidado().setText(NumberFormat.getCurrencyInstance().format(this.getValorDizimoConsolidado()));
		this.getIdLabelValorOfertaConsolidada().setText(NumberFormat.getCurrencyInstance().format(this.getValorOfertaConsolidado()));
		this.getIdLabelTotalSaidaConsolidada().setText(NumberFormat.getCurrencyInstance().format(this.getValorTransacaoSaidaConsolidado()));
		this.getIdLabelValorTotalCaixa().setText(NumberFormat.getCurrencyInstance().format((this.getValorDizimoConsolidado() + this.getValorOfertaConsolidado()) - this.getValorTransacaoSaidaConsolidado()));
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {

		double valorTotal = this.getValorDizimoConsolidado() + this.getValorOfertaConsolidado();
		this.getIdLabelTotalEntradaConsolidada().setText(NumberFormat.getCurrencyInstance().format(valorTotal));

		this.getCampoValor().textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				MascarasFX.mascaraReal(FXMLTabTesourariaConsultarController.this.getCampoValor());
			}
		});

		this.getCampoObservacao().textProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				FXMLTabTesourariaConsultarController.this.getCampoObservacao().setScrollTop(Double.MIN_VALUE);
			}
		});

		this.carregarListas();
		this.inserirDataCampoDataMovimentacao();
		this.configurarAutoCompleteCampoNomeMembro();

		this.atualizarMovimentoFinanceiro();

	}

	private void configurarAutoCompleteCampoNomeMembro() {
		TextFields.bindAutoCompletion(this.getComboBoxNomeMembro().getEditor(), this.getComboBoxNomeMembro().getItems());
	}

	@Override
	public void fecharTela() {

	}

	@Override
	public void start(Stage stage) throws Exception {

	}

	@FXML
	public void campoPesquisaDataUmAcao(ActionEvent event) {
		this.getCampoPesquisaDataDois().getEditor().setText(this.getCampoPesquisaDataUm().getEditor().getText());
	}

	public ComboBox<String> getComboBoxTipoMovimentacao() {
		if (this.comboBoxTipoMovimentacao == null) {
			this.comboBoxTipoMovimentacao = new ComboBox<>();
		}
		return this.comboBoxTipoMovimentacao;
	}

	public void setComboBoxTipoMovimentacao(ComboBox<String> comboBoxTipoMovimentacao) {
		this.comboBoxTipoMovimentacao = comboBoxTipoMovimentacao;
	}

	public ComboBox<String> getComboBoxNomeMembro() {
		if (this.comboBoxNomeMembro == null) {
			this.comboBoxNomeMembro = new ComboBox<>();
		}
		return this.comboBoxNomeMembro;
	}

	public void setComboBoxNomeMembro(ComboBox<String> comboBoxNomeMembro) {
		this.comboBoxNomeMembro = comboBoxNomeMembro;
	}

	public DatePicker getCampoDataMovimentacao() {
		if (this.campoDataMovimentacao == null) {
			this.campoDataMovimentacao = new DatePicker();
		}
		return this.campoDataMovimentacao;
	}

	public void setCampoDataMovimentacao(DatePicker campoDataMovimentacao) {
		this.campoDataMovimentacao = campoDataMovimentacao;
	}

	public Label getLabelNomeMembro() {
		if (this.labelNomeMembro == null) {
			this.labelNomeMembro = new Label();
		}
		return this.labelNomeMembro;
	}

	public void setLabelNomeMembro(Label labelNomeMembro) {
		this.labelNomeMembro = labelNomeMembro;
	}

	public Label getLabelObservacao() {
		if (this.labelObservacao == null) {
			this.labelObservacao = new Label();
		}
		return this.labelObservacao;
	}

	public void setLabelObservacao(Label labelObservacao) {
		this.labelObservacao = labelObservacao;
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

	public TextField getCampoValor() {
		if (this.campoValor == null) {
			this.campoValor = new TextField();
		}
		return this.campoValor;
	}

	public void setCampoValor(TextField campoValor) {
		this.campoValor = campoValor;
	}

	public ComboBox<String> getComboBoxSelMovimentConsulta() {
		if (this.comboBoxSelMovimentConsulta == null) {
			this.comboBoxSelMovimentConsulta = new ComboBox<>();
		}
		return this.comboBoxSelMovimentConsulta;
	}

	public void setComboBoxSelMovimentConsulta(ComboBox<String> comboBoxSelMovimentConsulta) {
		this.comboBoxSelMovimentConsulta = comboBoxSelMovimentConsulta;
	}

	public ComboBox<String> getComboBoxSelTipoConsulta() {
		if (this.comboBoxSelTipoConsulta == null) {
			this.comboBoxSelTipoConsulta = new ComboBox<>();
		}
		return this.comboBoxSelTipoConsulta;
	}

	public void setComboBoxSelTipoConsulta(ComboBox<String> comboBoxSelTipoConsulta) {
		this.comboBoxSelTipoConsulta = comboBoxSelTipoConsulta;
	}

	public DatePicker getCampoPesquisaDataUm() {
		if (this.campoPesquisaDataUm == null) {
			this.campoPesquisaDataUm = new DatePicker();
		}
		return this.campoPesquisaDataUm;
	}

	public void setCampoPesquisaDataUm(DatePicker campoPesquisaDataUm) {
		this.campoPesquisaDataUm = campoPesquisaDataUm;
	}

	public DatePicker getCampoPesquisaDataDois() {
		if (this.campoPesquisaDataDois == null) {
			this.campoPesquisaDataDois = new DatePicker();
		}
		return this.campoPesquisaDataDois;
	}

	public void setCampoPesquisaDataDois(DatePicker campoPesquisaDataDois) {
		this.campoPesquisaDataDois = campoPesquisaDataDois;
	}

	public ComboBox<String> getComboBoxNomeMembroConsulta() {
		if (this.comboBoxNomeMembroConsulta == null) {
			this.comboBoxNomeMembroConsulta = new ComboBox<>();
		}
		return this.comboBoxNomeMembroConsulta;
	}

	public void setComboBoxNomeMembroConsulta(ComboBox<String> comboBoxNomeMembroConsulta) {
		this.comboBoxNomeMembroConsulta = comboBoxNomeMembroConsulta;
	}

	public Label getIdLabelNomeMembroConsulta() {
		if (this.idLabelNomeMembroConsulta == null) {
			this.idLabelNomeMembroConsulta = new Label();
		}
		return this.idLabelNomeMembroConsulta;
	}

	public void setIdLabelNomeMembroConsulta(Label idLabelNomeMembroConsulta) {
		this.idLabelNomeMembroConsulta = idLabelNomeMembroConsulta;
	}

	public Button getIdBotaoExcluir() {
		if (this.idBotaoExcluir == null) {
			this.idBotaoExcluir = new Button();
		}
		return this.idBotaoExcluir;
	}

	public void setIdBotaoExcluir(Button idBotaoExcluir) {
		this.idBotaoExcluir = idBotaoExcluir;
	}

	public Label getIdLabelValorEntradaPesquisada() {
		if (this.idLabelValorEntradaPesquisada == null) {
			this.idLabelValorEntradaPesquisada = new Label();
		}
		return this.idLabelValorEntradaPesquisada;
	}

	public void setIdLabelValorEntradaPesquisada(Label idLabelValorEntradaPesquisada) {
		this.idLabelValorEntradaPesquisada = idLabelValorEntradaPesquisada;
	}

	public Label getIdLabelValorSaidasPesquisadas() {
		if (this.idLabelValorSaidasPesquisadas == null) {
			this.idLabelValorSaidasPesquisadas = new Label();
		}
		return this.idLabelValorSaidasPesquisadas;
	}

	public void setIdLabelValorSaidasPesquisadas(Label idLabelValorSaidasPesquisadas) {
		this.idLabelValorSaidasPesquisadas = idLabelValorSaidasPesquisadas;
	}

	public Label getIdLabelValorTotalCaixa() {
		if (this.idLabelValorTotalCaixa == null) {
			this.idLabelValorTotalCaixa = new Label();
		}
		return this.idLabelValorTotalCaixa;
	}

	public void setIdLabelValorTotalCaixa(Label idLabelValorTotalCaixa) {
		this.idLabelValorTotalCaixa = idLabelValorTotalCaixa;
	}

	public Label getIdLabelTotalSaidaConsolidada() {
		if (this.idLabelTotalSaidaConsolidada == null) {
			this.idLabelTotalSaidaConsolidada = new Label();
		}
		return this.idLabelTotalSaidaConsolidada;
	}

	public void setIdLabelTotalSaidaConsolidada(Label idLabelTotalSaidaConsolidada) {
		this.idLabelTotalSaidaConsolidada = idLabelTotalSaidaConsolidada;
	}

	public Label getIdLabelTotalEntradaConsolidada() {
		if (this.idLabelTotalEntradaConsolidada == null) {
			this.idLabelTotalEntradaConsolidada = new Label();
		}
		return this.idLabelTotalEntradaConsolidada;
	}

	public void setIdLabelTotalEntradaConsolidada(Label idLabelTotalEntradaConsolidada) {
		this.idLabelTotalEntradaConsolidada = idLabelTotalEntradaConsolidada;
	}

	public Label getIdLabelValorOfertaConsolidada() {
		if (this.idLabelValorOfertaConsolidada == null) {
			this.idLabelValorOfertaConsolidada = new Label();
		}
		return this.idLabelValorOfertaConsolidada;
	}

	public void setIdLabelValorOfertaConsolidada(Label idLabelValorOfertaConsolidada) {
		this.idLabelValorOfertaConsolidada = idLabelValorOfertaConsolidada;
	}

	public Label getIdLabelValorDizimoConsolidado() {
		if (this.idLabelValorDizimoConsolidado == null) {
			this.idLabelValorDizimoConsolidado = new Label();
		}
		return this.idLabelValorDizimoConsolidado;
	}

	public void setIdLabelValorDizimoConsolidado(Label idLabelValorDizimoConsolidado) {
		this.idLabelValorDizimoConsolidado = idLabelValorDizimoConsolidado;
	}

	public Label getIdLogoInicio() {
		if (this.idLogoInicio == null) {
			this.idLogoInicio = new Label();
		}
		return this.idLogoInicio;
	}

	public void setIdLogoInicio(Label idLogoInicio) {
		this.idLogoInicio = idLogoInicio;
	}

	public TableView<DizimoModelo> getIdTabelaDizimo() {
		if (this.idTabelaDizimo == null) {
			this.idTabelaDizimo = new TableView<>();
		}
		return this.idTabelaDizimo;
	}

	public void setIdTabelaDizimo(TableView<DizimoModelo> idTabelaDizimo) {
		this.idTabelaDizimo = idTabelaDizimo;
	}

	public TableColumn<DizimoModelo, String> getColunaDizimoId() {
		if (this.colunaDizimoId == null) {
			this.colunaDizimoId = new TableColumn<>();
		}
		return this.colunaDizimoId;
	}

	public void setColunaDizimoId(TableColumn<DizimoModelo, String> colunaDizimoId) {
		this.colunaDizimoId = colunaDizimoId;
	}

	public TableColumn<DizimoModelo, String> getColunaDizimoValor() {
		if (this.colunaDizimoValor == null) {
			this.colunaDizimoValor = new TableColumn<>();
		}
		return this.colunaDizimoValor;
	}

	public void setColunaDizimoValor(TableColumn<DizimoModelo, String> colunaDizimoValor) {
		this.colunaDizimoValor = colunaDizimoValor;
	}

	public TableColumn<DizimoModelo, String> getColunaDizimoData() {
		if (this.colunaDizimoData == null) {
			this.colunaDizimoData = new TableColumn<>();

		}
		return this.colunaDizimoData;
	}

	public void setColunaDizimoData(TableColumn<DizimoModelo, String> colunaDizimoData) {
		this.colunaDizimoData = colunaDizimoData;
	}

	public TableColumn<DizimoModelo, String> getColunaDizimoObservacao() {
		if (this.colunaDizimoObservacao == null) {
			this.colunaDizimoObservacao = new TableColumn<>();
		}
		return this.colunaDizimoObservacao;
	}

	public void setColunaDizimoObservacao(TableColumn<DizimoModelo, String> colunaDizimoObservacao) {
		this.colunaDizimoObservacao = colunaDizimoObservacao;
	}

	public TableColumn<DizimoModelo, String> getColunaDizimoCadastradoPor() {
		if (this.colunaDizimoCadastradoPor == null) {
			this.colunaDizimoCadastradoPor = new TableColumn<>();
		}
		return this.colunaDizimoCadastradoPor;
	}

	public void setColunaDizimoCadastradoPor(TableColumn<DizimoModelo, String> colunaDizimoCadastradoPor) {
		this.colunaDizimoCadastradoPor = colunaDizimoCadastradoPor;
	}

	public TableColumn<DizimoModelo, String> getColunaDizimoNomeMembro() {
		if (this.colunaDizimoNomeMembro == null) {
			this.colunaDizimoNomeMembro = new TableColumn<>();
		}
		return this.colunaDizimoNomeMembro;
	}

	public void setColunaDizimoNomeMembro(TableColumn<DizimoModelo, String> colunaDizimoNomeMembro) {
		this.colunaDizimoNomeMembro = colunaDizimoNomeMembro;
	}

	public TableView<OfertaModelo> getIdTabelaOFerta() {
		if (this.idTabelaOFerta == null) {
			this.idTabelaOFerta = new TableView<>();
		}
		return this.idTabelaOFerta;
	}

	public void setIdTabelaOFerta(TableView<OfertaModelo> idTabelaOFerta) {
		this.idTabelaOFerta = idTabelaOFerta;
	}

	public TableColumn<OfertaModelo, String> getColunaOfertaId() {
		if (this.colunaOfertaId == null) {
			this.colunaOfertaId = new TableColumn<>();
		}
		return this.colunaOfertaId;
	}

	public void setColunaOfertaId(TableColumn<OfertaModelo, String> colunaOfertaId) {
		this.colunaOfertaId = colunaOfertaId;
	}

	public TableColumn<OfertaModelo, String> getColunaOfertaValor() {
		if (this.colunaOfertaValor == null) {
			this.colunaOfertaValor = new TableColumn<>();
		}
		return this.colunaOfertaValor;
	}

	public void setColunaOfertaValor(TableColumn<OfertaModelo, String> colunaOfertaValor) {
		this.colunaOfertaValor = colunaOfertaValor;
	}

	public TableColumn<OfertaModelo, String> getColunaOfertaData() {
		if (this.colunaOfertaData == null) {
			this.colunaOfertaData = new TableColumn<>();
		}
		return this.colunaOfertaData;
	}

	public void setColunaOfertaData(TableColumn<OfertaModelo, String> colunaOfertaData) {
		this.colunaOfertaData = colunaOfertaData;
	}

	public TableColumn<OfertaModelo, String> getColunaOfertaObservacao() {
		if (this.colunaOfertaObservacao == null) {
			this.colunaOfertaObservacao = new TableColumn<>();
		}
		return this.colunaOfertaObservacao;
	}

	public void setColunaOfertaObservacao(TableColumn<OfertaModelo, String> colunaOfertaObservacao) {
		this.colunaOfertaObservacao = colunaOfertaObservacao;
	}

	public TableColumn<OfertaModelo, String> getColunaOfertaCadastradoPor() {
		if (this.colunaOfertaCadastradoPor == null) {
			this.colunaOfertaCadastradoPor = new TableColumn<>();
		}
		return this.colunaOfertaCadastradoPor;
	}

	public void setColunaOfertaCadastradoPor(TableColumn<OfertaModelo, String> colunaOfertaCadastradoPor) {
		this.colunaOfertaCadastradoPor = colunaOfertaCadastradoPor;
	}

	public TableView<TransacaoSaidaModelo> getIdTabelaSaida() {
		if (this.idTabelaSaida == null) {
			this.idTabelaSaida = new TableView<>();
		}
		return this.idTabelaSaida;
	}

	public void setIdTabelaSaida(TableView<TransacaoSaidaModelo> idTabelaSaida) {
		this.idTabelaSaida = idTabelaSaida;
	}

	public TableColumn<TransacaoSaidaModelo, String> getColunaTransacaoId() {
		if (this.colunaTransacaoId == null) {
			this.colunaTransacaoId = new TableColumn<>();
		}
		return this.colunaTransacaoId;
	}

	public void setColunaTransacaoId(TableColumn<TransacaoSaidaModelo, String> colunaTransacaoId) {
		this.colunaTransacaoId = colunaTransacaoId;
	}

	public TableColumn<TransacaoSaidaModelo, String> getColunaTransacaoValor() {
		if (this.colunaTransacaoValor == null) {
			this.colunaTransacaoValor = new TableColumn<>();
		}
		return this.colunaTransacaoValor;
	}

	public void setColunaTransacaoValor(TableColumn<TransacaoSaidaModelo, String> colunaTransacaoValor) {
		this.colunaTransacaoValor = colunaTransacaoValor;
	}

	public TableColumn<TransacaoSaidaModelo, String> getColunaTransacaoData() {
		if (this.colunaTransacaoData == null) {
			this.colunaTransacaoData = new TableColumn<>();
		}
		return this.colunaTransacaoData;
	}

	public void setColunaTransacaoData(TableColumn<TransacaoSaidaModelo, String> colunaTransacaoData) {
		this.colunaTransacaoData = colunaTransacaoData;
	}

	public TableColumn<TransacaoSaidaModelo, String> getColunaTransacaoCadastradoPor() {
		if (this.colunaTransacaoCadastradoPor == null) {
			this.colunaTransacaoCadastradoPor = new TableColumn<>();
		}
		return this.colunaTransacaoCadastradoPor;
	}

	public void setColunaTransacaoCadastradoPor(TableColumn<TransacaoSaidaModelo, String> colunaTransacaoCadastradoPor) {
		this.colunaTransacaoCadastradoPor = colunaTransacaoCadastradoPor;
	}

	public TableColumn<TransacaoSaidaModelo, String> getColunaTransacaoObservacao() {
		if (this.colunaTransacaoObservacao == null) {
			this.colunaTransacaoObservacao = new TableColumn<>();
		}
		return this.colunaTransacaoObservacao;
	}

	public void setColunaTransacaoObservacao(TableColumn<TransacaoSaidaModelo, String> colunaTransacaoObservacao) {
		this.colunaTransacaoObservacao = colunaTransacaoObservacao;
	}

	public Label getIdLabelDizimoOferta() {
		if (this.idLabelDizimoOferta == null) {
			this.idLabelDizimoOferta = new Label();
		}
		return this.idLabelDizimoOferta;
	}

	public void setIdLabelDizimoOferta(Label idLabelDizimoOferta) {
		this.idLabelDizimoOferta = idLabelDizimoOferta;
	}

	public Label getIdBotaoInicio() {
		if (this.idBotaoInicio == null) {
			this.idBotaoInicio = new Label();
		}
		return this.idBotaoInicio;
	}

	public void setIdBotaoInicio(Label idBotaoInicio) {
		this.idBotaoInicio = idBotaoInicio;
	}

	public void setIdTabInicio(Parent idTabInicio) {
		this.idTabInicio = idTabInicio;
	}

	public Button getBotaoGerarRelatorio() {
		if (this.botaoGerarRelatorio == null) {
			this.botaoGerarRelatorio = new Button();
		}
		return this.botaoGerarRelatorio;
	}

	public void setBotaoGerarRelatorio(Button botaoGerarRelatorio) {
		this.botaoGerarRelatorio = botaoGerarRelatorio;
	}

	public List<Dizimo> getListaDizimo() {
		if (this.listaDizimo == null) {
			this.listaDizimo = new ArrayList<>();
		}
		return this.listaDizimo;
	}

	public void setListaDizimo(List<Dizimo> listaDizimo) {
		this.listaDizimo = listaDizimo;
	}

	public List<Oferta> getListaOferta() {
		if (this.listaOferta == null) {
			this.listaOferta = new ArrayList<>();
		}
		return this.listaOferta;
	}

	public void setListaOferta(List<Oferta> listaOferta) {
		this.listaOferta = listaOferta;
	}
}
