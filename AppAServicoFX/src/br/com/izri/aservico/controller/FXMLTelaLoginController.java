package br.com.izri.aservico.controller;

import java.io.IOException;

import br.com.izri.Seguranca;
import br.com.izri.aservico.auxiliar.Labels;
import br.com.izri.aservico.auxiliar.PopupDialog;
import br.com.izri.aservico.controller.base.ControllerBase;
import br.com.izri.aservico.controller.session.UsuarioSessao;
import br.com.izri.aservico.model.entity.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLTelaLoginController extends ControllerBase {

	@FXML
	private TextField campoLogin;
	@FXML
	private PasswordField campoSenha;
	@FXML
	private Button botaoFechar;
	private Usuario usuario;
	@FXML
	private AnchorPane telaLogin;

	@FXML
	private Button botaoEsqueceuSenha;

	public void exibirTelaPrincipal() throws IOException {
		Stage stage = new Stage();

		Pane telaPrincipal = FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/FXMLTelaPrincipal.fxml"));

		Scene scene = new Scene(telaPrincipal, 1280, 700);

		scene.getStylesheets().add(this.getClass().getResource("/css/TelaPrincipal.css").toExternalForm());

		stage.setResizable(false);

		stage.getIcons().add(new Image("/imagens/icone.png"));

		stage.setScene(scene);

		stage.show();
	}

	private void fazerLogin() throws IOException {

		Usuario usuario = this.getUsuarioDAO().isUsuarioReadyToLogin(this.campoLogin.getText(), Seguranca.convertStringToMd5(this.campoSenha.getText()));

		if ((usuario.getLogin() == null) && (usuario.getSenha() == null)) {

			this.campoLogin.setText("");
			this.campoSenha.setText("");
			PopupDialog.getInstance().exibirPopupErro("Login e/ou senha inválidos. Tente novamente.", "Erro no login e/ou senha");

		} else {
			UsuarioSessao.getInstance().setUsuario(usuario);
			this.exibirTelaPrincipal();

			this.fecharTela();
		}
	}

	@FXML
	private void exibirPopupEsqueceuSenha(ActionEvent event) {
		PopupDialog.getInstance().exibirPopupEsqueceuSenha("Caso tenha esquecido a senha, entre em contato com: Izri - (31) 98880-2212", "Esqueceu a senha?");
	}

	@FXML
	private void botaoEntrarAcao(ActionEvent event) throws Exception {
		this.fazerLogin();
	}

	@FXML
	private void botaoFecharAcao(ActionEvent event) {
		this.fecharTela();
	}

	@FXML
	public void mouseEntrou(MouseEvent event) {
		System.out.println("Mouse entrouuuuuu");

	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.getIcons().add(new Image("/imagens/icone.png"));

		Pane telaLogin = FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/FXMLTelaLogin.fxml"));

		stage.setTitle(Labels.getInstance().getTituloTelaLogin());
		stage.setResizable(false);

		Scene scene = new Scene(telaLogin, 800, 600);

		stage.setScene(scene);

		stage.show();

	}

	@Override
	public void fecharTela() {
		Stage stage = (Stage) this.botaoFechar.getScene().getWindow();
		stage.close();
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TextField getCampoLogin() {
		if (this.campoLogin == null) {
			this.campoLogin = new TextField();
		}
		return this.campoLogin;
	}

	public void setCampoLogin(TextField campoLogin) {
		this.campoLogin = campoLogin;
	}
}
