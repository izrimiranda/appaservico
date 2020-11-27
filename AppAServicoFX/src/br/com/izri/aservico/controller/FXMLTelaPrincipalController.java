package br.com.izri.aservico.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.izri.aservico.auxiliar.Labels;
import br.com.izri.aservico.controller.base.ControllerBase;
import br.com.izri.aservico.model.entity.Usuario;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLTelaPrincipalController extends ControllerBase implements Initializable {

	public FXMLTelaPrincipalController() {
		System.out.println("FXMLTelaPrincipalController()");
	}

	@FXML
	private MenuItem idSubMenuCadastrarEntradaSaida;
	@FXML
	private MenuItem idSubMenuConsultarEntradaSaida;
	@FXML
	private MenuItem idSubMenuRelatorios;
	@FXML
	private MenuItem idSubMenuSobre;
	@FXML
	private MenuItem idSubMenuSair;
	@FXML
	private AnchorPane idTelaPrincipal;
	@FXML
	protected TabPane idPainelPrincipal;
	@FXML
	private Pane idPanePrincipal;
	@FXML
	private ImageView imageViewBotaoInicio;
	@FXML
	private ImageView imageViewBotaoMembros;
	@FXML
	private ImageView imageViewBotaoSecretaria;
	@FXML
	private ImageView imageViewBotaoTesouraria;
	@FXML
	private ImageView imageViewBotaoSistema;
	@FXML
	private ImageView imageViewBotaoSair;
	@FXML
	private Label idLogoPequena;
	@FXML
	private AnchorPane idTelaPrincipalCorpo;
	@FXML
	private Label botaoMembroCadastrar;
	@FXML
	private Label botaoMembroConsultar;
	@FXML
	private Label botaoSecretariaCadastrar;
	@FXML
	private Label botaoSecretariaConsultar;
	@FXML
	private Label botaoTesouriaCadastrar;
	@FXML
	private Label botaoTesouriaConsultar;
	@FXML
	private ImageView imageViewBotaoMembrosCadastrar;
	@FXML
	private ImageView imageViewBotaoMembroConsultar;
	@FXML
	private ImageView imageViewBotaoSecretariaCadastrar;
	@FXML
	private ImageView imageViewBotaoSecretariaConsultar;
	@FXML
	private ImageView imageViewBotaoTesourariaCadastrar;
	@FXML
	private ImageView imageViewBotaoTesourariaConsultar;

	public static Stage stage;

	private boolean controleBotaoInicio;
	private boolean controleBotaoMembros;
	private boolean controleBotaoSecretaria;
	private boolean controleBotaoTesouraria;
	private boolean controleBotaoSistema;

	private boolean abaMembroCadastrarAtiva;
	private boolean abaMembroConsultarAtiva;
	private boolean subBotoesMembroAtivo;

	private boolean abaSecretariaCadastrarAtiva;
	private boolean abaSecretariaConsultarAtiva;
	private boolean subBotoesSecretariaAtivo;

	private boolean abaTesourariaCadastrarAtiva;
	private boolean abaTesourariaConsultarAtiva;
	private boolean subBotoesTesourariaAtivo;

	private boolean abaAtiva;

	private void esconderBotoes() {
		if (this.isSubBotoesMembroAtivo()) {
			this.setBotaoFadeOut(this.getBotaoMembroCadastrar());
			this.setBotaoFadeOut(this.getBotaoMembroConsultar());

			this.setSubBotoesMembroAtivo(false);
		}

		if (this.isSubBotoesSecretariaAtivo()) {
			this.setBotaoFadeOut(this.getBotaoSecretariaCadastrar());
			this.setBotaoFadeOut(this.getBotaoSecretariaConsultar());

			this.setSubBotoesSecretariaAtivo(false);
		}

		if (this.isSubBotoesTesourariaAtivo()) {
			this.setBotaoFadeOut(this.getBotaoTesouriaCadastrar());
			this.setBotaoFadeOut(this.getBotaoTesouriaConsultar());

			this.setSubBotoesTesourariaAtivo(false);
		}
	}

	@FXML
	public void painelMouseMoved(MouseEvent event) {
		this.esconderBotoes();
	}

	/**
	 * Verifica quais dos controladores de aba estão ativos, uma vez que seu
	 * estado esteja "true" é feita a troca da imagem do botao para
	 * "pressionado" antes de exibilo.
	 */
	private void setBotaoPressedOrNot() {
		if (this.isAbaMembroCadastrarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosCadastrarPressed(this.getImageViewBotaoMembrosCadastrar());
		} else if (this.isAbaMembroConsultarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosConsultarPressed(this.getImageViewBotaoMembroConsultar());
		} else if (this.isAbaSecretariaCadastrarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaCadastrarPressed(this.getImageViewBotaoSecretariaCadastrar());
		} else if (this.isAbaSecretariaConsultarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaConsultarPressed(this.getImageViewBotaoSecretariaConsultar());
		} else if (this.isAbaTesourariaCadastrarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaCadastrarPressed(this.getImageViewBotaoTesourariaCadastrar());
		} else if (this.isAbaTesourariaConsultarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaConsultarPressed(this.getImageViewBotaoTesourariaConsultar());
		}
	}

	/**
	 * Passa o valor dos controladores das abas para "false" ou "true".
	 *
	 */
	private void setFalseOrTrueControlesSubBotoes(boolean membroCadastrarTrueOrFalse, boolean membroConsultarTrueOrFalse, boolean secretariaCadastrarTrueOrFalse, boolean secretariaConsultarTrueOrFalse, boolean tesourariaCadastrarTrueOrFalse, boolean tesourariaConsultarTrueOrFalse) {
		this.setAbaMembroCadastrarAtiva(membroCadastrarTrueOrFalse);
		this.setAbaMembroConsultarAtiva(membroConsultarTrueOrFalse);

		this.setAbaSecretariaCadastrarAtiva(secretariaCadastrarTrueOrFalse);
		this.setAbaSecretariaConsultarAtiva(secretariaConsultarTrueOrFalse);

		this.setAbaTesourariaCadastrarAtiva(tesourariaCadastrarTrueOrFalse);
		this.setAbaTesourariaConsultarAtiva(tesourariaConsultarTrueOrFalse);
	}

	@FXML
	public void botaoTesourariaConsultarMouseExited(MouseEvent event) {
		if (!this.isAbaTesourariaConsultarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaConsultarNormal(this.getImageViewBotaoTesourariaConsultar());
		}
	}

	@FXML
	public void botaoTesourariaConsultarMouseEntered(MouseEvent event) {
		if (!this.isAbaTesourariaConsultarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaConsultarMouseOver(this.getImageViewBotaoTesourariaConsultar());
		}
	}

	@FXML
	public void botaoTesourariaConsultarMouseClicked(MouseEvent event) {
		this.carregarTabTesourariaConsultar();

		this.setFalseOrTrueControlesSubBotoes(false, false, false, false, false, true);

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaConsultarPressed(this.getImageViewBotaoTesourariaConsultar());

		this.ativarBotoesNormais(true, true, true, true, true, false);

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioNormal(this.getImageViewBotaoInicio());
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSistemaNormal(this.getImageViewBotaoSistema());
	}

	@FXML
	public void botaoTesourariaCadastrarMouseExited(MouseEvent event) {
		if (!this.isAbaTesourariaCadastrarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaCadastrarNormal(this.getImageViewBotaoTesourariaCadastrar());
		}
	}

	@FXML
	public void botaoTesourariaCadastrarMouseEntered(MouseEvent event) {

		if (!this.isAbaTesourariaCadastrarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaCadastrarMouseOver(this.getImageViewBotaoTesourariaCadastrar());
		}
	}

	@FXML
	public void botaoTesourariaCadastrarMouseClicked(MouseEvent event) {
		this.carregarTabTesourariaCadastrar();

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaCadastrarPressed(this.getImageViewBotaoTesourariaCadastrar());

		this.ativarBotoesNormais(true, true, true, true, false, true);

		this.setFalseOrTrueControlesSubBotoes(false, false, false, false, true, false);
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioNormal(this.getImageViewBotaoInicio());
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSistemaNormal(this.getImageViewBotaoSistema());
	}

	@FXML
	public void botaoSecretariaConsultarMouseExited(MouseEvent event) {
		if (!this.isAbaSecretariaConsultarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaConsultarNormal(this.getImageViewBotaoSecretariaConsultar());
		}
	}

	@FXML
	public void botaoSecretariaConsultarMouseEntered(MouseEvent event) {

		if (!this.isAbaSecretariaConsultarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaConsultarMouseOver(this.getImageViewBotaoSecretariaConsultar());
		}
	}

	@FXML
	public void botaoSecretariaConsultarMouseClicked(MouseEvent event) {
		this.carregarTabSecretariaConsultar();

		this.setFalseOrTrueControlesSubBotoes(false, false, false, true, false, false);

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaConsultarPressed(this.getImageViewBotaoSecretariaConsultar());

		this.ativarBotoesNormais(true, true, true, false, true, false);

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioNormal(this.getImageViewBotaoInicio());
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSistemaNormal(this.getImageViewBotaoSistema());

	}

	@FXML
	public void botaoSecretariaCadastrarMouseExited(MouseEvent event) {
		if (!this.isAbaSecretariaCadastrarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaCadastrarNormal(this.getImageViewBotaoSecretariaCadastrar());
		}
	}

	@FXML
	public void botaoSecretariaCadastrarMouseEntered(MouseEvent event) {
		if (!this.isAbaSecretariaCadastrarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaCadastrarMouseOver(this.getImageViewBotaoSecretariaCadastrar());
		}
	}

	@FXML
	public void botaoSecretariaCadastrarMouseClicked(MouseEvent event) {
		this.carregarTabSecretariaCadastrar();

		this.setFalseOrTrueControlesSubBotoes(false, false, true, false, false, false);

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaCadastrarPressed(this.getImageViewBotaoSecretariaCadastrar());

		this.ativarBotoesNormais(true, true, false, true, true, true);

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioNormal(this.getImageViewBotaoInicio());
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSistemaNormal(this.getImageViewBotaoSistema());
	}

	@FXML
	public void botaoMembrosConsultarMouseExited(MouseEvent event) {

		if (!this.isAbaMembroConsultarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosConsultarNormal(this.getImageViewBotaoMembroConsultar());
		}
	}

	@FXML
	public void botaoMembrosConsultarMouseEntered(MouseEvent event) {

		if (!this.isAbaMembroConsultarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosConsultarMouseOver(this.getImageViewBotaoMembroConsultar());
		}
	}

	@FXML
	public void botaoMembrosConsultarMouseClicked(MouseEvent event) {
		this.setFalseOrTrueControlesSubBotoes(false, true, false, false, false, false);

		this.ativarBotoesNormais(true, false, true, true, true, true);

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosConsultarPressed(this.getImageViewBotaoMembroConsultar());
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioNormal(this.getImageViewBotaoInicio());
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSistemaNormal(this.getImageViewBotaoSistema());

		this.carregarTabMembrosConsultar();
	}

	@FXML
	public void botaoMembrosCadastrarMouseExited(MouseEvent event) {
		if (!this.isAbaMembroCadastrarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosCadastrarNormal(this.getImageViewBotaoMembrosCadastrar());
		}
	}

	@FXML
	public void botaoMembrosCadastrarMouseEntered(MouseEvent event) {
		if (!this.isAbaMembroCadastrarAtiva()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosCadastrarMouseOver(this.getImageViewBotaoMembrosCadastrar());
		}
	}

	@FXML
	public void botaoMembrosCadastrarMouseClicked(MouseEvent event) {
		this.setFalseOrTrueControlesSubBotoes(true, false, false, false, false, false);

		this.ativarBotoesNormais(false, true, true, true, true, true);

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosCadastrarPressed(this.getImageViewBotaoMembrosCadastrar());
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioNormal(this.getImageViewBotaoInicio());
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSistemaNormal(this.getImageViewBotaoSistema());

		this.carregarTabMembrosCadastrar();
	}

	/**
	 * Muda a imagem do botão para a imagem normal (sem estar pressionado ou com
	 * mouse over).
	 *
	 * @param membroCadastrar
	 * @param membroConsultar
	 * @param secretariaCadastrar
	 * @param secretariaConsultar
	 * @param tesourariaCadastrar
	 * @param tesourariaConsultar
	 */
	private void ativarBotoesNormais(boolean membroCadastrar, boolean membroConsultar, boolean secretariaCadastrar, boolean secretariaConsultar, boolean tesourariaCadastrar, boolean tesourariaConsultar) {

		if (membroCadastrar) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosCadastrarNormal(this.getImageViewBotaoMembrosCadastrar());
		}

		if (membroConsultar) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosConsultarNormal(this.getImageViewBotaoMembroConsultar());
		}

		if (secretariaCadastrar) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaCadastrarNormal(this.getImageViewBotaoSecretariaCadastrar());
		}

		if (secretariaConsultar) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaConsultarNormal(this.getImageViewBotaoSecretariaConsultar());
		}

		if (tesourariaCadastrar) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaCadastrarNormal(this.getImageViewBotaoTesourariaCadastrar());
		}

		if (tesourariaConsultar) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaConsultarNormal(this.getImageViewBotaoTesourariaConsultar());
		}
	}

	@FXML
	private void botaoSairMouseExited(MouseEvent event) {
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSairNormal(this.getImageViewBotaoSair());
	}

	@FXML
	private void botaoSairMouseEntered(MouseEvent event) {
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSairMouseOver(this.getImageViewBotaoSair());
	}

	@FXML
	private void botaoSairMousePressed(MouseEvent event) {
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSairPressed(this.getImageViewBotaoSair());
	}

	@FXML
	private void botaoSairMouseReleased(MouseEvent event) {
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSairNormal(this.getImageViewBotaoSair());

		Stage stage = (Stage) this.idTelaPrincipal.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void botaoSistemaMouseClicked(MouseEvent event) {

		this.setFalseOrTrueControlesSubBotoes(false, false, false, false, false, false);

		this.ativarBotoesNormais(true, true, true, true, true, true);

		this.setControleBotaoSistema(true);

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSistemaPressed(this.getImageViewBotaoSistema());

		this.carregarTabSistema();

		if (this.isControleBotaoInicio()) {
			this.setControleBotaoInicio(false);
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioNormal(this.getImageViewBotaoInicio());
		}
	}

	public void carregarTabMembrosCadastrar() {
		this.getIdTelaPrincipalCorpo().getChildren().clear();
		try {
			this.getIdTelaPrincipalCorpo().getChildren().add(FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/membro/FXMLTabMembroCadastrar.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("CARREGOU TELA CADASTRAR MEMBRO");
	}

	public void carregarTabMembrosConsultar() {
		this.getIdTelaPrincipalCorpo().getChildren().clear();
		try {
			this.getIdTelaPrincipalCorpo().getChildren().add(FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/membro/FXMLTabMembroConsultar.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarTabTesourariaCadastrar() {
		this.getIdTelaPrincipalCorpo().getChildren().clear();
		try {
			this.getIdTelaPrincipalCorpo().getChildren().add(FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/tesouraria/FXMLTabTesourariaCadastrar.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarTabTesourariaConsultar() {
		this.getIdTelaPrincipalCorpo().getChildren().clear();
		try {
			this.getIdTelaPrincipalCorpo().getChildren().add(FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/tesouraria/FXMLTabTesourariaConsultar.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarTabInicio() {
		this.getIdTelaPrincipalCorpo().getChildren().clear();
		try {
			this.getIdTelaPrincipalCorpo().getChildren().add(FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/FXMLTabInicio.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarTabSecretariaCadastrar() {
		this.getIdTelaPrincipalCorpo().getChildren().clear();
		try {
			this.getIdTelaPrincipalCorpo().getChildren().add(FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/secretaria/FXMLTabSecretariaCadastrar.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarTabSecretariaConsultar() {
		this.getIdTelaPrincipalCorpo().getChildren().clear();
		try {
			this.getIdTelaPrincipalCorpo().getChildren().add(FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/secretaria/FXMLTabSecretariaConsultar.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarTabSistema() {
		this.getIdTelaPrincipalCorpo().getChildren().clear();
		try {
			this.getIdTelaPrincipalCorpo().getChildren().add(FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/FXMLTabSistema.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void botaoInicioMouseClicked(MouseEvent event) {
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioPressed(this.getImageViewBotaoInicio());

		this.setControleBotaoInicio(true);

		this.ativarBotoesNormais(true, true, true, true, true, true);
		this.setFalseOrTrueControlesSubBotoes(false, false, false, false, false, false);

		this.carregarTabInicio();

		if (this.isControleBotaoSistema()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSistemaNormal(this.getImageViewBotaoSistema());
			this.setControleBotaoSistema(false);
		}
	}

	@FXML
	public void botaoInicioMouseEntered(MouseEvent event) {

		if (!this.isControleBotaoInicio()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioMouseOver(this.getImageViewBotaoInicio());
		}

		this.setBotaoFadeOut(this.getBotaoTesouriaCadastrar());
		this.setBotaoFadeOut(this.getBotaoTesouriaConsultar());

		this.setBotaoFadeOut(this.getBotaoMembroCadastrar());
		this.setBotaoFadeOut(this.getBotaoMembroConsultar());

		this.setBotaoFadeOut(this.getBotaoSecretariaCadastrar());
		this.setBotaoFadeOut(this.getBotaoSecretariaConsultar());

	}

	@FXML
	public void botaoInicioMouseExited(MouseEvent event) {

		if (!this.isControleBotaoInicio()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioNormal(this.getImageViewBotaoInicio());
		}
	}

	private void setBotaoFadeIn(Label botao) {

		FadeTransition ft = new FadeTransition(Duration.millis(150), botao);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();

		botao.setVisible(true);
	}

	private void setBotaoFadeOut(Label botao) {

		FadeTransition ft = new FadeTransition(Duration.millis(150), botao);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();

		// botao.setVisible(false);
	}

	private void setSubBotaoTransitionIn(Label botao) {
		TranslateTransition translate = new TranslateTransition();

		translate.setToX(90);
		translate.setDuration(Duration.millis(150));
		translate.setNode(botao);

		translate.play();
	}

	private void setSubBotaoTransitionOut(Label botao) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				botao.toBack();
			}
		});

		TranslateTransition translate = new TranslateTransition();

		translate.setToX(0);
		translate.setDuration(Duration.millis(150));
		translate.setNode(botao);

		translate.play();
	}

	@FXML
	private void botaoMembrosMouseEntered(MouseEvent event) {

		this.setSubBotoesMembroAtivo(true);
		this.setControleBotaoMembros(true);
		this.setBotaoPressedOrNot();

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosMouseOver(this.getImageViewBotaoMembros());

		this.setBotaoFadeIn(this.getBotaoMembroCadastrar());
		this.setBotaoFadeIn(this.getBotaoMembroConsultar());

		if (this.isSubBotoesSecretariaAtivo()) {
			this.setBotaoFadeOut(this.getBotaoSecretariaCadastrar());
			this.setBotaoFadeOut(this.getBotaoSecretariaConsultar());

			this.setSubBotoesSecretariaAtivo(false);
		}

		if (this.isSubBotoesTesourariaAtivo()) {
			this.setBotaoFadeOut(this.getBotaoTesouriaCadastrar());
			this.setBotaoFadeOut(this.getBotaoTesouriaConsultar());

			this.setSubBotoesTesourariaAtivo(false);
		}
	}

	@FXML
	private void botaoMembrosMouseExited(MouseEvent event) {
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoMembrosNormal(this.getImageViewBotaoMembros());

		this.setControleBotaoMembros(false);
	}

	@FXML
	private void botaoSecretariaMouseEntered(MouseEvent event) {

		this.setSubBotoesSecretariaAtivo(true);

		this.setControleBotaoSecretaria(true);

		this.setBotaoPressedOrNot();

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaMouseOver(this.getImageViewBotaoSecretaria());

		this.setBotaoFadeIn(this.getBotaoSecretariaCadastrar());
		this.setBotaoFadeIn(this.getBotaoSecretariaConsultar());

		if (this.isSubBotoesMembroAtivo()) {
			this.setBotaoFadeOut(this.getBotaoMembroCadastrar());
			this.setBotaoFadeOut(this.getBotaoMembroConsultar());

			this.setSubBotoesMembroAtivo(false);
		}

		if (this.isSubBotoesTesourariaAtivo()) {
			this.setBotaoFadeOut(this.getBotaoTesouriaCadastrar());
			this.setBotaoFadeOut(this.getBotaoTesouriaConsultar());

			this.setSubBotoesTesourariaAtivo(false);
			;
		}
	}

	@FXML
	private void botaoSecretariaMouseExited(MouseEvent event) {

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSecretariaNormal(this.getImageViewBotaoSecretaria());

		this.setControleBotaoSecretaria(false);
	}

	@FXML
	private void botaoTesourariaMouseEntered(MouseEvent event) {

		this.setSubBotoesTesourariaAtivo(true);

		this.setControleBotaoTesouraria(true);

		this.setBotaoPressedOrNot();

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaMouseOver(this.getImageViewBotaoTesouraria());

		this.setBotaoFadeIn(this.getBotaoTesouriaCadastrar());
		this.setBotaoFadeIn(this.getBotaoTesouriaConsultar());

		if (this.isSubBotoesMembroAtivo()) {
			this.setBotaoFadeOut(this.getBotaoMembroCadastrar());
			this.setBotaoFadeOut(this.getBotaoMembroConsultar());

			this.setSubBotoesMembroAtivo(false);
		}

		if (this.isSubBotoesSecretariaAtivo()) {
			this.setBotaoFadeOut(this.getBotaoSecretariaCadastrar());
			this.setBotaoFadeOut(this.getBotaoSecretariaConsultar());

			this.setSubBotoesSecretariaAtivo(false);
		}
	}

	@FXML
	private void botaoTesourariaMouseExited(MouseEvent event) {
		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoTesourariaNormal(this.getImageViewBotaoTesouraria());

		this.setControleBotaoTesouraria(false);
	}

	@FXML
	private void botaoSistemaMouseEntered(MouseEvent event) {

		if (!this.isControleBotaoSistema()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSistemaMouseOver(this.getImageViewBotaoSistema());
		}

		if (this.isSubBotoesTesourariaAtivo()) {
			this.setBotaoFadeOut(this.getBotaoTesouriaCadastrar());
			this.setBotaoFadeOut(this.getBotaoTesouriaConsultar());

			this.setSubBotoesTesourariaAtivo(false);
		}

		if (this.isSubBotoesMembroAtivo()) {
			this.setBotaoFadeOut(this.getBotaoMembroCadastrar());
			this.setBotaoFadeOut(this.getBotaoMembroConsultar());

			this.setSubBotoesMembroAtivo(false);
		}

		if (this.isSubBotoesSecretariaAtivo()) {
			this.setBotaoFadeOut(this.getBotaoSecretariaCadastrar());
			this.setBotaoFadeOut(this.getBotaoSecretariaConsultar());

			this.setSubBotoesSecretariaAtivo(false);
		}
	}

	@FXML
	private void botaoSistemaMouseExited(MouseEvent event) {
		if (!this.isControleBotaoSistema()) {
			AcaoBotoesPrincipais.getInstance().ativarImagemBotaoSistemaNormal(this.getImageViewBotaoSistema());
		}

		// this.setControleBotaoSistema(false);
	}

	public Pane getIdPanePrincipal() {
		if (this.idPanePrincipal == null) {
			this.idPanePrincipal = new Pane();
		}

		return this.idPanePrincipal;
	}

	@Override
	public void start(Stage stage) throws IOException {

		Pane telaPrincipal = FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/FXMLTelaPrincipal.fxml"));

		Scene scene = new Scene(telaPrincipal, 1280, 700);

		scene.getStylesheets().add(this.getClass().getResource("/css/TelaPrincipal.css").toExternalForm());

		stage.setResizable(false);

		this.getIdPanePrincipal().setStyle("-fx-background-color: black;");

		stage.getIcons().add(new Image("/imagens/icone.png"));

		stage.setTitle(Labels.getInstance().getTituloTelaPrincipal(this.getUsuarioTesteNome()));

		stage.setScene(scene);

		stage.show();
	}

	// USUARIO TESTE -- APAGAR
	public String getUsuarioTesteNome() {

		Usuario usuario = new Usuario();
		usuario.setNomeCompleto("Izrí Miranda");
		return usuario.getNomeCompleto();
	}

	@Override
	public void fecharTela() {
	}

	@FXML
	public void sairAcao() {

	}

	@FXML
	public void cadastrarEntradaSaidaAcao() {

	}

	@FXML
	public void consultarEntradaSaida() {

	}

	@FXML
	public void gerarRelatoriosAcao() {

	}

	@FXML
	public void abrirPopupSobreAcao() throws IOException {

		Pane telaPrincipal = FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/FXMLTelaSobre.fxml"));

		Scene scene = new Scene(telaPrincipal, 451, 247);

		Stage stage = new Stage();

		stage.setResizable(false);

		stage.getIcons().add(new Image("/imagens/icone.png"));

		stage.setTitle(Labels.getInstance().getLabelAServico());

		String css = FXMLTelaPrincipalController.class.getResource("/css/TelaPrincipal.css").toExternalForm();

		scene.getStylesheets().add(css);

		stage.setScene(scene);

		stage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle args) {

		// this.idLogoPequena.setVisible(false);
		this.setControleBotaoInicio(true);

		try {
			this.getIdTelaPrincipalCorpo().getChildren().add(FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/tab/FXMLTabInicio.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		AcaoBotoesPrincipais.getInstance().ativarImagemBotaoInicioPressed(this.getImageViewBotaoInicio());
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

	public Label getBotaoMembroConsultar() {
		if (this.botaoMembroConsultar == null) {
			this.botaoMembroConsultar = new Label();
		}
		return this.botaoMembroConsultar;
	}

	public void setBotaoMembroConsultar(Label botaoMembroConsultar) {
		this.botaoMembroConsultar = botaoMembroConsultar;
	}

	public boolean isControleBotaoInicio() {
		return this.controleBotaoInicio;
	}

	public void setControleBotaoInicio(boolean controleBotaoInicio) {
		this.controleBotaoInicio = controleBotaoInicio;
	}

	public boolean isControleBotaoMembros() {
		return this.controleBotaoMembros;
	}

	public void setControleBotaoMembros(boolean controleBotaoMembros) {
		this.controleBotaoMembros = controleBotaoMembros;
	}

	public boolean isControleBotaoSecretaria() {
		return this.controleBotaoSecretaria;
	}

	public void setControleBotaoSecretaria(boolean controleBotaoSecretaria) {
		this.controleBotaoSecretaria = controleBotaoSecretaria;
	}

	public boolean isControleBotaoTesouraria() {
		return this.controleBotaoTesouraria;
	}

	public void setControleBotaoTesouraria(boolean controleBotaoTesouraria) {
		this.controleBotaoTesouraria = controleBotaoTesouraria;
	}

	public boolean isControleBotaoSistema() {
		return this.controleBotaoSistema;
	}

	public void setControleBotaoSistema(boolean controleBotaoSistema) {
		this.controleBotaoSistema = controleBotaoSistema;
	}

	public boolean isAbaMembroCadastrarAtiva() {
		return this.abaMembroCadastrarAtiva;
	}

	public void setAbaMembroCadastrarAtiva(boolean abaMembroCadastrarAtiva) {
		this.abaMembroCadastrarAtiva = abaMembroCadastrarAtiva;
	}

	public boolean isAbaMembroConsultarAtiva() {
		return this.abaMembroConsultarAtiva;
	}

	public void setAbaMembroConsultarAtiva(boolean abaMembroConsultarAtiva) {
		this.abaMembroConsultarAtiva = abaMembroConsultarAtiva;
	}

	public boolean isAbaSecretariaCadastrarAtiva() {
		return this.abaSecretariaCadastrarAtiva;
	}

	public void setAbaSecretariaCadastrarAtiva(boolean abaSecretariaCadastrarAtiva) {
		this.abaSecretariaCadastrarAtiva = abaSecretariaCadastrarAtiva;
	}

	public boolean isAbaSecretariaConsultarAtiva() {
		return this.abaSecretariaConsultarAtiva;
	}

	public void setAbaSecretariaConsultarAtiva(boolean abaSecretariaConsultarAtiva) {
		this.abaSecretariaConsultarAtiva = abaSecretariaConsultarAtiva;
	}

	public boolean isAbaTesourariaCadastrarAtiva() {
		return this.abaTesourariaCadastrarAtiva;
	}

	public void setAbaTesourariaCadastrarAtiva(boolean abaTesourariaCadastrarAtiva) {
		this.abaTesourariaCadastrarAtiva = abaTesourariaCadastrarAtiva;
	}

	public boolean isAbaTesourariaConsultarAtiva() {
		return this.abaTesourariaConsultarAtiva;
	}

	public void setAbaTesourariaConsultarAtiva(boolean abaTesourariaConsultarAtiva) {
		this.abaTesourariaConsultarAtiva = abaTesourariaConsultarAtiva;
	}

	public Label getBotaoSecretariaCadastrar() {
		if (this.botaoSecretariaCadastrar == null) {
			this.botaoSecretariaCadastrar = new Label();
		}
		return this.botaoSecretariaCadastrar;
	}

	public void setBotaoSecretariaCadastrar(Label botaoSecretariaCadastrar) {
		this.botaoSecretariaCadastrar = botaoSecretariaCadastrar;
	}

	public Label getBotaoSecretariaConsultar() {
		if (this.botaoSecretariaConsultar == null) {
			this.botaoSecretariaConsultar = new Label();
		}
		return this.botaoSecretariaConsultar;
	}

	public void setBotaoSecretariaConsultar(Label botaoSecretariaConsultar) {
		this.botaoSecretariaConsultar = botaoSecretariaConsultar;
	}

	public Label getBotaoTesouriaCadastrar() {
		if (this.botaoTesouriaCadastrar == null) {
			this.botaoTesouriaCadastrar = new Label();
		}
		return this.botaoTesouriaCadastrar;
	}

	public void setBotaoTesouriaCadastrar(Label botaoTesouriaCadastrar) {
		this.botaoTesouriaCadastrar = botaoTesouriaCadastrar;
	}

	public Label getBotaoTesouriaConsultar() {
		if (this.botaoTesouriaConsultar == null) {
			this.botaoTesouriaConsultar = new Label();
		}
		return this.botaoTesouriaConsultar;
	}

	public void setBotaoTesouriaConsultar(Label botaoTesouriaConsultar) {
		this.botaoTesouriaConsultar = botaoTesouriaConsultar;
	}

	public ImageView getImageViewBotaoMembrosCadastrar() {
		if (this.imageViewBotaoMembrosCadastrar == null) {
			this.imageViewBotaoMembrosCadastrar = new ImageView();
		}
		return this.imageViewBotaoMembrosCadastrar;
	}

	public void setImageViewBotaoMembrosCadastrar(ImageView imageViewBotaoMembrosCadastrar) {
		this.imageViewBotaoMembrosCadastrar = imageViewBotaoMembrosCadastrar;
	}

	public boolean isAbaAtiva() {
		return this.abaAtiva;
	}

	public void setAbaAtiva(boolean abaAtiva) {
		this.abaAtiva = abaAtiva;
	}

	public ImageView getImageViewBotaoInicio() {
		if (this.imageViewBotaoInicio == null) {
			this.imageViewBotaoInicio = new ImageView();
		}
		return this.imageViewBotaoInicio;
	}

	public void setImageViewBotaoInicio(ImageView imageViewBotaoInicio) {
		this.imageViewBotaoInicio = imageViewBotaoInicio;
	}

	public ImageView getImageViewBotaoMembros() {
		if (this.imageViewBotaoMembros == null) {
			this.imageViewBotaoMembros = new ImageView();
		}
		return this.imageViewBotaoMembros;
	}

	public void setImageViewBotaoMembros(ImageView imageViewBotaoMembros) {
		this.imageViewBotaoMembros = imageViewBotaoMembros;
	}

	public ImageView getImageViewBotaoSecretaria() {
		if (this.imageViewBotaoSecretaria == null) {
			this.imageViewBotaoSecretaria = new ImageView();
		}
		return this.imageViewBotaoSecretaria;
	}

	public void setImageViewBotaoSecretaria(ImageView imageViewBotaoSecretaria) {
		this.imageViewBotaoSecretaria = imageViewBotaoSecretaria;
	}

	public ImageView getImageViewBotaoTesouraria() {
		if (this.imageViewBotaoTesouraria == null) {
			this.imageViewBotaoTesouraria = new ImageView();
		}
		return this.imageViewBotaoTesouraria;
	}

	public void setImageViewBotaoTesouraria(ImageView imageViewBotaoTesouraria) {
		this.imageViewBotaoTesouraria = imageViewBotaoTesouraria;
	}

	public ImageView getImageViewBotaoSistema() {
		if (this.imageViewBotaoSistema == null) {
			this.imageViewBotaoSistema = new ImageView();
		}
		return this.imageViewBotaoSistema;
	}

	public void setImageViewBotaoSistema(ImageView imageViewBotaoSistema) {
		this.imageViewBotaoSistema = imageViewBotaoSistema;
	}

	public ImageView getImageViewBotaoSair() {
		if (this.imageViewBotaoSair == null) {
			this.imageViewBotaoSair = new ImageView();
		}
		return this.imageViewBotaoSair;
	}

	public void setImageViewBotaoSair(ImageView imageViewBotaoSair) {
		this.imageViewBotaoSair = imageViewBotaoSair;
	}

	public ImageView getImageViewBotaoMembroConsultar() {
		if (this.imageViewBotaoMembroConsultar == null) {
			this.imageViewBotaoMembroConsultar = new ImageView();
		}
		return this.imageViewBotaoMembroConsultar;
	}

	public void setImageViewBotaoMembroConsultar(ImageView imageViewBotaoMembroConsultar) {
		this.imageViewBotaoMembroConsultar = imageViewBotaoMembroConsultar;
	}

	public ImageView getImageViewBotaoSecretariaCadastrar() {
		if (this.imageViewBotaoSecretariaCadastrar == null) {
			this.imageViewBotaoSecretariaCadastrar = new ImageView();
		}
		return this.imageViewBotaoSecretariaCadastrar;
	}

	public void setImageViewBotaoSecretariaCadastrar(ImageView imageViewBotaoSecretariaCadastrar) {
		this.imageViewBotaoSecretariaCadastrar = imageViewBotaoSecretariaCadastrar;
	}

	public ImageView getImageViewBotaoSecretariaConsultar() {
		if (this.imageViewBotaoSecretariaConsultar == null) {
			this.imageViewBotaoSecretariaConsultar = new ImageView();
		}
		return this.imageViewBotaoSecretariaConsultar;
	}

	public void setImageViewBotaoSecretariaConsultar(ImageView imageViewBotaoSecretariaConsultar) {
		this.imageViewBotaoSecretariaConsultar = imageViewBotaoSecretariaConsultar;
	}

	public ImageView getImageViewBotaoTesourariaCadastrar() {
		if (this.imageViewBotaoTesourariaCadastrar == null) {
			this.imageViewBotaoTesourariaCadastrar = new ImageView();
		}
		return this.imageViewBotaoTesourariaCadastrar;
	}

	public void setImageViewBotaoTesourariaCadastrar(ImageView imageViewBotaoTesourariaCadastrar) {
		this.imageViewBotaoTesourariaCadastrar = imageViewBotaoTesourariaCadastrar;
	}

	public ImageView getImageViewBotaoTesourariaConsultar() {
		if (this.imageViewBotaoTesourariaConsultar == null) {
			this.imageViewBotaoTesourariaConsultar = new ImageView();
		}
		return this.imageViewBotaoTesourariaConsultar;
	}

	public void setImageViewBotaoTesourariaConsultar(ImageView imageViewBotaoTesourariaConsultar) {
		this.imageViewBotaoTesourariaConsultar = imageViewBotaoTesourariaConsultar;
	}

	public boolean isSubBotoesMembroAtivo() {
		return this.subBotoesMembroAtivo;
	}

	public void setSubBotoesMembroAtivo(boolean subBotoesMembroAtivo) {
		this.subBotoesMembroAtivo = subBotoesMembroAtivo;
	}

	public boolean isSubBotoesSecretariaAtivo() {
		return this.subBotoesSecretariaAtivo;
	}

	public void setSubBotoesSecretariaAtivo(boolean subBotoesSecretariaAtivo) {
		this.subBotoesSecretariaAtivo = subBotoesSecretariaAtivo;
	}

	public boolean isSubBotoesTesourariaAtivo() {
		return this.subBotoesTesourariaAtivo;
	}

	public void setSubBotoesTesourariaAtivo(boolean subBotoesTesourariaAtivo) {
		this.subBotoesTesourariaAtivo = subBotoesTesourariaAtivo;
	}

	public AnchorPane getIdTelaPrincipalCorpo() {
		if (this.idTelaPrincipalCorpo == null) {
			this.idTelaPrincipalCorpo = new AnchorPane();
		}
		return this.idTelaPrincipalCorpo;
	}

	public void setIdTelaPrincipalCorpo(AnchorPane idTelaPrincipalCorpo) {
		this.idTelaPrincipalCorpo = idTelaPrincipalCorpo;
	}
}
